package good.damn.kamchatka.models

import android.graphics.Bitmap
import good.damn.kamchatka.models.remote.json.OOPTObject

data class OOPTObjectMap(
    val bitmap: Bitmap,
    val obj: OOPTObject
)