package good.damn.kamchatka.fragments.ui

import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import good.damn.kamchatka.Application
import good.damn.kamchatka.R
import good.damn.kamchatka.extensions.boundsFrame
import good.damn.kamchatka.extensions.setImageDrawableId
import good.damn.kamchatka.extensions.setTextColorId
import good.damn.kamchatka.extensions.setTextPx
import good.damn.kamchatka.extensions.size
import good.damn.kamchatka.fragments.StackFragment
import good.damn.kamchatka.views.button.ButtonBack

class LikesFragment
: StackFragment() {

    override fun hasPreciseMeasurement(): Boolean {
        return false
    }

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


        // Text size
        textViewTitle.setTextPx(
            measureUnit * 0.06949f
        )
        textViewDesc.setTextPx(
            measureUnit * 0.03743f
        )


        // Set up drawable
        imageViewLike.setImageDrawableId(
            R.drawable.ic_like_pink
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
            top = measureUnit * 0.1062f,
            left = marginStart
        )
        textViewDesc.boundsFrame(
            Gravity.START,
            top = measureUnit * 0.0507f,
            left = marginStart
        )
        imageViewLike.boundsFrame(
            Gravity.CENTER,
            width = (measureUnit * 0.3074f).toInt(),
            height = (measureUnit * 0.2584f).toInt()
        )


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

        btnBack.setOnClickListener(
            this::onClickBtnBack
        )

        return layout
    }
}

private fun LikesFragment.onClickBtnBack(
    view: View
) {
    popFragment()
}