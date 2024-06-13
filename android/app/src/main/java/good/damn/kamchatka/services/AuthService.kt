package good.damn.kamchatka.services

import android.util.Log
import good.damn.kamchatka.Application
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import org.json.JSONObject

class AuthService {

    companion object {
        private const val TAG = "AuthService"
        private val JSON = "application/json; charset=utf-8".toMediaTypeOrNull()
        private const val URL = "http://91.224.86.144:8000/"
        private const val URL_REGISTER = "${URL}register"
        private const val URL_TOKEN = "${URL}token"
    }

    fun token(
        email: String,
        password: String,
        completion: (Response) -> Unit
    ) {
        createPostRequest(
            URL_TOKEN,
            createUsernameJson(
                email,
                password
            ),
            completion
        )
    }

    fun registerUser(
        email: String,
        password: String,
        name: String,
        surname: String,
        lastname: String,
        telephone: String,
        completion: (Response) -> Unit
    ) {

        val json = createUsernameJson(
            email,
            password
        )

        json.put(
            "name",
            name
        )

        json.put(
            "surname",
            surname
        )

        json.put(
            "lastname",
            lastname
        )

        json.put(
            "phone_number",
            telephone
        )

        json.put(
            "email",
            email
        )

        createPostRequest(
            URL_REGISTER,
            json,
            completion
        )
    }

    private fun createUsernameJson(
        email: String,
        password: String
    ): JSONObject {
        val json = JSONObject()
        json.put(
            "username",
            email
        )

        json.put(
            "password",
            password
        )

        return json
    }

    private fun createPostRequest(
        url: String,
        json: JSONObject,
        completion: (Response) -> Unit
    ) {
        val client = OkHttpClient()

        val body = json.toString()
            .toRequestBody(JSON)

        val request = Request.Builder()
            .url(url)
            .post(body)
            .build()

        Thread {
            val response = client.newCall(
                request
            ).execute()

            completion(response)
        }.start()
    }

}