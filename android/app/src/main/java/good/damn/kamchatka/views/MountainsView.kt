package good.damn.kamchatka.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Point
import android.graphics.PointF
import android.graphics.Rect
import android.graphics.RectF
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import kotlin.random.Random

class MountainsView(
    context: Context
): View(
    context
) {

    var points = arrayOf(PointF())

    private val mPaint = Paint()
    private val mClipPath = Path()

    private var mWidth = 0f
    private var mHeight = 0f

    private var mRectView = RectF()

    init {
        mPaint.color = 0xffff0000.toInt()
    }

    override fun onLayout(
        changed: Boolean,
        left: Int,
        top: Int,
        right: Int,
        bottom: Int
    ) {
        super.onLayout(changed, left, top, right, bottom)
        mRectView.right = width.toFloat()
        mRectView.bottom = height.toFloat()

        mWidth = mRectView.right
        mHeight = mRectView.bottom

        setPointsFromNormal(
            points
        )
    }

    override fun onDraw(
        canvas: Canvas
    ) {
        super.onDraw(canvas)

        mClipPath.reset()

        val beginPoint = points[0]
        mClipPath.moveTo(
            0f,
            beginPoint.y
        )

        for (point in points) {
            mClipPath.lineTo(
                point.x,
                point.y
            )
        }

        mClipPath.lineTo(
            mWidth,
            mHeight
        )

        mClipPath.lineTo(
            0f,
            mHeight
        )

        mClipPath.close()

        canvas.clipPath(
            mClipPath
        )

        canvas.drawPaint(
            mPaint
        )
    }

    fun randomizePoints() {
        val s = 12
        val deltaX = 1f / s
        var x = -deltaX

        points = Array(s) {
            x += deltaX + Random.nextFloat() / 10
            var f = Random.nextFloat()

            if (f > 0.5f) {
                f = 0.5f + Random.nextFloat() * 0.3f
            }

            PointF(
                x,
                f
            )
        }
    }

    fun setPointsFromNormal(
        normals: Array<PointF>
    ) {
        points = normals
        for (point in points) {
            point.x *= width
            point.y *= height
        }
    }

    fun setProgressColor(
        @ColorInt color: Int
    ) {
        mPaint.color = color
    }

}