package good.damn.kamchatka.services

import android.content.Context
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
import good.damn.kamchatka.services.interfaces.OnGetRoutesListener
import good.damn.kamchatka.services.interfaces.OnGetSecurityZonesListener
import good.damn.kamchatka.services.network.NetworkService
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import kotlin.random.Random

class GeoService(
    context: Context
): NetworkService(
    context
) {

    companion object {
        private const val TAG = "GeoService"
        private const val URL_ROUTE = "${Application.URL}/geopaths"
        private const val URL_OOPT = "${Application.URL}/oopts"
    }

    private var mOnGetZonesListener: OnGetSecurityZonesListener? = null
    private var mOnGetRoutesListener: OnGetRoutesListener? = null

    fun requestRoutes() {
        if (mOnGetRoutesListener == null) {
            return
        }

        makeRequest(
            Request.Builder()
                .url(URL_ROUTE)
                .get()
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

        val body = "{\"OOPT_name\": \"\"}"
            .toRequestBody(
                Application.JSON
            )

        val request = Request.Builder()
            .url(URL_OOPT)
            .post(body)

        makeRequest(
            request
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
                10.0f,
                route.id
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

            val markerOptions = MarkerOptions()
                .position(
                    c[0]
                )

            SecurityZone(
                polyOptions,
                oopt,
                markerOptions
            )
        }

        Application.ui {
            mOnGetZonesListener?.onGetSecurityZones(
                zones
            )
        }
    }

}