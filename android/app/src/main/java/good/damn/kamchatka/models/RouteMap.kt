package good.damn.kamchatka.models

import com.google.android.gms.maps.model.PolylineOptions
import good.damn.kamchatka.models.remote.json.Route

data class RouteMap(
    val polyline: PolylineOptions,
    val route: Route
)