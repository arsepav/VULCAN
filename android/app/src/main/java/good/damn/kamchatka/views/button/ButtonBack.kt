package good.damn.kamchatka.views.button

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.graphics.RectF
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import androidx.annotation.ColorInt
import good.damn.kamchatka.Application
import good.damn.kamchatka.R
import good.damn.kamchatka.extensions.boundsFrame
import good.damn.kamchatka.extensions.boundsLinear
import good.damn.kamchatka.views.interactions.AnimatedTouchInteraction
import good.damn.kamchatka.views.interactions.interfaces.OnActionListener
import good.damn.kamchatka.views.interactions.interfaces.OnUpdateAnimationListener

class ButtonBack(
    context: Context
): View(
    context
), OnUpdateAnimationListener, OnActionListener {

    var cornerRadius = 0f

    private val mPaint = run {
        val p = Paint()
        p.color = Color.RED
        p.style = Paint.Style.STROKE
        p.strokeCap = Paint.Cap.ROUND
        p.strokeJoin = Paint.Join.ROUND
        p
    }

    private val mRectView = RectF()

    private val mPaintBack = Paint()

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

    override fun setBackgroundColor(
        color: Int
    ) {
        mPaintBack.color = color
        super.setBackgroundColor(0)
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

        mRectView.top = 0f
        mRectView.left = 0f
        mRectView.bottom = height.toFloat()
        mRectView.right = width.toFloat()
    }

    override fun onDraw(
        canvas: Canvas
    ) {
        super.onDraw(canvas)

        if (mPaintBack.color != 0) {
            canvas.drawRoundRect(
                mRectView,
                cornerRadius,
                cornerRadius,
                mPaintBack
            )
        }

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
        event: MotionEvent
    ) {
        mOnClickListener?.onClick(
            v
        )
    }

    override fun onDown(
        v: View,
        event: MotionEvent
    ) = Unit

    fun setStrokeColor(
        @ColorInt color: Int
    ) {
        mPaint.color = color
    }

    companion object {

        fun createDefaultLinear(
            measureUnit: Int,
            @ColorInt strokeColor: Int,
            context: Context
        ): ButtonBack {
            val b = createDefault(
                strokeColor,
                context
            )

            b.boundsLinear(
                Gravity.START,
                size = btnBackSize(
                    measureUnit
                ).toInt(),
                top = btnBackTop(
                    measureUnit
                ),
                left = btnBackStart(
                    measureUnit
                )
            )

            return b
        }

        fun createDefaultFrame(
            measureUnit: Int,
            @ColorInt strokeColor: Int,
            context: Context
        ): ButtonBack {
            val b = createDefault(
                strokeColor,
                context
            )

            b.boundsFrame(
                Gravity.START,
                size = btnBackSize(
                    measureUnit
                ).toInt(),
                top = btnBackTop(
                    measureUnit
                ),
                left = btnBackStart(
                    measureUnit
                )
            )

            return b
        }

        fun createDefault(
            @ColorInt strokeColor: Int,
            context: Context
        ): ButtonBack {
            val b = ButtonBack(
                context
            )

            b.setStrokeColor(
                strokeColor
            )

            return b
        }

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