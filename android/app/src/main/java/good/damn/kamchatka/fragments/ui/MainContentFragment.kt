package good.damn.kamchatka.fragments.ui

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import good.damn.kamchatka.Application
import good.damn.kamchatka.R
import good.damn.kamchatka.extensions.bottom
import good.damn.kamchatka.extensions.boundsFrame
import good.damn.kamchatka.extensions.boundsFrameRight
import good.damn.kamchatka.extensions.height
import good.damn.kamchatka.extensions.setTextPx
import good.damn.kamchatka.extensions.textSizeBounds
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
        val imageViewKamchatka = AppCompatImageView(
            context
        )
        val textViewKamchatka = AppCompatTextView(
            context
        )
        val textViewKamchatka2 = AppCompatTextView(
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
        imageViewKamchatka.setImageDrawable(
            Application.drawable(
                R.drawable.kamchatka
            )
        )



        // CornerRadius
        imageViewProfile.radius = btnProfileWidth * 0.5f
        imageViewLike.radius = btnProfileWidth * 0.5f




        // Text size
        textViewAppName.setTextPx(
            measureUnit * 0.0507f
        )
        textViewKamchatka.setTextPx(
            measureUnit * 0.06864f
        )
        textViewKamchatka2.setTextPx(
            measureUnit * 0.0381f
        )



        // Font
        textViewAppName.typeface = Application.font(
            R.font.open_sans_bold,
            context
        )
        textViewKamchatka.typeface = Application.font(
            R.font.open_sans_bold,
            context
        )
        textViewKamchatka2.typeface = Application.font(
            R.font.nunito_regular,
            context
        )



        // Text
        textViewAppName.setText(
            R.string.app_name
        )
        textViewKamchatka.setText(
            R.string.kamchatka
        )
        textViewKamchatka2.setText(
            R.string.maps_objects
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



        // Background colors
        layout.setBackgroundColor(
            Application.color(
                R.color.background
            )
        )



        // Text color
        val blackColor = Application.color(
            R.color.titleColor
        )
        textViewAppName.setTextColor(
            blackColor
        )
        textViewKamchatka.setTextColor(
            blackColor
        )
        textViewKamchatka2.setTextColor(
            blackColor
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
        imageViewKamchatka.boundsFrame(
            Gravity.CENTER_HORIZONTAL,
            width = (measureUnit * 0.4541f).toInt(),
            height = (measureUnit * 0.5072f).toInt(),
            top = measureUnit * 0.1425f + textViewAppName.bottom()
        )
        textViewKamchatka.boundsFrame(
            Gravity.CENTER_HORIZONTAL,
            top = imageViewKamchatka.bottom() + measureUnit * 0.03623f,
            height = textViewKamchatka.textSizeBounds()
        )
        textViewKamchatka2.boundsFrame(
            Gravity.CENTER_HORIZONTAL,
            top = textViewKamchatka.bottom() + measureUnit * 0.0099f,
            height = textViewKamchatka2.textSizeBounds()
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
        layout.addView(
            imageViewKamchatka
        )
        layout.addView(
            textViewKamchatka
        )
        layout.addView(
            textViewKamchatka2
        )

        return layout
    }

}