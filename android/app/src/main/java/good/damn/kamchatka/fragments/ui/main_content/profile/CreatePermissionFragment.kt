package good.damn.kamchatka.fragments.ui.main_content.profile

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import androidx.fragment.app.Fragment
import good.damn.kamchatka.Application
import good.damn.kamchatka.R
import good.damn.kamchatka.extensions.boundsLinear
import good.damn.kamchatka.extensions.height
import good.damn.kamchatka.extensions.setTextColorId
import good.damn.kamchatka.extensions.setTextPx
import good.damn.kamchatka.models.remote.json.Route
import good.damn.kamchatka.utils.ViewUtils
import good.damn.kamchatka.views.button.ButtonRound
import good.damn.kamchatka.views.layout.GroupCheckBox
import good.damn.kamchatka.views.layout.GroupTextField
import good.damn.kamchatka.views.layout.models.GroupField

class CreatePermissionFragment
: Fragment() {

    var routes: Array<Route?>? = null

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
        Application.font(
            R.font.open_sans_regular,
            context
        ).let {
            groupFieldName.typeface = it
            groupFieldPassport.typeface = it
            groupFieldContact.typeface = it
            groupCheckRoute.typeface = it
            groupCheckTransport.typeface = it
            groupCheckVisiting.typeface = it
            groupCheckVisitingTargets.typeface = it
            groupCheckCamera.typeface = it
        }

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
        Application.color(
            R.color.titleColor
        ).let {
            groupCheckRoute.textColor = it
            groupCheckTransport.textColor = it
            groupCheckVisiting.textColor = it
            groupCheckVisitingTargets.textColor = it
            groupCheckCamera.textColor = it
        }
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
        (measureUnit * 0.0603f).let {
            groupCheckRoute.checkBoxSize = it
            groupCheckTransport.checkBoxSize = it
            groupCheckVisiting.checkBoxSize = it
            groupCheckVisitingTargets.checkBoxSize = it
            groupCheckCamera.checkBoxSize = it
        }

        // Check box radius
        (groupCheckRoute.checkBoxSize * 0.25f).let {
            groupCheckRoute.checkBoxRadius = it
            groupCheckTransport.checkBoxRadius = it
            groupCheckVisiting.checkBoxRadius = it * 2f
            groupCheckVisitingTargets.checkBoxRadius = it
            groupCheckCamera.checkBoxRadius = it
        }

        // Check box stroke width
        (groupCheckRoute.checkBoxSize * 0.06f).let {
            groupCheckRoute.checkBoxStrokeWidth = it
            groupCheckTransport.checkBoxStrokeWidth = it
            groupCheckVisiting.checkBoxStrokeWidth = it
            groupCheckVisitingTargets.checkBoxStrokeWidth = it
            groupCheckCamera.checkBoxStrokeWidth = it
        }


        // Check colors
        Application.color(
            R.color.titleColor
        ).let {
            groupCheckRoute.checkBoxColor = it
            groupCheckTransport.checkBoxColor = it
            groupCheckVisiting.checkBoxColor = it
            groupCheckVisitingTargets.checkBoxColor = it
            groupCheckCamera.checkBoxColor = it
        }


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
                context,
                R.string.surname,
                R.drawable.ic_profile
            ),
            GroupField(
                context,
                R.string.firstName,
                R.drawable.ic_profile_out
            ),
            GroupField(
                context,
                R.string.lastName,
                R.drawable.ic_profile_out
            )
        )
        groupFieldPassport.fields = arrayOf(
            GroupField(
                context,
                R.string.birthDate,
                R.drawable.ic_calendar
            ),
            GroupField(
                context,
                R.string.country_passport,
                R.drawable.ic_identity_card
            ),
            GroupField(
                context,
                R.string.region_register,
                R.drawable.ic_globe
            ),
            GroupField(
                context,
                R.string.gender,
                R.drawable.ic_profile_out
            ),
            GroupField(
                context,
                R.string.passport_id,
                R.drawable.ic_passport
            )
        )
        groupFieldContact.fields = arrayOf(
            GroupField(
                context,
                R.string.email,
                R.drawable.ic_email
            ),
            GroupField(
                context,
                R.string.telephone,
                R.drawable.ic_call
            )
        )

        routes?.let {
            val routeFields = Array(it.size) { i ->
                GroupField(
                    it[i]?.name ?: "???"
                )
            }
            groupCheckRoute.fields = routeFields
        }





        groupCheckTransport.fields = arrayOf(
            GroupField(
                context,
                R.string.passenger
            )
        )
        groupCheckVisiting.fields = arrayOf(
            GroupField(
                context,
                R.string.adventure
            ),
            GroupField(
                context,
                R.string.one_day_adventure
            )
        )
        groupCheckVisitingTargets.fields = arrayOf(
            GroupField(
                context,
                R.string.target_visit1
            ),
            GroupField(
                context,
                R.string.target_visit2
            ),
            GroupField(
                context,
                R.string.target_visit3
            ),
            GroupField(
                context,
                R.string.target_visit4
            ),
            GroupField(
                context,
                R.string.target_visit5
            ),
            GroupField(
                context,
                R.string.target_visit6
            ),
            GroupField(
                context,
                R.string.target_visit7
            ),
            GroupField(
                context,
                R.string.target_visit8
            )
        )
        groupCheckCamera.fields = arrayOf(
            GroupField(
                context,
                R.string.req_film_1
            ),
            GroupField(
                context,
                R.string.req_film_2
            ),
            GroupField(
                context,
                R.string.req_film_3
            )
        )


        // Text
        btnNext.setText(
            R.string.next
        )



        // Title Size
        groupFieldName.titleTextSize = measureUnit * 0.04835f
        groupFieldName.titleTextSize.let {
            groupFieldPassport.titleTextSize = it
            groupFieldContact.titleTextSize = it
            groupCheckRoute.titleTextSize = it
            groupCheckTransport.titleTextSize = it
            groupCheckVisiting.titleTextSize = it
            groupCheckVisitingTargets.titleTextSize = it
            groupCheckCamera.titleTextSize = it
        }

        // Title bottom margin
        groupFieldName.titleBottomMargin = measureUnit * 0.04589f
        groupFieldName.titleBottomMargin.let {
            groupFieldPassport.titleBottomMargin = it
            groupFieldContact.titleBottomMargin = it
            groupCheckRoute.titleBottomMargin = it
            groupCheckTransport.titleBottomMargin = it
            groupCheckVisiting.titleBottomMargin = it
            groupCheckVisitingTargets.titleBottomMargin = it
            groupCheckCamera.titleBottomMargin = it
        }


        // Intervals
        (measureUnit * 0.0193f).let {
            groupFieldName.interval = it
            groupFieldPassport.interval = it
            groupFieldContact.interval = it
            groupCheckRoute.interval = it
            groupCheckTransport.interval = it
            groupCheckVisiting.interval = it
            groupCheckVisitingTargets.interval = it
            groupCheckCamera.interval = it
        }



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

        btnNext.setOnClickListener(
            this::onClickBtnNext
        )

        return scrollView
    }

    private fun onClickBtnNext(
        view: View
    ) {

    }

}
