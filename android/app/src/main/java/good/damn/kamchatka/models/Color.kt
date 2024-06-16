package good.damn.kamchatka.models

import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import good.damn.kamchatka.Application

class Color: Color() {
    companion object {

        @ColorInt
        fun parseFromHexId(
            @ColorRes id: Int,
            alpha: Float = 1.0f
        ): Int {
            return parseFromHex(
                Application.color(
                    id
                ),
                alpha
            )
        }

        @ColorInt
        fun parseFromHex(
            @ColorInt color: Int,
            alpha: Float
        ): Int {
            return ((255 * alpha).toInt() and 0xff shl 24) or (
                color and 0x00ffffff
            )
        }
    }
}