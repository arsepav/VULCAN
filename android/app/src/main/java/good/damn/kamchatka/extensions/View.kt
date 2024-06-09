package good.damn.kamchatka.extensions
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout

fun View.boundsLinear(
    gravity: Int,
    width: Int,
    height: Int
) {
    val params = LinearLayout
        .LayoutParams(
            width,
            height
        )
    params.gravity = gravity
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