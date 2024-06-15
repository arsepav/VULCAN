package good.damn.kamchatka.utils

import android.content.Context
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import good.damn.kamchatka.Application
import good.damn.kamchatka.R
import good.damn.kamchatka.extensions.setTextPx
import good.damn.kamchatka.models.Color
import good.damn.kamchatka.views.button.ButtonBack
import good.damn.kamchatka.views.button.ButtonRound
import good.damn.kamchatka.views.text_fields.TextFieldRound

class ViewUtils {
   companion object {

       fun verticalLinearLayout(
           context: Context
       ): LinearLayout {
           val layout = LinearLayout(
               context
           )

           layout.orientation = LinearLayout
               .VERTICAL

           layout.setBackgroundColor(
               Application.color(
                   R.color.background
               )
           )

           return layout
       }

       fun vulcanTextView(
           @StringRes id: Int,
           context: Context,
           measureUnit: Int
       ): TextView {
           val textView = TextView(
               context
           )

           val vulkan = context.getString(
               R.string.apologize_vulkan
           )

           val msg = context.getString(
               id
           )

           val span = SpannableString(
               "$msg\n\n$vulkan"
           )

           span.setSpan(
               object : ClickableSpan() {
                   override fun onClick(widget: View) = Unit

                   override fun updateDrawState(ds: TextPaint) {
                       super.updateDrawState(ds)
                       ds.typeface = Application.font(
                           R.font.open_sans_bold,
                           context
                       )
                       ds.color = Application.color(
                           R.color.accentColor
                       )
                       ds.textSize = measureUnit * 0.032f
                       ds.isUnderlineText = false
                   }
               },
               0, msg.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
           )

           val from = msg.length+2
           span.setSpan(
               object : ClickableSpan() {
                   override fun onClick(widget: View) = Unit

                   override fun updateDrawState(ds: TextPaint) {
                       super.updateDrawState(ds)
                       ds.typeface = Application.font(
                           R.font.open_sans_extra_bold,
                           context
                       )
                       ds.color = Color.parseFromHexId(
                           R.color.titleColor,
                           0.2f
                       )
                       ds.textSize = measureUnit * 0.035f
                       ds.isUnderlineText = false
                   }
               },
               from, from + vulkan.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
           )

           textView.text = span

           return textView
       }

       fun policyTerms(
           context: Context,
           measureUnit: Int
       ): TextView {
           val textView = TextView(
               context
           )
           textView.setText(
               R.string.policy_terms
           )
           textView.typeface = Application.font(
               R.font.open_sans_semi_bold,
               context
           )
           textView.gravity = Gravity
               .CENTER_HORIZONTAL
           textView.setTextColor(
               Application.color(
                   R.color.policyTerms
               )
           )
           textView.setTextPx(
               measureUnit * 0.02657f
           )
           return textView
       }

       fun titleBig(
           context: Context,
           @StringRes id: Int,
           measureUnit: Int
       ): View {
           val textView = TextView(
               context
           )

           textView.setText(
               id
           )

           textView.typeface = Application.font(
               R.font.open_sans_extra_bold,
               context
           )
           textView.gravity = Gravity
               .CENTER_HORIZONTAL
           textView.setTextColor(
               Application.color(
                   R.color.titleColor
               )
           )
           textView.setTextPx(
               measureUnit * 0.0676f
           )

           return textView
       }

   }
}