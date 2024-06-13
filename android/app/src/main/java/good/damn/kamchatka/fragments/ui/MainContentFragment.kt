package good.damn.kamchatka.fragments.ui

import android.content.Context
import android.content.res.ColorStateList
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import good.damn.kamchatka.Application
import good.damn.kamchatka.R
import good.damn.kamchatka.adapters.fragment_adapters.FragmentAdapter
import good.damn.kamchatka.extensions.boundsFrame
import good.damn.kamchatka.fragments.StackFragment
import good.damn.kamchatka.fragments.ui.main_content.MapsFragment
import good.damn.kamchatka.fragments.ui.main_content.PlacesFragment

class MainContentFragment
: ScrollableFragment() {

    override fun onCreateContentView(
        context: Context,
        measureUnit: Int
    ): View {

        // Allocating views
        val layout = FrameLayout(
            context
        )
        val textViewAppName = TextView(
            context
        )



        // Font
        textViewAppName.typeface = Application.font(
            R.font.open_sans_extra_bold,
            context
        )


        // Text
        textViewAppName.setText(
            R.string.app_name
        )



        // Text color
        layout.setBackgroundColor(
            Application.color(
                R.color.background
            )
        )
        textViewAppName.setTextColor(
            Application.color(
                R.color.titleColor
            )
        )






        // Layout params
        textViewAppName.boundsFrame(
            Gravity.CENTER_HORIZONTAL
        )

        layout.addView(
            textViewAppName
        )

        return layout
    }

}