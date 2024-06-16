package good.damn.kamchatka.fragments.ui.main_content.details

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import good.damn.kamchatka.extensions.boundsLinear
import good.damn.kamchatka.fragments.ui.ScrollableFragment
import good.damn.kamchatka.fragments.ui.main_content.AnthropInfoFragment
import good.damn.kamchatka.fragments.ui.main_content.maps.MapsFragment
import good.damn.kamchatka.models.ShortOOPT
import good.damn.kamchatka.utils.ViewUtils
import good.damn.kamchatka.views.special.details.CardHeader
import good.damn.kamchatka.views.special.details.CardItemDescription
import good.damn.kamchatka.views.special.details.CardItemName

abstract class BaseDetailsFragment<MODEL>
: ScrollableFragment() {

    protected lateinit var mCardHeader: CardHeader
    protected lateinit var mCardName: CardItemName
    protected lateinit var mCardDesc: CardItemDescription

    protected var model: MODEL? = null

    final override fun onCreateContentView(
        context: Context,
        measureUnit: Int
    ): View {

        val layout = ViewUtils.verticalLinearLayout(
            context
        )
        mCardHeader = CardHeader(
            context
        )

        mCardName = CardItemName(
            context
        )
        mCardDesc = CardItemDescription(
            context
        )

        mCardHeader.setOnClickBackListener(
            this::onClickBtnBack
        )

        onSetupProperties(
            model
        )


        mCardHeader.boundsLinear(
            Gravity.START,
            width = measureUnit,
            height = (measureUnit * 0.6473f).toInt(),
        )

        mCardName.boundsLinear(
            Gravity.START,
            width = measureUnit,
            height = (measureUnit * 0.6521f).toInt(),
            top = -50f
        )
        mCardDesc.boundsLinear(
            Gravity.START,
            width = measureUnit
        )

        mCardHeader.layoutIt(
            measureUnit
        )

        mCardName.layoutIt()
        mCardDesc.layoutIt()

        layout.apply {
            addView(mCardHeader)
            addView(mCardName)
            addView(mCardDesc)
            onCreateCardItems(
                layout,
                measureUnit
            )
        }


        mCardName.setOnClickRateText(
            this::onClickDangerText
        )

        mCardName.setOnClickMap(
            this::onClickMap
        )


        return layout
    }

    fun setModelDetails(
        m: MODEL?
    ) {
        model = m
    }

    abstract fun onClickMap(
        view: View
    )


    abstract fun onCreateCardItems(
        layout: LinearLayout,
        measureUnit: Int
    )

    abstract fun onSetupProperties(
        model: MODEL?
    )

    private fun onClickDangerText(
        view: View
    ) {
        pushFragment(
            AnthropInfoFragment()
        )
    }

    private fun onClickBtnBack(
        view: View
    ) {
        popFragment()
    }
}