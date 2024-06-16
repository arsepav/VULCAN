package good.damn.kamchatka.views.text_fields

import android.content.Context
import android.text.method.PasswordTransformationMethod

class TextFieldRoundPassword(
    context: Context
): TextFieldRound(
    context
) {

    init {
        transformationMethod = PasswordTransformationMethod()
    }

}