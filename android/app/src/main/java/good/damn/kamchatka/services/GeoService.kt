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
import good.damn.kamchatka.models.remote.json.OOPTObject
import good.damn.kamchatka.models.remote.json.Route
import good.damn.kamchatka.services.interfaces.OnGetObjectsListener
import good.damn.kamchatka.services.interfaces.OnGetRoutesListener
import good.damn.kamchatka.services.interfaces.OnGetSecurityZonesListener
import good.damn.kamchatka.services.network.NetworkService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.internal.wait
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
        private const val URL_OOPT_OBJS = "${Application.URL}/oopt_objects"
    }

    private var mOnGetZonesListener: OnGetSecurityZonesListener? = null
    private var mOnGetRoutesListener: OnGetRoutesListener? = null
    private var mOnGetObjectsListener: OnGetObjectsListener? = null

    fun requestRoutes(
        ooptId: Int = -1
    ) {
        if (mOnGetRoutesListener == null) {
            return
        }

        var urlParam = URL_ROUTE

        if (ooptId != -1) {
            urlParam += "?oopt_id=$ooptId"
        }

        makeRequest(
            Request.Builder()
                .url(urlParam)
                .get(),
            noConnection = {
                val cacheDir = mContext.cacheDir
                CoroutineScope(
                    Dispatchers.IO
                ).launch {
                    Log.d(TAG, "requestRoutes: noConnection: PREP:")
                    val json = loadJSONArrayFromCache(
                        urlParam,
                        cacheDir
                    ) ?: return@launch
                    Log.d(TAG, "requestRoutes: noConnection: $json")
                    processRoutes(
                        json
                    )

                }
            }
        ) { client, request ->
            queueRequest(
                client,
                request,
                this::processRoutes
            )
        }
    }

    fun requestObjects() {
        if (mOnGetObjectsListener == null) {
            return
        }

        makeRequest(
            Request.Builder()
                .url(URL_OOPT_OBJS)
                .get(),
            noConnection = {
                val cacheDir = mContext.cacheDir
                CoroutineScope(
                    Dispatchers.IO
                ).launch {
                    Log.d(TAG, "requestObjects: noConnection: PREP:")
                    val json = loadJSONArrayFromCache(
                        URL_OOPT_OBJS,
                        cacheDir
                    ) ?: return@launch
                    Log.d(TAG, "requestObjects: noConnection: $json")
                    processObjects(
                        json
                    )

                }
            }
        ) { client, request ->
            queueRequest(
                client,
                request,
                this::processObjects
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

        makeRequest(
            Request.Builder()
                .url(URL_OOPT)
                .post(body),
            noConnection = {
                val cacheDir = mContext.cacheDir
                CoroutineScope(
                    Dispatchers.IO
                ).launch {
                    Log.d(TAG, "requestSecurityZones: noConnection: PREP:")
                    val json = loadJSONArrayFromCache(
                        URL_OOPT,
                        cacheDir
                    ) ?: return@launch
                    Log.d(TAG, "requestSecurityZones: noConnection: $json")
                    processZones(
                        json
                    )

                }
            }
        ) { client, request ->
            queueRequest(
                client,
                request,
                this::processZones
            )
        }
    }

    fun setOnGetObjectsListener(
        l: OnGetObjectsListener?
    ) {
        mOnGetObjectsListener = l
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
    private fun processObjects(
        json: JSONArray
    ) {
        Log.d(TAG, "processObjects: ${json.length()}")
        val objects: Array<OOPTObject?> = Array(json.length()) {
            OOPTObject.createFromJSON(
                json.getJSONObject(it)
            )
        }

        Application.ui {
            mOnGetObjectsListener?.onGetObjects(
                objects
            )
        }
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

            Log.d(TAG, "processRoutes: ${route?.coords}")
            val c = route?.coords ?: return@Array null

            val polyline = PolylineOptions()
                .addAll(c.asIterable())
            
            RouteMap(
                polyline,
                route
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