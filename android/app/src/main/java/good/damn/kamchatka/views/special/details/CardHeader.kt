package good.damn.kamchatka.views.special.details

import android.content.Context
import android.view.Gravity
import androidx.cardview.widget.CardView
import good.damn.kamchatka.R
import good.damn.kamchatka.extensions.boundsFrame
import good.damn.kamchatka.extensions.boundsFrameRight
import good.damn.kamchatka.extensions.setImageDrawableId
import good.damn.kamchatka.models.Color
import good.damn.kamchatka.views.RoundedImageView

class CardHeader(
    context: Context
): CardView(
    context
) {
    fun layoutIt(
        measureUnit: Int
    ) {
        val imageViewBack = RoundedImageView(
            context
        )
        val imageViewLike = RoundedImageView(
            context
        )

        // Background color
        Color.parseFromHexId(
            R.color.background,
            0.7f
        ).let {
            imageViewBack.setBackgroundColor(
                it
            )
            imageViewLike.setBackgroundColor(
                it
            )
        }

        imageViewBack.setImageDrawable(
            R.drawable.ic_arrow
        )

        imageViewLike.setImageDrawable(
            R.drawable.ic_like
        )

        imageViewBack.setImageScale(
            0.65f,
            0.65f
        )

        imageViewLike.setImageScale(
            0.5f,
            0.5f
        )

        (measureUnit * 0.09903f).toInt().let { size ->

            imageViewBack.radius = size * 0.5f
            imageViewLike.radius = size * 0.5f


            (measureUnit * 0.06521f).let { top ->
                (measureUnit * 0.0483f).let { left ->
                    imageViewBack.boundsFrame(
                        Gravity.START,
                        size = size,
                        top = top,
                        left = left
                    )

                    imageViewLike.boundsFrameRight(
                        Gravity.END,
                        width = size,
                        height = size,
                        top = top,
                        right = left
                    )
                }
            }
        }


        addView(
            imageViewBack
        )

        addView(
            imageViewLike
        )

    }
}