package good.damn.kamchatka.extensions
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout

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