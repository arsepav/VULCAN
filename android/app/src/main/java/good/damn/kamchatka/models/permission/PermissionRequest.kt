package good.damn.kamchatka.models.permission

import android.text.BoringLayout
import kotlinx.coroutines.awaitAll
import org.json.JSONObject

data class PermissionRequest(
    val id: Int,
    val surname: String,
    val name: String,
    val birthday: String,
    val citizenship: Int,
    val isMale: Boolean,
    val passport: String,
    val email: String,
    val phone: String,
    val isOneDay: Boolean,
    val purposeSkis: Boolean,
    val purposeSport: Boolean,
    val purposeScience: Boolean,
    val purposePhoto: Boolean,
    val purposeMountain: Boolean,
    val purposeAnother: Boolean,
    val cameraProfi: Boolean,
    val cameraDrones: Boolean,
    val reviewed: Boolean,
    val approved: Boolean
) {
    companion object {
        fun createFromJSON(
            json: JSONObject
        ): PermissionRequest {

            val id = json.get(
                "id"
            ) as? Int ?: -1

            val name = json.get(
                "name"
            ) as? String ?: ""

            val surname = json.get(
                "surname"
            ) as? String ?: ""

            val birthday = json.get(
                "birthday"
            ) as? String ?: ""

            val citizenship = json.get(
                "citizenship"
            ) as? Int ?: 0

            val isMale = json.get(
                "isMale"
            ) as? Boolean ?: true

            val passport = json.get(
                "passport"
            ) as? String ?: ""

            val email = json.get(
                "email"
            ) as? String ?: ""

            val phone = json.get(
                "phone_number"
            ) as? String ?: ""

            val isOneDay = json.get(
                "is_one_day_only"
            ) as? Boolean ?: true

            val purposeSki = json.get(
                "purpose_skis"
            ) as? Boolean ?: true

            val purposeSport = json.get(
                "purpose_sport"
            ) as? Boolean ?: true

            val purposeScience = json.get(
                "purpose_science"
            ) as? Boolean ?: true

            val purposePhoto = json.get(
                "purpose_photo_video"
            ) as? Boolean ?: true

            val purposeMountain = json.get(
                "purpose_mountaineering"
            ) as? Boolean ?: true

            val purposeAnother = json.get(
                "purpose_another"
            ) as? Boolean ?: true

            val cameraProfi = json.get(
                "photo_video_professional"
            ) as? Boolean ?: true

            val cameraDrones = json.get(
                "photo_video_drones"
            ) as? Boolean ?: true

            val reviewed = json.get(
                "reviewed"
            ) as? Boolean ?: true

            val approved = json.get(
                "approved"
            ) as? Boolean ?: true


            return PermissionRequest(
                id,
                surname,
                name,
                birthday,
                citizenship,
                isMale,
                passport,
                email,
                phone,
                isOneDay,
                purposeSki,
                purposeSport,
                purposeScience,
                purposePhoto,
                purposeMountain,
                purposeAnother,
                cameraProfi,
                cameraDrones,
                reviewed,
                approved
            )
        }
    }
}