package good.damn.kamchatka.views.button

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.Gravity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import good.damn.kamchatka.Application
import good.damn.kamchatka.R
import good.damn.kamchatka.extensions.boundsFrame
import good.damn.kamchatka.extensions.boundsFrameRight
import good.damn.kamchatka.extensions.height
import good.damn.kamchatka.extensions.left
import good.damn.kamchatka.extensions.setImageDrawableId
import good.damn.kamchatka.extensions.setTextColorId
import good.damn.kamchatka.extensions.setTextPx
import good.damn.kamchatka.extensions.textSizeBounds
import good.damn.kamchatka.extensions.top
import good.damn.kamchatka.extensions.width
import good.damn.kamchatka.models.Color

class ButtonCard(
    context: Context
): CardView(
    context
) {
    var title = ""
        set(v) {
            mTextViewTitle.text = v
            field = v
        }

    var drawableEnd: Drawable? = null
        set(v) {
            mImageView.setImageDrawable(
                v
            )
            field = v
        }

    var scaleYImage: Float = 1.0f
        set(v) {
            field = v
            mImageView.scaleY = scaleYImage
        }

    private val mTextViewTitle = AppCompatTextView(
        context
    )
    private val mImageView = AppCompatImageView(
        context
    )

    init {
        mTextViewTitle.apply {
            setTextColorId(R.color.titleColor)
            typeface = Application.font(
                R.font.open_sans_semi_bold,
                context
            )
        }

        addView(
            mTextViewTitle
        )

        addView(
            mImageView
        )
    }

    fun layoutIt() {
        val w = width()
        val h = height()


        mTextViewTitle.apply {
            setTextPx(h * 0.1828f)
            boundsFrame(
                Gravity.START or Gravity.CENTER_VERTICAL,
                left = w * 0.1351f
            )
        }

        mImageView.apply {
            val size = (h * 0.4213f).toInt()
            boundsFrameRight(
                Gravity.END or Gravity.CENTER_VERTICAL,
                width = size,
                height = size,
                right = w * 0.123f
            )
        }

    }

}