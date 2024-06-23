package good.damn.kamchatka.services.interfaces

import good.damn.kamchatka.models.remote.json.OOPTObject

interface OnGetObjectsListener {
    fun onGetObjects(
        objects: Array<OOPTObject?>
    )
}