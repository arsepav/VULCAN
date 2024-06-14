package good.damn.kamchatka.extensions

import android.content.Context
import android.util.TypedValue
import android.widget.TextView
import good.damn.kamchatka.Application

fun TextView.textSizeBounds(): Int {
    return (textSize * 1.4f).toInt()
}

fun TextView.setTextPx(
    v: Float
) {
    setTextSize(
        TypedValue.COMPLEX_UNIT_PX,
        v
    )
}