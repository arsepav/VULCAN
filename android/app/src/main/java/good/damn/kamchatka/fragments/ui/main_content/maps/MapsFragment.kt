package good.damn.kamchatka.fragments.ui.main_content.maps

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import com.google.android.gms.maps.model.LatLng
import good.damn.kamchatka.Application
import good.damn.kamchatka.R
import good.damn.kamchatka.fragments.StackFragment

class MapsFragment
: StackFragment() {

    var markerLatLng: LatLng? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        GoogleMapFragment().let {
            it.markerLatLng = markerLatLng
            childFragmentManager
                .beginTransaction()
                .replace(view.id, it)
                .commit()
        }

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

    companion object {
        fun create(
            marker: LatLng
        ): MapsFragment {
            val f = MapsFragment()
            f.markerLatLng = marker
            return f
        }
    }

}