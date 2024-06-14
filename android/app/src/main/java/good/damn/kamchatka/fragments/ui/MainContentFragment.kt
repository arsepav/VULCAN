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
import good.damn.kamchatka.extensions.boundsFrameRight
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
        val imageViewLike = RoundedImageView(
            context
        )


        // Image drawable
        imageViewProfile.setImageDrawable(
            Application.drawable(
                R.mipmap.ic_launcher
            )
        )
        imageViewLike.setImageDrawable(
            Application.drawable(
                R.drawable.ic_like
            )
        )



        // CornerRadius
        imageViewProfile.radius = btnProfileWidth * 0.5f
        imageViewLike.radius = btnProfileWidth * 0.5f




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

        imageViewLike.setStrokeColor(
            Application.color(
                R.color.titleColor
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


        // Alpha
        imageViewLike.setStrokeAlpha(
            0.1f
        )


        // Scale
        imageViewLike.setImageScale(
            x = 0.55f,
            y = 0.55f
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
            top = imageViewProfile.top() + textViewAppName.textSize * 0.3f
        )
        imageViewLike.boundsFrameRight(
            Gravity.END,
            width = btnProfileWidth.toInt(),
            height = btnProfileWidth.toInt(),
            top = imageViewProfile.top(),
            right = measureUnit * 0.0700f
        )



        // Adding views
        layout.addView(
            imageViewProfile
        )
        layout.addView(
            textViewAppName
        )
        layout.addView(
            imageViewLike
        )

        return layout
    }

}