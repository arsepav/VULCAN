package good.damn.kamchatka.fragments.ui.main_content.maps

import good.damn.kamchatka.models.Color
import android.os.Bundle
import android.util.Log
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Polygon
import com.google.android.gms.maps.model.RoundCap
import good.damn.kamchatka.Application
import good.damn.kamchatka.models.RouteMap
import good.damn.kamchatka.models.SecurityZone
import good.damn.kamchatka.models.map.AntroColors
import good.damn.kamchatka.models.map.OOPTColors
import good.damn.kamchatka.services.GeoService
import good.damn.kamchatka.services.interfaces.OnGetRoutesListener
import good.damn.kamchatka.services.interfaces.OnGetSecurityZonesListener
import kotlin.random.Random

class GoogleMapFragment
: SupportMapFragment(),
OnMapReadyCallback,
GoogleMap.OnPolygonClickListener,
OnGetSecurityZonesListener,
OnGetRoutesListener {

    companion object {
        private const val TAG = "MapsFragment"
    }

    private val mZoneColors = OOPTColors
        .entries
        .toTypedArray()

    private val mOOPTColors = HashMap<Int, Int>()

    private var mGeoService: GeoService? = null
    private lateinit var map: GoogleMap

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(savedInstanceState)

        context?.let {
            mGeoService = GeoService(
                it
            )
            getMapAsync(
                this
            )
        }
    }

    override fun onMapReady(
        googleMap: GoogleMap
    ) {
        map = googleMap

        val kamchatka = LatLng(
            55.184952,
            158.394596
        )


        mGeoService?.setOnGetZonesListener(
            this
        )

        mGeoService?.setOnGetRoutesListener(
            this
        )

        mGeoService?.requestSecurityZones()

        map.moveCamera(
            CameraUpdateFactory
                .newLatLngZoom(
                    kamchatka,
                    5.0f
                )
        )

        map.setOnPolygonClickListener(
            this
        )

    }

    override fun onPolygonClick(
        poly: Polygon
    ) {
        context?.let {
            Application.toast(
                poly.tag?.toString() ?: return,
                it
            )
        }
    }

    override fun onGetSecurityZones(
        zones: Array<SecurityZone?>
    ) {
        zones.forEach { zone ->
            if (zone == null) {
                return@forEach
            }

            val fillColor = mZoneColors[
                Random.nextInt(
                    mZoneColors.size
                )
            ].color

            val oopt = zone.oopt

            val dangerRate = oopt.dangerRate.toInt()

            zone.oopt.id?.let {
                mOOPTColors[it] = fillColor
            }


            map.addMarker(
                zone.marker
            )?.apply {
                setIcon(
                    BitmapDescriptorFactory.fromResource(
                        AntroColors.markers[dangerRate]
                    )
                )
            }


            map.addPolygon(
                zone.polygon
            ).apply {
                isClickable = true
                this.fillColor = fillColor
                strokeColor = AntroColors.colors[
                    dangerRate
                ]
                strokeWidth = 11.0f
            }
        }
        mGeoService?.requestRoutes()
    }

    override fun onGetRoutes(
        routes: Array<RouteMap?>
    ) {
        val cap = RoundCap()
        val errorColor = Color.parseFromHex(
            0xffff0000.toInt(),
            0.3f
        )
        routes.forEach { route ->
            if (route == null) {
                return@forEach
            }

            map.addPolyline(
                route.route
            ).apply {

                color = if (
                    route.ooptId == null
                ) errorColor else mOOPTColors[route.ooptId]
                    ?: errorColor

                width = route.strokeWidth
                startCap = cap
                endCap = cap
            }
        }
    }

}