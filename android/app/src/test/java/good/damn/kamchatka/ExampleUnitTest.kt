package good.damn.kamchatka

import android.util.Log
import good.damn.kamchatka.fragments.ui.auth.SignInFragment
import good.damn.kamchatka.services.AuthService
import org.json.JSONException
import org.json.JSONObject
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    companion object {
        private const val TAG = "ExampleUnitTest"
    }

    @Test
    fun register_test() {
        AuthService().registerUser(
            email = "test@gmail.com",
            password = "test_",
            name = "",
            surname = "",
            lastname = "",
            telephone = ""
        ) {
            val body = it.body?.string() ?: return@registerUser

            val json = try {
                JSONObject(
                    body
                )
            } catch (e: Exception) {
                Log.d(TAG, "register_test: $")
                null
            }

            val success = try {
                json?.get(
                    "success"
                )
            } catch (e: JSONException) {
                null
            }

            Log.d(TAG, "register_test: SUCCESS: $success ${success as? Boolean}")

        }


    }

}