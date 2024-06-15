package good.damn.kamchatka.services.interfaces

import good.damn.kamchatka.models.SecurityZone

interface GeoServiceListener {

    fun onGetSecurityZones(
        zones: Array<SecurityZone?>
    )

}