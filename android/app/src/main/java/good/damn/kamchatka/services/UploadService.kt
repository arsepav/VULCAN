package good.damn.kamchatka.services

import android.content.Context
import android.provider.MediaStore.Audio.Media
import android.util.Log
import good.damn.kamchatka.Application
import good.damn.kamchatka.services.network.NetworkService
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class UploadService(
    context: Context
): NetworkService(
    context
) {

    companion object {
        private const val TAG = "UploadService"
        private const val URL_FILE = "${Application.URL}/uploadfile"
        private val PLAIN = "text/plain".toMediaTypeOrNull()
    }

    fun upload(
        file: File,
        completion: ((Int) -> Unit)
    ) {
        val body = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart(
                "file",
                file.name,
                file.asRequestBody(PLAIN)
            ).build()
        makeRequest(
            Request.Builder()
                .url(URL_FILE)
                .post(body)
        ) { client, request ->
            Thread {

                val response = client.newCall(
                    request
                ).execute()

                val body = response.body?.string()

                Log.d(TAG, "upload: $response $body")

                Application.ui {
                    completion(1)
                }

                Thread.currentThread()
                    .interrupt()
            }.start()
        }
    }

}