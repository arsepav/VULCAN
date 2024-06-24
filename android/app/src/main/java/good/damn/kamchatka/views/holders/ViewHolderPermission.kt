package good.damn.kamchatka.views.holders

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import good.damn.kamchatka.Application
import good.damn.kamchatka.extensions.size
import good.damn.kamchatka.views.button.CardState

class ViewHolderPermission(
    private val mCardState: CardState,
): RecyclerView.ViewHolder(
    mCardState
) {

    fun setTitle(
        t: String
    ) {
        mCardState.title = t
    }

    fun setSubtitle(
        t: String
    ) {
        mCardState.subtitle = t
    }

    fun setState(
        t: String
    ) {
        mCardState.state = t
    }

    fun setDrawableEnd(
        @DrawableRes id: Int
    ) {
        setDrawableEnd(
            Application.drawable(
                id
            )
        )
    }

    fun setDrawableEnd(
        d: Drawable?
    ) {
        mCardState.drawableEnd = d
    }

    companion object {
        fun create(
            context: Context
        ): ViewHolderPermission {
            val card = CardState(
                context
            )
            val height = Application.WIDTH * 0.31884f
            val width = Application.WIDTH * 0.8937f

            card.radius = height * 0.1386f
            card.cardElevation = height * 0.02f

            card.size(
                width = width.toInt(),
                height = height.toInt()
            )

            card.layoutIt()


            return ViewHolderPermission(
                card
            )
        }
    }

}