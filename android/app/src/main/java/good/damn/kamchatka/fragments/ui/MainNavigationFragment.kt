package good.damn.kamchatka.fragments.ui

import android.content.Context
import android.content.res.ColorStateList
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import good.damn.kamchatka.Application
import good.damn.kamchatka.R
import good.damn.kamchatka.extensions.boundsFrame
import good.damn.kamchatka.fragments.StackFragment
import good.damn.kamchatka.fragments.ui.main_navigation.MapsFragment
import good.damn.kamchatka.fragments.ui.main_navigation.PlacesFragment

class MainNavigationFragment
: StackFragment(),
NavigationBarView.OnItemSelectedListener {

    private lateinit var mLayout: FrameLayout
    private lateinit var mContainer: FrameLayout
    private lateinit var mBottomNavigation: BottomNavigationView

    override fun onCreateView(
        context: Context
    ): View {
        mLayout = FrameLayout(
            context
        )

        mContainer = FrameLayout(
            context
        )

        mBottomNavigation = BottomNavigationView(
            context
        )

        mContainer.id = View.generateViewId()

        mLayout.setBackgroundColor(
            Application.color(
                R.color.background
            )
        )

        mBottomNavigation.itemTextColor = ColorStateList.valueOf(
            Application.color(
                R.color.editTextText
            )
        )

        mBottomNavigation.setOnItemSelectedListener(
            this
        )

        mBottomNavigation.setBackgroundColor(
            Application.color(
                R.color.background
            )
        )

        mBottomNavigation.boundsFrame(
            Gravity.BOTTOM,
            -1,
            -2
        )

        mBottomNavigation.inflateMenu(
            R.menu.menu_main
        )

        mLayout.addView(
            mContainer
        )

        mLayout.addView(
            mBottomNavigation
        )

        mLayout.post(
            this::onLayout
        )

        replaceFragment(
            MapsFragment()
        )

        return mLayout
    }

    override fun onNavigationItemSelected(
        menuItem: MenuItem
    ): Boolean {
        replaceFragment(
            when (menuItem.itemId) {
                R.id.menu_main_map -> MapsFragment()
                else -> PlacesFragment()
            }
        )
        return true
    }

    private fun onLayout() {
        mContainer.boundsFrame(
            Gravity.NO_GRAVITY,
            -1,
            mLayout.height - mBottomNavigation.height
        )
    }

    private fun replaceFragment(
        fragment: Fragment
    ) {
        childFragmentManager
            .beginTransaction()
            .replace(
                mContainer.id,
                fragment
            ).commit()
    }
}