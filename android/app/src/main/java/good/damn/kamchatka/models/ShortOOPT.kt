package good.damn.kamchatka.models

import android.graphics.Bitmap
import good.damn.kamchatka.models.remote.json.OOPT

data class ShortOOPT(
    val oopt: OOPT,
    val type: String?,
    var image: Bitmap? = null,
    var shortName: String
)