package good.damn.kamchatka.models.view

import android.graphics.drawable.Drawable

data class Park(
    val imagePreview: Drawable?,
    val name: String,
    val type: String
)