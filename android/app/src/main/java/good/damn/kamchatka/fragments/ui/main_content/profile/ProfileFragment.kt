package good.damn.kamchatka.fragments.ui.main_content.profile

import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.annotation.WorkerThread
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import good.damn.kamchatka.Application
import good.damn.kamchatka.R
import good.damn.kamchatka.adapters.fragment_adapters.PermissionRequestAdapter
import good.damn.kamchatka.extensions.boundsFrame
import good.damn.kamchatka.extensions.boundsLinear
import good.damn.kamchatka.extensions.height
import good.damn.kamchatka.extensions.left
import good.damn.kamchatka.extensions.setImageDrawableId
import good.damn.kamchatka.extensions.setTextColorId
import good.damn.kamchatka.extensions.setTextPx
import good.damn.kamchatka.extensions.size
import good.damn.kamchatka.extensions.width
import good.damn.kamchatka.fragments.ui.ScrollableFragment
import good.damn.kamchatka.models.Color
import good.damn.kamchatka.models.permission.PermissionRequest
import good.damn.kamchatka.services.PermissionService
import good.damn.kamchatka.utils.ViewUtils
import good.damn.kamchatka.views.RoundedImageView
import good.damn.kamchatka.views.button.ButtonBack
import good.damn.kamchatka.views.button.ButtonCard
import good.damn.kamchatka.views.button.CardState

class ProfileFragment
: ScrollableFragment() {

    private var mPermissions: Array<PermissionRequest>? = null

    private lateinit var mCardState: CardState
    private lateinit var mTextViewSeeAll: AppCompatTextView
    private lateinit var mLayoutNoPerms: LinearLayout

    override fun onCreateContentView(
        context: Context,
        measureUnit: Int
    ): View {


        // Allocating views
        val layout = ViewUtils.verticalLinearLayout(
            context
        )
        mLayoutNoPerms = ViewUtils.verticalLinearLayout(
            context
        )
        val layoutPerm = FrameLayout(
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
        mCardState = CardState(
            context
        )
        val imageViewNoPerm = AppCompatImageView(
            context
        )
        val textViewNoPerm = AppCompatTextView(
            context
        )
        mTextViewSeeAll = AppCompatTextView(
            context
        )
        val notification = AppCompatTextView(
            context
        )
        val cardReport = ButtonCard(
            context
        )
        val vulcanMsg = ViewUtils.vulcanTextView(
            R.string.profile_msg,
            context,
            measureUnit
        )



        // Text
        textViewAppName.apply {
            isAllCaps = true
            setText(R.string.app_name)
        }
        textViewHello.text = "${getString(R.string.hello)} ${Application.TOKEN?.name}"
        notification.setText(
            R.string.notify_people
        )
        mTextViewSeeAll.setText(
            R.string.see_all_perm
        )
        textViewNoPerm.setText(
            R.string.no_perms
        )
        mCardState.apply {
            title = "Загрузка..."
            state = title
            subtitle = title
            drawableEnd = Application.drawable(
                R.drawable.ic_reviewing
            )
            visibility = View.INVISIBLE
            mTextViewSeeAll.visibility = visibility
        }



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
        imageViewNoPerm.setImageDrawableId(
            R.drawable.ic_info
        )


        // Text Color
        textViewNoPerm.setTextColorId(
            R.color.accentColor30
        )
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
        mTextViewSeeAll.setTextColorId(
            R.color.accentColor
        )
        notification.setTextColor(
            Color.parseFromHexId(
                R.color.titleColor,
                0.3f
            )
        )

        // Font
        Application.font(
            R.font.open_sans_semi_bold,
            context
        )?.let {
            textViewHello.typeface = it
            mTextViewSeeAll.typeface = it
            textViewNoPerm.typeface = it
        }

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
        mTextViewSeeAll.setTextPx(
            measureUnit * 0.02681f
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
        layoutPerm.boundsLinear(
            Gravity.CENTER_HORIZONTAL,
            width = (measureUnit * 0.9033f).toInt(),
            height = (measureUnit * 0.3188f).toInt(),
            top = measureUnit * 0.05917f
        )
        mCardState.size(
            width = layoutPerm.width(),
            height = layoutPerm.height()
        )
        mTextViewSeeAll.boundsLinear(
            Gravity.CENTER_HORIZONTAL,
            width = (measureUnit * 0.88164f).toInt(),
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

        mCardState.apply {
            radius = height() * 0.191f
            layoutIt()
        }

        layoutPerm.apply {
            imageViewNoPerm.boundsLinear(
                Gravity.CENTER_HORIZONTAL,
                size = (height() * 0.37878f).toInt()
            )
            textViewNoPerm.setTextPx(
                height() * 0.13113f
            )
            textViewNoPerm.boundsLinear(
                Gravity.CENTER_HORIZONTAL
            )
        }

        mLayoutNoPerms.apply {
            addView(imageViewNoPerm)
            addView(textViewNoPerm)
        }

        layoutPerm.apply {
            addView(mLayoutNoPerms)
            addView(mCardState)
        }

        layout.apply {
            addView(btnBack)
            addView(textViewAppName)
            addView(imageViewAvatar)
            addView(textViewHello)
            addView(textViewPermissions)
            addView(layoutPerm)
            addView(mTextViewSeeAll)
            addView(notification)
            addView(cardReport)
            addView(vulcanMsg)
        }


        // Listeners
        btnBack.setOnClickListener(
            this::onClickBtnBack
        )
        mTextViewSeeAll.setOnClickListener(
            this::onClickCardViewRequest
        )
        cardReport.setOnClickListener(
            this::onClickBtnReport
        )


        PermissionService(
            context
        ).getPermissions(
            this::onGetPermissions
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
        mPermissions?.let {
            pushFragment(
                ViewPermissionsFragment.create(
                    it
                )
            )
        }
    }


    @WorkerThread
    private fun onGetPermissions(
        perms: Array<PermissionRequest>
    ) {
        mPermissions = perms
        if (perms.isEmpty()) {
            return
        }
        Application.ui {
            mCardState.apply {
                val p = perms.last()
                title = p.name
                subtitle = getString(
                    R.string.park
                )

                if (p.approved) {
                    state = getString(
                        R.string.approved
                    )
                    setDrawableEndId(
                        R.drawable.ic_approved
                    )
                    return@apply
                }

                if (p.reviewed) {
                    state = getString(
                        R.string.rejected
                    )
                    setDrawableEndId(
                        R.drawable.ic_x_mark
                    )
                    return@apply
                }

                state = getString(
                    R.string.reviewing
                )
                setDrawableEndId(
                    R.drawable.ic_reviewing
                )
            }
        }
    }
}

private fun ProfileFragment.onClickBtnBack(
    view: View
) {
    popFragment()
}