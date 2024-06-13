package good.damn.kamchatka.extensions

import android.content.Context
import android.util.TypedValue
import android.widget.TextView
import good.damn.kamchatka.Application

fun TextView.setTextPx(
    v: Float
) {
    setTextSize(
        TypedValue.COMPLEX_UNIT_PX,
        v
    )
}