package good.damn.kamchatka.services

import android.util.Log
import androidx.annotation.WorkerThread
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolygonOptions
import com.google.android.gms.maps.model.PolylineOptions
import good.damn.kamchatka.Application
import good.damn.kamchatka.models.RouteMap
import good.damn.kamchatka.models.SecurityZone
import good.damn.kamchatka.models.map.AntroColors
import good.damn.kamchatka.models.map.OOPTColors
import good.damn.kamchatka.models.remote.json.OOPT
import good.damn.kamchatka.models.remote.json.Route
import good.damn.kamchatka.services.GeoService.Companion.JSON
import good.damn.kamchatka.services.GeoService.Companion.URL_OOPT
import good.damn.kamchatka.services.interfaces.OnGetRoutesListener
import good.damn.kamchatka.services.interfaces.OnGetSecurityZonesListener
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import kotlin.random.Random

class GeoService {

    companion object {
        private const val TAG = "GeoService"
        const val URL_OOPT = "http://91.224.86.144:8000/oopts"
        val JSON = "application/json; charset=utf-8"
            .toMediaTypeOrNull()
    }

    private var mOnGetZonesListener: OnGetSecurityZonesListener? = null
    private var mOnGetRoutesListener: OnGetRoutesListener? = null

    private val mZoneColors = OOPTColors
        .entries
        .toTypedArray()

    fun requestRoutes() {
        if (mOnGetRoutesListener == null) {
            return
        }

        makeRequest(
            ""
        ) { client, request ->
            queueRequest(
                client,
                request,
                this::processRoutes
            )
        }
    }

    fun requestSecurityZones() {
        if (mOnGetZonesListener == null) {
            return
        }
        makeRequest(
            "{\"OOPT_name\": \"\"}"
        ) { client, request ->
            queueRequest(
                client,
                request,
                this::processZones
            )
        }
    }

    fun setOnGetRoutesListener(
        l: OnGetRoutesListener?
    ) {
        mOnGetRoutesListener = l
    }

    fun setOnGetZonesListener(
        l: OnGetSecurityZonesListener?
    ) {
        mOnGetZonesListener = l
    }

    @WorkerThread
    private fun processRoutes(
        json: JSONArray
    ) {
        Log.d(TAG, "processRoutes: LEN: ${json.length()}")
        val routes: Array<RouteMap?> = Array(json.length()) {
            val route = Route.createFromJSON(
                json.getJSONObject(it)
            )

            val c = route?.coords ?: return@Array null

            val polyline = PolylineOptions()
                .addAll(c.asIterable())
            RouteMap(
                polyline,
                0xffFFC700.toInt(),
                10.0f
            )
        }

        Application.ui {
            mOnGetRoutesListener?.onGetRoutes(
                routes
            )
        }
    }

    @WorkerThread
    private fun processZones(
        json: JSONArray
    ) {
        Log.d(TAG, "processZones: LEN: ${json.length()}")

        val zones: Array<SecurityZone?> = Array(json.length()) {
            val oopt = OOPT.createFromJSON(
                json.getJSONObject(it)
            )

            val c = oopt?.coords ?: return@Array null

            val polyOptions = PolygonOptions()
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
            mOnGetZonesListener?.onGetSecurityZones(
                zones
            )
        }
    }

}

private fun GeoService.makeRequest(
    jsonString: String,
    c: ((OkHttpClient, Request)->Unit)
) {
    val client = OkHttpClient()
    val request = Request.Builder()
        .url(URL_OOPT)
        .post(jsonString.toRequestBody(JSON))
        .build()

    c(client,request)
}

@WorkerThread
private fun GeoService.queueRequest(
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

        process(
            JSONArray(
                body
            )
        )
    }.start()
}