package good.damn.kamchatka.views.special.details

import android.content.Context
import android.view.Gravity
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatTextView
import good.damn.kamchatka.Application
import good.damn.kamchatka.R
import good.damn.kamchatka.extensions.boundsFrame
import good.damn.kamchatka.extensions.boundsLinear
import good.damn.kamchatka.extensions.setImageDrawableId
import good.damn.kamchatka.extensions.setTextPx
import good.damn.kamchatka.views.RoundedImageView

class CardItemName(
    context: Context
): CardItem(
    context
) {

    var name: String? = null
    var type: String? = null

    override fun onCreateLinearLayout(
        layout: LinearLayout,
        height: Int,
        width: Int,
        left: Int
    ) {
        val textViewName = AppCompatTextView(
            context
        )
        val textViewType = AppCompatTextView(
            context
        )
        val imageViewMap = RoundedImageView(
            context
        )


        textViewName.text = name
        textViewType.text = type


        textViewName.setTextPx(
            height * 0.08148f
        )
        textViewType.setTextPx(
            height * 0.05925f
        )


        imageViewMap.setImageDrawableId(
            R.drawable.details_map
        )



        Application.font(
            R.font.open_sans_bold,
            context
        ).let {
            textViewName.typeface = it
            textViewType.typeface = it
        }



        (width * 0.0483f).let { left ->
            textViewName.boundsLinear(
                Gravity.START,
                left = left
            )
            textViewType.boundsLinear(
                Gravity.START,
                left = left
            )
            imageViewMap.boundsLinear(
                Gravity.CENTER_HORIZONTAL,
                width = (width * 0.913f).toInt(),
                height = (height * 0.311f).toInt()
            )
        }


        layout.addView(
            textViewName
        )

        layout.addView(
            textViewType
        )
        layout.addView(
            imageViewMap
        )
    }


}