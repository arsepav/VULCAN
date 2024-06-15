package good.damn.kamchatka.fragments.ui.main_content.profile

import android.content.Context
import android.view.Gravity
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import good.damn.kamchatka.Application
import good.damn.kamchatka.R
import good.damn.kamchatka.extensions.boundsLinear
import good.damn.kamchatka.extensions.setTextColorId
import good.damn.kamchatka.extensions.setTextPx
import good.damn.kamchatka.fragments.ui.ScrollableFragment
import good.damn.kamchatka.models.Color
import good.damn.kamchatka.utils.ViewUtils
import good.damn.kamchatka.views.button.ButtonBack
import good.damn.kamchatka.views.layout.GroupCheckBox
import good.damn.kamchatka.views.layout.GroupTextField
import good.damn.kamchatka.views.layout.models.GroupField

class RequestFragment
: ScrollableFragment() {

    override fun hasPreciseMeasurement(): Boolean {
        return false
    }

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
                R.color.titleColor
            ),
            context
        )
        val textViewObjectName = AppCompatTextView(
            context
        )
        val textViewObjectType = AppCompatTextView(
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


        // Group typeface
        val font = Application.font(
            R.font.open_sans_regular,
            context
        )
        groupFieldName.typeface = font
        groupFieldPassport.typeface = font
        groupFieldContact.typeface = font
        groupCheckRoute.typeface = font


        // Typeface
        textViewObjectName.typeface = Application.font(
            R.font.open_sans_bold,
            context
        )
        textViewObjectType.typeface =
            textViewObjectName.typeface



        // Text colors
        textViewObjectName.setTextColorId(
            R.color.titleColor
        )
        textViewObjectType.setTextColor(
            Color.parseFromHexId(
                R.color.titleColor,
                0.3f
            )
        )
        groupCheckRoute.textColor = Application.color(
            R.color.titleColor
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
        groupCheckRoute.checkBoxSize = measureUnit * 0.0603f

        // Check box radius
        groupCheckRoute.checkBoxRadius = groupCheckRoute
            .checkBoxSize * 0.25f


        // Check box stroke width
        groupCheckRoute.checkBoxStrokeWidth = groupCheckRoute
            .checkBoxSize * 0.06f



        // Check colors
        groupCheckRoute.checkBoxColor = Application.color(
            R.color.titleColor
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
        groupCheckRoute.setTitle(
            R.string.select_route
        )



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


        // Text
        textViewObjectName.setText(
            "Южно-Камчатский"
        )
        textViewObjectType.setText(
            "Природный парк"
        )




        // Text size
        textViewObjectName.setTextPx(
            measureUnit * 0.05314f
        )
        textViewObjectType.setTextPx(
            measureUnit * 0.03743f
        )




        // Title Size
        groupFieldName.titleTextSize = measureUnit * 0.04835f
        groupFieldPassport.titleTextSize = groupFieldName.titleTextSize
        groupFieldContact.titleTextSize = groupFieldName.titleTextSize
        groupCheckRoute.titleTextSize = groupFieldName.titleTextSize


        // Title bottom margin
        groupFieldName.titleBottomMargin = measureUnit * 0.04589f
        groupFieldPassport.titleBottomMargin = groupFieldName.titleBottomMargin
        groupFieldContact.titleBottomMargin = groupFieldName.titleBottomMargin
        groupCheckRoute.titleBottomMargin = groupFieldName.titleBottomMargin



        // Intervals
        val interval = measureUnit * 0.0193f
        groupFieldName.interval = interval
        groupFieldPassport.interval = interval
        groupFieldContact.interval = interval
        groupCheckRoute.interval = interval




        // Layout params
        val widthGroup = (measureUnit * 0.8961f).toInt()
        val topMargin = measureUnit * 0.09782f
        val marginStart = (measureUnit - widthGroup) * 0.5f
        textViewObjectName.boundsLinear(
            Gravity.START,
            top = measureUnit * 0.07729f,
            left = marginStart
        )
        textViewObjectType.boundsLinear(
            Gravity.START,
            top = measureUnit * 0.00657f,
            left = marginStart
        )
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



        // Layout fields
        groupFieldName.layoutFields()
        groupFieldPassport.layoutFields()
        groupFieldContact.layoutFields()
        groupCheckRoute.layoutFields()



        // Adding views
        layout.addView(
            btnBack
        )
        layout.addView(
            textViewObjectName
        )
        layout.addView(
            textViewObjectType
        )
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



        // Listeners
        btnBack.setOnClickListener(
            this::onClickBtnBack
        )



        return layout
    }
}

private fun RequestFragment.onClickBtnBack(
    view: View
) {
    popFragment()
}