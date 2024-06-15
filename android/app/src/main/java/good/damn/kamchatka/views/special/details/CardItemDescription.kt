package good.damn.kamchatka.views.special.details

import android.content.Context
import android.view.Gravity
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatTextView
import good.damn.kamchatka.extensions.boundsLinear

class CardItemDescription(
    context: Context
): CardItem(
    context
) {

    var about = "ABOUTSDSADASD"
    var desc = "DESCIsadsadsadasdas"

    override fun onCreateLinearLayout(
        layout: LinearLayout,
        height: Int,
        width: Int,
        left: Int
    ) {
        val textViewAbout = AppCompatTextView(
            context
        )
        val textViewDesc = AppCompatTextView(
            context
        )

        textViewAbout.text = about
        textViewDesc.text = desc

        textViewAbout.boundsLinear(
            Gravity.START,
            left = left.toFloat(),
        )

        layout.apply {
            addView(
                textViewAbout
            )
            addView(
                textViewDesc
            )
        }

    }


}