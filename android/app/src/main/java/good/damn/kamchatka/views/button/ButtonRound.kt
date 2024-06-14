package good.damn.kamchatka.views.button

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.view.MotionEvent
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.appcompat.widget.AppCompatButton
import good.damn.kamchatka.Application
import good.damn.kamchatka.R
import good.damn.kamchatka.extensions.setTextPx
import good.damn.kamchatka.views.interactions.AnimatedTouchInteraction
import good.damn.kamchatka.views.interactions.interfaces.OnActionListener
import good.damn.kamchatka.views.interactions.interfaces.OnUpdateAnimationListener

class ButtonRound(
    context: Context
): AppCompatButton(
    context
), OnUpdateAnimationListener, OnActionListener {

    var cornerRadius = 1f
    private val mRectView = RectF()
    private val mPaint = Paint()

    private val mInteraction = AnimatedTouchInteraction()

    private var mOnClickListener: OnClickListener? = null

    init {
        isAllCaps = false
        super.setOnTouchListener(
            mInteraction
        )

        mInteraction.minValue = 0.75f
        mInteraction.maxValue = 1.0f

        mInteraction.setOnUpdateAnimationListener(
            this
        )

        mInteraction.setOnActionListener(
            this
        )
    }

    final override fun setBackgroundColor(color: Int) {
        mPaint.color = color
        super.setBackgroundColor(
            Color.TRANSPARENT
        )
    }

    final override fun setOnTouchListener(
        l: OnTouchListener?
    ) = Unit

    final override fun setOnClickListener(
        l: OnClickListener?
    ) {
        mOnClickListener = l
        super.setOnClickListener(
            null
        )
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        mRectView.bottom = height.toFloat()
        mRectView.right = width.toFloat()
    }

    override fun onDraw(
        canvas: Canvas
    ) {
        canvas.drawRoundRect(
            mRectView,
            cornerRadius,
            cornerRadius,
            mPaint
        )
        super.onDraw(canvas)
    }


    companion object {
        private const val TAG = "ButtonRound"
        fun createDefault(
            context: Context,
            heightBtn: Int,
            @StringRes textId: Int,
            @ColorRes textColorId: Int,
            @ColorRes backgroundColorId: Int
        ): ButtonRound {
            val btnRound = ButtonRound(
                context
            )

            btnRound.setTextColor(
                Application.color(
                    textColorId
                )
            )

            btnRound.setText(
                textId
            )

            btnRound.setTextPx(
                heightBtn * 0.283f
            )

            btnRound.typeface = Application.font(
                R.font.open_sans_bold,
                context
            )

            btnRound.setBackgroundColor(
                Application.color(
                    backgroundColorId
                )
            )

            btnRound.cornerRadius = heightBtn * 0.5f

            return btnRound
        }
    }

    override fun onUpdateAnimation(
        animatedValue: Float
    ) {
        alpha = animatedValue
        scaleX = animatedValue
        scaleY = animatedValue
    }

    override fun onDown(
        v: View,
        action: MotionEvent
    ) = Unit

    override fun onUp(
        v: View,
        action: MotionEvent
    ) {
        mOnClickListener?.onClick(
            v
        )
    }
}