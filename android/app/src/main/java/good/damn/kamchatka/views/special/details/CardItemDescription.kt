package good.damn.kamchatka.views.special.details

import android.content.Context
import android.view.Gravity
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatTextView
import good.damn.kamchatka.Application
import good.damn.kamchatka.R
import good.damn.kamchatka.extensions.boundsLinear
import good.damn.kamchatka.extensions.setTextPx

class CardItemDescription(
    context: Context
): CardItem(
    context
) {

    var about: String? = null
    var desc: String? = null

    override fun onCreateLinearLayout(
        layout: LinearLayout,
        measureUnit: Int,
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

        Application.color(
            R.color.titleColor
        ).let {
            textViewAbout.setTextColor(
                it
            )

            textViewDesc.setTextColor(
                it
            )
        }

        Application.font(
            R.font.open_sans_bold,
            context
        ).let {
            textViewAbout.typeface = it
        }

        Application.font(
            R.font.open_sans_regular,
            context
        ).let {
            textViewDesc.typeface = it
        }

        (measureUnit * 0.04835f).let {
            textViewAbout.setTextPx(
                it
            )
        }

        (measureUnit * 0.03743f).let {
            textViewDesc.setTextPx(
                it
            )
        }

        textViewAbout.boundsLinear(
            Gravity.START
        )

        textViewDesc.boundsLinear(
            Gravity.START,
            top = measureUnit * 0.04347f
        )

        (measureUnit * 0.06159f).toInt().let {
            layout.setPadding(
                left,
                it,
                left,
                it
            )
        }

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