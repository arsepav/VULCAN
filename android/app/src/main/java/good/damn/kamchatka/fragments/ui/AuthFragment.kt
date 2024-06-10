package good.damn.kamchatka.fragments.ui

import android.content.Context
import android.text.method.PasswordTransformationMethod
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.ColorInt
import good.damn.kamchatka.Application
import good.damn.kamchatka.R
import good.damn.kamchatka.extensions.boundsLinear
import good.damn.kamchatka.extensions.setTextPx
import good.damn.kamchatka.fragments.StackFragment
import good.damn.kamchatka.views.button.ButtonRound
import good.damn.kamchatka.views.text_fields.TextField
import good.damn.kamchatka.views.text_fields.TextFieldRound

class AuthFragment
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

    override fun onCreateView(
        context: Context
    ): View {

        val width = Application.WIDTH
        val height = Application.HEIGHT
        val widthField = (width * 0.816f)
            .toInt()
        val heightField = (height * 0.051f)
            .toInt()
        val heightBtnLogin = (height * 0.057f)
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
        mEditTextPassword = TextFieldRound(
            context
        )
        mEditTextPasswordRepeat = TextFieldRound(
            context
        )

        val btnLogin = ButtonRound(
            context
        )
        val textViewHaveAccount = TextView(
            context
        )


        // Some props
        mEditTextPassword.transformationMethod =
            PasswordTransformationMethod()

        layout.orientation = LinearLayout
            .VERTICAL


        //Colors
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
        textViewHaveAccount.setTextColor(
            Application.color(
                R.color.accentColor
            )
        )


        // Fonts
        textViewLetSign.typeface = Application.font(
            R.font.open_sans_extra_bold,
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



        // Styling TextFields
        val offsetBetween = height * 0.013f
        val offsetPart = height * 0.025f
        styleTextFieldRound(
            mEditTextLastName,
            fieldColor,
            heightField,
            widthField,
            topMargin = height * 0.057f
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
            topMargin = offsetBetween
        )


        // Gravity
        textViewLetSign.gravity = Gravity
            .CENTER_HORIZONTAL



        // TextSize
        textViewLetSign.setTextPx(
            height * 0.03f
        )

        // Corners
        btnLogin.cornerRadius = heightBtnLogin * 0.5f


        // Text
        textViewLetSign.setHint(
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
        mEditTextPasswordRepeat.setHint(
            R.string.passwordRepeat
        )
        btnLogin.setText(
            R.string.sign_in
        )
        textViewHaveAccount.setHint(
            R.string.have_an_account
        )


        // LinearLayout params
        textViewLetSign.boundsLinear(
            Gravity.CENTER_HORIZONTAL
        )
        textViewHaveAccount.boundsLinear(
            Gravity.CENTER_HORIZONTAL
        )
        btnLogin.boundsLinear(
            Gravity.CENTER_HORIZONTAL,
            (width * 0.925f).toInt(),
            heightBtnLogin
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
            mEditTextPasswordRepeat
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

        layout.post {
            navigationBarColor(
                Application.color(
                    R.color.background
                )
            )
        }

        return layout
    }

}

private fun AuthFragment.styleTextFieldRound(
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

private fun AuthFragment.onClickBtnLogIn(
    view: View
) {
    pushFragment(
        MainNavigationFragment()
    )
    removeFragment()
}