package good.damn.kamchatka.services.network

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.annotation.WorkerThread
import good.damn.kamchatka.Application
import good.damn.kamchatka.R
import good.damn.kamchatka.extensions.isAvailable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import org.json.JSONObject
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.nio.charset.StandardCharsets

open class NetworkService(
    protected val mContext: Context
) {

    companion object {
        private const val TAG = "NetworkService"
    }

    private val mConnectivity = mContext.getSystemService(
        Context.CONNECTIVITY_SERVICE
    ) as ConnectivityManager

    private val mClient = OkHttpClient.Builder()
        .cache(Cache(
            File(mContext.cacheDir, "http"),
            maxSize = 200L * 1024 * 1024
        )).authenticator { _, response ->
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
        noConnection: (()->Unit)? = null,
        c: ((OkHttpClient, Request)->Unit)
    ) {
        if (!mConnectivity.isAvailable()) {
            Application.toast(
                R.string.no_network,
                mContext
            )
            noConnection?.invoke()
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
        val cacheDir = mContext.cacheDir
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = client.newCall(
                    request
                ).execute()
                val body = response
                    .body?.string()
                    ?: return@launch

                Log.d(TAG, "queueRequest: ${response.code} ${request.url}")

                saveJsonToCache(
                    request.url.toString(),
                    cacheDir,
                    body
                )

                process(
                    JSONArray(
                        body
                    )
                )
            } catch (e: Exception) {
                e.printStackTrace()
                return@launch
            }
        }
    }

    private fun cacheFile(
        url: String,
        cacheDir: File
    ): File {
        val jsonDir = cacheJsonDir(
            cacheDir
        )
        if (jsonDir.mkdirs()) {
            Log.d(TAG, "cacheFile: DIRS created")
        }

        return File(
            "$jsonDir/${url.hashCode()}"
        )
    }

    private fun cacheJsonDir(
        cacheDir: File
    ) = File("${cacheDir}/json")

    protected fun loadJSONFromCache(
        url: String,
        cacheDir: File
    ): JSONObject? {
        return try {
            loadJson(
                url,
                cacheDir
            )?.let {
                JSONObject(
                    it
                )
            }
        } catch (e: Exception) {
            Log.d(TAG, "loadJSONFromCache: ERROR: ${e.message}")
            null
        }
    }

    protected fun loadJSONArrayFromCache(
        url: String,
        cacheDir: File
    ): JSONArray? {
        return try {
            loadJson(
                url,
                cacheDir
            )?.let {
                JSONArray(
                    it
                )
            }
        } catch (e: Exception) {
            Log.d(TAG, "loadJSONArrayFromCache: ERROR: ${e.message}")
            null
        }
    }

    protected fun saveJsonToCache(
        url: String,
        cacheDir: File,
        json: String
    ) {
        val cacheFile = cacheFile(
            url,
            cacheDir
        )

        if (!cacheFile.exists() && cacheFile.createNewFile()) {
            Log.d(TAG, "saveJsonToCache: ${cacheFile.name} created")
        }

        val fos = FileOutputStream(
            cacheFile
        )

        fos.write(
            json.toByteArray(
                StandardCharsets.UTF_8
            )
        )

        fos.close()
    }

    private fun loadJson(
        url: String,
        cacheDir: File
    ): String? {
        val cacheFile = cacheFile(
            url,
            cacheDir
        )

        if (!cacheFile.exists()) {
            return null
        }

        try {
            val fis = FileInputStream(
                cacheFile
            )

            val buffer = ByteArray(1024*1024)
            val baos = ByteArrayOutputStream()
            var n: Int

            while (true) {
                n = fis.read(buffer)

                if (n == -1) {
                    break
                }

                baos.write(
                    buffer,
                    0,
                    n
                )
            }
            fis.close()

            val data = baos.toByteArray()
            baos.close()

            val json = String(
                data,
                StandardCharsets.UTF_8
            )

            return json

        } catch (e: Exception) {
            Log.d(TAG, "loadJSONFromCache: JSON_ERROR: ${e.message}")
            return null
        }
    }
}