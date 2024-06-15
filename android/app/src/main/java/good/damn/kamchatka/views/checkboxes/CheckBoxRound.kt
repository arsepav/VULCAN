package good.damn.kamchatka.views.checkboxes

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Typeface
import android.view.View
import androidx.annotation.ColorInt
import org.intellij.lang.annotations.JdkConstants.FontStyle

class CheckBoxRound(
    context: Context
): View(
    context
) {

    var radius = 1f

    var checkBoxWidth = 1f
    var checkBoxHeight = 1f

    var textPadding = 1f

    var text = ""

    var isChecked = false

    private val mPaintCheck = Paint()
    private val mPaintCheckStroke = Paint()

    private val mRectCheck = RectF()

    private var mTextY = 0f
    private var mTextX = 0f

    init {
        mPaintCheckStroke.style = Paint.Style.STROKE
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
            mPaintCheck
        )

        if (isChecked) {
            canvas.drawRoundRect(
                mRectCheck,
                radius,
                radius,
                mPaintCheck
            )
            return
        }

        canvas.drawRoundRect(
            mRectCheck,
            radius,
            radius,
            mPaintCheckStroke
        )

    }

    fun setStrokeWidth(
        width: Float
    ) {
        mPaintCheckStroke.strokeWidth = width
    }

    fun setTextSizePx(
        t: Float
    ) {
        mPaintCheck.textSize = t
    }

    fun setTypeface(
        @FontStyle f: Typeface
    ) {
        mPaintCheck.typeface = f
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
    }

}