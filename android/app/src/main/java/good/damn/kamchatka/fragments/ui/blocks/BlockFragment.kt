package good.damn.kamchatka.fragments.ui.blocks

import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.widget.AppCompatImageView
import good.damn.kamchatka.Application
import good.damn.kamchatka.R
import good.damn.kamchatka.extensions.bottom
import good.damn.kamchatka.extensions.boundsFrame
import good.damn.kamchatka.extensions.height
import good.damn.kamchatka.extensions.setImageDrawableId
import good.damn.kamchatka.extensions.setTextColorId
import good.damn.kamchatka.extensions.setTextPx
import good.damn.kamchatka.extensions.size
import good.damn.kamchatka.extensions.textSizeBounds
import good.damn.kamchatka.extensions.top
import good.damn.kamchatka.fragments.StackFragment
import good.damn.kamchatka.utils.ViewUtils
import good.damn.kamchatka.views.button.ButtonBack

abstract class BlockFragment
: StackFragment() {

    override fun hasPreciseMeasurement(): Boolean {
        return false
    }

    @StringRes
    abstract fun onMsgVulcan(): Int

    @StringRes
    abstract fun onTitle(): Int

    @StringRes
    abstract fun onDesc(): Int

    @DrawableRes
    abstract fun onIcon(): Int

    override fun onCreateView(
        context: Context,
        measureUnit: Int
    ): View {

        // Allocating views
        val layout = FrameLayout(
            context
        )
        val btnBack = ButtonBack(
            context
        )
        val textViewTitle = TextView(
            context
        )
        val textViewDesc = TextView(
            context
        )
        val imageViewLike = AppCompatImageView(
            context
        )
        val vulkanMsg = ViewUtils.vulcanTextView(
            onMsgVulcan(),
            context,
            measureUnit
        )

        // Typeface
        textViewTitle.typeface = Application.font(
            R.font.open_sans_bold,
            context
        )
        textViewDesc.typeface = Application.font(
            R.font.nunito_regular,
            context
        )


        // Text color
        textViewTitle.setTextColorId(
            R.color.titleColor
        )
        textViewDesc.setTextColorId(
            R.color.titleColor
        )


        // Background color
        layout.setBackgroundColor(
            Application.color(
                R.color.background
            )
        )



        // Text size
        textViewTitle.setTextPx(
            measureUnit * 0.06949f
        )
        textViewDesc.setTextPx(
            measureUnit * 0.03743f
        )


        // Set up drawable
        imageViewLike.setImageDrawableId(
            onIcon()
        )



        // Text
        textViewTitle.setText(
            onTitle()
        )
        textViewDesc.setText(
            onDesc()
        )



        // Stroke color
        btnBack.setStrokeColor(
            Application.color(
                R.color.titleColor
            )
        )


        // Layout params
        val ms = ButtonBack.btnBackStart(
            measureUnit
        )
        val marginStart = ms * 1.35f
        layout.size(
            -1,
            -1
        )
        btnBack.boundsFrame(
            Gravity.START,
            size = ButtonBack.btnBackSize(
                measureUnit
            ).toInt(),
            top = ButtonBack.btnBackTop(
                measureUnit
            ),
            left = ms
        )
        textViewTitle.boundsFrame(
            Gravity.START,
            top = btnBack.bottom() + measureUnit * 0.1062f,
            left = marginStart
        )
        textViewDesc.boundsFrame(
            Gravity.START,
            top = textViewTitle.top() + textViewTitle.textSizeBounds() + measureUnit * 0.0507f,
            left = marginStart
        )
        imageViewLike.boundsFrame(
            Gravity.CENTER,
            width = (measureUnit * 0.3074f).toInt(),
            height = (measureUnit * 0.2584f).toInt()
        )
        vulkanMsg.boundsFrame(
            Gravity.START,
            top = Application.HEIGHT * 0.7442f,
            width = -1,
            left = marginStart
        )

        textViewTitle.post {
            textViewDesc.boundsFrame(
                Gravity.START,
                top = textViewTitle.top() + textViewTitle.height + measureUnit * 0.0507f,
                left = marginStart
            )
        }

        // Adding views
        layout.addView(
            btnBack
        )
        layout.addView(
            textViewTitle
        )
        layout.addView(
            textViewDesc
        )
        layout.addView(
            imageViewLike
        )
        layout.addView(
            vulkanMsg
        )

        btnBack.setOnClickListener(
            this::onClickBtnBack
        )

        return layout
    }
}

private fun BlockFragment.onClickBtnBack(
    view: View
) {
    popFragment()
}