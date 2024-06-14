package good.damn.kamchatka.fragments.ui

import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.LinearLayout
import good.damn.kamchatka.Application
import good.damn.kamchatka.R
import good.damn.kamchatka.extensions.boundsFrame
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

private fun LikesFragment.onClickBtnBack(
    view: View
) {
    popFragment()
}