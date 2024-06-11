package good.damn.kamchatka.views.button

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.view.MotionEvent
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintSet.Motion
import good.damn.kamchatka.Application
import good.damn.kamchatka.R
import good.damn.kamchatka.extensions.setTextPx

class ButtonRound(
    context: Context
): AppCompatButton(
    context
), View.OnTouchListener, ValueAnimator.AnimatorUpdateListener {

    var cornerRadius = 1f
    private val mRectView = RectF()
    private val mPaint = Paint()

    private val mAnimator = ValueAnimator()

    private var mOnClickListener: OnClickListener? = null

    init {
        isAllCaps = false
        setOnTouchListener(
            this
        )

        mAnimator.duration = 350
        mAnimator.interpolator = DecelerateInterpolator()
        mAnimator.addUpdateListener(
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
    ) {
        super.setOnTouchListener(
            this
        )
    }

    final override fun setOnClickListener(
        l: OnClickListener?
    ) {
        mOnClickListener = l
        super.setOnClickListener(null)
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

    override fun onAnimationUpdate(
        animation: ValueAnimator
    ) {
        val f = animation.animatedValue as Float
        alpha = f
        scaleX = f
        scaleY = f
    }

    override fun onTouch(
        v: View?,
        event: MotionEvent?
    ): Boolean {
        if (event == null) {
            return false
        }

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                mAnimator.setFloatValues(
                    1.0f, 0.75f
                )
            }

            MotionEvent.ACTION_UP -> {
                mAnimator.setFloatValues(
                    alpha, 1.0f
                )
            }
        }

        mAnimator.start()

        return true
    }

    companion object {
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
}