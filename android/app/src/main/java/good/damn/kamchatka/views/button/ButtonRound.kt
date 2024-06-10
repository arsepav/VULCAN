package good.damn.kamchatka.views.button

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import androidx.appcompat.widget.AppCompatButton

class ButtonRound(
    context: Context
): AppCompatButton(
    context
) {

    var cornerRadius = 1f
    private val mRectView = RectF()
    private val mPaint = Paint()

    init {
        isAllCaps = false
    }

    override fun setBackgroundColor(color: Int) {
        mPaint.color = color
        super.setBackgroundColor(
            Color.TRANSPARENT
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
}