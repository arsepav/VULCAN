package good.damn.kamchatka.services

import android.content.Context
import android.util.Log
import good.damn.kamchatka.Application
import good.damn.kamchatka.services.network.NetworkService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import org.json.JSONObject

class AuthService(
    context: Context
): NetworkService(
    context
) {

    companion object {
        private const val TAG = "AuthService"
        private const val URL_REGISTER = "${Application.URL}/register"
        private const val URL_TOKEN = "${Application.URL}/token"
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
        makeRequest(
            Request.Builder()
                .url(url)
                .post(json.toString().toRequestBody(
                    Application.JSON
                ))
        ) { client, request ->
            CoroutineScope(
                Dispatchers.IO
            ).launch {
                val response = client.newCall(
                    request
                ).execute()

                completion(response)
            }
        }


    }

}