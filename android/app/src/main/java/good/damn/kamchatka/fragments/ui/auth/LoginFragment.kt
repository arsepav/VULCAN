package good.damn.kamchatka.fragments.ui.auth

import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.WorkerThread
import androidx.core.view.isVisible
import good.damn.kamchatka.Application
import good.damn.kamchatka.R
import good.damn.kamchatka.extensions.boundsFrame
import good.damn.kamchatka.extensions.boundsLinear
import good.damn.kamchatka.fragments.ui.MainContentFragment
import good.damn.kamchatka.models.TokenAuth
import good.damn.kamchatka.services.AuthService
import good.damn.kamchatka.services.TokenService
import good.damn.kamchatka.utils.NotifyUtils
import good.damn.kamchatka.utils.StyleUtils
import good.damn.kamchatka.utils.ViewUtils
import good.damn.kamchatka.views.button.ButtonRound
import good.damn.kamchatka.views.text_fields.TextFieldRound
import good.damn.kamchatka.views.text_fields.TextFieldRoundPassword
import okhttp3.Response
import org.json.JSONObject

class LoginFragment
: LoginBaseFragment() {

    private val mAuthService = AuthService()

    private lateinit var mTextFieldEmail: TextFieldRound
    private lateinit var mTextFieldPassword: TextFieldRoundPassword
    private lateinit var mBtnLogin: ButtonRound
    private lateinit var mTextViewForgotPassword: TextView

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
        mBtnLogin = ButtonRound.createDefault(
            context,
            heightField,
            textId = R.string.log_in,
            textColorId = R.color.textColorBtn,
            backgroundColorId = R.color.accentColor
        )
        mTextViewForgotPassword = TextView(
            context
        )



        // Orientation
        layout.orientation = LinearLayout
            .VERTICAL




        // Text
        mBtnLogin.setText(
            R.string.log_in
        )
        mTextViewForgotPassword.setText(
            R.string.forgetPassword
        )



        // Text color
        mTextViewForgotPassword.setTextColor(
            Application.color(
                R.color.accentColor
            )
        )



        // Font
        mTextViewForgotPassword.typeface = Application.font(
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
        mBtnLogin.boundsLinear(
            Gravity.CENTER_HORIZONTAL,
            width = (measureUnit * 0.925f).toInt(),
            height = (measureUnit * 0.128f).toInt(),
            top = measureUnit * 0.111f
        )
        mTextViewForgotPassword.boundsLinear(
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
            mBtnLogin
        )
        layout.addView(
            mTextViewForgotPassword
        )

        layout.post {
            layout.boundsFrame(
                Gravity.CENTER,
                bottom = layout.height * 0.189f
            )
        }

        mBtnLogin.setOnClickListener(
            this::onClickBtnLogin
        )

        mTextViewForgotPassword.setOnClickListener(
            this::onClickTextViewForgotPassword
        )

        return layout
    }

    override fun onClickBtnBack(
        view: View
    ) {
        popFragment()
    }



    private fun onClickBtnLogin(
        view: View
    ) {
        val email = mTextFieldEmail
            .text?.toString() ?: return

        val password = mTextFieldPassword
            .text?.toString() ?: return

        if (NotifyUtils.notifyBlankField(
            email,
            mTextFieldEmail
        )) return


        if (NotifyUtils.notifyBlankField(
            password,
            mTextFieldPassword
        )) return

        enableInteraction(false)
        mAuthService.token(
            email,
            password,
            this::onResponseToken
        )

    }


    @WorkerThread
    private fun onResponseToken(
        response: Response
    ) {
        Application.ui {

            val context = context
                ?: return@ui

            if (response.code == 401) {
                Application.toast(
                    R.string.incorrect_password,
                    context
                )
                enableInteraction(true)
                return@ui
            }

            val body = response.body?.string()

            if (response.code != 200 || body == null) {
                Application.toast(
                    "Error: ${response.code} $body",
                    context
                )
                enableInteraction(true)
                return@ui
            }

            val tokenAuth = TokenAuth.createFromJSON(
                JSONObject(
                    body
                )
            )

            val tokenService = TokenService(
                context.getSharedPreferences(
                    Application.KEY_SHARED,
                    Context.MODE_PRIVATE
                )
            )

            tokenService.token = tokenAuth
            tokenService.saveToken()

            Application.TOKEN = tokenAuth

            pushFragment(
                MainContentFragment()
            )
            removeFragment()
        }
    }

    private fun enableInteraction(
        b: Boolean
    ) {
        mBtnLogin.isEnabled = b
        mTextViewForgotPassword.isEnabled = b
    }

}

private fun LoginFragment.onClickTextViewForgotPassword(
    view: View
) {
    pushFragment(
        ForgotPasswordLoginFragment()
    )
}