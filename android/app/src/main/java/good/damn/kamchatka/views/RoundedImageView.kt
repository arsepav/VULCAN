package good.damn.kamchatka.views

import android.content.Context
import android.widget.ImageView
import androidx.cardview.widget.CardView

class RoundedImageView(
    context: Context
): CardView(
    context
) {
    var imageView = ImageView(
        context
    )

    init {
        addView(
            imageView,
            -1,
            -1
        )
    }

}