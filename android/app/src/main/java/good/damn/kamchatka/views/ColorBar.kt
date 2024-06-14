package good.damn.kamchatka.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View

class ColorBar(
    context: Context
): View(
    context
) {

    var colors: IntArray = intArrayOf(
        0xffff0000.toInt(),
        0xff00ff00.toInt()
    )

    private val mPaint = Paint()

    private val mRect = Rect()
    private var mInterval = 0

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        mInterval = width / colors.size

        mRect.bottom = height
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        var current = 0
        for (color in colors) {
            mPaint.color = color
            mRect.left = current
            mRect.right = current + mInterval
            canvas.drawRect(
                mRect,
                mPaint
            )
            current += mInterval
        }
    }

}