package good.damn.kamchatka.views.interactions.interfaces

import android.view.MotionEvent
import android.view.View

interface OnActionListener {

    fun onDown(
        v: View,
        action: MotionEvent
    )

    fun onUp(
        v: View,
        action: MotionEvent
    )

}