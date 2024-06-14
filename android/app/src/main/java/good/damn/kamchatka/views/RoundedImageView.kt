package good.damn.kamchatka.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.graphics.drawable.Drawable
import android.view.MotionEvent
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.appcompat.widget.AppCompatImageView
import androidx.annotation.ColorInt
import good.damn.kamchatka.views.interactions.AnimatedTouchInteraction
import good.damn.kamchatka.views.interactions.interfaces.OnActionListener
import good.damn.kamchatka.views.interactions.interfaces.OnUpdateAnimationListener

class RoundedImageView(
    context: Context
): AppCompatImageView(
    context
), OnActionListener,
OnUpdateAnimationListener {

    var radius = 1f

    private val mPaintStroke = Paint()
    private val mPaintStrokeOffset = Paint()

    private val mRectView = RectF()

    private val mRectRound = RectF()
    private val mRectRoundOffset = RectF()

    private val mPath = Path()

    private var mImageScaleX = 0f
    private var mImageScaleY = 0f

    private val mTouchInteraction = AnimatedTouchInteraction()
    private var mOnClickListener: OnClickListener? = null

    init {
        mPaintStroke.color = 0
        mPaintStrokeOffset.color = 0

        mPaintStroke.style = Paint.Style.STROKE
        mPaintStrokeOffset.style = Paint.Style.STROKE

        mTouchInteraction.minValue = 1.0f
        mTouchInteraction.maxValue = 0.0f

        mTouchInteraction.setInterpolator(
            OvershootInterpolator()
        )

        mTouchInteraction.setDuration(
            190
        )

        mTouchInteraction.setOnUpdateAnimationListener(
            this
        )

        mTouchInteraction.setOnActionListener(
            this
        )

        super.setOnTouchListener(
            mTouchInteraction
        )
    }


    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        mRectView.bottom = height.toFloat()
        mRectView.right = width.toFloat()


        mPaintStroke.strokeWidth = width * 0.08878f
        mPaintStrokeOffset.strokeWidth = width * 0.1151f

        val hsw = mPaintStroke.strokeWidth * 0.5f
        val hswo = mPaintStrokeOffset.strokeWidth * 0.5f

        mRectRound.top = hsw
        mRectRound.bottom = height - hsw
        mRectRound.left = hsw
        mRectRound.right = width - hsw

        mRectRoundOffset.top = hswo + hsw
        mRectRoundOffset.bottom = mRectRound.bottom - hswo
        mRectRoundOffset.left = hswo + hsw
        mRectRoundOffset.right = mRectRound.right - hswo

        val xOffset = (width * mImageScaleX * 0.5f)
            .toInt()

        val yOffset = (height * mImageScaleY * 0.5f)
            .toInt()

        drawable?.setBounds(
            xOffset,
            yOffset,
            width-xOffset,
            height-yOffset
        )
    }


    override fun onDraw(
        canvas: Canvas
    ) {
        mPath.reset()
        mPath.addRoundRect(
            mRectRound,
            radius,
            radius,
            Path.Direction.CW
        )
        mPath.close()

        canvas.clipPath(
            mPath
        )

        drawable.draw(
            canvas
        )

        if (mPaintStrokeOffset.color != 0) {
            canvas.drawRoundRect(
                mRectRoundOffset,
                radius,
                radius,
                mPaintStrokeOffset
            )
        }

        if (mPaintStroke.color != 0) {
            canvas.drawRoundRect(
                mRectRound,
                radius,
                radius,
                mPaintStroke
            )
        }
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

    override fun onUpdateAnimation(
        animatedValue: Float
    ) {
        scaleX = 1.0f + animatedValue * 0.25f
        scaleY = scaleX
    }

    override fun setOnClickListener(
        l: OnClickListener?
    ) {
        mOnClickListener = l
    }

    override fun setOnTouchListener(
        l: OnTouchListener?
    ) = Unit

    fun setImageScale(
        x: Float,
        y: Float
    ) {
        mImageScaleX = x
        mImageScaleY = y
        requestLayout()
    }

    fun setStrokeOffsetColor(
        @ColorInt color: Int
    ) {
        mPaintStrokeOffset.color = color
    }

    fun setStrokeAlpha(
        alpha: Float
    ) {
        mPaintStroke.alpha = (alpha * 255).toInt()
    }

    fun setStrokeColor(
        @ColorInt color: Int
    ) {
        mPaintStroke.color = color
    }

}