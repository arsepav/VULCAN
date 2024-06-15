package good.damn.kamchatka.services

import android.util.Log
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.PolygonOptions
import good.damn.kamchatka.models.SecurityZone
import good.damn.kamchatka.services.interfaces.GeoServiceListener
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class GeoService(
    private val mListener: GeoServiceListener
) {

    companion object {
        private const val TAG = "GeoService"
        private const val URL_OOPT = "http://91.224.86.144:8000/oopts"
        private val JSON = "application/json; charset=utf-8"
            .toMediaTypeOrNull()
    }

    fun requestSecurityZones() {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(URL_OOPT)
            .post("{\"OOPT_name\": \"\"}".toRequestBody(JSON))
            .build()

        Thread {
            val response = client.newCall(
                request
            ).execute()

            val body = response
                .body?.string()
                ?: return@Thread

            Log.d(TAG, "requestSecurityZones: $body")

        }.start()
    }

    fun getSecurityZones() {



        val zones = arrayOf(
            SecurityZone(
                PolygonOptions()
                    .clickable(true)
                    .add(
                        LatLng(55.527197, 158.462162),
                        LatLng(55.477418, 159.654178),
                        LatLng(54.773885, 159.434452)
                    ),
                "Red zone",
                0x55ff0000.toInt(),
                0xffff0000.toInt(),
                3.0f
            ),
            SecurityZone(
                PolygonOptions()
                    .clickable(true)
                    .add(
                        LatLng(54.527197, 158.462162),
                        LatLng(54.477418, 159.654178),
                        LatLng(55.773885, 159.434452),
                        LatLng(56.773885, 159.424452)
                    ),
                "Green zone",
                0x5500ff00.toInt(),
                0xff00ff00.toInt(),
                3.0f
            )
        )

    }

}