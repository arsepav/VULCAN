package good.damn.kamchatka.views.holders

import android.content.Context
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import good.damn.kamchatka.Application
import good.damn.kamchatka.R
import good.damn.kamchatka.extensions.size
import good.damn.kamchatka.utils.ViewUtils

class ViewHolderPermission(
    layout: View,
    private val mTextViewName: AppCompatTextView,
    private val mTextViewState: AppCompatTextView
): RecyclerView.ViewHolder(
    layout
) {

    fun setState(
        t: String
    ) {
        mTextViewState.text = t
    }

    fun setName(
        t: String
    ) {
        mTextViewName.text = t
    }

    companion object {
        fun create(
            context: Context
        ): ViewHolderPermission {
            val card = CardView(
                context
            )
            val layout = ViewUtils.verticalLinearLayout(
                context
            )
            val textViewName = AppCompatTextView(
                context
            )
            val textViewState = AppCompatTextView(
                context
            )


            Application.color(
                R.color.titleColor
            ).let {
                textViewName.setTextColor(
                    it
                )
                textViewState.setTextColor(
                    it
                )
            }

            Application.font(
                R.font.open_sans_bold,
                context
            ).let {
                textViewName.typeface = it
            }

            Application.font(
                R.font.open_sans_semi_bold,
                context
            ).let {
                textViewState.typeface = it
            }

            val height = Application.WIDTH * 0.31884f
            val width = Application.WIDTH * 0.8937f


            card.size(
                width = width.toInt(),
                height = height.toInt()
            )


            layout.addView(
                textViewName
            )
            layout.addView(
                textViewState
            )

            card.addView(
                layout
            )

            return ViewHolderPermission(
                card,
                textViewName,
                textViewState
            )
        }
    }

}