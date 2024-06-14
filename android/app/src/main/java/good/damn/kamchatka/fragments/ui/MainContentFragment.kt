package good.damn.kamchatka.fragments.ui

import android.content.Context
import android.content.res.ColorStateList
import android.os.Bundle
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
import good.damn.kamchatka.extensions.setTextPx
import good.damn.kamchatka.extensions.top
import good.damn.kamchatka.fragments.StackFragment
import good.damn.kamchatka.fragments.ui.main_content.MapsFragment
import good.damn.kamchatka.fragments.ui.main_content.PlacesFragment
import good.damn.kamchatka.views.RoundedImageView

class MainContentFragment
: ScrollableFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigationBarColor(
            Application.color(
                R.color.background
            )
        )
    }

    override fun onCreateContentView(
        context: Context,
        measureUnit: Int
    ): View {

        val btnProfileWidth = measureUnit * 0.0748f

        // Allocating views
        val layout = FrameLayout(
            context
        )
        val textViewAppName = TextView(
            context
        )
        val btnRoundProfile = RoundedImageView(
            context
        )


        // Image drawable
        btnRoundProfile.imageView.setImageDrawable(
            Application.drawable(
                R.mipmap.ic_launcher
            )
        )


        // CornerRadius
        btnRoundProfile.radius = btnProfileWidth * 0.5f

        // Text size
        textViewAppName.setTextPx(
            measureUnit * 0.0507f
        )



        // Font
        textViewAppName.typeface = Application.font(
            R.font.open_sans_bold,
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
        btnRoundProfile.boundsFrame(
            Gravity.START,
            width = btnProfileWidth.toInt(),
            height = btnProfileWidth.toInt(),
            left = measureUnit * 0.0700f,
            top = measureUnit * 0.0966f
        )
        textViewAppName.boundsFrame(
            Gravity.CENTER_HORIZONTAL,
            top = btnRoundProfile.top()
        )



        // Adding views
        layout.addView(
            btnRoundProfile
        )
        layout.addView(
            textViewAppName
        )

        return layout
    }

}