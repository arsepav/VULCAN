package good.damn.kamchatka.fragments.ui.auth

import android.content.Context
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.ColorInt
import good.damn.kamchatka.Application
import good.damn.kamchatka.R
import good.damn.kamchatka.extensions.boundsLinear
import good.damn.kamchatka.extensions.setTextPx
import good.damn.kamchatka.fragments.StackFragment
import good.damn.kamchatka.fragments.ui.MainNavigationFragment
import good.damn.kamchatka.utils.ViewUtils
import good.damn.kamchatka.views.button.ButtonRound
import good.damn.kamchatka.views.text_fields.TextFieldRound
import good.damn.kamchatka.views.text_fields.TextFieldRoundPassword

class SignInFragment
: StackFragment() {

    companion object {
        private const val TAG = "AuthFragment"
    }

    private lateinit var mEditTextLastName: TextFieldRound
    private lateinit var mEditTextFirstName: TextFieldRound
    private lateinit var mEditTextSecondName: TextFieldRound
    private lateinit var mEditTextTelephone: TextFieldRound
    private lateinit var mEditTextEmail: TextFieldRound
    private lateinit var mEditTextPassword: TextFieldRound
    private lateinit var mEditTextPasswordRepeat: TextFieldRound

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        navigationBarColor(
            Application.color(
                R.color.background
            )
        )
    }

    override fun onCreateView(
        context: Context
    ): View {

        val measureUnit = Application.WIDTH
        val widthField = (measureUnit * 0.816f)
            .toInt()
        val heightField = (measureUnit * 0.1135f)
            .toInt()
        val heightBtnLogin = (measureUnit * 0.128f)
            .toInt()


        // Allocating views
        val layout = LinearLayout(
            context
        )
        val textViewLetSign = TextView(
            context
        )
        mEditTextLastName = TextFieldRound(
            context
        )
        mEditTextFirstName = TextFieldRound(
            context
        )
        mEditTextSecondName = TextFieldRound(
            context
        )
        mEditTextTelephone = TextFieldRound(
            context
        )
        mEditTextEmail = TextFieldRound(
            context
        )
        mEditTextPassword = TextFieldRoundPassword(
            context
        )
        val textViewPasswordInfo = TextView(
            context
        )
        mEditTextPasswordRepeat = TextFieldRoundPassword(
            context
        )
        val textViewPolicy = ViewUtils.policyTerms(
            context,
            measureUnit
        )
        val btnLogin = ButtonRound(
            context
        )
        val textViewHaveAccount = TextView(
            context
        )


        // Some props
        layout.orientation = LinearLayout
            .VERTICAL

        // Fonts
        textViewLetSign.typeface = Application.font(
            R.font.open_sans_extra_bold,
            context
        )
        textViewPasswordInfo.typeface = Application.font(
            R.font.nunito_regular,
            context
        )
        btnLogin.typeface = Application.font(
            R.font.open_sans_bold,
            context
        )
        textViewHaveAccount.typeface = Application.font(
            R.font.open_sans_bold,
            context
        )




        // Colors
        val fieldColor = Application.color(
            R.color.mountainsColor
        )
        val fieldColor2 = Application.color(
            R.color.signInStrokeColor2
        )
        val fieldColor3 = Application.color(
            R.color.signInStrokeColor3
        )
        textViewLetSign.setTextColor(
            Application.color(
                R.color.titleColor
            )
        )
        btnLogin.setBackgroundColor(
            Application.color(
                R.color.titleColor
            )
        )
        btnLogin.setTextColor(
            Application.color(
                R.color.textColorBtn
            )
        )

        textViewPasswordInfo.setTextColor(
            Application.color(
                R.color.largeTextColor
            )
        )
        textViewHaveAccount.setTextColor(
            Application.color(
                R.color.accentColor
            )
        )




        // Styling TextFields
        val offsetBetween = 0.0289f * measureUnit
        val offsetPart = 0.055f * measureUnit
        styleTextFieldRound(
            mEditTextLastName,
            fieldColor,
            heightField,
            widthField,
            topMargin = 0.11f * measureUnit
        )
        styleTextFieldRound(
            mEditTextFirstName,
            fieldColor,
            heightField,
            widthField,
            topMargin = offsetBetween
        )
        styleTextFieldRound(
            mEditTextSecondName,
            fieldColor,
            heightField,
            widthField,
            topMargin = offsetBetween
        )
        styleTextFieldRound(
            mEditTextTelephone,
            fieldColor2,
            heightField,
            widthField,
            topMargin = offsetPart
        )
        styleTextFieldRound(
            mEditTextEmail,
            fieldColor2,
            heightField,
            widthField,
            topMargin = offsetBetween
        )
        styleTextFieldRound(
            mEditTextPassword,
            fieldColor3,
            heightField,
            widthField,
            topMargin = offsetPart
        )
        styleTextFieldRound(
            mEditTextPasswordRepeat,
            fieldColor3,
            heightField,
            widthField,
            topMargin = 0.03381f * measureUnit
        )


        // Gravity
        textViewLetSign.gravity = Gravity
            .CENTER_HORIZONTAL



        // TextSize
        textViewLetSign.setTextPx(
            measureUnit * 0.0676f
        )
        textViewPasswordInfo.setTextPx(
            measureUnit * 0.03f
        )
        btnLogin.setTextPx(
            heightBtnLogin * 0.283f
        )
        textViewHaveAccount.setTextPx(
            btnLogin.textSize
        )

        // Corners
        btnLogin.cornerRadius = heightBtnLogin * 0.5f


        // Text
        textViewLetSign.setText(
            R.string.let_sign_in
        )
        mEditTextLastName.setHint(
            R.string.lastName
        )
        mEditTextFirstName.setHint(
            R.string.firstName
        )
        mEditTextSecondName.setHint(
            R.string.secondName
        )
        mEditTextTelephone.setHint(
            R.string.telephone
        )
        mEditTextEmail.setHint(
            R.string.email
        )
        mEditTextPassword.setHint(
            R.string.password
        )
        textViewPasswordInfo.setText(
            R.string.latin_spells_numbers
        )
        mEditTextPasswordRepeat.setHint(
            R.string.passwordRepeat
        )
        btnLogin.setText(
            R.string.sign_in
        )
        textViewHaveAccount.setText(
            R.string.have_an_account
        )


        // LinearLayout params
        textViewLetSign.boundsLinear(
            Gravity.CENTER_HORIZONTAL,
            top = (0.082f * measureUnit).toInt()
        )
        textViewPasswordInfo.boundsLinear(
            Gravity.CENTER_HORIZONTAL,
            top = (0.01932f * measureUnit).toInt()
        )
        textViewPolicy.boundsLinear(
            Gravity.CENTER_HORIZONTAL,
            top = (0.099f * measureUnit).toInt()
        )
        btnLogin.boundsLinear(
            Gravity.CENTER_HORIZONTAL,
            width = (measureUnit * 0.925f).toInt(),
            top = (measureUnit * 0.10149f).toInt(),
            height = heightBtnLogin
        )
        textViewHaveAccount.boundsLinear(
            Gravity.CENTER_HORIZONTAL,
            top = (measureUnit * 0.04589f).toInt()
        )


        // Adding views
        layout.addView(
            textViewLetSign
        )
        layout.addView(
            mEditTextLastName
        )
        layout.addView(
            mEditTextFirstName
        )
        layout.addView(
            mEditTextSecondName
        )
        layout.addView(
            mEditTextTelephone
        )
        layout.addView(
            mEditTextEmail
        )
        layout.addView(
            mEditTextPassword
        )
        layout.addView(
            textViewPasswordInfo
        )
        layout.addView(
            mEditTextPasswordRepeat
        )
        layout.addView(
            textViewPolicy
        )
        layout.addView(
            btnLogin
        )
        layout.addView(
            textViewHaveAccount
        )

        btnLogin.setOnClickListener(
            this::onClickBtnLogIn
        )
        textViewHaveAccount.setOnClickListener(
            this::onClickTextViewHaveAccount
        )

        return layout
    }

}

private fun SignInFragment.styleTextFieldRound(
    field: TextFieldRound,
    @ColorInt strokeColor: Int,
    heightField: Int,
    widthField: Int,
    topMargin: Float
) {
    field.cornerRadius = heightField * 0.5f
    field.setStrokeColor(
        strokeColor
    )
    field.setStrokeWidth(
        heightField * 0.042f
    )

    field.typeface = context?.let {
        Application.font(
            R.font.open_sans_semi_bold,
            it
        )
    }

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

    field.boundsLinear(
        Gravity.CENTER_HORIZONTAL,
        width = widthField,
        top = topMargin.toInt()
    )
}

private fun SignInFragment.onClickTextViewHaveAccount(
    view: View
) {
    pushFragment(
        LoginFragment()
    )
    removeFragment()
}

private fun SignInFragment.onClickBtnLogIn(
    view: View
) {
    pushFragment(
        MainNavigationFragment()
    )
    removeFragment()
}