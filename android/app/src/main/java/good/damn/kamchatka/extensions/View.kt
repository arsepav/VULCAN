package good.damn.kamchatka.extensions
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout


fun View.width(): Int {
    return layoutParams.width
}

fun View.height(): Int {
    return layoutParams.height
}

fun View.top(): Float {
    margins().apply {
        return topMargin.toFloat()
    }
}

fun View.bottom(): Int {
    margins().apply {
        return topMargin + height
    }
}

fun View.left(): Float {
    margins().apply {
        return leftMargin.toFloat()
    }
}

fun View.margins(): ViewGroup.MarginLayoutParams {
    return layoutParams as ViewGroup.MarginLayoutParams
}

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

fun View.size(
    width: Int = -2,
    height: Int = -2
) {
    layoutParams = ViewGroup.LayoutParams(
        width,
        height
    )
}

fun View.boundsLinear(
    gravity: Int = Gravity.NO_GRAVITY,
    size: Int = -2,
    top: Float = 0f,
    left: Float = 0f
) {
    boundsLinear(
        gravity = gravity,
        width = size,
        height = size,
        top = top,
        left = left
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

fun View.boundsFrameRight(
    gravity: Int = Gravity.NO_GRAVITY,
    width: Int = -2,
    height: Int = -2,
    top: Float = 0f,
    bottom: Float = 0f,
    left: Float = 0f,
    right: Float = 0f
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
    params.rightMargin = right.toInt()
    layoutParams = params
}

fun View.boundsFrame(
    gravity: Int = Gravity.NO_GRAVITY,
    size: Int = -2,
    top: Float = 0f,
    bottom: Float = 0f,
    left: Float = 0f,
) {
    boundsFrame(
        gravity = gravity,
        size,
        size,
        top,
        bottom,
        left
    )
}

fun View.boundsFrame(
    gravity: Int = Gravity.NO_GRAVITY,
    width: Int = -2,
    height: Int = -2,
    top: Float = 0f,
    bottom: Float = 0f,
    left: Float = 0f,
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