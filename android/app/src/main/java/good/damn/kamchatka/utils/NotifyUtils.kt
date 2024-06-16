package good.damn.kamchatka.utils

import good.damn.kamchatka.Application
import good.damn.kamchatka.R
import good.damn.kamchatka.views.text_fields.TextField

class NotifyUtils {
    companion object {
        fun notifyBlankField(
            inp: String,
            field: TextField
        ): Boolean {
            val b = inp.isBlank()
            if (b) {
                Application.toast(
                    "'${field.hint}' ${field.context.getString(R.string.empty)}",
                    field.context
                )
            }
            return b
        }
    }
}