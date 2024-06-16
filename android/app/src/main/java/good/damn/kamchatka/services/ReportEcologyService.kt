package good.damn.kamchatka.services

import android.content.Context
import android.util.Log
import good.damn.kamchatka.Application
import good.damn.kamchatka.services.network.NetworkService
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import kotlin.coroutines.coroutineContext

class ReportEcologyService(
    context: Context
): NetworkService(
    context
) {

    companion object {
        private const val TAG = "ReportEcologyService"
        private const val URL_CREATE = "${Application.URL}/ecology_problems"
    }

    fun report(
        name: String,
        description: String,
        fileId: Int,
        categoryId: Int,
        completion: (()->Unit)
    ) {
        val json = JSONObject()
        json.put(
            "name",
            name
        )

        json.put(
            "description",
            description
        )

        json.put(
            "file_id",
            fileId
        )

        json.put(
            "category_id",
            categoryId
        )

        val coords = JSONArray()
        coords.put(55.756215)
        coords.put(153.5632)

        val geom = JSONObject()
        geom.put(
            "type",
            "Point"
        )
        geom.put(
            "coordinates",
            coords
        )


        json.put(
            "geom",
            geom
        )

        Log.d(TAG, "report: $json")
        
        makeRequest(
            Request.Builder()
                .url(URL_CREATE)
                .post(json.toString().toRequestBody(
                    Application.JSON
                ))
        ) { client, request ->
            Thread {
                val response = client.newCall(
                    request
                ).execute()

                val body = response.body?.string()
                Log.d(TAG, "report: $response $body")

                Application.ui {
                    completion()
                }
            }.start()
        }
    }

}