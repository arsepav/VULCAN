package good.damn.kamchatka.fragments.ui

import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.GravityCompat
import good.damn.kamchatka.Application
import good.damn.kamchatka.R
import good.damn.kamchatka.extensions.boundsLinear
import good.damn.kamchatka.extensions.setImageDrawableId
import good.damn.kamchatka.extensions.setTextColorId
import good.damn.kamchatka.extensions.setTextPx
import good.damn.kamchatka.extensions.size
import good.damn.kamchatka.views.button.ButtonBack

class AnthropInfoFragment
: ScrollableFragment() {

    override fun onCreateContentView(
        context: Context,
        measureUnit: Int
    ): View {

        // Allocating views
        val layout = LinearLayout(
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
        val textViewDesc2 = TextView(
            context
        )
        val imageViewMap = ImageView(
            context
        )


        layout.orientation = LinearLayout
            .VERTICAL



        // Background color
        layout.setBackgroundColor(
            Application.color(
                R.color.background
            )
        )




        // Image Drawable
        imageViewMap.setImageDrawableId(
            R.drawable.anthrop_info_map
        )




        // Text
        textViewTitle.setText(
            R.string.info_force_anthropy
        )
        textViewDesc.setText(
            R.string.anthrop_kamchatka
        )
        textViewDesc2.setText(
            R.string.anthrop_kamchatka2
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
        textViewDesc2.typeface = textViewDesc
            .typeface



        // Text size
        textViewTitle.setTextPx(
            measureUnit * 0.05314f
        )
        textViewDesc.setTextPx(
            measureUnit * 0.03743f
        )
        textViewDesc2.setTextPx(
            textViewDesc.textSize
        )




        // TextColor
        textViewTitle.setTextColorId(
            R.color.titleColor
        )
        textViewDesc.setTextColorId(
            R.color.titleColor
        )
        textViewDesc2.setTextColorId(
            R.color.titleColor
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
        btnBack.boundsLinear(
            Gravity.START,
            size = ButtonBack.btnBackSize(
                measureUnit
            ).toInt(),
            top = ButtonBack.btnBackTop(
                measureUnit
            ),
            left = ms
        )
        textViewTitle.boundsLinear(
            Gravity.START,
            top = measureUnit * 0.1062f,
            left = marginStart
        )
        textViewDesc.boundsLinear(
            Gravity.START,
            top = measureUnit * 0.0797f,
            left = marginStart
        )
        textViewDesc2.boundsLinear(
            Gravity.START,
            top = measureUnit * 0.11473f,
            left = marginStart
        )
        imageViewMap.boundsLinear(
            width = -1,
            height = (measureUnit * 0.7198f).toInt(),
            top = measureUnit * 0.09661f
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
            textViewDesc2
        )
        layout.addView(
            imageViewMap
        )


        btnBack.setOnClickListener(
            this::onClickBtnBack
        )

        return layout
    }

}

private fun AnthropInfoFragment.onClickBtnBack(
    view: View
) {
    popFragment()
}