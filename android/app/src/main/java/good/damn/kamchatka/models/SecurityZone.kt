package good.damn.kamchatka.models

import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolygonOptions
import good.damn.kamchatka.models.remote.json.OOPT

data class SecurityZone(
    val polygon: PolygonOptions,
    val oopt: OOPT,
    val marker: MarkerOptions,
    val dangerRate: Float
)