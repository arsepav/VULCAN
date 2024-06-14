package good.damn.kamchatka.fragments.ui

import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import good.damn.kamchatka.Application
import good.damn.kamchatka.R
import good.damn.kamchatka.extensions.boundsLinear
import good.damn.kamchatka.fragments.StackFragment
import good.damn.kamchatka.views.button.ButtonBack

class AnthropInfoFragment
: StackFragment() {

    override fun onCreateView(
        context: Context,
        measureUnit: Int
    ): View {

        val layout = LinearLayout(
            context
        )

        val btnBack = ButtonBack(
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



        // Stroke color
        btnBack.setStrokeColor(
            Application.color(
                R.color.titleColor
            )
        )



        // Layout params
        btnBack.boundsLinear(
            Gravity.START,
            size = ButtonBack.btnBackSize(
                measureUnit
            ).toInt(),
            top = ButtonBack.btnBackTop(
                measureUnit
            ),
            left = ButtonBack.btnBackStart(
                measureUnit
            )
        )




        // Adding views
        layout.addView(
            btnBack
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