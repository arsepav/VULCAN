package good.damn.kamchatka.fragments.ui.main_navigation

import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import good.damn.kamchatka.services.GeoService

class MapsFragment
: SupportMapFragment(),
OnMapReadyCallback {

    private lateinit var map: GoogleMap

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(savedInstanceState)

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

        GeoService().getSecurityZones {
            val polygon = map.addPolygon(
                it.polygon
            )
            polygon.fillColor = it.fillColor
            polygon.strokeColor = it.strokeColor
            polygon.strokeWidth = it.strokeWidth
        }

        map.moveCamera(
            CameraUpdateFactory
                .newLatLngZoom(
                    kamchatka,
                    0.2f
                )
        )
    }

}