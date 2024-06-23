package good.damn.kamchatka.views.button

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import good.damn.kamchatka.Application
import good.damn.kamchatka.R
import good.damn.kamchatka.extensions.setTextColorId
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
                drawableEnd
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
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

    }

}