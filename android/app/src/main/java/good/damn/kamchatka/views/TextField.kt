package good.damn.kamchatka.views

import android.content.Context
import android.view.inputmethod.EditorInfo
import androidx.appcompat.widget.AppCompatEditText

class TextField(
    context: Context
): AppCompatEditText(
    context
) {

    init {
        maxLines = 1
        minLines = 1
        inputType = EditorInfo.TYPE_CLASS_TEXT
    }

}