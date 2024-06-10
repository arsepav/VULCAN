package good.damn.kamchatka.utils

import android.content.Context
import android.view.Gravity
import android.widget.TextView
import good.damn.kamchatka.Application
import good.damn.kamchatka.R
import good.damn.kamchatka.extensions.setTextPx

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
   }
}