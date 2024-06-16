package good.damn.kamchatka.views.special.details

import android.content.Context
import android.icu.text.ListFormatter.Width
import android.icu.util.MeasureUnit
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import good.damn.kamchatka.extensions.height
import good.damn.kamchatka.extensions.width
import good.damn.kamchatka.utils.ViewUtils

abstract class CardItem(
    context: Context
): CardView(
    context
) {

    fun layoutIt() {
        val layout = ViewUtils.verticalLinearLayout(
            context
        )

        radius = width() * 0.0603f

        onCreateLinearLayout(
            layout,
            width(),
            (width() * 0.0483f).toInt()
        )

        addView(
            layout
        )
    }

    abstract fun onCreateLinearLayout(
        layout: LinearLayout,
        measureUnit: Int,
        left: Int
    )

}