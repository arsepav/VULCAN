package good.damn.kamchatka.fragments.ui.main_content.details

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import good.damn.kamchatka.Application
import good.damn.kamchatka.R
import good.damn.kamchatka.extensions.boundsFrame
import good.damn.kamchatka.extensions.boundsLinear
import good.damn.kamchatka.extensions.height
import good.damn.kamchatka.extensions.setTextColorId
import good.damn.kamchatka.fragments.ui.ScrollableFragment
import good.damn.kamchatka.fragments.ui.main_content.AnthropInfoFragment
import good.damn.kamchatka.fragments.ui.main_content.maps.MapsFragment
import good.damn.kamchatka.fragments.ui.main_content.profile.RequestFragment
import good.damn.kamchatka.fragments.ui.main_content.visit_permission.PermissionFragment
import good.damn.kamchatka.models.Color
import good.damn.kamchatka.models.ShortOOPT
import good.damn.kamchatka.utils.ViewUtils
import good.damn.kamchatka.views.button.ButtonRound
import good.damn.kamchatka.views.special.details.CardHeader
import good.damn.kamchatka.views.special.details.CardItemDescription
import good.damn.kamchatka.views.special.details.CardItemName

abstract class BaseDetailsFragment
: ScrollableFragment() {

    protected lateinit var mCardHeader: CardHeader
    protected lateinit var mCardName: CardItemName
    protected lateinit var mCardDesc: CardItemDescription

    protected var model: ShortOOPT? = null

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

        onSetupProperties()


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

        layout.setPadding(
            0,
            0,
            0,
            (measureUnit * 0.2815f).toInt()
        )

        return layout
    }

    override fun onCreateTopView(
        context: Context,
        measureUnit: Int
    ): View? {
        val layout = FrameLayout(
            context
        )

        val btnMove = ButtonRound(
            context
        )


        btnMove.setBackgroundColor(
            Application.color(
                R.color.titleColor
            )
        )

        layout.setBackgroundColor(
            Color.parseFromHexId(
                R.color.background,
                0.9f
            )
        )

        btnMove.setText(
            R.string.visit_permission
        )

        btnMove.setTextColorId(
            R.color.textColorBtn
        )

        layout.boundsFrame(
            Gravity.BOTTOM,
            width = -1,
            height = (measureUnit * 0.2415f).toInt()
        )
        btnMove.boundsFrame(
            Gravity.CENTER,
            width = (measureUnit * 0.932f).toInt(),
            height = (layout.height() * 0.56f).toInt()
        )

        btnMove.cornerRadius = btnMove.height() * 0.3035f


        layout.addView(
            btnMove
        )


        btnMove.setOnClickListener(
            this::onClickBtnMoveVisit
        )

        return layout
    }

    fun setModelDetails(
        m: ShortOOPT?
    ) {
        model = m
    }

    protected abstract fun onClickMap(
        view: View
    )


    protected abstract fun onCreateCardItems(
        layout: LinearLayout,
        measureUnit: Int
    )

    protected abstract fun onSetupProperties()

    private fun onClickBtnMoveVisit(
        view: View
    ) {
        model?.let {
            pushFragment(
                PermissionFragment.create(
                    it
                )
            )
        }
    }

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