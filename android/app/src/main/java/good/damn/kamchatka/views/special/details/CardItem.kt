package good.damn.kamchatka.views.special.details

import android.content.Context
import android.icu.text.ListFormatter.Width
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

        radius = height() * 0.1f

        onCreateLinearLayout(
            layout,
            height(),
            width(),
            (width() * 0.0483f).toInt()
        )

        addView(
            layout
        )
    }

    abstract fun onCreateLinearLayout(
        layout: LinearLayout,
        height: Int,
        width: Int,
        left: Int
    )

}