package good.damn.kamchatka.views.button

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
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
import good.damn.kamchatka.views.interactions.AnimatedTouchInteraction
import good.damn.kamchatka.views.interactions.interfaces.OnActionListener
import good.damn.kamchatka.views.interactions.interfaces.OnUpdateAnimationListener

class ButtonCardBig(
    context: Context
): CardView(
    context
), OnUpdateAnimationListener, OnActionListener {
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

    private val mTouchInteraction = AnimatedTouchInteraction()

    private var mOnClickListener: View.OnClickListener? = null

    init {

        mTouchInteraction.setOnUpdateAnimationListener(
            this
        )

        mTouchInteraction.setOnActionListener(
            this
        )

        mTouchInteraction.setDuration(
            350
        )

        mTouchInteraction.setInterpolator(
            AccelerateDecelerateInterpolator()
        )

        setOnTouchListener(
            mTouchInteraction
        )

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

    override fun onUpdateAnimation(
        animatedValue: Float
    ) {
        val scale = 1.0f - 0.25f * animatedValue
        scaleX = scale
        scaleY = scale
    }

    override fun onDown(
        v: View,
        event: MotionEvent
    ) = Unit

    override fun onUp(
        v: View,
        event: MotionEvent
    ) {
        mOnClickListener?.onClick(
            v
        )
    }

    override fun setOnClickListener(
        l: OnClickListener?
    ) {
        mOnClickListener = l
        super.setOnClickListener(null)
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