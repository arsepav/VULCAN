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
import good.damn.kamchatka.extensions.boundsLinear
import good.damn.kamchatka.extensions.height
import good.damn.kamchatka.extensions.left
import good.damn.kamchatka.extensions.setImageDrawableId
import good.damn.kamchatka.extensions.setTextPx
import good.damn.kamchatka.extensions.textSizeBounds
import good.damn.kamchatka.extensions.top
import good.damn.kamchatka.fragments.StackFragment
import good.damn.kamchatka.fragments.ui.ScrollableFragment
import good.damn.kamchatka.models.Color
import good.damn.kamchatka.utils.ViewUtils
import good.damn.kamchatka.views.RoundedImageView
import good.damn.kamchatka.views.button.ButtonBack
import good.damn.kamchatka.views.special.profile.CardViewRequest

class ProfileFragment
: ScrollableFragment() {

    override fun onCreateContentView(
        context: Context,
        measureUnit: Int
    ): View {


        // Allocating views
        val layout = ViewUtils.verticalLinearLayout(
            context
        )
        val btnBack = ButtonBack.createDefaultLinear(
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
        val cardViewRequest = AppCompatButton(
            context
        )
        val btnReport = AppCompatButton(
            context
        )
        val notification = AppCompatTextView(
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

        notification.setText(
            R.string.notify_people
        )

        cardViewRequest.setText(
            "Посмотреть все заявки"
        )

        btnReport.setText(
            "Сообщить о проблеме"
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
        notification.setTextColor(
            Color.parseFromHexId(
                R.color.titleColor,
                0.3f
            )
        )

        // Font
        textViewHello.typeface = Application.font(
            R.font.open_sans_semi_bold,
            context
        )

        Application.font(
            R.font.open_sans_bold,
            context
        )?.let {
            notification.typeface = it
            textViewAppName.typeface = it
        }






        // Text Size
        textViewAppName.setTextPx(
            measureUnit * 0.0366f
        )
        textViewHello.setTextPx(
            measureUnit * 0.04685f
        )
        notification.setTextPx(
            measureUnit * 0.02946f
        )






        // Background color
        layout.setBackgroundColor(
            Application.color(
                R.color.background
            )
        )




        // LayoutParams
        textViewAppName.boundsLinear(
            Gravity.CENTER_HORIZONTAL,
            top = measureUnit * 0.08454f
        )
        imageViewAvatar.boundsLinear(
            Gravity.CENTER_HORIZONTAL,
            size = (measureUnit * 0.2028f).toInt(),
            top = measureUnit * 0.0483f
        )
        textViewHello.boundsLinear(
            Gravity.CENTER_HORIZONTAL,
            top = measureUnit * 0.0483f
        )
        cardViewRequest.boundsLinear(
            Gravity.CENTER_HORIZONTAL,
            width = (measureUnit * 0.88164f).toInt(),
            height = (measureUnit * 0.3913f).toInt(),
            top = measureUnit * 0.0724f
        )
        btnReport.boundsLinear(
            Gravity.START,
            width = -1
        )
        notification.boundsLinear(
            Gravity.CENTER_HORIZONTAL,
            width = (measureUnit * 0.8357f).toInt(),
            top = measureUnit * 0.1f
        )
        vulcanMsg.boundsLinear(
            Gravity.START,
            top = measureUnit * 0.2f,
            left = btnBack.left() * 1.3f
        )



        // Corner radius
        imageViewAvatar.radius = imageViewAvatar.height() * 0.5f



        // Gravity
        notification.gravity = Gravity
            .CENTER_HORIZONTAL




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
            notification
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