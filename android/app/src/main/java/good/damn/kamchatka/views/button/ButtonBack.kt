package good.damn.kamchatka.views.button

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.view.MotionEvent
import android.view.View
import androidx.annotation.ColorInt
import good.damn.kamchatka.views.interactions.AnimatedTouchInteraction
import good.damn.kamchatka.views.interactions.interfaces.OnActionListener
import good.damn.kamchatka.views.interactions.interfaces.OnUpdateAnimationListener

class ButtonBack(
    context: Context
): View(
    context
), OnUpdateAnimationListener, OnActionListener {

    private val mPaint = run {
        val p = Paint()
        p.color = Color.RED
        p.style = Paint.Style.STROKE
        p.strokeCap = Paint.Cap.ROUND
        p.strokeJoin = Paint.Join.ROUND
        p
    }

    private var mPointStartX = 1f
    private var mPointStartY = 1f
    private var mPointCenterX = 1f
    private var mPointCenterY = 1f
    private var mPointEndY = 1f

    private var mOnClickListener: OnClickListener? = null
    private val mTouchInteraction = AnimatedTouchInteraction()
    
    init {
        super.setOnTouchListener(
            mTouchInteraction
        )

        mTouchInteraction.setOnActionListener(
            this
        )
        
        mTouchInteraction.setOnUpdateAnimationListener(
            this
        )

        mTouchInteraction.setDuration(
            100
        )
    }

    override fun setOnTouchListener(
        l: OnTouchListener?
    ) = Unit

    override fun setOnClickListener(
        l: OnClickListener?
    ) {
        mOnClickListener = l
    }
    
    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        mPaint.strokeWidth = width * 0.08571f

        mPointStartX = width * 0.68571f
        mPointStartY = height * 0.14285f
        mPointCenterX = width * 0.34285f
        mPointCenterY = height * 0.48571f
        mPointEndY = height * 0.828571f
    }

    override fun onDraw(
        canvas: Canvas
    ) {
        super.onDraw(canvas)

        canvas.drawLine(
            mPointStartX,
            mPointStartY,
            mPointCenterX,
            mPointCenterY,
            mPaint
        )

        canvas.drawLine(
            mPointCenterX,
            mPointCenterY,
            mPointStartX,
            mPointEndY,
            mPaint
        )
    }

    override fun onUpdateAnimation(
        animatedValue: Float
    ) {
        alpha = animatedValue
        scaleX = animatedValue
        scaleY = animatedValue
    }

    override fun onUp(
        v: View,
        action: MotionEvent
    ) {
        mOnClickListener?.onClick(
            v
        )
    }

    override fun onDown(
        v: View,
        action: MotionEvent
    ) = Unit

    fun setStrokeColor(
        @ColorInt color: Int
    ) {
        mPaint.color = color
    }

    companion object {
        fun btnBackSize(
            measureUnit: Int
        ): Float {
            return 0.0845f * measureUnit
        }

        fun btnBackTop(
            measureUnit: Int
        ): Float {
            return 0.0321f * measureUnit
        }

        fun btnBackStart(
            measureUnit: Int
        ): Float {
            return 0.0483f * measureUnit
        }
    }

}