package good.damn.kamchatka.views.holders

import android.content.Context
import android.graphics.Bitmap
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import good.damn.kamchatka.Application
import good.damn.kamchatka.R
import good.damn.kamchatka.extensions.boundsLinear
import good.damn.kamchatka.extensions.height
import good.damn.kamchatka.extensions.setTextColorId
import good.damn.kamchatka.extensions.setTextPx
import good.damn.kamchatka.extensions.size
import good.damn.kamchatka.extensions.width

class ViewHolderOOPT(
    layout: LinearLayout,
    private val mImageView: AppCompatImageView,
    private val mTextViewName: AppCompatTextView,
    private val mTextViewType: AppCompatTextView
): RecyclerView.ViewHolder(
    layout
) {

    var imageSize = mImageView.width()
        private set

    fun setPreview(
        d: Bitmap?
    ) {
        mImageView.setImageBitmap(
            d
        )
    }

    fun setType(
        t: String?
    ) {
        mTextViewType.text = t
    }

    fun setName(
        t: String?
    ) {
        mTextViewName.text = t
    }

    companion object {
        fun create(
            recyclerViewHeight: Float,
            context: Context
        ): ViewHolderOOPT {

            val imageViewWidth = (recyclerViewHeight * 0.673553f)
                .toInt()

            val layout = LinearLayout(
                context
            )
            val cardView = CardView(
                context
            )
            val imageView = AppCompatImageView(
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


            imageView.adjustViewBounds = true
            imageView.scaleType = ImageView.ScaleType
                .CENTER_CROP


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

            cardView.setCardBackgroundColor(
                0xffc5c5c5.toInt()
            )

            cardView.cardElevation = 0.0f


            // Layout params
            layout.size(
                height = recyclerViewHeight.toInt()
            )
            cardView.boundsLinear(
                Gravity.CENTER_HORIZONTAL,
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


            cardView.apply {
                radius = height() * 0.15f
                addView(
                    imageView,
                    imageViewWidth,
                    imageViewWidth
                )
            }


            // Adding views
            layout.addView(
                cardView
            )
            layout.addView(
                textViewName
            )
            layout.addView(
                textViewType
            )

            return ViewHolderOOPT(
                layout,
                imageView,
                textViewName,
                textViewType
            )
        }
    }
}