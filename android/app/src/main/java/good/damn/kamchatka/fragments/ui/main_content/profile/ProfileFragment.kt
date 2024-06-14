package good.damn.kamchatka.fragments.ui.main_content.profile

import android.content.Context
import android.view.View
import android.widget.FrameLayout
import good.damn.kamchatka.Application
import good.damn.kamchatka.R
import good.damn.kamchatka.fragments.StackFragment

class ProfileFragment
: StackFragment() {

    override fun onCreateView(
        context: Context,
        measureUnit: Int
    ): View {

        // Allocating views
        val layout = FrameLayout(
            context
        )

        layout.setBackgroundColor(
            Application.color(
                R.color.background
            )
        )


        return layout
    }


}