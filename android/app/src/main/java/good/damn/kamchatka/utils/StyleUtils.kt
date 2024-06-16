package good.damn.kamchatka.utils

import android.content.Context
import android.graphics.Typeface
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.text.style.StyleSpan
import android.text.style.TypefaceSpan
import android.view.Gravity
import android.view.View
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.StringRes
import good.damn.kamchatka.Application
import good.damn.kamchatka.R
import good.damn.kamchatka.extensions.boundsLinear
import good.damn.kamchatka.extensions.setTextPx
import good.damn.kamchatka.models.Color
import good.damn.kamchatka.views.text_fields.TextFieldRound

class StyleUtils {
    companion object {

        fun textFieldRoundAuth(
            field: TextFieldRound,
            @StringRes hintId: Int,
            @ColorInt strokeColor: Int,
            heightField: Int
        ) {
            field.cornerRadius = heightField * 0.5f
            field.setStrokeColor(
                strokeColor
            )
            field.setStrokeWidth(
                heightField * 0.042f
            )

            field.typeface = Application.font(
                R.font.open_sans_semi_bold,
                field.context
            )

            field.setHint(
                hintId
            )

            field.setTextPx(
                heightField * 0.319f
            )

            field.gravity = Gravity
                .CENTER_HORIZONTAL

            field.setTextColor(
                Application.color(
                    R.color.accentColor
                )
            )

            field.setHintTextColor(
                Application.color(
                    R.color.accentColor30
                )
            )
        }
    }
}