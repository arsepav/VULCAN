package good.damn.kamchatka.views.special.details

import android.content.Context
import android.view.Gravity
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatTextView
import good.damn.kamchatka.Application
import good.damn.kamchatka.R
import good.damn.kamchatka.extensions.boundsLinear
import good.damn.kamchatka.extensions.setTextPx
import good.damn.kamchatka.models.map.AntroColors
import good.damn.kamchatka.views.RoundedImageView

class CardItemName(
    context: Context
): CardItem(
    context
) {

    var name: String? = null
    var type: String? = null
    var dangerRate = 0.3f
        set(v) {
            field = v
            mTextViewDangerRate.text = "%.1f".format(v)
            mTextViewDangerRate.setBackgroundColor(
                AntroColors.colors[
                    if (v > 10.0f)
                        9
                    else v.toInt()
                ]
            )

            val arr = resources.getStringArray(
                R.array.rates
            )
            mTextViewRateText.text = when (
                v.toInt()
            ) {
                in 0..2 -> arr[0]
                in 3..5 -> arr[1]
                else -> arr[2]
            }
        }

    private lateinit var mTextViewDangerRate: AppCompatTextView
    private lateinit var mTextViewRateText: AppCompatTextView

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
        val layoutRate = LinearLayout(
            context
        )
        mTextViewDangerRate = AppCompatTextView(
            context
        )
        mTextViewRateText = AppCompatTextView(
            context
        )

        layoutRate.orientation = LinearLayout
            .HORIZONTAL


        textViewName.text = name
        textViewType.text = type
        mTextViewRateText.setText(
            R.string.calculating_rate
        )


        textViewName.setTextPx(
            height * 0.08148f
        )
        textViewType.setTextPx(
            height * 0.05925f
        )


        imageViewMap.setImageDrawable(
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
            layout.boundsLinear(
                Gravity.START,
                left = left
            )
        }

        mTextViewDangerRate.boundsLinear(
            Gravity.START
        )
        mTextViewRateText.boundsLinear(
            Gravity.START
        )

        layoutRate.addView(
            mTextViewDangerRate
        )
        layoutRate.addView(
            mTextViewRateText
        )

        layout.addView(
            textViewName
        )

        layout.addView(
            textViewType
        )
        layout.addView(
            imageViewMap
        )
        layout.addView(
            layoutRate
        )
    }


}