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
import good.damn.kamchatka.fragments.StackFragment
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

        val measureUnit = Application.WIDTH
        val widthField = (measureUnit * 0.816f)
            .toInt()
        val heightField = (measureUnit * 0.113f)
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

        val btnLogin = Button(
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


        // Styling TextFields
        val fieldColor = Application.color(
            R.color.mountainsColor
        )
        val fieldColor2 = Application.color(
            R.color.signInStrokeColor2
        )
        val fieldColor3 = Application.color(
            R.color.signInStrokeColor3
        )
        styleTextFieldRound(
            mEditTextLastName,
            fieldColor,
            heightField
        )
        styleTextFieldRound(
            mEditTextFirstName,
            fieldColor,
            heightField
        )
        styleTextFieldRound(
            mEditTextSecondName,
            fieldColor,
            heightField
        )
        styleTextFieldRound(
            mEditTextTelephone,
            fieldColor2,
            heightField
        )
        styleTextFieldRound(
            mEditTextEmail,
            fieldColor2,
            heightField
        )
        styleTextFieldRound(
            mEditTextPassword,
            fieldColor3,
            heightField
        )
        styleTextFieldRound(
            mEditTextPasswordRepeat,
            fieldColor3,
            heightField
        )

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
            Gravity.CENTER_HORIZONTAL,
            -2,
            -2
        )
        mEditTextLastName.boundsLinear(
            Gravity.CENTER_HORIZONTAL,
            width = widthField,
        )
        mEditTextFirstName.boundsLinear(
            Gravity.CENTER_HORIZONTAL,
            width = widthField
        )
        mEditTextSecondName.boundsLinear(
            Gravity.CENTER_HORIZONTAL,
            width = widthField
        )
        mEditTextTelephone.boundsLinear(
            Gravity.CENTER_HORIZONTAL,
            width = widthField
        )
        mEditTextEmail.boundsLinear(
            Gravity.CENTER_HORIZONTAL,
            width = widthField
        )
        mEditTextPassword.boundsLinear(
            Gravity.CENTER_HORIZONTAL,
            width = widthField
        )
        mEditTextPasswordRepeat.boundsLinear(
            Gravity.CENTER_HORIZONTAL,
            width = widthField
        )
        textViewHaveAccount.boundsLinear(
            Gravity.CENTER_HORIZONTAL,
            -2,
            -2
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
            btnLogin,
            -1,
            -2
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
    heightField: Int
) {
    field.cornerRadius = heightField * 0.5f
    field.setStrokeColor(
        strokeColor
    )
    field.setStrokeWidth(
        heightField * 0.042f
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