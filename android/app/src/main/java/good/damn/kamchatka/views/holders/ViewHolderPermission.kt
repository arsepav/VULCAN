package good.damn.kamchatka.views.holders

import android.content.Context
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
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
                layout,
                textViewName,
                textViewState
            )
        }
    }

}