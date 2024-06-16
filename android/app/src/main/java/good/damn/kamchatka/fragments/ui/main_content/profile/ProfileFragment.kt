package good.damn.kamchatka.fragments.ui.main_content.profile

import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import good.damn.kamchatka.Application
import good.damn.kamchatka.R
import good.damn.kamchatka.extensions.bottom
import good.damn.kamchatka.extensions.boundsFrame
import good.damn.kamchatka.extensions.height
import good.damn.kamchatka.extensions.left
import good.damn.kamchatka.extensions.setImageDrawableId
import good.damn.kamchatka.extensions.setTextPx
import good.damn.kamchatka.extensions.textSizeBounds
import good.damn.kamchatka.extensions.top
import good.damn.kamchatka.fragments.StackFragment
import good.damn.kamchatka.utils.ViewUtils
import good.damn.kamchatka.views.RoundedImageView
import good.damn.kamchatka.views.button.ButtonBack
import good.damn.kamchatka.views.special.profile.CardViewRequest

class ProfileFragment
: StackFragment() {

    override fun onCreateView(
        context: Context,
        measureUnit: Int
    ): View {


        // Allocating views
        val layout = FrameLayout(
            context
        )
        val btnBack = ButtonBack.createDefaultFrame(
            measureUnit,
            Application.color(
                R.color.btnBackArrow
            ),
            context
        )
        val textViewAppName = AppCompatTextView(
            context
        )
        val imageViewAvatar = RoundedImageView(
            context
        )
        val textViewHello = AppCompatTextView(
            context
        )
        val cardViewRequest = CardViewRequest(
            context
        )
        val btnReport = AppCompatButton(
            context
        )
        val vulcanMsg = ViewUtils.vulcanTextView(
            R.string.profile_msg,
            context,
            measureUnit
        )



        // Text
        textViewAppName.isAllCaps = true
        textViewAppName.setText(
            R.string.app_name
        )
        textViewHello.text = "${getString(R.string.hello)} ${Application.TOKEN?.name}"


        btnReport.setText(
            "Report"
        )

        // Drawable
        imageViewAvatar.setImageDrawable(
            R.drawable.icon
        )


        // Text Color
        textViewAppName.setTextColor(
            Application.color(
                R.color.accentColor
            )
        )
        textViewHello.setTextColor(
            Application.color(
                R.color.titleColor
            )
        )



        // Font
        textViewAppName.typeface = Application.font(
            R.font.open_sans_bold,
            context
        )
        textViewHello.typeface = Application.font(
            R.font.open_sans_semi_bold,
            context
        )


        // Text Size
        textViewAppName.setTextPx(
            measureUnit * 0.0366f
        )
        textViewHello.setTextPx(
            measureUnit * 0.04685f
        )






        // Background color
        layout.setBackgroundColor(
            Application.color(
                R.color.background
            )
        )
        cardViewRequest.setCardBackgroundColor(
            Application.color(
                R.color.background
            )
        )




        // LayoutParams
        textViewAppName.boundsFrame(
            Gravity.CENTER_HORIZONTAL,
            top = btnBack.bottom() + measureUnit * 0.08454f
        )
        imageViewAvatar.boundsFrame(
            Gravity.CENTER_HORIZONTAL,
            size = (measureUnit * 0.2028f).toInt(),
            top = textViewAppName.top() + textViewAppName.textSizeBounds() + (measureUnit * 0.0483f)
        )
        textViewHello.boundsFrame(
            Gravity.CENTER_HORIZONTAL,
            top = imageViewAvatar.bottom() + (measureUnit * 0.0483f)
        )
        cardViewRequest.boundsFrame(
            Gravity.CENTER_HORIZONTAL,
            width = (measureUnit * 0.88164f).toInt(),
            height = (measureUnit * 0.3913f).toInt(),
            top = textViewHello.textSizeBounds() + textViewHello.top() + measureUnit * 0.0724f
        )
        btnReport.boundsFrame(
            Gravity.START,
            width = -1,
            top = cardViewRequest.bottom().toFloat()
        )
        vulcanMsg.boundsFrame(
            Gravity.START,
            top = Application.HEIGHT * 0.671f,
            left = btnBack.left() * 1.3f
        )



        // Corner radius
        imageViewAvatar.radius = imageViewAvatar.height() * 0.5f
        cardViewRequest.radius = cardViewRequest.height() * 0.1386f


        // Shadow
        cardViewRequest.cardElevation = cardViewRequest.height() * 0.05f




        // Adding views
        layout.addView(
            btnBack
        )
        layout.addView(
            textViewAppName
        )
        layout.addView(
            imageViewAvatar
        )
        layout.addView(
            textViewHello
        )
        layout.addView(
            cardViewRequest
        )
        layout.addView(
            btnReport
        )
        layout.addView(
            vulcanMsg
        )





        // Listeners
        btnBack.setOnClickListener(
            this::onClickBtnBack
        )
        cardViewRequest.setOnClickListener(
            this::onClickCardViewRequest
        )
        btnReport.setOnClickListener(
            this::onClickBtnReport
        )




        return layout
    }

    private fun onClickBtnReport(
        view: View
    ) {
        pushFragment(
            ReportEcologyFragment()
        )
    }

    private fun onClickCardViewRequest(
        view: View
    ) {
        pushFragment(
            ViewPermissionsFragment()
        )
    }

}

private fun ProfileFragment.onClickBtnBack(
    view: View
) {
    popFragment()
}