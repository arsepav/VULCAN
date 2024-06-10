package good.damn.kamchatka.fragments.ui

import android.content.Context
import android.text.method.PasswordTransformationMethod
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import good.damn.kamchatka.Application
import good.damn.kamchatka.R
import good.damn.kamchatka.extensions.boundsLinear
import good.damn.kamchatka.fragments.StackFragment
import good.damn.kamchatka.views.TextField

class AuthFragment
: StackFragment() {

    companion object {
        private const val TAG = "AuthFragment"
    }

    private lateinit var mEditTextLastName: TextField
    private lateinit var mEditTextFirstName: TextField
    private lateinit var mEditTextSecondName: TextField
    private lateinit var mEditTextTelephone: TextField
    private lateinit var mEditTextEmail: TextField
    private lateinit var mEditTextPassword: TextField
    private lateinit var mEditTextPasswordRepeat: TextField

    override fun onCreateView(
        context: Context
    ): View {

        val measureUnit = Application.WIDTH
        val widthField = (measureUnit * 0.816f)
            .toInt()





        // Allocating views
        val layout = LinearLayout(
            context
        )
        val textViewLetSign = TextView(
            context
        )
        mEditTextLastName = TextField(
            context
        )
        mEditTextFirstName = TextField(
            context
        )
        mEditTextSecondName = TextField(
            context
        )
        mEditTextTelephone = TextField(
            context
        )
        mEditTextEmail = TextField(
            context
        )
        mEditTextPassword = TextField(
            context
        )
        mEditTextPasswordRepeat = TextField(
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

private fun AuthFragment.onClickBtnLogIn(
    view: View
) {
    pushFragment(
        MainNavigationFragment()
    )
    removeFragment()
}