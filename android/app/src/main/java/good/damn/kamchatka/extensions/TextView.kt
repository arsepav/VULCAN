package good.damn.kamchatka.extensions

import android.util.TypedValue
import android.widget.TextView

fun TextView.setTextPx(
    v: Float
) {
    setTextSize(
        TypedValue.COMPLEX_UNIT_PX,
        v
    )
}