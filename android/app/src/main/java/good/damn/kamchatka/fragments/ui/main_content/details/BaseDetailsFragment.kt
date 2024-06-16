package good.damn.kamchatka.fragments.ui.main_content.details

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.view.Gravity
import android.view.View
import good.damn.kamchatka.extensions.boundsLinear
import good.damn.kamchatka.fragments.ui.ScrollableFragment
import good.damn.kamchatka.models.RouteMap
import good.damn.kamchatka.models.ShortOOPT
import good.damn.kamchatka.services.GeoService
import good.damn.kamchatka.services.interfaces.OnGetRoutesListener
import good.damn.kamchatka.utils.ViewUtils
import good.damn.kamchatka.views.special.details.CardHeader
import good.damn.kamchatka.views.special.details.CardItem
import good.damn.kamchatka.views.special.details.CardItemDescription
import good.damn.kamchatka.views.special.details.CardItemName
import good.damn.kamchatka.views.special.details.CardItemRoutes

class BaseDetailsFragment
: ScrollableFragment(), OnGetRoutesListener {

    private lateinit var mCardItemRoutes: CardItemRoutes

    private lateinit var mZone: ShortOOPT

    override fun onCreateContentView(
        context: Context,
        measureUnit: Int
    ): View {
        val layout = ViewUtils.verticalLinearLayout(
            context
        )
        val cardHeader = CardHeader(
            context
        )
        val cardName = CardItemName(
            context
        )
        val cardDesc = CardItemDescription(
            context
        )
        mCardItemRoutes = CardItemRoutes(
            context
        )

        GeoService(
            context
        ).let {
            it.setOnGetRoutesListener(
                this
            )
            it.requestRoutes()
        }

        cardHeader.setOnClickBackListener(
            this::onClickBtnBack
        )

        mZone.oopt.apply {
            cardName.name = name
            cardDesc.desc = "$desc"
            cardDesc.about = "О парке"
        }

        mZone.apply {
            cardName.type = type
            if (image == null) {
                return@apply
            }
            cardHeader.background = BitmapDrawable(
                resources,
                image
            )
        }






        cardHeader.boundsLinear(
            Gravity.START,
            width = measureUnit,
            height = (measureUnit * 0.6473f).toInt(),
        )

        cardName.boundsLinear(
            Gravity.START,
            width = measureUnit,
            height = (measureUnit * 0.6521f).toInt(),
            top = -50f
        )
        cardDesc.boundsLinear(
            Gravity.START,
            width = measureUnit
        )
        mCardItemRoutes.boundsLinear(
            Gravity.START,
            width = measureUnit,
            height = (measureUnit * 0.7995f).toInt()
        )

        cardHeader.layoutIt(
            measureUnit
        )

        cardName.layoutIt()
        cardDesc.layoutIt()
        mCardItemRoutes.layoutIt()

        layout.apply {
            addView(cardHeader)
            addView(cardName)
            addView(cardDesc)
            addView(mCardItemRoutes)
        }


        return layout
    }


    fun setSecurityZone(
        zone: ShortOOPT
    ) {
        mZone = zone
    }

    companion object {
        fun create(
            zone: ShortOOPT
        ): BaseDetailsFragment {
            val frag = BaseDetailsFragment()
            frag.setSecurityZone(
                zone
            )
            return frag
        }
    }

    override fun onGetRoutes(
        routes: Array<RouteMap?>
    ) {
        mCardItemRoutes.routes = Array(routes.size) {
            routes[it]?.route
        }
    }

}


private fun BaseDetailsFragment.onClickBtnBack(
    view: View
) {
    popFragment()
}