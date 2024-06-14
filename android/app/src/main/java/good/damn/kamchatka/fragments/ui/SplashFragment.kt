package good.damn.kamchatka.fragments.ui

import android.content.Context
import android.graphics.Color
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
import good.damn.kamchatka.views.RoundedImageView

class SplashFragment
: StackFragment() {

    var onEndAnimation: (()->Unit)? = null

    override fun onCreateView(
        context: Context,
        measureUnit: Int
    ): View {

        val factsService = FactsService(
            Application.RESOURCES
        )

        val appIconSize = (measureUnit * 0.248f)
            .toInt()


        // Allocating views
        val layout = LinearLayout(
            context
        )
        val imageViewAppIcon = RoundedImageView(
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



        // Background color
        layout.setBackgroundColor(
            Application.color(
                R.color.background
            )
        )
        imageViewAppIcon.setBackgroundColor(
            Color.BLACK
        )



        // Image Scale
        imageViewAppIcon.setImageScale(
            0.25f,
            0.25f
        )




        // Colors
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
        mountainsView.setMountainsBackgroundColor(
            Application.color(
                R.color.mountainsColorTransp
            )
        )
        mountainsView.setProgressColor(
            Application.color(
                R.color.mountainsColor
            )
        )
        navigationBarColor(
            Application.color(
                R.color.mountainsColor
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


        // Alpha
        textViewPowered.alpha = 0.2f
        textViewFactLabel.alpha = 0.5f

        // Corner radius
        imageViewAppIcon.radius = appIconSize * 0.25f

        // Image
        imageViewAppIcon.setImageDrawable(
            Application.drawable(
                R.drawable.icon
            )
        )

        // Gravity
        textViewFact.gravity = Gravity
            .CENTER_HORIZONTAL

        mountainsView.progress = 0.0f


        // Mountains points
        mountainsView.randomizePoints()



        // LinearLayout params
        imageViewAppIcon.boundsLinear(
            Gravity.CENTER_HORIZONTAL,
            width = appIconSize,
            height = appIconSize,
            top = Application.HEIGHT * 0.111f
        )
        textViewAppName.boundsLinear(
            Gravity.CENTER_HORIZONTAL,
            top = Application.HEIGHT * 0.016f
        )
        textViewPowered.boundsLinear(
            Gravity.CENTER_HORIZONTAL,
            top = Application.HEIGHT * 0.011f
        )
        textViewFact.boundsLinear(
            Gravity.CENTER_HORIZONTAL,
            width = (measureUnit * 0.773f)
                .toInt(),
            top = Application.HEIGHT * 0.071f
        )
        textViewFactLabel.boundsLinear(
            Gravity.CENTER_HORIZONTAL,
            top = Application.HEIGHT * 0.016f
        )




        // Adding views
        layout.addView(
            imageViewAppIcon
        )
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
            mountainsView,
            -1,
            -1
        )

        layout.post {
            Thread {
                var f = 0.0f

                while (f < 1.0f) {
                    Thread.sleep(1)
                    f += 0.0004f
                    Application.ui {
                        mountainsView.progress = f
                    }
                }

                Application.ui {
                    onEndAnimation?.invoke()
                }
            }.start()
        }
        return layout
    }

}