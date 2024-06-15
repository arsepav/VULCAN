package good.damn.kamchatka.views.checkboxes

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.view.View
import androidx.annotation.ColorInt

class CheckBoxRound(
    context: Context
): View(
    context
) {

    var radius = 1f

    var checkBoxWidth = 1f
    var checkBoxHeight = 1f

    var isChecked = false
        set(v) {
            field = v
            invalidate()
        }

    private val mPaintCheck = Paint()
    private val mPaintCheckStroke = Paint()

    private val mRectCheck = RectF()

    init {
        mPaintCheckStroke.style = Paint.Style.STROKE
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        mRectCheck.top = 0f
        mRectCheck.left = 0f
        mRectCheck.bottom = checkBoxWidth
        mRectCheck.right = checkBoxHeight
    }

    override fun onDraw(
        canvas: Canvas
    ) {
        super.onDraw(canvas)

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