package good.damn.kamchatka.views.checkboxes

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Typeface
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.annotation.ColorInt
import good.damn.kamchatka.views.interactions.AnimatedTouchInteraction
import good.damn.kamchatka.views.interactions.interfaces.OnActionListener
import good.damn.kamchatka.views.interactions.interfaces.OnUpdateAnimationListener
import org.intellij.lang.annotations.JdkConstants.FontStyle

class CheckBoxRound(
    context: Context
): View(
    context
), OnActionListener {

    companion object {
        private const val TAG = "CheckBoxRound"
    }
    
    var radius = 1f

    var checkBoxWidth = 1f
    var checkBoxHeight = 1f

    var textPadding = 1f

    var text = ""

    var isChecked = false

    private val mPaintCheck = Paint()
    private val mPaintCheckStroke = Paint()
    private val mPaintText = Paint()

    private val mRectCheck = RectF()

    private var mTextY = 0f
    private var mTextX = 0f

    private val mTouchInteraction = AnimatedTouchInteraction()

    init {
        mPaintCheckStroke.style = Paint.Style.STROKE

        mPaintText.isAntiAlias = true

        super.setOnTouchListener(
            mTouchInteraction
        )

        mTouchInteraction.minValue = 1.0f
        mTouchInteraction.maxValue = 0.0f

        mTouchInteraction.setDuration(
            150
        )

        mTouchInteraction.setOnActionListener(
            this
        )
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        mRectCheck.top = 0f
        mRectCheck.left = 0f
        mRectCheck.bottom = checkBoxWidth
        mRectCheck.right = checkBoxHeight

        mTextX = mRectCheck.right + textPadding
        mTextY = mRectCheck.top + (checkBoxHeight + mPaintCheck.textSize) * 0.5f
    }

    override fun onDraw(
        canvas: Canvas
    ) {
        super.onDraw(canvas)

        canvas.drawText(
            text,
            mTextX,
            mTextY,
            mPaintText
        )

        canvas.drawRoundRect(
            mRectCheck,
            radius,
            radius,
            mPaintCheckStroke
        )

        if (isChecked) {
            canvas.drawRoundRect(
                mRectCheck,
                radius,
                radius,
                mPaintCheck
            )
        }
    }

    override fun setOnTouchListener(l: OnTouchListener?) = Unit


    override fun onDown(
        v: View,
        event: MotionEvent
    ) = Unit

    override fun onUp(
        v: View,
        event: MotionEvent
    ) {
        isChecked = !isChecked
        invalidate()
    }

    fun setStrokeWidth(
        width: Float
    ) {
        mPaintCheckStroke.strokeWidth = width
    }

    fun setTextSizePx(
        t: Float
    ) {
        mPaintText.textSize = t
    }

    fun setTypeface(
        @FontStyle f: Typeface
    ) {
        mPaintText.typeface = f
    }

    fun setStrokeColor(
        @ColorInt color: Int
    ) {
        mPaintCheckStroke.color = color
    }

    fun setFillColor(
        @ColorInt color: Int
    ) {
        mPaintCheck.color = color
        mPaintText.color = color
    }

}