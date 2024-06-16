package good.damn.kamchatka.views.text_fields

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.Log
import android.view.Gravity
import androidx.annotation.ColorInt

open class TextFieldRound(
    context: Context
): TextField(
    context
) {

    companion object {
        private const val TAG = "TextFieldRound"
    }

    var cornerRadius = 1f

    private val mPaint = Paint()
    private val mRectView = RectF()

    init {
        background = null
        mPaint.color = Color.RED
        mPaint.isAntiAlias = true
        mPaint.style = Paint.Style.STROKE
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        val halfStrokeWidth = mPaint.strokeWidth * 0.5f

        mRectView.top = halfStrokeWidth
        mRectView.left = halfStrokeWidth
        mRectView.bottom = height.toFloat() - halfStrokeWidth
        mRectView.right = width.toFloat() - halfStrokeWidth

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.translate(
            scrollX.toFloat(),
            scrollY.toFloat()
        )

        canvas.drawRoundRect(
            mRectView,
            cornerRadius,
            cornerRadius,
            mPaint
        )
    }

    fun setStrokeWidth(
        width: Float
    ) {
        mPaint.strokeWidth = width
    }

    fun setStrokeColor(
        @ColorInt color: Int
    ) {
        mPaint.color = color
    }

}