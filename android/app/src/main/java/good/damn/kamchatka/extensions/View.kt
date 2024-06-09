package good.damn.kamchatka.extensions
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout

fun View.boundsLinear(
    gravity: Int = Gravity.NO_GRAVITY,
    width: Int = -2,
    height: Int = -2,
    top: Int = 0,
    left: Int = 0
) {
    val params = LinearLayout
        .LayoutParams(
            width,
            height
        )
    params.gravity = gravity
    params.topMargin = top
    params.leftMargin = left
    layoutParams = params
}

fun View.boundsFrame(
    gravity: Int,
    width: Int,
    height: Int
) {
    val params = FrameLayout
        .LayoutParams(
            width,
            height
        )
    params.gravity = gravity
    layoutParams = params
}