package good.damn.kamchatka.fragments.ui.main_content.details

import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatImageView
import good.damn.kamchatka.R
import good.damn.kamchatka.extensions.boundsLinear
import good.damn.kamchatka.fragments.StackFragment
import good.damn.kamchatka.fragments.ui.ScrollableFragment
import good.damn.kamchatka.utils.ViewUtils
import good.damn.kamchatka.views.special.details.CardHeader
import good.damn.kamchatka.views.special.details.CardItemDescription
import good.damn.kamchatka.views.special.details.CardItemName

class BaseDetailsFragment
: ScrollableFragment() {

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



        // Set background image
        cardHeader.setBackgroundResource(
            R.drawable.temp_image
        )





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

        cardHeader.layoutIt(
            measureUnit
        )

        cardName.layoutIt()
        cardDesc.layoutIt()

        layout.apply {
            addView(cardHeader)
            addView(cardName)
            addView(cardDesc)
        }


        return layout
    }
}