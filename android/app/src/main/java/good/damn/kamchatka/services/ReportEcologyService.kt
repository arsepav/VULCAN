package good.damn.kamchatka.services

import android.content.Context
import android.util.Log
import com.google.android.gms.common.server.response.FastJsonResponse.Field
import good.damn.kamchatka.Application
import good.damn.kamchatka.extensions.isAvailable
import good.damn.kamchatka.services.network.NetworkService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import org.json.JSONArray
import org.json.JSONObject
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.nio.charset.StandardCharsets
import kotlin.coroutines.coroutineContext
import kotlin.math.log

class ReportEcologyService(
    context: Context
): NetworkService(
    context
) {

    companion object {
        private const val TAG = "ReportEcologyService"
        private const val URL_CREATE = "${Application.URL}/ecology_problems"
    }

    private fun saveString(
        file: File,
        data: String
    ) {
        if (!file.exists() && file.createNewFile()) {
            Log.d(TAG, "saveString: ${file.name} created")
        }

        try {
            val fos = FileOutputStream(file)

            fos.write(
                data.toByteArray(
                    StandardCharsets.UTF_8
                )
            )

            fos.close()

        } catch (e: Exception) {
            Log.d(TAG, "saveString: ERROR: ${e.message}")
        }
    }
    
    fun getString(
        file: File
    ): String? {
        if (!file.exists()) {
            return null
        }

        try {
            val fis = FileInputStream(file)

            val buffer = ByteArray(1024 * 1024)
            var n: Int

            val fos = ByteArrayOutputStream()

            while (true) {
                n = fis.read(buffer)
                if (n == -1) {
                    break
                }

                fos.write(
                    buffer,
                    0,
                    n
                )
            }
            fis.close()

            val data = fos.toByteArray()
            fos.close()

            return String(
                data,
                StandardCharsets.UTF_8
            )
        } catch (e: Exception) {
            Log.d(TAG, "sendCachedReports: ERROR: ${e.message}")
        }
        return null
    }
    
    fun sendCachedReports() {
        if (!mConnectivity.isAvailable()) {
            return
        }

        val mainFolder = File(
            "${mContext.cacheDir}/reports"
        )

        if (!mainFolder.exists()) {
            return
        }

        val reports = mainFolder.listFiles() ?: return
        reports.forEach {
            val name = getString(
                File("$it/name")
            ) ?: return@forEach

            val desc = getString(
                File("$it/desc")
            ) ?: ""

            val catId = getString(
                File("$it/catId")
            )?.toInt() ?: return@forEach

            val lat = getString(
                File("$it/lat")
            )?.toDouble() ?: return@forEach

            val long = getString(
                File("$it/long")
            )?.toDouble() ?: return@forEach

            val image = File(
                "$it/image"
            )

            if (!image.exists()) {
                return@forEach
            }

            UploadService(
                mContext
            ).upload(
                image
            ) { fileId ->

                report(
                    name,
                    desc,
                    fileId,
                    catId,
                    lat,
                    long
                ) {

                }

            }

        }

    }

    fun saveToCache(
        name: String,
        description: String,
        image: File,
        categoryId: Int,
        lat: Double,
        lng: Double
    ) {

        val mainFolder = File(
            "${mContext.cacheDir}/reports"
        )

        if (mainFolder.mkdir()) {
            Log.d(TAG, "saveToCache: REPORTS folder created")
        }

        val countReports = mainFolder.listFiles()?.size ?: 0

        val reportFolder = File(
            "$mainFolder/report$countReports"
        )

        if (reportFolder.mkdir()) {
            Log.d(TAG, "saveToCache: ${reportFolder.name} created")
        }

        saveString(
            File("$reportFolder/name"),
            name
        )

        saveString(
            File("$reportFolder/desc"),
            description
        )

        saveString(
            File("$reportFolder/catId"),
            categoryId.toString()
        )

        saveString(
            File("$reportFolder/lat"),
            lat.toString()
        )
        saveString(
            File("$reportFolder/long"),
            lng.toString()
        )

        // Save image
        try {
            val fileImage = File(
                "$reportFolder/image"
            )

            if (!fileImage.exists() && fileImage.createNewFile()) {
                Log.d(TAG, "saveToCache: Image file created")
            }

            val fis = FileInputStream(image)
            val fos = FileOutputStream(fileImage)

            val buffer = ByteArray(1024*1024)
            var n: Int

            while(true) {
                n = fis.read(buffer)
                if (n == -1) {
                    break
                }

                fos.write(
                    buffer,
                    0,
                    n
                )
            }
            fos.close()
            fis.close()
        } catch (e: Exception) {
            Log.d(TAG, "saveToCache: ERROR_IMAGE: ${e.message}")
        }

    }

    fun report(
        name: String,
        description: String,
        fileId: Int,
        categoryId: Int,
        lat: Double,
        lng: Double,
        completion: ((Response)->Unit)
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

        Log.d(TAG, "report: LAT_LNG: $lat $lng")
        val coords = JSONArray()
        coords.put(lng)
        coords.put(lat)

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
            CoroutineScope(
                Dispatchers.IO
            ).launch {
                val response = client.newCall(
                    request
                ).execute()

                val body = response.body?.string()
                Log.d(TAG, "report: $response $body")

                Application.ui {
                    completion(response)
                }
            }
        }
    }

}