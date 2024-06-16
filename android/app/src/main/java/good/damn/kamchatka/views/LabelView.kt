package good.damn.kamchatka.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Typeface
import android.view.View

class LabelView(
    context: Context
): View(
    context
) {

    var textColor = Color.RED
        set(v) {
            field = v
            mPaintText.color = v
        }

    var typeface = Typeface.DEFAULT
        set(v) {
            field = v
            mPaintText.typeface = v
        }

    var text = "0"

    var cornerRadius = 1f

    private val mPaintText = Paint()
    private val mPaintBack = Paint()

    private val mRectView = RectF()

    private var mTextX = 0f
    private var mTextY = 0f

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        mRectView.top = 0f
        mRectView.left = 0f
        mRectView.right = width.toFloat()
        mRectView.bottom = height.toFloat()

        val textSize = height * 0.7142f

        mPaintText.textSize = textSize

        mTextX = (width - mPaintText.measureText(text)) * 0.5f
        mTextY = textSize + (height - textSize) * 0.5f
    }

    override fun onDraw(
        canvas: Canvas
    ) {
        super.onDraw(canvas)
        canvas.drawRoundRect(
            mRectView,
            cornerRadius,
            cornerRadius,
            mPaintBack
        )

        canvas.drawText(
            text,
            mTextX,
            mTextY,
            mPaintText
        )
    }

    override fun setBackgroundColor(
        color: Int
    ) {
        mPaintBack.color = color
        super.setBackgroundColor(
            Color.TRANSPARENT
        )
    }

}