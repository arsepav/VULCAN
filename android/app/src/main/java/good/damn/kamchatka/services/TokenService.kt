package good.damn.kamchatka.services

import android.content.SharedPreferences

class TokenService(
    private val mShared: SharedPreferences
) {
    companion object {
        private const val TAG = "TokenService"
        private const val KEY_TOKEN = "token"
        private const val KEY_SAVE_TIME = "time"
        private const val TOKEN_DELTA_SECONDS = 1 * 24 * 60 * 60L // 1 day
    }

    var tokenTime = mShared.getLong(
        KEY_SAVE_TIME,
        0
    )
    private set

    var token = mShared.getString(
        KEY_TOKEN,
        null
    )

    var isTokenExpired = false
        private set
        get() = TimeService.checkExpiration(
            tokenTime,
            TOKEN_DELTA_SECONDS
        )

    var isTokenAvailable = false
        private set
        get() = token == null


    fun saveToken() {
        mShared.edit().apply {
            putString(
                KEY_TOKEN,
                token
            )

            putLong(
                KEY_SAVE_TIME,
                TimeService.currentTimeSec()
            )

            apply()
        }
    }

}