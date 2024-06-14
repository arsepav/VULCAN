package good.damn.kamchatka.services

import android.content.SharedPreferences
import good.damn.kamchatka.models.TokenAuth

class TokenService(
    private val mShared: SharedPreferences
) {
    companion object {
        private const val TAG = "TokenService"
        private const val KEY_TOKEN = "token"
        private const val KEY_SAVE_TIME = "time"
    }

    var token = TokenAuth(
        mShared.getString(
            KEY_TOKEN,
            null
        ),
        "bearer",
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

            putLong(
                KEY_SAVE_TIME,
                TimeService.currentTimeSec()
            )

            apply()
        }
    }

}