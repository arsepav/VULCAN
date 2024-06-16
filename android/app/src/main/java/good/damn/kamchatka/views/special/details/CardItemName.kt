package good.damn.kamchatka.views.special.details

import android.content.Context
import android.view.Gravity
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import good.damn.kamchatka.Application
import good.damn.kamchatka.R
import good.damn.kamchatka.extensions.boundsLinear
import good.damn.kamchatka.extensions.height
import good.damn.kamchatka.extensions.left
import good.damn.kamchatka.extensions.setTextPx
import good.damn.kamchatka.extensions.textSizeBounds
import good.damn.kamchatka.models.Color
import good.damn.kamchatka.models.map.AntroColors
import good.damn.kamchatka.views.LabelView
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

            mTextViewDangerRate.text = v.toInt().toString()
            mTextViewDangerRate.setBackgroundColor(
                AntroColors.colors[
                    if (v > 10.0f)
                        9
                    else v.toInt()
                ]
            )

            mTextViewDangerRate.requestLayout()

            val arr = resources.getStringArray(
                R.array.rates
            )

            val t = when (
                v.toInt()
            ) {
                in 0..2 -> arr[0]
                in 3..5 -> arr[1]
                else -> arr[2]
            }

            mTextViewRateText?.text = "$t  "
        }

    private lateinit var mTextViewDangerRate: LabelView
    private var mTextViewRateText: AppCompatTextView? = null
    private var mImageViewMap: RoundedImageView? = null


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
        mImageViewMap = RoundedImageView(
            context
        )
        val layoutRate = LinearLayout(
            context
        )
        mTextViewDangerRate = LabelView(
            context
        )
        mTextViewRateText = AppCompatTextView(
            context
        )

        layoutRate.orientation = LinearLayout
            .HORIZONTAL


        textViewName.text = name
        textViewType.text = type
        mTextViewRateText?.setText(
            R.string.calculating_rate
        )


        textViewName.setTextPx(
            height * 0.08148f
        )
        textViewType.setTextPx(
            height * 0.05925f
        )

        mImageViewMap?.animation = {
            mImageViewMap?.alpha = 0.75f + (1.0f-it) * 0.25f
        }

        mImageViewMap?.setImageDrawable(
            R.drawable.details_map
        )

        mTextViewRateText?.let {
            Application.drawable(
                R.drawable.ic_info
            )?.let { d ->
                it.text = "${it.text}  "
                val s = (it.textSize * 1.2f)
                    .toInt()
                d.setBounds(
                    0,
                    0,
                    s,
                    s
                )
                it.setCompoundDrawables(
                    null,
                    null,
                    d,
                    null
                )
            }
        }


        Application.font(
            R.font.open_sans_bold,
            context
        ).let {
            textViewName.typeface = it
            textViewType.typeface = it
        }

        Application.color(
            R.color.titleColor
        ).let {
            textViewName.setTextColor(
                it
            )

            textViewType.setTextColor(
                Color.parseFromHex(
                    it,
                    0.3f
                )
            )
        }


        (width * 0.0483f).let { left ->
            textViewName.boundsLinear(
                Gravity.START,
                top = (height * 0.0888f),
                left = left
            )
            textViewType.boundsLinear(
                Gravity.START,
                left = textViewName.left(),
                top = height * 0.001f
            )
            mImageViewMap?.boundsLinear(
                Gravity.CENTER_HORIZONTAL,
                width = (width * 0.913f).toInt(),
                height = (height * 0.311f).toInt(),
                top = (height * 0.0888f)
            )
            layoutRate.boundsLinear(
                Gravity.START,
                left = left,
                top = (height * 0.0888f)
            )
        }

        mTextViewDangerRate.boundsLinear(
            Gravity.START,
            height = (height * 0.0777f).toInt(),
            width = (width * 0.06521f).toInt()
        )
        mTextViewRateText?.boundsLinear(
            Gravity.START
        )


        mTextViewDangerRate.apply {
            cornerRadius = this.height() * 0.25f
            textColor = 0xffffffff.toInt()
        }



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
            mImageViewMap
        )
        layout.addView(
            layoutRate
        )
    }

    fun setOnClickMap(
        l: OnClickListener?
    ) {
        mImageViewMap?.setOnClickListener(
            l
        )
    }

    fun setOnClickRateText(
        l: OnClickListener?
    ) {
        mTextViewRateText?.setOnClickListener(
            l
        )
    }

}