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

class CardState(
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

    var state = ""
        set(v) {
            mTextViewState.text = v
            field = v
        }

    var drawableEnd: Drawable? = null
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
    private val mTextViewState = AppCompatTextView(
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
                    0.3f
                )
            )
            typeface = mTextViewTitle.typeface
        }

        mTextViewState.apply {
            setTextColorId(
                R.color.accentColor
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
            mTextViewState
        )

        addView(
            mImageView
        )
    }

    fun layoutIt() {
        val w = width()
        val h = height()


        mTextViewTitle.apply {
            setTextPx(h * 0.1312f)
            boundsFrame(
                Gravity.START,
                left = w * 0.1351f,
                top = h * 0.2424f
            )
        }

        mTextViewSubtitle.apply {
            setTextPx(mTextViewTitle.textSize * 0.8519f)
            boundsFrame(
                Gravity.START,
                left = mTextViewTitle.left(),
                top = mTextViewTitle.top() + mTextViewTitle.textSizeBounds()
            )
        }

        mTextViewState.apply {
            setTextPx(h * 0.08409f)
            boundsFrame(
                Gravity.START,
                left = mTextViewSubtitle.left(),
                top = h * 0.659f
            )
        }

        mImageView.apply {
            val size = (h * 0.2803f).toInt()
            boundsFrameRight(
                Gravity.END,
                width = size,
                height = size,
                top = h * 0.303f,
                right = w * 0.123f
            )
        }

    }

}