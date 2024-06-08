package good.damn.kamchatka.fragments.ui.main_navigation

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Polygon
import good.damn.kamchatka.services.GeoService

class MapsFragment
: SupportMapFragment(),
OnMapReadyCallback, GoogleMap.OnPolygonClickListener {

    companion object {
        private const val TAG = "MapsFragment"
    }

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

        GeoService().getSecurityZones {
            val polygon = map.addPolygon(
                it.polygon
            )
            polygon.fillColor = it.fillColor
            polygon.strokeColor = it.strokeColor
            polygon.strokeWidth = it.strokeWidth
            polygon.tag = it.title
        }

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
        Toast
            .makeText(
                context,
                poly.tag as String,
                Toast.LENGTH_SHORT
            ).show()
    }

}