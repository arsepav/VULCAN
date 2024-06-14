package good.damn.kamchatka.fragments.ui

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import good.damn.kamchatka.Application
import good.damn.kamchatka.R
import good.damn.kamchatka.extensions.boundsFrame
import good.damn.kamchatka.extensions.height
import good.damn.kamchatka.extensions.setTextPx
import good.damn.kamchatka.extensions.top
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

        val btnProfileWidth = measureUnit * 0.1f

        // Allocating views
        val layout = FrameLayout(
            context
        )
        val textViewAppName = TextView(
            context
        )
        val imageViewProfile = RoundedImageView(
            context
        )


        // Image drawable
        imageViewProfile.setImageDrawable(
            Application.drawable(
                R.mipmap.ic_launcher
            )
        )


        // CornerRadius
        imageViewProfile.radius = btnProfileWidth * 0.5f

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


        // Stroke colors
        imageViewProfile.setStrokeColor(
            Application.color(
                R.color.mountainsColor
            )
        )
        imageViewProfile.setStrokeOffsetColor(
            Application.color(
                R.color.background
            )
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
        imageViewProfile.boundsFrame(
            Gravity.START,
            width = btnProfileWidth.toInt(),
            height = btnProfileWidth.toInt(),
            left = measureUnit * 0.0700f,
            top = measureUnit * 0.0966f
        )
        textViewAppName.boundsFrame(
            Gravity.CENTER_HORIZONTAL,
            top = imageViewProfile.top() + (
                imageViewProfile.height() - textViewAppName.textSize
                ) * 0.5f
        )



        // Adding views
        layout.addView(
            imageViewProfile
        )
        layout.addView(
            textViewAppName
        )

        return layout
    }

}