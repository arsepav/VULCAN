package good.damn.kamchatka.services

import android.content.Context
import android.net.Network
import good.damn.kamchatka.Application
import good.damn.kamchatka.services.network.NetworkService
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import org.json.JSONObject

class PermissionService(
    context: Context
): NetworkService(
    context
) {
    companion object {
        private const val TAG = "PermissionService"
        private const val URL_CREATE = "${Application.URL}/create_visit_permission"
        private const val URL_GET = "${Application.URL}/visit_permissions_user"
    }

    fun getPermissions() {
        makeRequest(
            Request.Builder()
                .url(URL_GET)
                .get()
        ) { client, request ->
            Thread {
                val response = client.newCall(
                    request
                ).execute()

            }.start()
        }
    }

    fun createPermission(
        arrivalDate: String,
        surname: String,
        name: String,
        lastname: String,
        birthday: String,
        citizenship: Int,
        isMale: Boolean,
        passport: String,
        email: String,
        phoneNumber: String,
        pathId: Int,
        isOneDay: Boolean,
        purposeSkis: Boolean,
        purposeSport: Boolean,
        purposeScience: Boolean,
        purposePhotoVideo: Boolean,
        purposeMountain: Boolean,
        purposeAnother: Boolean,
        photoVideoProf: Boolean,
        photoVideoDrones: Boolean,
        completionBackground: ((Response) -> Unit)
    ) {

        val json = JSONObject()

        json.apply {
            put("arrival_date", arrivalDate)
            put("surname", surname)
            put("name",name)
            put("lastname", lastname)
            put("birthday", birthday)
            put("citizenship", citizenship)
            put("isMale", isMale)
            put("passport", passport)
            put("email", email)
            put("phone_number", phoneNumber)
            put("path_id", pathId)
            put("is_one_day_only",isOneDay)
            put("purpose_skis", purposeSkis)
            put("purpose_sport", purposeSport)
            put("purpose_science", purposeScience)
            put("purpose_photo_video", purposePhotoVideo)
            put("purpose_mountaineering", purposeMountain)
            put("purpose_another", purposeAnother)
            put("photo_video_professional", photoVideoProf)
            put("photo_video_drones", photoVideoDrones)
        }


        makeRequest(
            Request.Builder()
                .url(URL_CREATE)
                .post(
                    json.toString().toRequestBody(
                        Application.JSON
                    )
                )
        ) { client, request ->
            Thread {
                val response = client
                    .newCall(request)
                    .execute()

                completionBackground(
                    response
                )
            }.start()
        }
    }

}