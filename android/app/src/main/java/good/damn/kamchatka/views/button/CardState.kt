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
import good.damn.kamchatka.extensions.setImageDrawableId
import good.damn.kamchatka.extensions.setTextColorId
import good.damn.kamchatka.extensions.setTextPx
import good.damn.kamchatka.extensions.textSizeBounds
import good.damn.kamchatka.extensions.top
import good.damn.kamchatka.extensions.width
import good.damn.kamchatka.models.Color
import good.damn.kamchatka.views.interactions.AnimatedTouchInteraction
import good.damn.kamchatka.views.interactions.interfaces.OnActionListener
import good.damn.kamchatka.views.interactions.interfaces.OnUpdateAnimationListener

class CardState(
    context: Context
): CardView(
    context
), OnUpdateAnimationListener,
OnActionListener {
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
            mTextViewState.text = "${context.getString(R.string.state)}: $v"
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
            150
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

        mTextViewState.isAllCaps = true

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

    fun setDrawableEndId(
        @DrawableRes id: Int
    ) {
        drawableEnd = Application.drawable(
            id
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

    override fun setOnClickListener(
        l: OnClickListener?
    ) {
        mOnClickListener = l
        super.setOnClickListener(null)
    }

    override fun onUpdateAnimation(
        animatedValue: Float
    ) {
        val scale = 0.65f + 0.35f * animatedValue
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

}