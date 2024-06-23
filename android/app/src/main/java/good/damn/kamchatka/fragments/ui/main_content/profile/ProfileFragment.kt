package good.damn.kamchatka.fragments.ui.main_content.profile

import android.content.Context
import android.view.Gravity
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import good.damn.kamchatka.Application
import good.damn.kamchatka.R
import good.damn.kamchatka.extensions.boundsLinear
import good.damn.kamchatka.extensions.height
import good.damn.kamchatka.extensions.left
import good.damn.kamchatka.extensions.setTextPx
import good.damn.kamchatka.fragments.ui.ScrollableFragment
import good.damn.kamchatka.models.Color
import good.damn.kamchatka.utils.ViewUtils
import good.damn.kamchatka.views.RoundedImageView
import good.damn.kamchatka.views.button.ButtonBack
import good.damn.kamchatka.views.button.ButtonCard
import good.damn.kamchatka.views.button.CardState

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
        val textViewPermissions = ViewUtils.titleOption(
            measureUnit,
            R.string.permission_list,
            context
        )
        val textViewRequest = AppCompatTextView(
            context
        )
        val cardReport = ButtonCard(
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

        textViewRequest.setText(
            R.string.see_all_perm
        )

        cardReport.apply {
            title = getString(
                R.string.report_ecology
            )
            drawableEnd = Application.drawable(
                R.drawable.ic_info_red
            )
        }


        // Drawable
        imageViewAvatar.setImageDrawable(
            R.drawable.icon
        )


        // Text Color
        textViewAppName.setTextColor(
            Application.color(
                R.color.accentColor30
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
        textViewPermissions.boundsLinear(
            Gravity.START,
            left = btnBack.left(),
            top = measureUnit * 0.1207f
        )
        textViewRequest.boundsLinear(
            Gravity.CENTER_HORIZONTAL,
            width = (measureUnit * 0.88164f).toInt(),
            height = (measureUnit * 0.3913f).toInt(),
            top = measureUnit * 0.05193f
        )
        notification.boundsLinear(
            Gravity.CENTER_HORIZONTAL,
            width = (measureUnit * 0.8357f).toInt(),
            top = measureUnit * 0.07125f
        )
        cardReport.boundsLinear(
            Gravity.CENTER_HORIZONTAL,
            width = (measureUnit * 0.9082f).toInt(),
            height = (measureUnit * 0.215f).toInt(),
            top = measureUnit * 0.0483f
        )
        vulcanMsg.boundsLinear(
            Gravity.START,
            top = measureUnit * 0.1135f,
            left = btnBack.left() * 1.3f
        )



        // Corner radius
        imageViewAvatar.radius = imageViewAvatar.height() * 0.5f



        // Gravity
        notification.gravity = Gravity
            .CENTER_HORIZONTAL

        cardReport.apply {
            radius = height() * 0.191f
            layoutIt()
        }

        layout.apply {
            addView(btnBack)
            addView(textViewAppName)
            addView(imageViewAvatar)
            addView(textViewHello)
            addView(textViewPermissions)
            addView(textViewRequest)
            addView(notification)
            addView(cardReport)
            addView(vulcanMsg)
        }


        // Listeners
        btnBack.setOnClickListener(
            this::onClickBtnBack
        )
        textViewRequest.setOnClickListener(
            this::onClickCardViewRequest
        )
        cardReport.setOnClickListener(
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