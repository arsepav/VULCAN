package good.damn.kamchatka.services

import android.util.Log
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolygonOptions
import good.damn.kamchatka.Application
import good.damn.kamchatka.R
import good.damn.kamchatka.models.Color
import good.damn.kamchatka.models.SecurityZone
import good.damn.kamchatka.models.map.AntroColors
import good.damn.kamchatka.models.map.OOPTColors
import good.damn.kamchatka.models.remote.json.OOPT
import good.damn.kamchatka.services.interfaces.GeoServiceListener
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import kotlin.random.Random

class GeoService(
    private val mListener: GeoServiceListener
) {

    companion object {
        private const val TAG = "GeoService"
        private const val URL_OOPT = "http://91.224.86.144:8000/oopts"
        private val JSON = "application/json; charset=utf-8"
            .toMediaTypeOrNull()
    }

    private val mZoneColors = OOPTColors
        .entries
        .toTypedArray()

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


            val json = JSONArray(
                body
            )
            Log.d(TAG, "requestSecurityZones: ${json.length()}")

            val zones: Array<SecurityZone?> = Array(json.length()) {
                val oopt = OOPT.createFromJSON(
                    json.getJSONObject(it)
                )

                val c = oopt?.coords ?: return@Array null

                Log.d(TAG, "requestSecurityZones: ${oopt.id}")

                val polyOptions = PolygonOptions()
                    .clickable(true)
                    .addAll(
                        c.asIterable()
                    )

                val dangerRate = 0.2f.toInt()

                val markerOptions = MarkerOptions()
                    .position(
                        c[0]
                    ).icon(
                        BitmapDescriptorFactory.fromResource(
                            AntroColors.markers[dangerRate]
                        )
                    )

                SecurityZone(
                    polyOptions,
                    markerOptions,
                    oopt.name ?: "",
                    mZoneColors[
                        Random.nextInt(
                            mZoneColors.size
                        )
                    ].color,
                    AntroColors.colors[dangerRate],
                    11.0f
                )
            }

            Application.ui {
                mListener.onGetSecurityZones(
                    zones
                )
            }
            
        }.start()
    }

}