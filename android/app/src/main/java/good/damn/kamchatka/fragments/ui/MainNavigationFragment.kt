package good.damn.kamchatka.fragments.ui

import android.content.Context
import android.content.res.ColorStateList
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import good.damn.kamchatka.Application
import good.damn.kamchatka.R
import good.damn.kamchatka.adapters.fragment_adapters.FragmentAdapter
import good.damn.kamchatka.extensions.boundsFrame
import good.damn.kamchatka.fragments.StackFragment
import good.damn.kamchatka.fragments.ui.main_navigation.MapsFragment
import good.damn.kamchatka.fragments.ui.main_navigation.PlacesFragment

class MainNavigationFragment
: StackFragment(),
NavigationBarView.OnItemSelectedListener {

    private lateinit var mLayout: FrameLayout
    private lateinit var mViewPager: ViewPager2
    private lateinit var mBottomNavigation: BottomNavigationView

    private val mFragments = arrayOf(
        MapsFragment(),
        PlacesFragment()
    )

    override fun onCreateView(
        context: Context
    ): View {
        mLayout = FrameLayout(
            context
        )

        mViewPager = ViewPager2(
            context
        )

        mBottomNavigation = BottomNavigationView(
            context
        )

        mViewPager.id = View.generateViewId()
        mViewPager.adapter = FragmentAdapter(
            childFragmentManager,
            lifecycle,
            mFragments
        )
        mViewPager.isUserInputEnabled = false

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
            mViewPager
        )

        mLayout.addView(
            mBottomNavigation
        )

        mLayout.post(
            this::onLayout
        )

        mViewPager.currentItem = 0

        return mLayout
    }

    override fun onNavigationItemSelected(
        menuItem: MenuItem
    ): Boolean {

        mViewPager.currentItem = when (
            menuItem.itemId
        ) {
            R.id.menu_main_map -> 0
            else -> 1
        }

        return true
    }

    private fun onLayout() {
        mViewPager.boundsFrame(
            Gravity.NO_GRAVITY,
            -1,
            mLayout.height - mBottomNavigation.height
        )
    }
}