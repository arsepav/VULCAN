package good.damn.kamchatka.fragments.ui.main_content.maps

import android.os.Bundle
import android.util.Log
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Polygon
import good.damn.kamchatka.Application
import good.damn.kamchatka.models.SecurityZone
import good.damn.kamchatka.models.map.OOPTColors
import good.damn.kamchatka.services.GeoService
import good.damn.kamchatka.services.interfaces.OnGetSecurityZonesListener

class GoogleMapFragment
: SupportMapFragment(),
OnMapReadyCallback,
GoogleMap.OnPolygonClickListener,
OnGetSecurityZonesListener {

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
        map = googleMap

        val kamchatka = LatLng(
            55.184952,
            158.394596
        )

        val geo = GeoService()

        geo

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
                fillColor = zone.fillColor
                strokeColor = zone.strokeColor
                strokeWidth = zone.strokeWidth
                tag = zone.title
            }
        }
    }

}