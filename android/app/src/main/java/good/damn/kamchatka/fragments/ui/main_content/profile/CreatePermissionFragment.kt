package good.damn.kamchatka.fragments.ui.main_content.profile

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import androidx.annotation.WorkerThread
import androidx.fragment.app.Fragment
import good.damn.kamchatka.Application
import good.damn.kamchatka.R
import good.damn.kamchatka.extensions.boundsLinear
import good.damn.kamchatka.extensions.height
import good.damn.kamchatka.extensions.setTextColorId
import good.damn.kamchatka.extensions.setTextPx
import good.damn.kamchatka.models.remote.json.Route
import good.damn.kamchatka.services.PermissionService
import good.damn.kamchatka.utils.ViewUtils
import good.damn.kamchatka.views.button.ButtonRound
import good.damn.kamchatka.views.layout.GroupCheckBox
import good.damn.kamchatka.views.layout.GroupTextField
import good.damn.kamchatka.views.layout.models.GroupField
import okhttp3.Response
import kotlin.math.log

class CreatePermissionFragment
: Fragment() {

    var routes: Array<Route?>? = null

    private var mPermissionService: PermissionService? = null

    private var keyName = ""
    private var keySurname = ""
    private var keyLastName = ""
    private var keyBirth = ""
    private var keyCitizenship = ""
    private var keyGender = ""
    private var keyPassport = ""
    private var keyEmail = ""
    private var keyNumber = ""

    private lateinit var mGroupFieldName: GroupTextField
    private lateinit var mGroupFieldPassport: GroupTextField
    private lateinit var mGroupFieldContact: GroupTextField

    companion object {
        private const val TAG = "CreatePermissionFragmen"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        keySurname = getString(
            R.string.surname
        )
        keyName = getString(
            R.string.firstName
        )
        keyLastName = getString(
            R.string.lastName
        )
        keyBirth = getString(
            R.string.birthDate
        )
        keyCitizenship = getString(
            R.string.country_passport
        )
        keyGender = getString(
            R.string.gender
        )
        keyPassport = getString(
            R.string.passport_id
        )
        keyEmail = getString(
            R.string.email
        )
        keyNumber = getString(
            R.string.telephone
        )

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val measureUnit = Application.WIDTH
        val context = context ?: return null

        mPermissionService = PermissionService(
            context
        )

        // Allocating views
        val scrollView = ScrollView(
            context
        )
        val layout = ViewUtils.verticalLinearLayout(
            context
        )

        mGroupFieldName = GroupTextField(
            context
        )
        mGroupFieldPassport = GroupTextField(
            context
        )
        mGroupFieldContact = GroupTextField(
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
            mGroupFieldName.typeface = it
            mGroupFieldPassport.typeface = it
            mGroupFieldContact.typeface = it
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
        mGroupFieldName.fieldColor = Application.color(
            R.color.mountainsColor
        )
        mGroupFieldPassport.fieldColor = Application.color(
            R.color.signInStrokeColor2
        )
        mGroupFieldContact.fieldColor = Application.color(
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
        mGroupFieldName.setTitle(
            R.string.snl
        )
        mGroupFieldPassport.setTitle(
            R.string.passport_data
        )
        mGroupFieldContact.setTitle(
            R.string.contact_data
        )
        groupCheckRoute.title = R.string.select_route
        groupCheckTransport.title = R.string.use_transport
        groupCheckVisiting.title = R.string.format_visiting
        groupCheckVisitingTargets.title = R.string.target_visiting
        groupCheckCamera.title = R.string.filming





        // Fields
        mGroupFieldName.fields = arrayOf(
            GroupField(
                keySurname,
                R.drawable.ic_profile
            ),
            GroupField(
                keyName,
                R.drawable.ic_profile_out
            ),
            GroupField(
                keyLastName,
                R.drawable.ic_profile_out
            )
        )
        mGroupFieldPassport.fields = arrayOf(
            GroupField(
                keyBirth,
                R.drawable.ic_calendar
            ),
            GroupField(
                keyCitizenship,
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
                keyPassport,
                R.drawable.ic_passport
            )
        )
        mGroupFieldContact.fields = arrayOf(
            GroupField(
                keyEmail,
                R.drawable.ic_email
            ),
            GroupField(
               keyNumber,
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
        mGroupFieldName.titleTextSize = measureUnit * 0.04835f
        mGroupFieldName.titleTextSize.let {
            mGroupFieldPassport.titleTextSize = it
            mGroupFieldContact.titleTextSize = it
            groupCheckRoute.titleTextSize = it
            groupCheckTransport.titleTextSize = it
            groupCheckVisiting.titleTextSize = it
            groupCheckVisitingTargets.titleTextSize = it
            groupCheckCamera.titleTextSize = it
        }

        // Title bottom margin
        mGroupFieldName.titleBottomMargin = measureUnit * 0.04589f
        mGroupFieldName.titleBottomMargin.let {
            mGroupFieldPassport.titleBottomMargin = it
            mGroupFieldContact.titleBottomMargin = it
            groupCheckRoute.titleBottomMargin = it
            groupCheckTransport.titleBottomMargin = it
            groupCheckVisiting.titleBottomMargin = it
            groupCheckVisitingTargets.titleBottomMargin = it
            groupCheckCamera.titleBottomMargin = it
        }


        // Intervals
        (measureUnit * 0.0193f).let {
            mGroupFieldName.interval = it
            mGroupFieldPassport.interval = it
            mGroupFieldContact.interval = it
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
        mGroupFieldName.boundsLinear(
            Gravity.CENTER_HORIZONTAL,
            width = widthGroup,
            top = topMargin
        )
        mGroupFieldPassport.boundsLinear(
            Gravity.CENTER_HORIZONTAL,
            width = widthGroup,
            top = topMargin
        )
        mGroupFieldContact.boundsLinear(
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
        mGroupFieldName.layoutFields()
        mGroupFieldPassport.layoutFields()
        mGroupFieldContact.layoutFields()
        groupCheckRoute.layoutFields()
        groupCheckTransport.layoutFields()
        groupCheckVisiting.layoutFields()
        groupCheckVisitingTargets.layoutFields()
        groupCheckCamera.layoutFields()



        // Adding views
        layout.addView(
            mGroupFieldName
        )
        layout.addView(
            mGroupFieldPassport
        )
        layout.addView(
            mGroupFieldContact
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

        if (routes?.isEmpty() ?: true) {
            Application.toast(
                R.string.error,
                view.context
            )
            return
        }

        val nameData = mGroupFieldName.getData()
        val passportData = mGroupFieldPassport.getData()
        val contactData = mGroupFieldContact.getData()

        if (nameData.isEmpty()
            || passportData.isEmpty()
            || contactData.isEmpty()
        ) {
            Application.toast(
                R.string.not_all_data,
                view.context
            )
            return
        }

        Log.d(TAG, "onClickBtnNext: $nameData")
        Log.d(TAG, "onClickBtnNext: $passportData")
        Log.d(TAG, "onClickBtnNext: $contactData")

        val id = routes!![0]?.id ?: return

        mPermissionService?.createPermission(
            arrivalDate = "2025-06-16",
            surname = nameData[keySurname]!!,
            name = nameData[keyName]!!,
            lastname = nameData[keyLastName]!!,
            birthday = passportData[keyBirth]!!,
            citizenship = passportData[keyCitizenship]!!.toInt(),
            isMale = passportData[keyGender]!!.contains("M".toRegex()),
            passport = passportData[keyPassport]!!,
            email = contactData[keyEmail]!!,
            phoneNumber = contactData[keyNumber]!!,
            pathId = id,
            isOneDay = true,
            purposeSkis = true,
            purposeSport = true,
            purposeScience = true,
            purposePhotoVideo = true,
            purposeMountain = true,
            purposeAnother = true,
            photoVideoProf = false,
            photoVideoDrones = false,
            this::onResponsePermission
        )
    }

    @WorkerThread
    private fun onResponsePermission(
        response: Response
    ) {

        val body = response.body?.string()
        Log.d(TAG, "onResponsePermission: ${response.code} $body")

    }

}
