package good.damn.kamchatka.services.network

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.annotation.WorkerThread
import good.damn.kamchatka.Application
import good.damn.kamchatka.R
import good.damn.kamchatka.extensions.isAvailable
import okhttp3.Authenticator
import okhttp3.Credentials
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
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

    private val mClient = OkHttpClient.Builder()
        .authenticator { _, response ->
            val token = Application.TOKEN
            val auth = if (
                token == null
            ) "" else "Bearer ${token.token}"

            Log.d(TAG, "CLIENT: AUTH: $auth ")

            response.request.newBuilder()
                .header(
                    "Authorization",
                    auth
                ).build()
        }.build()



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

        c(mClient,request.build())
    }

    @WorkerThread
    protected fun queueRequest(
        client: OkHttpClient,
        request: Request,
        process: ((JSONArray) -> Unit)
    ) {
        Thread {
            try {
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
            } catch (e: Exception) {
                e.printStackTrace()
                return@Thread
            }
        }.start()
    }

}