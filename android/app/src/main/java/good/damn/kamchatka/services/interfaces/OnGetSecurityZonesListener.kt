package good.damn.kamchatka.services.interfaces

import good.damn.kamchatka.models.SecurityZone

interface OnGetSecurityZonesListener {

    fun onGetSecurityZones(
        zones: Array<SecurityZone?>
    )

}