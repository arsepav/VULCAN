package good.damn.kamchatka.fragments.ui.auth

import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import good.damn.kamchatka.Application
import good.damn.kamchatka.R
import good.damn.kamchatka.extensions.boundsFrame
import good.damn.kamchatka.extensions.boundsLinear
import good.damn.kamchatka.extensions.width
import good.damn.kamchatka.utils.StyleUtils
import good.damn.kamchatka.utils.ViewUtils
import good.damn.kamchatka.views.button.ButtonRound
import good.damn.kamchatka.views.text_fields.TextFieldRound

class ForgotPasswordLoginFragment
: LoginBaseFragment() {

    override fun onClickBtnBack(
        view: View
    ) {
        popFragment()
    }

    private lateinit var mTextFieldEmail: TextFieldRound
    private lateinit var mBtnSendCode: ButtonRound

    override fun onCreateContentFrameView(
        context: Context,
        measureUnit: Int
    ): View {

        val heightField = (measureUnit * 0.1135f)
            .toInt()

        val widthField = (measureUnit * 0.816f)
            .toInt()


        // Allocating views
        val layout = LinearLayout(
            context
        )
        val textViewBack = ViewUtils.titleBig(
            context,
            R.string.enter_your_email,
            measureUnit
        )
        val vulcanMsg = ViewUtils.vulcanTextView(
            R.string.msg_email,
            context,
            measureUnit
        )

        mTextFieldEmail = TextFieldRound(
            context
        )

        mBtnSendCode = ButtonRound.createDefault(
            context,
            heightField,
            textId = R.string.log_in,
            textColorId = R.color.textColorBtn,
            backgroundColorId = R.color.accentColor
        )



        // Orientation
        layout.orientation = LinearLayout
            .VERTICAL




        // Text
        mBtnSendCode.setText(
            R.string.next
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




        // Bounds
        layout.boundsFrame(
            Gravity.CENTER
        )
        textViewBack.boundsLinear(
            Gravity.CENTER_HORIZONTAL,
            top = measureUnit * 0.15f
        )
        mTextFieldEmail.boundsLinear(
            Gravity.CENTER_HORIZONTAL,
            width = widthField,
            height = heightField,
            top = measureUnit * 0.099f
        )
        mBtnSendCode.boundsLinear(
            Gravity.CENTER_HORIZONTAL,
            width = (measureUnit * 0.925f).toInt(),
            height = (measureUnit * 0.128f).toInt(),
            top = measureUnit * 0.111f
        )
        vulcanMsg.boundsLinear(
            Gravity.START,
            top = measureUnit * 0.2608f,
            left = (measureUnit - mBtnSendCode.width()) * 0.5f
        )




        // Adding views
        layout.addView(
            textViewBack
        )
        layout.addView(
            mTextFieldEmail
        )
        layout.addView(
            mBtnSendCode
        )
        layout.addView(
            vulcanMsg
        )

        layout.post {
            layout.boundsFrame(
                Gravity.CENTER,
                bottom = layout.height * 0.189f
            )
        }

        return layout
    }


}