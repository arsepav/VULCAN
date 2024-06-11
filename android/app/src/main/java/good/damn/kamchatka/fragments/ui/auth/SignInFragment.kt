package good.damn.kamchatka.fragments.ui.auth

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.StringRes
import androidx.annotation.WorkerThread
import okhttp3.Response
import good.damn.kamchatka.Application
import good.damn.kamchatka.R
import good.damn.kamchatka.extensions.boundsLinear
import good.damn.kamchatka.extensions.setTextPx
import good.damn.kamchatka.fragments.ui.MainNavigationFragment
import good.damn.kamchatka.fragments.ui.ScrollableFragment
import good.damn.kamchatka.services.AuthService
import good.damn.kamchatka.utils.StyleUtils
import good.damn.kamchatka.utils.ViewUtils
import good.damn.kamchatka.views.button.ButtonRound
import good.damn.kamchatka.views.text_fields.TextFieldRound
import good.damn.kamchatka.views.text_fields.TextFieldRoundPassword

class SignInFragment
: ScrollableFragment() {

    companion object {
        private const val TAG = "AuthFragment"
    }

    private val mAuthService = AuthService()

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

    override fun onCreateContentView(
        context: Context,
        measureUnit: Int
    ): View {

        val widthField = measureUnit * 0.816f
        val heightField = measureUnit * 0.1135f
        val heightBtnLogin = (measureUnit * 0.128f)
            .toInt()


        // Allocating views
        val layout = LinearLayout(
            context
        )
        val textViewLetSign = ViewUtils.titleBig(
            context,
            R.string.let_sign_in,
            measureUnit
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
        val btnSignIn = ButtonRound.createDefault(
            context,
            heightBtnLogin,
            textId = R.string.sign_in,
            textColorId = R.color.textColorBtn,
            backgroundColorId = R.color.titleColor
        )
        val textViewHaveAccount = TextView(
            context
        )


        // Some props
        layout.orientation = LinearLayout
            .VERTICAL



        // Fonts
        textViewPasswordInfo.typeface = Application.font(
            R.font.nunito_regular,
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
            R.string.lastName,
            fieldColor,
            heightField,
            widthField,
            topMargin = 0.11f * measureUnit
        )
        styleTextFieldRound(
            mEditTextFirstName,
            R.string.firstName,
            fieldColor,
            heightField,
            widthField,
            topMargin = offsetBetween
        )
        styleTextFieldRound(
            mEditTextSecondName,
            R.string.secondName,
            fieldColor,
            heightField,
            widthField,
            topMargin = offsetBetween
        )
        styleTextFieldRound(
            mEditTextTelephone,
            R.string.telephone,
            fieldColor2,
            heightField,
            widthField,
            topMargin = offsetPart
        )
        styleTextFieldRound(
            mEditTextEmail,
            R.string.email,
            fieldColor2,
            heightField,
            widthField,
            topMargin = offsetBetween
        )
        styleTextFieldRound(
            mEditTextPassword,
            R.string.password,
            fieldColor3,
            heightField,
            widthField,
            topMargin = offsetPart
        )
        styleTextFieldRound(
            mEditTextPasswordRepeat,
            R.string.passwordRepeat,
            fieldColor3,
            heightField,
            widthField,
            topMargin = 0.03381f * measureUnit
        )




        // TextSize
        textViewPasswordInfo.setTextPx(
            measureUnit * 0.03f
        )
        textViewHaveAccount.setTextPx(
            btnSignIn.textSize
        )

        // Text
        textViewPasswordInfo.setText(
            R.string.latin_spells_numbers
        )
        textViewHaveAccount.setText(
            R.string.have_an_account
        )


        // LinearLayout params
        textViewLetSign.boundsLinear(
            Gravity.CENTER_HORIZONTAL,
            top = 0.082f * measureUnit
        )
        textViewPasswordInfo.boundsLinear(
            Gravity.CENTER_HORIZONTAL,
            top = 0.01932f * measureUnit
        )
        textViewPolicy.boundsLinear(
            Gravity.CENTER_HORIZONTAL,
            top = 0.099f * measureUnit
        )
        btnSignIn.boundsLinear(
            Gravity.CENTER_HORIZONTAL,
            width = (measureUnit * 0.925f).toInt(),
            top = measureUnit * 0.10149f,
            height = heightBtnLogin
        )
        textViewHaveAccount.boundsLinear(
            Gravity.CENTER_HORIZONTAL,
            top = measureUnit * 0.04589f
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
            btnSignIn
        )
        layout.addView(
            textViewHaveAccount
        )

        btnSignIn.setOnClickListener(
            this::onClickBtnLogIn
        )
        textViewHaveAccount.setOnClickListener(
            this::onClickTextViewHaveAccount
        )

        return layout
    }


    private fun onClickBtnLogIn(
        view: View
    ) {
        getCredentials { email, password ->
            mAuthService.registerUser(
                email,
                password,
                this::onResponseRegister
            )
        }
    }

    @WorkerThread
    private fun onResponseRegister(
        response: Response
    ) {
        Application.ui {
            Log.d(TAG, "onResponseRegister: $response")
            val context = context
                ?: return@ui

            if (response.code != 200) {
                Application.toast(
                    "Error: ${response.code} ${response.body}",
                    context
                )
                return@ui
            }

            Application.toast(
                R.string.almostReady,
                context
            )

            getCredentials { email, password ->
                mAuthService.token(
                    email,
                    password,
                    this::onResponseToken
                )
            }
        }
    }

    @WorkerThread
    private fun onResponseToken(
        response: Response
    ) {
        Application.ui {

            Log.d(TAG, "onResponseToken: $response")
            
            val context = context
                ?: return@ui

            if (response.code != 200) {
                Application.toast(
                    "Error: ${response.code} ${response.body}",
                    context
                )
                return@ui
            }

            Application.toast(
                R.string.successRegister,
                context
            )

            pushFragment(
                MainNavigationFragment()
            )
            removeFragment()
        }
    }


    private fun getCredentials(
        completion: (String, String) -> Unit
    ) {
        val email = mEditTextEmail
            .text?.toString()

        val password = mEditTextPassword
            .text?.toString()

        if (email == null || password == null) {
            return
        }

        completion(
            email,
            password
        )
    }

}

private fun SignInFragment.styleTextFieldRound(
    field: TextFieldRound,
    @StringRes hintId: Int,
    @ColorInt strokeColor: Int,
    heightField: Float,
    widthField: Float,
    topMargin: Float
) {
    StyleUtils.textFieldRoundAuth(
        field,
        hintId,
        strokeColor,
        heightField.toInt()
    )
    field.boundsLinear(
        Gravity.CENTER_HORIZONTAL,
        width = widthField.toInt(),
        top = topMargin
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