package good.damn.kamchatka.views.textviews

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.SpannableString
import android.text.Spanned
import android.text.style.DynamicDrawableSpan
import android.text.style.ImageSpan
import androidx.appcompat.widget.AppCompatTextView
import good.damn.kamchatka.Application
import good.damn.kamchatka.R

class ImageSpanTextView(
    context: Context
): AppCompatTextView(
    context
) {
    fun setImageSpan(
        drawable: Drawable?,
        at: Int
    ) {
        if (drawable == null) {
            return
        }

        val span = SpannableString(text)
        span.setSpan(
            ImageSpan(
                drawable,
                DynamicDrawableSpan.ALIGN_BASELINE
            ),
            at,
            at+1,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        text = span
    }

}