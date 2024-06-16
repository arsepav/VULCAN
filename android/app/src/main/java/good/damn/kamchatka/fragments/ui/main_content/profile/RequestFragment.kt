package good.damn.kamchatka.fragments.ui.main_content.profile

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.Group
import androidx.fragment.app.Fragment
import good.damn.kamchatka.Application
import good.damn.kamchatka.R
import good.damn.kamchatka.extensions.boundsLinear
import good.damn.kamchatka.extensions.height
import good.damn.kamchatka.extensions.setTextColorId
import good.damn.kamchatka.extensions.setTextPx
import good.damn.kamchatka.fragments.ui.ScrollableFragment
import good.damn.kamchatka.models.Color
import good.damn.kamchatka.utils.ViewUtils
import good.damn.kamchatka.views.button.ButtonBack
import good.damn.kamchatka.views.button.ButtonRound
import good.damn.kamchatka.views.layout.GroupCheckBox
import good.damn.kamchatka.views.layout.GroupTextField
import good.damn.kamchatka.views.layout.models.GroupField

class RequestFragment
: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val measureUnit = Application.WIDTH
        val context = context ?: return null

        // Allocating views
        val scrollView = ScrollView(
            context
        )
        val layout = ViewUtils.verticalLinearLayout(
            context
        )

        val groupFieldName = GroupTextField(
            context
        )
        val groupFieldPassport = GroupTextField(
            context
        )
        val groupFieldContact = GroupTextField(
            context
        )
        val groupCheckRoute = GroupCheckBox(
            context
        )
        val groupCheckTransport = GroupCheckBox(
            context
        )
        val groupCheckVisiting = GroupCheckBox(
            context
        )
        val groupCheckVisitingTargets = GroupCheckBox(
            context
        )
        val groupCheckCamera = GroupCheckBox(
            context
        )
        val btnNext = ButtonRound(
            context
        )



        // Group typeface
        val font = Application.font(
            R.font.open_sans_regular,
            context
        )
        groupFieldName.typeface = font
        groupFieldPassport.typeface = font
        groupFieldContact.typeface = font
        groupCheckRoute.typeface = font
        groupCheckTransport.typeface = font
        groupCheckVisiting.typeface = font
        groupCheckVisitingTargets.typeface = font
        groupCheckCamera.typeface = font


        // Typeface

        btnNext.typeface = Application.font(
            R.font.open_sans_semi_bold,
            context
        )


        // Background color
        btnNext.setBackgroundColor(
            Application.color(
                R.color.titleColor
            )
        )





        // Text colors

        groupCheckRoute.textColor = Application.color(
            R.color.titleColor
        )
        groupCheckRoute.textColor = groupCheckRoute.textColor
        groupCheckTransport.textColor = groupCheckRoute.textColor
        groupCheckVisiting.textColor = groupCheckRoute.textColor
        groupCheckVisitingTargets.textColor = groupCheckRoute.textColor
        groupCheckCamera.textColor = groupCheckRoute.textColor
        btnNext.setTextColorId(
            R.color.textColorBtn
        )


        // Stroke colors
        groupFieldName.fieldColor = Application.color(
            R.color.mountainsColor
        )
        groupFieldPassport.fieldColor = Application.color(
            R.color.signInStrokeColor2
        )
        groupFieldContact.fieldColor = Application.color(
            R.color.signInStrokeColor3
        )


        // Check box size
        val checkBoxSize = measureUnit * 0.0603f
        groupCheckRoute.checkBoxSize = checkBoxSize
        groupCheckTransport.checkBoxSize = checkBoxSize
        groupCheckVisiting.checkBoxSize = checkBoxSize
        groupCheckVisitingTargets.checkBoxSize = checkBoxSize
        groupCheckCamera.checkBoxSize = checkBoxSize


        // Check box radius
        val checkBoxRadius = groupCheckRoute
            .checkBoxSize * 0.25f
        groupCheckRoute.checkBoxRadius = checkBoxRadius
        groupCheckTransport.checkBoxRadius = checkBoxRadius
        groupCheckVisiting.checkBoxRadius = checkBoxRadius * 2f
        groupCheckVisitingTargets.checkBoxRadius = checkBoxRadius
        groupCheckCamera.checkBoxRadius = checkBoxRadius


        // Check box stroke width
        val checkBoxStrokeWidth = groupCheckRoute
            .checkBoxSize * 0.06f
        groupCheckRoute.checkBoxStrokeWidth = checkBoxStrokeWidth
        groupCheckTransport.checkBoxStrokeWidth = checkBoxStrokeWidth
        groupCheckVisiting.checkBoxStrokeWidth = checkBoxStrokeWidth
        groupCheckVisitingTargets.checkBoxStrokeWidth = checkBoxStrokeWidth
        groupCheckCamera.checkBoxStrokeWidth = checkBoxStrokeWidth



        // Check colors
        val checkBoxColor = Application.color(
            R.color.titleColor
        )
        groupCheckRoute.checkBoxColor = checkBoxColor
        groupCheckTransport.checkBoxColor = checkBoxColor
        groupCheckVisiting.checkBoxColor = checkBoxColor
        groupCheckVisitingTargets.checkBoxColor = checkBoxColor
        groupCheckCamera.checkBoxColor = checkBoxColor



        // Check box drawable
        groupCheckRoute.textDrawable = Application.drawable(
            R.drawable.ic_route
        )
        groupCheckTransport.textDrawable = Application.drawable(
            R.drawable.ic_car
        )
        groupCheckVisiting.textDrawable = Application.drawable(
            R.drawable.ic_map
        )
        groupCheckVisitingTargets.textDrawable = Application.drawable(
            R.drawable.ic_extension
        )
        groupCheckCamera.textDrawable = Application.drawable(
            R.drawable.ic_camera
        )



        // Group titles
        groupFieldName.setTitle(
            R.string.snl
        )
        groupFieldPassport.setTitle(
            R.string.passport_data
        )
        groupFieldContact.setTitle(
            R.string.contact_data
        )
        groupCheckRoute.title = R.string.select_route
        groupCheckTransport.title = R.string.use_transport
        groupCheckVisiting.title = R.string.format_visiting
        groupCheckVisitingTargets.title = R.string.target_visiting
        groupCheckCamera.title = R.string.filming





        // Fields
        groupFieldName.fields = arrayOf(
            GroupField(
                R.string.surname,
                R.drawable.ic_profile
            ),
            GroupField(
                R.string.firstName,
                R.drawable.ic_profile_out
            ),
            GroupField(
                R.string.lastName,
                R.drawable.ic_profile_out
            )
        )
        groupFieldPassport.fields = arrayOf(
            GroupField(
                R.string.birthDate,
                R.drawable.ic_calendar
            ),
            GroupField(
                R.string.country_passport,
                R.drawable.ic_identity_card
            ),
            GroupField(
                R.string.region_register,
                R.drawable.ic_globe
            ),
            GroupField(
                R.string.gender,
                R.drawable.ic_profile_out
            ),
            GroupField(
                R.string.passport_id,
                R.drawable.ic_passport
            )
        )
        groupFieldContact.fields = arrayOf(
            GroupField(
                R.string.email,
                R.drawable.ic_email
            ),
            GroupField(
                R.string.telephone,
                R.drawable.ic_call
            )
        )
        groupCheckRoute.fields = arrayOf(
            GroupField(
                R.string.email
            ),
            GroupField(
                R.string.kamchatka
            ),
            GroupField(
                R.string.snl
            )
        )
        groupCheckTransport.fields = arrayOf(
            GroupField(
                R.string.passenger
            )
        )
        groupCheckVisiting.fields = arrayOf(
            GroupField(
                R.string.adventure
            ),
            GroupField(
                R.string.one_day_adventure
            )
        )
        groupCheckVisitingTargets.fields = arrayOf(
            GroupField(
                R.string.target_visit1
            ),
            GroupField(
                R.string.target_visit2
            ),
            GroupField(
                R.string.target_visit3
            ),
            GroupField(
                R.string.target_visit4
            ),
            GroupField(
                R.string.target_visit5
            ),
            GroupField(
                R.string.target_visit6
            ),
            GroupField(
                R.string.target_visit7
            ),
            GroupField(
                R.string.target_visit8
            )
        )
        groupCheckCamera.fields = arrayOf(
            GroupField(
                R.string.req_film_1
            ),
            GroupField(
                R.string.req_film_2
            ),
            GroupField(
                R.string.req_film_3
            )
        )


        // Text
        btnNext.setText(
            R.string.next
        )



        // Title Size
        groupFieldName.titleTextSize = measureUnit * 0.04835f
        groupFieldPassport.titleTextSize = groupFieldName.titleTextSize
        groupFieldContact.titleTextSize = groupFieldName.titleTextSize
        groupCheckRoute.titleTextSize = groupFieldName.titleTextSize
        groupCheckTransport.titleTextSize = groupFieldName.titleTextSize
        groupCheckVisiting.titleTextSize = groupFieldName.titleTextSize
        groupCheckVisitingTargets.titleTextSize = groupFieldName.titleTextSize
        groupCheckCamera.titleTextSize = groupFieldName.titleTextSize


        // Title bottom margin
        groupFieldName.titleBottomMargin = measureUnit * 0.04589f
        groupFieldPassport.titleBottomMargin = groupFieldName.titleBottomMargin
        groupFieldContact.titleBottomMargin = groupFieldName.titleBottomMargin
        groupCheckRoute.titleBottomMargin = groupFieldName.titleBottomMargin
        groupCheckTransport.titleBottomMargin = groupFieldName.titleBottomMargin
        groupCheckVisiting.titleBottomMargin = groupFieldName.titleBottomMargin
        groupCheckVisitingTargets.titleBottomMargin = groupFieldName.titleBottomMargin
        groupCheckCamera.titleBottomMargin = groupFieldName.titleBottomMargin



        // Intervals
        val interval = measureUnit * 0.0193f
        groupFieldName.interval = interval
        groupFieldPassport.interval = interval
        groupFieldContact.interval = interval
        groupCheckRoute.interval = interval
        groupCheckTransport.interval = interval
        groupCheckVisiting.interval = interval
        groupCheckVisitingTargets.interval = interval
        groupCheckCamera.interval = interval




        // Layout params
        val widthGroup = (measureUnit * 0.8961f).toInt()
        val topMargin = measureUnit * 0.09782f
        val marginStart = (measureUnit - widthGroup) * 0.5f

        groupFieldName.boundsLinear(
            Gravity.CENTER_HORIZONTAL,
            width = widthGroup,
            top = topMargin
        )
        groupFieldPassport.boundsLinear(
            Gravity.CENTER_HORIZONTAL,
            width = widthGroup,
            top = topMargin
        )
        groupFieldContact.boundsLinear(
            Gravity.CENTER_HORIZONTAL,
            width = widthGroup,
            top = topMargin
        )
        groupCheckRoute.boundsLinear(
            Gravity.CENTER_HORIZONTAL,
            width = widthGroup,
            top = topMargin
        )
        groupCheckTransport.boundsLinear(
            Gravity.CENTER_HORIZONTAL,
            width = widthGroup,
            top = topMargin
        )
        groupCheckVisiting.boundsLinear(
            Gravity.CENTER_HORIZONTAL,
            width = widthGroup,
            top = topMargin
        )
        groupCheckVisitingTargets.boundsLinear(
            Gravity.CENTER_HORIZONTAL,
            width = widthGroup,
            top = topMargin
        )
        groupCheckCamera.boundsLinear(
            Gravity.CENTER_HORIZONTAL,
            width = widthGroup,
            top = topMargin
        )
        btnNext.boundsLinear(
            Gravity.CENTER_HORIZONTAL,
            width = (measureUnit * 0.93236f).toInt(),
            height = (measureUnit * 0.128f).toInt(),
            top = measureUnit * 0.09661f
        )

        layout.setPadding(
            0,
            0,
            0,
            (measureUnit * 0.1f).toInt()
        )

        // Text size
        btnNext.setTextPx(
            btnNext.height() * 0.2678f
        )


        // Corner radius
        btnNext.cornerRadius = btnNext.height() * 0.303f


        // Check box text padding
        val checkBoxTextPadding = measureUnit * 0.03623f
        groupCheckRoute
            .checkBoxTextPadding = checkBoxTextPadding
        groupCheckTransport
            .checkBoxTextPadding = checkBoxTextPadding
        groupCheckVisiting
            .checkBoxTextPadding = checkBoxTextPadding
        groupCheckVisitingTargets
            .checkBoxTextPadding = checkBoxTextPadding
        groupCheckCamera
            .checkBoxTextPadding = checkBoxTextPadding




        // Layout fields
        groupFieldName.layoutFields()
        groupFieldPassport.layoutFields()
        groupFieldContact.layoutFields()
        groupCheckRoute.layoutFields()
        groupCheckTransport.layoutFields()
        groupCheckVisiting.layoutFields()
        groupCheckVisitingTargets.layoutFields()
        groupCheckCamera.layoutFields()



        // Adding views
        layout.addView(
            groupFieldName
        )
        layout.addView(
            groupFieldPassport
        )
        layout.addView(
            groupFieldContact
        )
        layout.addView(
            groupCheckRoute
        )
        layout.addView(
            groupCheckTransport
        )
        layout.addView(
            groupCheckVisiting
        )
        layout.addView(
            groupCheckVisitingTargets
        )
        layout.addView(
            groupCheckCamera
        )
        layout.addView(
            btnNext
        )


        // Listeners

        scrollView.addView(
            layout
        )

        return scrollView
    }
}
