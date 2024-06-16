package good.damn.kamchatka.views.layout.models

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.annotation.StringRes

class GroupField {

    val text: String
    @DrawableRes val drawableId: Int

    constructor(
        text: String,
        @DrawableRes drawableId: Int = 0
    ) {
        this.text = text
        this.drawableId = drawableId
    }

    constructor(
        context: Context,
        @StringRes hintId: Int,
        @DrawableRes drawableId: Int = 0
    ) {
        this.text = context.getString(
            hintId
        )
        this.drawableId = drawableId
    }

}