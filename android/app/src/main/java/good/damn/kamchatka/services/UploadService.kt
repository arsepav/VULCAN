package good.damn.kamchatka.services

import android.content.Context
import good.damn.kamchatka.Application
import good.damn.kamchatka.services.network.NetworkService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.Request
import okhttp3.RequestBody.Companion.asRequestBody
import org.json.JSONObject
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
        val mbody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart(
                "file",
                file.name,
                file.asRequestBody(PLAIN)
            ).build()
        makeRequest(
            Request.Builder()
                .url(URL_FILE)
                .post(mbody)
        ) { client, request ->
            CoroutineScope(
                Dispatchers.IO
            ).launch {
                val response = client.newCall(
                    request
                ).execute()

                val body = response.body?.string() ?: return@launch

                val json = JSONObject(
                    body
                )

                val fileId = json.get(
                    "file_id"
                ) as? Int ?: 0

                Application.ui {
                    completion(fileId)
                }
            }
        }
    }

}