package good.damn.kamchatka.views.special.main_content

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Typeface
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.appcompat.widget.AppCompatImageView

class MainCardImage(
    context: Context
): AppCompatImageView(
    context
) {

    var typeface = Typeface.DEFAULT
        set(v) {
            mPaintTitle.typeface = v
            mPaintSubtitle.typeface = v
            field = v
        }
    var title: String = ""
    var subtitle: Array<String> = arrayOf()

    private var mTitleX = 0f
    private var mTitleY = 0f

    private val mPaintTitle = Paint()
    private val mPaintSubtitle = Paint()

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        mPaintTitle.textSize = height * 0.1277f
        mPaintSubtitle.textSize = height * 0.06258f

        mTitleX = (width - mPaintTitle.measureText(title)) * 0.5f
        mTitleY = height * 0.69f
    }

    override fun onDraw(
        canvas: Canvas
    ) {
        super.onDraw(canvas)

        canvas.drawText(
            title,
            mTitleX,
            mTitleY,
            mPaintTitle
        )
    }


    fun setTextColor(
        @ColorInt color: Int
    ) {
        mPaintTitle.color = color
        mPaintSubtitle.color = color
    }
}