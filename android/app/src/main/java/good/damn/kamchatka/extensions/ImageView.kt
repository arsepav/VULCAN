package good.damn.kamchatka.extensions

import android.widget.ImageView
import androidx.annotation.DrawableRes
import good.damn.kamchatka.Application

fun ImageView.setImageDrawableId(
    @DrawableRes id: Int
) {
    setImageDrawable(
        Application.drawable(
            id
        )
    )
}