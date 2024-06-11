package good.damn.kamchatka.views.button

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.appcompat.widget.AppCompatButton
import good.damn.kamchatka.Application
import good.damn.kamchatka.R
import good.damn.kamchatka.extensions.setTextPx

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

    companion object {
        fun createDefault(
            context: Context,
            heightBtn: Int,
            @StringRes textId: Int,
            @ColorRes textColorId: Int,
            @ColorRes backgroundColorId: Int
        ): ButtonRound {
            val btnRound = ButtonRound(
                context
            )

            btnRound.setTextColor(
                Application.color(
                    textColorId
                )
            )

            btnRound.setText(
                textId
            )

            btnRound.setTextPx(
                heightBtn * 0.283f
            )

            btnRound.typeface = Application.font(
                R.font.open_sans_bold,
                context
            )

            btnRound.setBackgroundColor(
                Application.color(
                    backgroundColorId
                )
            )

            btnRound.cornerRadius = heightBtn * 0.5f

            return btnRound
        }
    }
}