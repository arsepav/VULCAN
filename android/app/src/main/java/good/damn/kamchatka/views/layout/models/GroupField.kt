package good.damn.kamchatka.views.layout.models

import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.annotation.StringRes

data class GroupField(
    @StringRes val hintId: Int,
    @DrawableRes val drawableId: Int = 0
)