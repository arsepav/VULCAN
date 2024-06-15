package good.damn.kamchatka.models

import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolygonOptions

data class SecurityZone(
    val polygon: PolygonOptions,
    val marker: MarkerOptions,
    val title: String,
    val fillColor: Int,
    val strokeColor: Int,
    val strokeWidth: Float
)