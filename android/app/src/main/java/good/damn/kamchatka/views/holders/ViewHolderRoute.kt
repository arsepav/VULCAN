package good.damn.kamchatka.views.holders

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import good.damn.kamchatka.extensions.boundsFrame
import good.damn.kamchatka.extensions.boundsFrameRight
import good.damn.kamchatka.extensions.boundsLinear
import good.damn.kamchatka.extensions.setTextPx
import good.damn.kamchatka.models.map.AntroColors
import good.damn.kamchatka.utils.ViewUtils
import org.w3c.dom.Text

class ViewHolderRoute(
    layout: LinearLayout,
    private val mPreview: CardView,
    private val mTextViewDanger: TextView,
    private val mTextViewName: TextView
): RecyclerView.ViewHolder(
    layout
) {

    fun setPreview(
        d: Bitmap?
    ) {
        mPreview.background = BitmapDrawable(
            mPreview.context.resources,
            d
        )
    }

    fun setName(
        v: String?
    ) {
        mTextViewName.text = v
    }

    fun setDangerRate(
        v: Int
    ) {
        mTextViewDanger.text = "${v+1}"
        mTextViewDanger.setBackgroundColor(
            AntroColors.colors[v]
        )
    }

    companion object {
        fun create(
            recyclerViewHeight: Int,
            context: Context
        ): ViewHolderRoute {
            val layout = ViewUtils.verticalLinearLayout(
                context
            )
            val preview = CardView(
                context
            )
            val textViewName = AppCompatTextView(
                context
            )
            val textViewDangerRate = AppCompatTextView(
                context
            )

            preview.apply {
                cardElevation = 0.0f
                setCardBackgroundColor(
                    0xffcacaca.toInt()
                )
                (recyclerViewHeight * 0.5269f).toInt().let {
                    boundsLinear(
                        Gravity.START,
                        width = it,
                        height = it
                    )
                    textViewDangerRate.setTextPx(
                        it * 0.1532f
                    )
                }


                textViewDangerRate.boundsFrameRight(
                    Gravity.END,
                    top = recyclerViewHeight * 0.08759f
                )

                addView(
                    textViewDangerRate
                )

            }

            textViewName.boundsLinear(
                Gravity.START
            )



            layout.addView(preview)
            layout.addView(textViewName)


            return ViewHolderRoute(
                layout,
                preview,
                textViewDangerRate,
                textViewName
            )
        }
    }
}