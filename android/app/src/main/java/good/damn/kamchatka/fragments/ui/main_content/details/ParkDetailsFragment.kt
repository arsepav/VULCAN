package good.damn.kamchatka.fragments.ui.main_content.details

import android.graphics.drawable.BitmapDrawable
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import good.damn.kamchatka.R
import good.damn.kamchatka.adapters.recycler_view.RoutesAdapter
import good.damn.kamchatka.extensions.boundsLinear
import good.damn.kamchatka.fragments.ui.main_content.maps.MapsFragment
import good.damn.kamchatka.models.RouteMap
import good.damn.kamchatka.models.ShortOOPT
import good.damn.kamchatka.models.remote.json.Route
import good.damn.kamchatka.services.GeoService
import good.damn.kamchatka.services.interfaces.OnGetRoutesListener
import good.damn.kamchatka.views.special.details.CardItemRoutes
import good.damn.kamchatka.views.special.details.listeners.OnSelectModelListener

class ParkDetailsFragment
: BaseDetailsFragment(),
OnGetRoutesListener, OnSelectModelListener<Route> {

    private lateinit var mCardItemRoutes: CardItemRoutes

    override fun onSetupProperties() {
        model?.oopt?.apply {
            mCardName.name = name
            mCardDesc.desc = desc

            mCardName.type = getString(
                R.string.park
            )

            mCardDesc.about = getString(
                R.string.about_zone
            )
        }

        model?.apply {
            mCardName.type = type
            if (image == null) {
                return@apply
            }
            mCardHeader.background = BitmapDrawable(
                resources,
                image
            )
        }
    }

    override fun onCreateCardItems(
        layout: LinearLayout,
        measureUnit: Int
    ) {
        val context = context ?: return

        mCardItemRoutes = CardItemRoutes(
            context
        )


        mCardItemRoutes.boundsLinear(
            Gravity.START,
            width = measureUnit,
            height = (measureUnit * 0.7995f).toInt()
        )

        mCardItemRoutes.setOnSelectRouteListener(
            this
        )

        mCardItemRoutes.layoutIt()

        layout.addView(
            mCardItemRoutes
        )

        GeoService(
            context
        ).let {
            it.setOnGetRoutesListener(
                this
            )
            it.requestRoutes(
                ooptId = model?.oopt?.id ?: -1
            )
        }
    }

    override fun onClickMap(
        view: View
    ) {
        model?.oopt?.coords?.let {
            if (it.isEmpty()) {
                return
            }
            pushFragment(
                MapsFragment.create(
                    it[(it.size * 0.5f).toInt()]
                )
            )
        }
    }

    override fun onGetRoutes(
        routes: Array<RouteMap?>
    ) {
        if (routes.isEmpty()) {
            return
        }

        var rate = 0f
        this.routes = Array(routes.size) {
            routes[it]?.route?.let {
                rate += it.dangerRate
                it
            }
        }
        mCardItemRoutes.routes = this.routes
        mCardName.dangerRate = rate / routes.size
    }


    override fun onSelectModel(
        model: Route
    ) {
        pushFragment(
            RouteDetailsFragment.create(
                model,
                this.model,
                routes
            )
        )
    }

    companion object {
        fun create(
            zone: ShortOOPT
        ): BaseDetailsFragment {
            ParkDetailsFragment().let {
                it.setModelDetails(
                    zone
                )
                return it
            }
        }
    }
}