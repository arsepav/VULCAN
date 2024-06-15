package good.damn.kamchatka.models

import com.google.android.gms.maps.model.PolylineOptions

data class RouteMap(
    val route: PolylineOptions,
    val color: Int,
    val strokeWidth: Float,
    val ooptId: Int?
)