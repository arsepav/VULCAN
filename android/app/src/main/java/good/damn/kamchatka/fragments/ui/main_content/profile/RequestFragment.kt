package good.damn.kamchatka.fragments.ui.main_content.profile

import android.content.Context
import android.view.Gravity
import android.view.View
import good.damn.kamchatka.Application
import good.damn.kamchatka.R
import good.damn.kamchatka.extensions.boundsLinear
import good.damn.kamchatka.fragments.ui.ScrollableFragment
import good.damn.kamchatka.utils.ViewUtils
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
        val groupFieldName = GroupTextField(
            context
        )
        val groupFieldPassport = GroupTextField(
            context
        )
        val groupFieldContact = GroupTextField(
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




        // Fields
        groupFieldName.fields = arrayOf(
            GroupField(
                R.string.surname
            ),
            GroupField(
                R.string.firstName
            ),
            GroupField(
                R.string.lastName
            )
        )
        groupFieldPassport.fields = arrayOf(
            GroupField(
                R.string.birthDate
            ),
            GroupField(
                R.string.country_passport
            ),
            GroupField(
                R.string.region_register
            ),
            GroupField(
                R.string.gender
            ),
            GroupField(
                R.string.passport_id
            )
        )
        groupFieldContact.fields = arrayOf(
            GroupField(
                R.string.email
            ),
            GroupField(
                R.string.telephone
            )
        )


        // Intervals
        val interval = measureUnit * 0.0193f
        groupFieldName.interval = interval
        groupFieldPassport.interval = interval
        groupFieldContact.interval = interval




        // Layout params
        val widthGroup = (measureUnit * 0.8961f).toInt()
        groupFieldName.boundsLinear(
            Gravity.CENTER_HORIZONTAL,
            width = widthGroup
        )
        groupFieldPassport.boundsLinear(
            Gravity.CENTER_HORIZONTAL,
            width = widthGroup
        )
        groupFieldContact.boundsLinear(
            Gravity.CENTER_HORIZONTAL,
            width = widthGroup
        )


        // Layout fields
        groupFieldName.layoutFields()
        groupFieldPassport.layoutFields()
        groupFieldContact.layoutFields()



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

        return layout
    }
}