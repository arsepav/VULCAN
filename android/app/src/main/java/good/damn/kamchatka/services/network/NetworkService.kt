package good.damn.kamchatka.services.network

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.annotation.WorkerThread
import good.damn.kamchatka.Application
import good.damn.kamchatka.R
import good.damn.kamchatka.extensions.isAvailable
import good.damn.kamchatka.services.GeoService
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray

open class NetworkService(
    private val mContext: Context
) {

    companion object {
        private const val TAG = "NetworkService"
    }

    private val mConnectivity = mContext.getSystemService(
        Context.CONNECTIVITY_SERVICE
    ) as ConnectivityManager

    protected fun makeRequest(
        request: Request.Builder,
        c: ((OkHttpClient, Request)->Unit)
    ) {
        if (!mConnectivity.isAvailable()) {
            Application.toast(
                R.string.no_network,
                mContext
            )
            return
        }

        val client = OkHttpClient()
        c(client,request.build())
    }

    @WorkerThread
    protected fun queueRequest(
        client: OkHttpClient,
        request: Request,
        process: ((JSONArray) -> Unit)
    ) {
        Thread {
            val response = client.newCall(
                request
            ).execute()

            val body = response
                .body?.string()
                ?: return@Thread

            Log.d(TAG, "queueRequest: ${response.code} $body")

            process(
                JSONArray(
                    body
                )
            )
        }.start()
    }

}