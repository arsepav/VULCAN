package good.damn.kamchatka.utils

import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import good.damn.kamchatka.Application
import good.damn.kamchatka.R
import good.damn.kamchatka.extensions.setTextPx
import good.damn.kamchatka.views.button.ButtonRound
import good.damn.kamchatka.views.text_fields.TextFieldRound

class ViewUtils {
   companion object {
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