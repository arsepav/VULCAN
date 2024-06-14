package good.damn.kamchatka.fragments.ui.main_content.maps

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Polygon
import good.damn.kamchatka.Application
import good.damn.kamchatka.R
import good.damn.kamchatka.fragments.StackFragment
import good.damn.kamchatka.services.GeoService

class MapsFragment
: StackFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        childFragmentManager
            .beginTransaction()
            .replace(view.id, GoogleMapFragment())
            .commit()
    }
    override fun onCreateView(
        context: Context,
        measureUnit: Int
    ): View {
        val layout = FrameLayout(
            context
        )

        layout.setBackgroundColor(
            Application.color(
                R.color.background
            )
        )

        layout.id = View.generateViewId()

        return layout
    }

}