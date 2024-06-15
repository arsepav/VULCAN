package good.damn.kamchatka.fragments.ui.main_content.maps

import android.os.Bundle
import android.util.Log
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Polygon
import com.google.android.gms.maps.model.RoundCap
import good.damn.kamchatka.Application
import good.damn.kamchatka.models.RouteMap
import good.damn.kamchatka.models.SecurityZone
import good.damn.kamchatka.models.map.OOPTColors
import good.damn.kamchatka.services.GeoService
import good.damn.kamchatka.services.interfaces.OnGetRoutesListener
import good.damn.kamchatka.services.interfaces.OnGetSecurityZonesListener

class GoogleMapFragment
: SupportMapFragment(),
OnMapReadyCallback,
GoogleMap.OnPolygonClickListener,
OnGetSecurityZonesListener,
OnGetRoutesListener {

    companion object {
        private const val TAG = "MapsFragment"
    }

    private val mOOPTColors = OOPTColors
        .entries
        .toTypedArray()

    private lateinit var map: GoogleMap

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "onCreate: ")

        getMapAsync(
            this
        )
    }

    override fun onMapReady(
        googleMap: GoogleMap
    ) {
        val context = context ?: return

        map = googleMap

        val kamchatka = LatLng(
            55.184952,
            158.394596
        )

        val geo = GeoService(
            context
        )

        geo.setOnGetZonesListener(
            this
        )

        geo.setOnGetRoutesListener(
            this
        )

        geo.requestSecurityZones()
        geo.requestRoutes()

        map.moveCamera(
            CameraUpdateFactory
                .newLatLngZoom(
                    kamchatka,
                    0.2f
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
                poly.tag as String,
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

            map.addMarker(
                zone.marker
            )

            map.addPolygon(
                zone.polygon
            ).apply {
                isClickable = true
                fillColor = zone.fillColor
                strokeColor = zone.strokeColor
                strokeWidth = zone.strokeWidth
                tag = zone.title
            }
        }
    }

    override fun onGetRoutes(
        routes: Array<RouteMap?>
    ) {
        val cap = RoundCap()
        routes.forEach { route ->
            if (route == null) {
                return@forEach
            }

            map.addPolyline(
                route.route
            ).apply {
                color = route.color
                width = route.strokeWidth
                startCap = cap
                endCap = cap
            }
        }
    }

}