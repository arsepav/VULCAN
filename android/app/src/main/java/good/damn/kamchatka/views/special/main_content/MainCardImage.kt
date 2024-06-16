package good.damn.kamchatka.views.special.main_content

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Typeface
import android.view.MotionEvent
import android.view.View
import androidx.annotation.ColorInt
import androidx.appcompat.widget.AppCompatImageView
import good.damn.kamchatka.views.interactions.AnimatedTouchInteraction
import good.damn.kamchatka.views.interactions.interfaces.OnActionListener
import good.damn.kamchatka.views.interactions.interfaces.OnUpdateAnimationListener

class MainCardImage(
    context: Context
): AppCompatImageView(
    context
), OnUpdateAnimationListener, OnActionListener {

    var typeface = Typeface.DEFAULT
        set(v) {
            mPaintTitle.typeface = v
            mPaintSubtitle.typeface = v
            field = v
        }
    var title = ""
    var subtitle = ""
        set(v) {
            field = v
            val arr = v.split(
                "\n".toRegex()
            )
            mSubtitlePos = Array(arr.size) { i ->
                TextPosition(
                    arr[i],
                    0f,
                    0f
                )
            }
        }

    var mOnClickListener: OnClickListener? = null

    private val mTouchInteraction = AnimatedTouchInteraction()

    private var mSubtitlePos: Array<TextPosition>? = null

    private var mTitleX = 0f
    private var mTitleY = 0f

    private val mPaintTitle = Paint()
    private val mPaintSubtitle = Paint()

    init {
        mPaintTitle.isAntiAlias = true
        mPaintSubtitle.isAntiAlias = true

        mTouchInteraction.minValue = 0.0f
        mTouchInteraction.maxValue = 1.0f

        mTouchInteraction.setOnUpdateAnimationListener(
            this
        )

        mTouchInteraction.setOnActionListener(
            this
        )

        super.setOnTouchListener(
            mTouchInteraction
        )
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        mPaintTitle.textSize = height * 0.1277f
        mPaintSubtitle.textSize = height * 0.06258f

        mTitleX = (width - mPaintTitle.measureText(title)) * 0.5f
        mTitleY = height * 0.69f

        if (mSubtitlePos == null) {
            return
        }

        var y = height * 0.7428f
        val interval = mPaintSubtitle.textSize * 0.32f
        for (sub in mSubtitlePos!!) {
            val w = mPaintSubtitle.measureText(
                sub.text
            )
            y += mPaintSubtitle.textSize
            sub.x = (width - w) * 0.5f
            sub.y = y
            y += interval
        }
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

        mSubtitlePos?.let { s ->
            s.forEach {
                canvas.drawText(
                    it.text,
                    it.x,
                    it.y,
                    mPaintSubtitle
                )
            }
        }
    }

    override fun onUpdateAnimation(
        animatedValue: Float
    ) {
        scaleX = 1.0f + (1.0f-animatedValue) * 0.07f
        scaleY = scaleX
    }

    override fun onDown(v: View, event: MotionEvent) = Unit

    override fun onUp(
        v: View,
        event: MotionEvent
    ) {
        if (event.action == MotionEvent.ACTION_UP) {
            mOnClickListener?.onClick(
                v
            )
        }
    }

    fun setTextColor(
        @ColorInt color: Int
    ) {
        mPaintTitle.color = color
        mPaintSubtitle.color = color
    }



    private data class TextPosition(
        var text: String,
        var x: Float,
        var y: Float
    )

}