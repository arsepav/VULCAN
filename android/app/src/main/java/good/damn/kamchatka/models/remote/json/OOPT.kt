package good.damn.kamchatka.models.remote.json

import android.graphics.Point
import android.graphics.PointF
import android.util.Log
import com.google.android.gms.maps.model.LatLng
import org.json.JSONObject

data class OOPT(
    val id: Int?,
    val name: String?,
    val desc: String?,
    val coords: Array<LatLng>?,
    val image_url: String?
) {
    companion object {
        private const val TAG = "OOPT"

        fun createFromJSON(
            json: JSONObject
        ): OOPT? {
            val id = json.get(
                "id"
            ) as? Int ?: return null

            val name = json.get(
                "name"
            ) as? String ?: return null

            val desc = json.get(
                "description"
            ) as? String ?: return null

            val image_url = json.get(
                "image_url"
            ) as? String

            val geom = json.getJSONObject(
                "geom"
            ).getJSONArray(
                "coordinates"
            ).getJSONArray(
                0
            )

            Log.d(TAG, "createFromJSON: ${geom.length()}")

            val coords = Array(geom.length()) {
                val point = geom.getJSONArray(it)
                LatLng(
                    point.getDouble(0),
                    point.getDouble(1)
                )
            }

            return OOPT(
                id,
                name,
                desc,
                coords,
                image_url
            )
        }
    }


}