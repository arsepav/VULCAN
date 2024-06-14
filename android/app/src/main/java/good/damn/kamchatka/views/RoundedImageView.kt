package good.damn.kamchatka.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.graphics.drawable.Drawable
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.annotation.ColorInt

class RoundedImageView(
    context: Context
): AppCompatImageView(
    context
) {
    var radius = 1f

    private val mPaintStroke = Paint()
    private val mPaintStrokeOffset = Paint()

    private val mRectView = RectF()

    private val mRectRound = RectF()
    private val mRectRoundOffset = RectF()

    private val mPath = Path()

    private var mImageScaleX = 0f
    private var mImageScaleY = 0f

    init {
        mPaintStroke.color = 0
        mPaintStrokeOffset.color = 0

        mPaintStroke.style = Paint.Style.STROKE
        mPaintStrokeOffset.style = Paint.Style.STROKE
    }


    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        mRectView.bottom = height.toFloat()
        mRectView.right = width.toFloat()


        mPaintStroke.strokeWidth = width * 0.08878f
        mPaintStrokeOffset.strokeWidth = width * 0.1151f

        val hsw = mPaintStroke.strokeWidth * 0.5f
        val hswo = mPaintStrokeOffset.strokeWidth * 0.5f

        mRectRound.top = hsw
        mRectRound.bottom = height - hsw
        mRectRound.left = hsw
        mRectRound.right = width - hsw

        mRectRoundOffset.top = hswo + hsw
        mRectRoundOffset.bottom = mRectRound.bottom - hswo
        mRectRoundOffset.left = hswo + hsw
        mRectRoundOffset.right = mRectRound.right - hswo

        val xOffset = (width * mImageScaleX * 0.5f)
            .toInt()

        val yOffset = (height * mImageScaleY * 0.5f)
            .toInt()

        drawable?.setBounds(
            xOffset,
            yOffset,
            width-xOffset,
            height-yOffset
        )
    }


    override fun onDraw(
        canvas: Canvas
    ) {
        mPath.reset()
        mPath.addRoundRect(
            mRectRound,
            radius,
            radius,
            Path.Direction.CW
        )
        mPath.close()

        canvas.clipPath(
            mPath
        )

        drawable.draw(
            canvas
        )

        if (mPaintStrokeOffset.color != 0) {
            canvas.drawRoundRect(
                mRectRoundOffset,
                radius,
                radius,
                mPaintStrokeOffset
            )
        }

        if (mPaintStroke.color != 0) {
            canvas.drawRoundRect(
                mRectRound,
                radius,
                radius,
                mPaintStroke
            )
        }
    }

    fun setImageScale(
        x: Float,
        y: Float
    ) {
        mImageScaleX = x
        mImageScaleY = y
        requestLayout()
    }

    fun setStrokeOffsetColor(
        @ColorInt color: Int
    ) {
        mPaintStrokeOffset.color = color
    }

    fun setStrokeAlpha(
        alpha: Float
    ) {
        mPaintStroke.alpha = (alpha * 255).toInt()
    }

    fun setStrokeColor(
        @ColorInt color: Int
    ) {
        mPaintStroke.color = color
    }

}