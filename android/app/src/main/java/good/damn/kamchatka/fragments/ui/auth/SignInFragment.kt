package good.damn.kamchatka.fragments.ui.auth

import android.content.Context
import android.os.Bundle
import android.text.InputType
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
import good.damn.kamchatka.fragments.ui.MainContentFragment
import good.damn.kamchatka.fragments.ui.ScrollableFragment
import good.damn.kamchatka.models.TokenAuth
import good.damn.kamchatka.services.AuthService
import good.damn.kamchatka.services.TokenService
import good.damn.kamchatka.utils.StyleUtils
import good.damn.kamchatka.utils.ViewUtils
import good.damn.kamchatka.views.button.ButtonRound
import good.damn.kamchatka.views.text_fields.TextFieldRound
import good.damn.kamchatka.views.text_fields.TextFieldRoundPassword
import org.json.JSONException
import org.json.JSONObject
import good.damn.kamchatka.utils.NotifyUtils.Companion.notifyBlankField

class SignInFragment
: ScrollableFragment() {

    companion object {
        private const val TAG = "AuthFragment"
    }

    private var mAuthService: AuthService? = null
    private var mTokenService: TokenService? = null

    private var mEmail = ""
    private var mPassword = ""

    private lateinit var mEditTextSurname: TextFieldRound
    private lateinit var mEditTextFirstName: TextFieldRound
    private lateinit var mEditTextLastName: TextFieldRound
    private lateinit var mEditTextTelephone: TextFieldRound
    private lateinit var mEditTextEmail: TextFieldRound
    private lateinit var mEditTextPassword: TextFieldRound
    private lateinit var mEditTextPasswordRepeat: TextFieldRound
    private lateinit var mBtnSignIn: ButtonRound
    private lateinit var mTextViewHaveAccount: TextView

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

        activity?.let {
            mTokenService = TokenService(
                it.getSharedPreferences(
                    Application.KEY_SHARED,
                    Context.MODE_PRIVATE
                )
            )
        }
    }

    override fun onCreateContentView(
        context: Context,
        measureUnit: Int
    ): View {

        mAuthService = AuthService(
            context
        )

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
        mEditTextSurname = TextFieldRound(
            context
        )
        mEditTextFirstName = TextFieldRound(
            context
        )
        mEditTextLastName = TextFieldRound(
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
        mBtnSignIn = ButtonRound.createDefault(
            context,
            heightBtnLogin,
            textId = R.string.sign_in,
            textColorId = R.color.textColorBtn,
            backgroundColorId = R.color.titleColor
        )
        mTextViewHaveAccount = TextView(
            context
        )


        // Some props

        mEditTextTelephone.inputType = mEditTextTelephone.inputType or InputType.TYPE_CLASS_PHONE

        layout.orientation = LinearLayout
            .VERTICAL



        // Fonts
        textViewPasswordInfo.typeface = Application.font(
            R.font.nunito_regular,
            context
        )
        mTextViewHaveAccount.typeface = Application.font(
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
        mTextViewHaveAccount.setTextColor(
            Application.color(
                R.color.accentColor
            )
        )




        // Styling TextFields
        val offsetBetween = 0.0289f * measureUnit
        val offsetPart = 0.055f * measureUnit
        styleTextFieldRound(
            mEditTextSurname,
            R.string.surname,
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
            mEditTextLastName,
            R.string.lastName,
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
        mTextViewHaveAccount.setTextPx(
            mBtnSignIn.textSize
        )

        // Text
        textViewPasswordInfo.setText(
            R.string.latin_spells_numbers
        )
        mTextViewHaveAccount.setText(
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
        mBtnSignIn.boundsLinear(
            Gravity.CENTER_HORIZONTAL,
            width = (measureUnit * 0.925f).toInt(),
            top = measureUnit * 0.10149f,
            height = heightBtnLogin
        )
        mTextViewHaveAccount.boundsLinear(
            Gravity.CENTER_HORIZONTAL,
            top = measureUnit * 0.04589f
        )


        // Adding views
        layout.addView(
            textViewLetSign
        )
        layout.addView(
            mEditTextSurname
        )
        layout.addView(
            mEditTextFirstName
        )
        layout.addView(
            mEditTextLastName
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
            mBtnSignIn
        )
        layout.addView(
            mTextViewHaveAccount
        )

        mBtnSignIn.setOnClickListener(
            this::onClickBtnLogIn
        )
        mTextViewHaveAccount.setOnClickListener(
            this::onClickTextViewHaveAccount
        )

        return layout
    }


    private fun onClickBtnLogIn(
        view: View
    ) {

        val context = view.context

        val name = mEditTextFirstName
            .text?.toString() ?: return
        val surname = mEditTextSurname
            .text?.toString() ?: return
        val lastName = mEditTextLastName
            .text?.toString() ?: return
        val telephone = mEditTextTelephone
            .text?.toString() ?: return

        val email = mEditTextEmail
            .text?.toString() ?: return

        val password = mEditTextPassword
            .text?.toString() ?: return

        val passwordRepeat = mEditTextPasswordRepeat
            .text?.toString() ?: return

        if (notifyBlankField(
            surname,
            mEditTextSurname
        )) return

        if (notifyBlankField(
            name,
            mEditTextFirstName
        )) return

        if (notifyBlankField(
            lastName,
            mEditTextLastName
        )) return

        if (notifyBlankField(
            telephone,
            mEditTextTelephone
        )) return

        if (notifyBlankField(
            email,
            mEditTextEmail
        )) return

        if (notifyBlankField(
            password,
            mEditTextPassword
        )) return

        if (notifyBlankField(
            passwordRepeat,
            mEditTextPasswordRepeat
        )) return

        if (!password.contains("_")) {
            Application.toast(
                R.string.need_downscape,
                context
            )
            return
        }

        if (passwordRepeat != password) {
            Application.toast(
                R.string.passwords_not_equals,
                context
            )
            return
        }
        enableInteraction(false)

        mEmail = email
        mPassword = password

        mAuthService?.registerUser(
            email = email,
            password = password,
            name = name,
            surname = surname,
            lastname = lastName,
            telephone = telephone,
            this::onResponseRegister
        )
    }

    @WorkerThread
    private fun onResponseRegister(
        response: Response
    ) {
        Application.ui {

            Log.d(TAG, "onResponseRegister: $response")
            val body = response.body?.string() ?: return@ui

            Log.d(TAG, "onResponseRegister: $body")

            val context = context
                ?: return@ui

            if (response.code != 200) {
                Application.toast(
                    "Error: ${response.code} $body",
                    context
                )
                enableInteraction(true)
                return@ui
            }

            val json = JSONObject(
                body
            )

            val success = try {
                json.get(
                    "success"
                )
            } catch (e: JSONException) {
                null
            }

            Log.d(TAG, "onResponseToken: SUCCESS: $success ${success as? Boolean}")

            (success as? Boolean)?.let {
                if (!success) {
                    enableInteraction(true)
                    Application.toast(
                        R.string.account_exists,
                        context
                    )
                    return@ui
                }
            }

            Application.toast(
                R.string.almostReady,
                context
            )
            mAuthService?.token(
                mEmail,
                mPassword,
                this::onResponseToken
            )

        }
    }

    @WorkerThread
    private fun onResponseToken(
        response: Response
    ) {
        Application.ui {

            val context = context
                ?: return@ui
            val body = response.body?.string()

            Log.d(TAG, "onResponseToken: $response")
            Log.d(TAG, "onResponseToken: BODY $body")

            if (!(response.code == 200 || body == null)) {
                Application.toast(
                    "Error: ${response.code} $body",
                    context
                )
                enableInteraction(true)
                return@ui
            }
            val tokenAuth = TokenAuth.createFromJSON(
                mPassword,
                JSONObject(
                    body!!
                )
            )

            Log.d(TAG, "onResponseToken: TOKEN: $tokenAuth")

            tokenAuth.let {
                mTokenService?.token = it
                Application.TOKEN = it
                mTokenService?.saveToken()
            }

            Application.toast(
                R.string.successRegister,
                context
            )

            pushFragment(
                MainContentFragment()
            )
            removeFragment()
        }
    }

    private fun enableInteraction(
        b: Boolean
    ) {
        mBtnSignIn.isEnabled = b
        mTextViewHaveAccount.isEnabled = b
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