package good.damn.kamchatka.services

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.PolygonOptions
import good.damn.kamchatka.models.SecurityZone

class GeoService {

    fun getSecurityZones(
        onEachZone: ((SecurityZone)->Unit)
    ) {
        val zones = arrayOf(
            SecurityZone(
                PolygonOptions()
                    .clickable(true)
                    .add(
                        LatLng(55.527197, 158.462162),
                        LatLng(55.477418, 159.654178),
                        LatLng(54.773885, 159.434452)
                    ),
                "Red zone",
                0x55ff0000.toInt(),
                0xffff0000.toInt(),
                3.0f
            ),
            SecurityZone(
                PolygonOptions()
                    .clickable(true)
                    .add(
                        LatLng(54.527197, 158.462162),
                        LatLng(54.477418, 159.654178),
                        LatLng(55.773885, 159.434452),
                        LatLng(56.773885, 159.424452)
                    ),
                "Green zone",
                0x5500ff00.toInt(),
                0xff00ff00.toInt(),
                3.0f
            )
        )

        for (zone in zones) {
            onEachZone(zone)
        }
    }

}