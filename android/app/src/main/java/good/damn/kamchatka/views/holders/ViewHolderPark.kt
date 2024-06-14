package good.damn.kamchatka.views.holders

import android.content.Context
import android.graphics.drawable.Drawable
import android.media.Image
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.RecyclerView
import good.damn.kamchatka.Application
import good.damn.kamchatka.R
import good.damn.kamchatka.extensions.boundsLinear
import good.damn.kamchatka.extensions.setTextColorId
import good.damn.kamchatka.extensions.setTextPx
import good.damn.kamchatka.extensions.size

class ViewHolderPark(
    layout: LinearLayout,
    private val mImageView: ImageView,
    private val mTextViewName: TextView,
    private val mTextViewType: TextView
): RecyclerView.ViewHolder(
    layout
) {

    fun setPreview(
        d: Drawable?
    ) {
        mImageView.setImageDrawable(
            d
        )
    }

    fun setType(
        t: String
    ) {
        mTextViewType.text = t
    }

    fun setName(
        t: String
    ) {
        mTextViewName.text = t
    }

    companion object {
        fun create(
            recyclerViewHeight: Float,
            context: Context
        ): ViewHolderPark {

            val imageViewWidth = (recyclerViewHeight * 0.673553f)
                .toInt()

            val layout = LinearLayout(
                context
            )
            val imageView = ImageView(
                context
            )
            val textViewName = AppCompatTextView(
                context
            )
            val textViewType = AppCompatTextView(
                context
            )

            layout.orientation = LinearLayout
                .VERTICAL

            // Gravity
            textViewName.gravity = Gravity
                .CENTER_HORIZONTAL
            textViewType.gravity = Gravity
                .CENTER_HORIZONTAL




            // Font
            textViewName.typeface = Application.font(
                R.font.open_sans_bold,
                context
            )
            textViewType.typeface = Application.font(
                R.font.nunito_regular,
                context
            )


            // Text size
            textViewName.setTextPx(
                recyclerViewHeight * 0.072f
            )
            textViewType.setTextPx(
                recyclerViewHeight * 0.0555f
            )


            // Background color 0
            layout.setBackgroundColor(0)
            imageView.setBackgroundColor(0)


            // Text Color
            textViewName.setTextColorId(
                R.color.titleColor
            )
            textViewType.setTextColorId(
                R.color.titleColor
            )


            // Alpha
            textViewType.alpha = 0.5f




            // Layout params
            layout.size(
                height = recyclerViewHeight.toInt()
            )
            imageView.boundsLinear(
                width = imageViewWidth,
                height = imageViewWidth
            )
            textViewName.boundsLinear(
                Gravity.CENTER_HORIZONTAL,
                top = (recyclerViewHeight * 0.05782f)
            )
            textViewType.boundsLinear(
                Gravity.CENTER_HORIZONTAL,
                top = (recyclerViewHeight * 0.01f)
            )






            // Adding views
            layout.addView(
                imageView
            )
            layout.addView(
                textViewName
            )
            layout.addView(
                textViewType
            )

            return ViewHolderPark(
                layout,
                imageView,
                textViewName,
                textViewType
            )
        }
    }
}