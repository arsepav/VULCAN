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
import good.damn.kamchatka.Application
import good.damn.kamchatka.R
import good.damn.kamchatka.extensions.boundsFrame
import good.damn.kamchatka.extensions.boundsFrameRight
import good.damn.kamchatka.extensions.boundsLinear
import good.damn.kamchatka.extensions.height
import good.damn.kamchatka.extensions.setTextPx
import good.damn.kamchatka.extensions.size
import good.damn.kamchatka.models.map.AntroColors
import good.damn.kamchatka.utils.ViewUtils
import good.damn.kamchatka.views.LabelView
import org.w3c.dom.Text

class ViewHolderRoute(
    layout: LinearLayout,
    private val mPreview: CardView,
    private val mTextViewDanger: LabelView,
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
        mTextViewDanger.requestLayout()
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
            val textViewDangerRate = LabelView(
                context
            )


            preview.apply {
                cardElevation = 0.0f
                setCardBackgroundColor(
                    0xffcacaca.toInt()
                )
                (recyclerViewHeight * 0.5269f).toInt().let {
                    size ->
                    boundsLinear(
                        Gravity.START,
                        size = size
                    )
                    radius = size * 0.14203f
                    layout.size(
                        width = size,
                        height = recyclerViewHeight
                    )

                    textViewName.setTextPx(
                        size * 0.1154f
                    )

                    textViewName.boundsLinear(
                        Gravity.START,
                        top = size * 0.08394f
                    )

                    textViewDangerRate.boundsLinear(
                        Gravity.END,
                        height = (size * 0.15328f).toInt(),
                        width = (size * 0.19708f).toInt(),
                        top = size * 0.08759f,
                        left = size * 0.708f
                    )
                }


                addView(
                    textViewDangerRate
                )

            }

            textViewDangerRate.apply {
                cornerRadius = this.height() * 0.25f
                textColor = 0xffffffff.toInt()
            }

            Application.color(
                R.color.titleColor
            ).let {
                textViewName.setTextColor(
                    it
                )
            }

            Application.font(
                R.font.open_sans_semi_bold,
                context
            ).let {
                textViewName.typeface = it
            }


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