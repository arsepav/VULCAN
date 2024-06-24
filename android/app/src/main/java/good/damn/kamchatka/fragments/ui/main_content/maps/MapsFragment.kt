package good.damn.kamchatka.fragments.ui.main_content.maps

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import good.damn.kamchatka.Application
import good.damn.kamchatka.R
import good.damn.kamchatka.fragments.StackFragment
import good.damn.kamchatka.models.ShortOOPT

class MapsFragment
: StackFragment() {

    var mapFragment: SupportMapFragment? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d(TAG, "onViewCreated: MAP: $mapFragment")
        mapFragment?.let {
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
        private const val TAG = "MapsFragment"
        fun create(
            withMap: SupportMapFragment
        ): MapsFragment {
            val f = MapsFragment()
            f.mapFragment = withMap
            Log.d(TAG, "create: MAP: ${f.mapFragment}")
            return f
        }
    }
}