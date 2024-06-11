package good.damn.kamchatka.fragments.ui.auth

import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import good.damn.kamchatka.Application
import good.damn.kamchatka.R
import good.damn.kamchatka.extensions.boundsFrame
import good.damn.kamchatka.extensions.boundsLinear
import good.damn.kamchatka.utils.StyleUtils
import good.damn.kamchatka.utils.ViewUtils
import good.damn.kamchatka.views.button.ButtonRound
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

        val offsetBetween = 0.0289f * measureUnit;


        // Allocating views
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
        val btnLogin = ButtonRound.createDefault(
            context,
            heightField,
            textId = R.string.log_in,
            textColorId = R.color.textColorBtn,
            backgroundColorId = R.color.accentColor
        )
        val textViewForgetPas = TextView(
            context
        )



        // Orientation
        layout.orientation = LinearLayout
            .VERTICAL




        // Text
        btnLogin.setText(
            R.string.log_in
        )
        textViewForgetPas.setText(
            R.string.forgetPassword
        )



        // Text color
        textViewForgetPas.setTextColor(
            Application.color(
                R.color.accentColor
            )
        )



        // Font
        textViewForgetPas.typeface = Application.font(
            R.font.open_sans_bold,
            context
        )




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
            Gravity.CENTER
        )
        textViewBack.boundsLinear(
            Gravity.CENTER_HORIZONTAL
        )
        mTextFieldEmail.boundsLinear(
            Gravity.CENTER_HORIZONTAL,
            width = widthField,
            height = heightField,
            top = measureUnit * 0.099f
        )
        mTextFieldPassword.boundsLinear(
            Gravity.CENTER_HORIZONTAL,
            width = widthField,
            height = heightField,
            top = offsetBetween
        )
        btnLogin.boundsLinear(
            Gravity.CENTER_HORIZONTAL,
            width = (measureUnit * 0.925f).toInt(),
            height = (measureUnit * 0.128f).toInt(),
            top = measureUnit * 0.111f
        )
        textViewForgetPas.boundsLinear(
            Gravity.CENTER_HORIZONTAL,
            top = measureUnit * 0.04589f
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
        layout.addView(
            btnLogin
        )
        layout.addView(
            textViewForgetPas
        )

        layout.post {
            layout.boundsFrame(
                Gravity.CENTER,
                bottom = layout.height * 0.189f
            )
        }

        textViewForgetPas.setOnClickListener(
            this::onClickTextViewForgotPassword
        )

        return layout
    }

    override fun onClickBtnBack(
        view: View
    ) {
        popFragment()
    }
}

private fun LoginFragment.onClickTextViewForgotPassword(
    view: View
) {
    pushFragment(
        ForgotPasswordLoginFragment()
    )
}