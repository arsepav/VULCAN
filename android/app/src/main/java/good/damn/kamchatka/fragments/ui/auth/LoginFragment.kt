package good.damn.kamchatka.fragments.ui.auth

import android.content.Context
import android.icu.util.MeasureUnit
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import good.damn.kamchatka.Application
import good.damn.kamchatka.R
import good.damn.kamchatka.extensions.boundsFrame
import good.damn.kamchatka.extensions.boundsLinear
import good.damn.kamchatka.fragments.StackFragment
import good.damn.kamchatka.utils.StyleUtils
import good.damn.kamchatka.utils.ViewUtils
import good.damn.kamchatka.views.text_fields.TextFieldRound
import good.damn.kamchatka.views.text_fields.TextFieldRoundPassword

class LoginFragment
: LoginBaseFragment() {

    private lateinit var mTextFieldEmail: TextFieldRound
    private lateinit var mTextFieldPassword: TextFieldRoundPassword

    override fun onCreateContentFrameView(
        context: Context,
        measureUnit: Int
    ): View {

        val heightField = (measureUnit * 0.1135f)
            .toInt()
        val widthField = (measureUnit * 0.816f)
            .toInt()

        val offsetBetween = 0.0289f * measureUnit


        val heightContent = heightField + offsetBetween

        val layout = LinearLayout(
            context
        )
        val textViewBack = ViewUtils.titleBig(
            context,
            R.string.youAreBack,
            measureUnit
        )
        mTextFieldEmail = TextFieldRound(
            context
        )
        mTextFieldPassword = TextFieldRoundPassword(
            context
        )

        // Orientation
        layout.orientation = LinearLayout
            .VERTICAL


        // Style
        val fieldColor2 = Application.color(
            R.color.signInStrokeColor2
        )
        StyleUtils.textFieldRoundAuth(
            mTextFieldEmail,
            R.string.email,
            fieldColor2,
            heightField
        )

        StyleUtils.textFieldRoundAuth(
            mTextFieldPassword,
            R.string.password,
            fieldColor2,
            heightField
        )

        // Bounds
        layout.boundsFrame(
            Gravity.CENTER,
            bottom = heightContent * 0.1f
        )
        textViewBack.boundsLinear(
            Gravity.CENTER_HORIZONTAL
        )
        mTextFieldEmail.boundsLinear(
            Gravity.CENTER_HORIZONTAL,
            width = widthField,
            height = heightField
        )
        mTextFieldPassword.boundsLinear(
            Gravity.CENTER_HORIZONTAL,
            width = widthField,
            height = heightField,
            top = offsetBetween
        )




        // Adding views
        layout.addView(
            textViewBack
        )
        layout.addView(
            mTextFieldEmail
        )
        layout.addView(
            mTextFieldPassword
        )

        return layout
    }

    override fun onClickBtnBack(
        view: View
    ) {
        pushFragment(
            SignInFragment()
        )
        removeFragment()
    }
}