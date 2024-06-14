package good.damn.kamchatka.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.Typeface
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

    var textColor = Color.RED
    var textSize = 0.2f

    var typeface = Typeface.DEFAULT
        set(v) {
            mPaint.typeface = v
            field = v
        }

    private val mPaint = Paint()

    private val mRect = Rect()
    private var mInterval = 0
    private var mTextY = 0f

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        mInterval = width / colors.size

        mPaint.textSize = height * textSize
        mRect.bottom = (height * 0.5635f).toInt()

        mTextY = height.toFloat()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        var current = 0
        for (i in colors.indices) {

            mRect.left = current
            mRect.right = current + mInterval

            mPaint.color = textColor
            val t = "${i+1}"
            canvas.drawText(
                t,
                mRect.left + (mRect.right - mRect.left - mPaint.measureText(t)) * 0.5f,
                mTextY,
                mPaint
            )


            mPaint.color = colors[i]
            canvas.drawRect(
                mRect,
                mPaint
            )

            current += mInterval
        }
    }

}