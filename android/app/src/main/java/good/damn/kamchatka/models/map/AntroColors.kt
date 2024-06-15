package good.damn.kamchatka.models.map

import androidx.annotation.DrawableRes
import good.damn.kamchatka.R

class AntroColors {
    companion object {
        val colors = intArrayOf(
            0xff65EC6A.toInt(),
            0xff90EC65.toInt(),
            0xffB9EC65.toInt(),
            0xffFFD600.toInt(),
            0xffFFC700.toInt(),
            0xffFFB800.toInt(),
            0xffFF8A00.toInt(),
            0xffFF5C00.toInt(),
            0xffFF3D00.toInt(),
            0xffFF0000.toInt(),
        )

        @DrawableRes
        val markers = intArrayOf(
            R.drawable.antr_1,
            R.drawable.antr_2,
            R.drawable.antr_3,
            R.drawable.antr_4,
            R.drawable.antr_5,
            R.drawable.antr_6,
            R.drawable.antr_7,
            R.drawable.antr_8,
            R.drawable.antr_9,
            R.drawable.antr_10,
        )
    }
}