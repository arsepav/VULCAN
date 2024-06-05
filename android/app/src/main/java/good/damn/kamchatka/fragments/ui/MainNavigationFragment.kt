package good.damn.kamchatka.fragments.ui

import android.content.Context
import android.content.res.ColorStateList
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import good.damn.kamchatka.Application
import good.damn.kamchatka.R
import good.damn.kamchatka.extensions.boundsFrame
import good.damn.kamchatka.fragments.StackFragment

class MainNavigationFragment
: StackFragment() {

    override fun onCreateView(
        context: Context
    ): View {
        val layout = FrameLayout(
            context
        )

        val bottomNavigation = BottomNavigationView(
            context
        )

        layout.setBackgroundColor(
            Application.color(
                R.color.background
            )
        )

        bottomNavigation.itemTextColor = ColorStateList.valueOf(
            Application.color(
                R.color.editTextText
            )
        )

        bottomNavigation.setBackgroundColor(
            Application.color(
                R.color.background
            )
        )

        bottomNavigation.boundsFrame(
            Gravity.BOTTOM,
            -1,
            -2
        )

        bottomNavigation.inflateMenu(
            R.menu.menu_main
        )

        layout.addView(
            bottomNavigation
        )

        return layout
    }

}