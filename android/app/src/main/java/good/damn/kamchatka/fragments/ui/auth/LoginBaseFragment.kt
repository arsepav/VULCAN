package good.damn.kamchatka.fragments.ui.auth

import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import good.damn.kamchatka.Application
import good.damn.kamchatka.R
import good.damn.kamchatka.extensions.boundsFrame
import good.damn.kamchatka.fragments.StackFragment
import good.damn.kamchatka.utils.ViewUtils
import good.damn.kamchatka.views.button.ButtonBack

abstract class LoginBaseFragment
: StackFragment() {

    final override fun onCreateView(
        context: Context
    ): View {

        val width = Application.WIDTH
        val height = Application.HEIGHT

        val btnSize = (0.0845f * width)
            .toInt()

        val layout = FrameLayout(
            context
        )

        val btnBack = ButtonBack(
            context
        )

        val policy = ViewUtils.policyTerms(
            context,
            width
        )

        val contentView = onCreateContentView(
            context
        )


        // Bounds
        btnBack.boundsFrame(
            Gravity.START,
            width = btnSize,
            height = btnSize,
            top = height * 0.0321f,
            left = width * 0.0483f
        )
        policy.boundsFrame(
            Gravity.BOTTOM,
            width = -1,
            bottom = height * 0.04775f
        )

        // Coloring
        btnBack.setStrokeColor(
            Application.color(
                R.color.btnBackArrow
            )
        )

        // Alpha
        btnBack.alpha = 0.8f

        // Adding views
        layout.addView(
            btnBack
        )
        layout.addView(
            contentView
        )
        layout.addView(
            policy
        )

        return layout
    }

    abstract fun onCreateContentView(
        context: Context
    ): View

}