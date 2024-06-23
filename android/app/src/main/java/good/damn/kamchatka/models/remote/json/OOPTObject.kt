package good.damn.kamchatka.models.remote.json

import android.util.Log
import com.google.android.gms.maps.model.LatLng
import org.json.JSONObject

data class OOPTObject(
    val id: Int,
    val name: String,
    val desc: String?,
    val coords: LatLng,
    val image_url: String?,
    val oopt_id: Int?
) {
    companion object {

        fun createFromJSON(
            json: JSONObject
        ): OOPTObject? {

            val coordsJson = json.getJSONObject(
                "geom"
            ).getJSONArray(
                "coordinates"
            )

            val coords = LatLng(
                coordsJson.get(0) as? Double ?: 0.0,
                coordsJson.get(1) as? Double ?: 0.0
            )

            val name = json.get(
                "name"
            ) as? String ?: return null

            val id = json.get(
                "id"
            ) as? Int ?: return null

            val oopt_id = json.get(
                "oopt_id"
            ) as? Int

            val image_url = json.get(
                "image_url"
            ) as? String

            val desc = json.get(
                "description"
            ) as? String

            return OOPTObject(
                id,
                name,
                desc,
                coords,
                image_url,
                oopt_id
            )
        }

    }
}