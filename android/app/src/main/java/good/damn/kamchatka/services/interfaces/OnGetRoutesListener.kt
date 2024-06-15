package good.damn.kamchatka.services.interfaces

import good.damn.kamchatka.models.RouteMap

interface OnGetRoutesListener {

    fun onGetRoutes(
        routes: Array<RouteMap?>
    )

}