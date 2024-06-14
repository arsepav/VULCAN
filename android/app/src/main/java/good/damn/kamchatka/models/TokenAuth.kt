package good.damn.kamchatka.models

import good.damn.kamchatka.services.TimeService
import org.json.JSONObject

data class TokenAuth(
    var token: String?,
    var tokenType: String?,
    var tokenPeriod: Long = TOKEN_TTL
) {
    companion object {
        const val TOKEN_TTL = 1 * 24 * 60 * 60L
        fun createFromJSON(
            json: JSONObject
        ): TokenAuth {
            return TokenAuth(
                json.get("access_token") as? String,
                json.get("token_type") as? String
            )
        }
    }


    override fun toString(): String {
        return "$token $tokenType $tokenPeriod"
    }

}