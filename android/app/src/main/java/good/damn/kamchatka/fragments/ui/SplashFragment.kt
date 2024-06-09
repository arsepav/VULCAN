package good.damn.kamchatka.fragments.ui

import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import good.damn.kamchatka.Application
import good.damn.kamchatka.R
import good.damn.kamchatka.extensions.boundsLinear
import good.damn.kamchatka.extensions.setTextPx
import good.damn.kamchatka.fragments.StackFragment
import good.damn.kamchatka.services.FactsService
import good.damn.kamchatka.views.MountainsView

class SplashFragment
: StackFragment() {

    override fun onCreateView(
        context: Context
    ): View {

        val factsService = FactsService(
            Application.RESOURCES
        )
        val measureUnit = Application.WIDTH


        // Allocating views
        val layout = LinearLayout(
            context
        )
        val textViewAppName = TextView(
            context
        )
        val textViewPowered = TextView(
            context
        )
        val textViewFact = TextView(
            context
        )
        val textViewFactLabel = TextView(
            context
        )
        val mountainsView = MountainsView(
            context
        )




        // Setup orientation
        layout.orientation = LinearLayout
            .VERTICAL




        // Colors
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
        textViewPowered.setTextColor(
            Application.color(
                R.color.titleColor
            )
        )
        textViewFact.setTextColor(
            Application.color(
                R.color.largeTextColor
            )
        )
        textViewFactLabel.setTextColor(
            Application.color(
                R.color.titleColor
            )
        )





        // Setting text
        textViewAppName.setText(
            R.string.app_name
        )

        textViewPowered.setText(
            R.string.poweredByVulkan
        )

        textViewFact.text = factsService
            .getRandomFact()

        textViewFactLabel.setText(
            R.string.INTERESTING_FACT
        )


        // Text Size
        textViewAppName.setTextPx(
            measureUnit * 0.077f
        )
        textViewPowered.setTextPx(
            textViewAppName.textSize * 0.46f
        )
        textViewFact.setTextPx(
            measureUnit * 0.0404f
        )
        textViewFactLabel.setTextPx(
            measureUnit * 0.03f
        )


        // Font
        val fontNunitoRegular = Application.font(
            R.font.nunito_regular,
            context
        )
        val fontOpenSansExtraBold = Application.font(
            R.font.open_sans_extra_bold,
            context
        )
        textViewAppName.typeface    = fontOpenSansExtraBold
        textViewPowered.typeface    = fontOpenSansExtraBold
        textViewFact.typeface       = fontNunitoRegular
        textViewFactLabel.typeface  = fontOpenSansExtraBold




        // Text Gravity
        textViewFact.gravity = Gravity
            .CENTER_HORIZONTAL



        // LinearLayout params
        textViewAppName.boundsLinear(
            Gravity.CENTER_HORIZONTAL,
            -2,
            -2
        )
        textViewPowered.boundsLinear(
            Gravity.CENTER_HORIZONTAL,
            -2,
            -2
        )
        textViewFact.boundsLinear(
            Gravity.CENTER_HORIZONTAL,
            (measureUnit * 0.773f)
                .toInt(),
            -2
        )
        textViewFactLabel.boundsLinear(
            Gravity.CENTER_HORIZONTAL,
            -2,
            -2
        )





        // Adding views
        layout.addView(
            textViewAppName
        )
        layout.addView(
            textViewPowered
        )
        layout.addView(
            textViewFact
        )
        layout.addView(
            textViewFactLabel
        )
        layout.addView(
            mountainsView
        )

        return layout
    }

}