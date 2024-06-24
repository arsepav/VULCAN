package good.damn.kamchatka.fragments.ui.main_content.details

import android.view.View
import android.widget.LinearLayout
import good.damn.kamchatka.R
import good.damn.kamchatka.fragments.ui.main_content.maps.GoogleMapFragment
import good.damn.kamchatka.fragments.ui.main_content.maps.MapsFragment
import good.damn.kamchatka.models.RouteMap
import good.damn.kamchatka.models.ShortOOPT
import good.damn.kamchatka.models.remote.json.Route
import good.damn.kamchatka.services.GeoService

class RouteDetailsFragment
: BaseDetailsFragment() {

    private var mRoute: Route? = null

    override fun onClickMap(
        view: View
    ) {
        val coords = mRoute?.coords
            ?: return

        if (coords.isEmpty()) {
            return
        }

        val map = GoogleMapFragment()
        map.markerLatLng = coords[
            (coords.size * 0.5f).toInt()
        ]

        pushFragment(
            MapsFragment.create(map)
        )
    }

    fun setModelDetails(
        route: Route?,
        m: ShortOOPT?,
        routes: Array<Route?>?
    ) {
        mRoute = route
        this.routes = routes
        super.setModelDetails(m)
    }

    override fun onCreateCardItems(
        layout: LinearLayout,
        measureUnit: Int
    ) {
        mCardName.dangerRate = mRoute?.dangerRate ?: 11.0f
    }

    override fun onSetupProperties() {
        mRoute?.let {

            mCardHeader.setBackgroundResource(
                R.drawable.zakaznik
            )

            mCardName.name = it.name
            mCardDesc.desc = it.desc

            mCardName.type = getString(
                R.string.route
            )

            mCardDesc.about = getString(
                R.string.about_route
            )
        }
    }

    companion object {
        fun create(
            route: Route?,
            oopt: ShortOOPT?,
            routes: Array<Route?>?,
        ): BaseDetailsFragment {
            RouteDetailsFragment().let {
                it.setModelDetails(
                    route,
                    oopt,
                    routes
                )
                return it
            }
        }
    }

}