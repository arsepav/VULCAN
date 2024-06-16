package good.damn.kamchatka.views.text_fields

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.view.Gravity
import android.view.inputmethod.EditorInfo
import androidx.appcompat.widget.AppCompatEditText

open class TextField(
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