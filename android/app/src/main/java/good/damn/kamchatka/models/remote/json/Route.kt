package good.damn.kamchatka.models.remote.json

import com.google.android.gms.maps.model.LatLng
import org.json.JSONObject

data class Route(
    val id: Int?,
    val name: String?,
    val coords: Array<LatLng>,
    val ooptId: Int?,
    val categoryId: Int?
) {
    companion object {
        fun createFromJSON(
            json: JSONObject
        ): Route? {
            val id = json.get(
                "id"
            ) as? Int ?: return null

            val name = json.get(
                "name"
            ) as? String ?: return null

            val ooptId = json.get(
                "oopt_id"
            ) as? Int ?: return null

            val categoryId = json.get(
                "category_id"
            ) as? Int ?: return null

            val geom = json.getJSONObject(
                "geom"
            ).getJSONArray(
                "coordinates"
            ).getJSONArray(
                0
            )

            val coords = Array(geom.length()) {
                val point = geom.getJSONArray(it)
                LatLng(
                    point.getDouble(0),
                    point.getDouble(1)
                )
            }

            return Route(
                id,
                name,
                coords,
                ooptId,
                categoryId
            )
        }
    }
}