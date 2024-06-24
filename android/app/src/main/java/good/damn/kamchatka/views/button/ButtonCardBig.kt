package good.damn.kamchatka.views.button

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.Gravity
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import good.damn.kamchatka.Application
import good.damn.kamchatka.R
import good.damn.kamchatka.extensions.boundsFrame
import good.damn.kamchatka.extensions.boundsFrameRight
import good.damn.kamchatka.extensions.height
import good.damn.kamchatka.extensions.left
import good.damn.kamchatka.extensions.setTextColorId
import good.damn.kamchatka.extensions.setTextPx
import good.damn.kamchatka.extensions.size
import good.damn.kamchatka.extensions.textSizeBounds
import good.damn.kamchatka.extensions.top
import good.damn.kamchatka.extensions.width
import good.damn.kamchatka.models.Color

class ButtonCardBig(
    context: Context
): CardView(
    context
) {
    var title = ""
        set(v) {
            mTextViewTitle.text = v
            field = v
        }

    var subtitle = ""
        set(v) {
            mTextViewSubtitle.text = v
            field = v
        }

    var drawableStart: Drawable? = null
        set(v) {
            mImageView.setImageDrawable(
                v
            )
            field = v
        }

    private val mTextViewTitle = AppCompatTextView(
        context
    )
    private val mTextViewSubtitle = AppCompatTextView(
        context
    )
    private val mImageView = AppCompatImageView(
        context
    )

    init {
        mTextViewTitle.apply {
            setTextColorId(R.color.titleColor)
            typeface = Application.font(
                R.font.open_sans_bold,
                context
            )
        }

        mTextViewSubtitle.apply {
            setTextColor(
                Color.parseFromHexId(
                    R.color.titleColor,
                    0.5f
                )
            )
            typeface = Application.font(
                R.font.open_sans_regular,
                context
            )
        }

        addView(
            mTextViewTitle
        )

        addView(
            mTextViewSubtitle
        )

        addView(
            mImageView
        )
    }

    fun setDrawableEndId(
        @DrawableRes id: Int
    ) {
        drawableStart = Application.drawable(
            id
        )
    }

    fun layoutIt() {
        val w = width()
        val h = height()

        mImageView.apply {
            val size = (h * 0.36956f).toInt()
            boundsFrame(
                Gravity.START or Gravity.CENTER_VERTICAL,
                width = size,
                height = size,
                left = w * 0.05585f
            )
        }

        mTextViewTitle.apply {
            setTextPx(h * 0.17184f)
            boundsFrame(
                Gravity.START,
                left = mImageView.left() * 2 + mImageView.width(),
                top = h * 0.2826f,
            )
        }

        mTextViewSubtitle.apply {
            setTextPx(mTextViewTitle.textSize * 0.84946f)
            boundsFrame(
                Gravity.START,
                left = mTextViewTitle.left(),
                top = mTextViewTitle.top() + mTextViewTitle.textSizeBounds() + h * 0.05413f
            )
        }

    }

}