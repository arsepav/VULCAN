package good.damn.kamchatka.extensions
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout


fun View.checkBounds(
    touchX: Float,
    touchY: Float
): Boolean {
    return !(touchX < 0
        || touchX > width
        || touchY < 0
        || touchY > height
        )
}

fun View.boundsLinear(
    gravity: Int = Gravity.NO_GRAVITY,
    width: Int = -2,
    height: Int = -2,
    top: Float = 0f,
    left: Float = 0f
) {
    val params = LinearLayout
        .LayoutParams(
            width,
            height
        )
    params.gravity = gravity
    params.topMargin = top.toInt()
    params.leftMargin = left.toInt()
    layoutParams = params
}

fun View.boundsFrame(
    gravity: Int = Gravity.NO_GRAVITY,
    width: Int = -2,
    height: Int = -2,
    top: Float = 0f,
    bottom: Float = 0f,
    left: Float = 0f
) {
    val params = FrameLayout
        .LayoutParams(
            width,
            height
        )
    params.gravity = gravity
    params.bottomMargin = bottom.toInt()
    params.topMargin = top.toInt()
    params.leftMargin = left.toInt()
    layoutParams = params
}