package good.damn.kamchatka.services

import android.content.SharedPreferences
import good.damn.kamchatka.models.TokenAuth

class TokenService(
    private val mShared: SharedPreferences
) {
    companion object {
        private const val TAG = "TokenService"
        private const val KEY_TOKEN = "token"
        private const val KEY_TOKEN_TYPE = "tokenType"
        private const val KEY_EMAIL = "email"
        private const val KEY_NAME = "name"
        private const val KEY_SAVE_TIME = "time"
        private const val KEY_PASSWORD = "password"
    }

    var token = TokenAuth(
        mShared.getString(
            KEY_TOKEN,
            null
        ),
        mShared.getString(
            KEY_TOKEN_TYPE,
            null
        ),
        mShared.getString(
            KEY_EMAIL,
            null
        ),
        mShared.getString(
            KEY_NAME,
            null
        ),
        mShared.getString(
            KEY_PASSWORD,
            null
        ),
        TokenAuth.TOKEN_TTL
    )

    var tokenTime = mShared.getLong(
        KEY_SAVE_TIME,
        0
    )

    var isTokenExpired = false
        get() = TimeService.checkExpiration(
            tokenTime,
            token.tokenPeriod
        )

    fun saveToken() {
        mShared.edit().apply {
            putString(
                KEY_TOKEN,
                token.token
            )

            putString(
                KEY_EMAIL,
                token.email
            )

            putString(
                KEY_NAME,
                token.name
            )

            putString(
                KEY_PASSWORD,
                token.password
            )

            putString(
                KEY_TOKEN_TYPE,
                token.tokenType
            )


            putLong(
                KEY_SAVE_TIME,
                TimeService.currentTimeSec()
            )

            apply()
        }
    }

}