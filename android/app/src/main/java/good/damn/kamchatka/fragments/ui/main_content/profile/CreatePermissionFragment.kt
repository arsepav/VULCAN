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

    private lateinit var mGroupCheckRoute: GroupCheckBox
    private lateinit var mGroupCheckTransport: GroupCheckBox
    private lateinit var mGroupCheckVisiting: GroupCheckBox
    private lateinit var mGroupCheckVisitingTargets: GroupCheckBox
    private lateinit var mGroupCheckCamera: GroupCheckBox

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
        mGroupCheckRoute = GroupCheckBox(
            context
        )
        mGroupCheckTransport = GroupCheckBox(
            context
        )
        mGroupCheckVisiting = GroupCheckBox(
            context
        )
        mGroupCheckVisitingTargets = GroupCheckBox(
            context
        )
        mGroupCheckCamera = GroupCheckBox(
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
            mGroupCheckRoute.typeface = it
            mGroupCheckTransport.typeface = it
            mGroupCheckVisiting.typeface = it
            mGroupCheckVisitingTargets.typeface = it
            mGroupCheckCamera.typeface = it
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
            mGroupCheckRoute.textColor = it
            mGroupCheckTransport.textColor = it
            mGroupCheckVisiting.textColor = it
            mGroupCheckVisitingTargets.textColor = it
            mGroupCheckCamera.textColor = it
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
            mGroupCheckRoute.checkBoxSize = it
            mGroupCheckTransport.checkBoxSize = it
            mGroupCheckVisiting.checkBoxSize = it
            mGroupCheckVisitingTargets.checkBoxSize = it
            mGroupCheckCamera.checkBoxSize = it
        }

        // Check box radius
        (mGroupCheckRoute.checkBoxSize * 0.25f).let {
            mGroupCheckRoute.checkBoxRadius = it
            mGroupCheckTransport.checkBoxRadius = it
            mGroupCheckVisiting.checkBoxRadius = it * 2f
            mGroupCheckVisitingTargets.checkBoxRadius = it
            mGroupCheckCamera.checkBoxRadius = it
        }

        // Check box stroke width
        (mGroupCheckRoute.checkBoxSize * 0.06f).let {
            mGroupCheckRoute.checkBoxStrokeWidth = it
            mGroupCheckTransport.checkBoxStrokeWidth = it
            mGroupCheckVisiting.checkBoxStrokeWidth = it
            mGroupCheckVisitingTargets.checkBoxStrokeWidth = it
            mGroupCheckCamera.checkBoxStrokeWidth = it
        }


        // Check colors
        Application.color(
            R.color.titleColor
        ).let {
            mGroupCheckRoute.checkBoxColor = it
            mGroupCheckTransport.checkBoxColor = it
            mGroupCheckVisiting.checkBoxColor = it
            mGroupCheckVisitingTargets.checkBoxColor = it
            mGroupCheckCamera.checkBoxColor = it
        }


        // Check box drawable
        mGroupCheckRoute.textDrawable = Application.drawable(
            R.drawable.ic_route
        )
        mGroupCheckTransport.textDrawable = Application.drawable(
            R.drawable.ic_car
        )
        mGroupCheckVisiting.textDrawable = Application.drawable(
            R.drawable.ic_map
        )
        mGroupCheckVisitingTargets.textDrawable = Application.drawable(
            R.drawable.ic_extension
        )
        mGroupCheckCamera.textDrawable = Application.drawable(
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
        mGroupCheckRoute.title = R.string.select_route
        mGroupCheckTransport.title = R.string.use_transport
        mGroupCheckVisiting.title = R.string.format_visiting
        mGroupCheckVisitingTargets.title = R.string.target_visiting
        mGroupCheckCamera.title = R.string.filming





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
            mGroupCheckRoute.fields = routeFields
        }





        mGroupCheckTransport.fields = arrayOf(
            GroupField(
                context,
                R.string.passenger
            )
        )
        mGroupCheckVisiting.fields = arrayOf(
            GroupField(
                context,
                R.string.adventure
            ),
            GroupField(
                context,
                R.string.one_day_adventure
            )
        )
        mGroupCheckVisitingTargets.fields = arrayOf(
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
        mGroupCheckCamera.fields = arrayOf(
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
            mGroupCheckRoute.titleTextSize = it
            mGroupCheckTransport.titleTextSize = it
            mGroupCheckVisiting.titleTextSize = it
            mGroupCheckVisitingTargets.titleTextSize = it
            mGroupCheckCamera.titleTextSize = it
        }

        // Title bottom margin
        mGroupFieldName.titleBottomMargin = measureUnit * 0.04589f
        mGroupFieldName.titleBottomMargin.let {
            mGroupFieldPassport.titleBottomMargin = it
            mGroupFieldContact.titleBottomMargin = it
            mGroupCheckRoute.titleBottomMargin = it
            mGroupCheckTransport.titleBottomMargin = it
            mGroupCheckVisiting.titleBottomMargin = it
            mGroupCheckVisitingTargets.titleBottomMargin = it
            mGroupCheckCamera.titleBottomMargin = it
        }


        // Intervals
        (measureUnit * 0.0193f).let {
            mGroupFieldName.interval = it
            mGroupFieldPassport.interval = it
            mGroupFieldContact.interval = it
            mGroupCheckRoute.interval = it
            mGroupCheckTransport.interval = it
            mGroupCheckVisiting.interval = it
            mGroupCheckVisitingTargets.interval = it
            mGroupCheckCamera.interval = it
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
        mGroupCheckRoute.boundsLinear(
            Gravity.CENTER_HORIZONTAL,
            width = widthGroup,
            top = topMargin
        )
        mGroupCheckTransport.boundsLinear(
            Gravity.CENTER_HORIZONTAL,
            width = widthGroup,
            top = topMargin
        )
        mGroupCheckVisiting.boundsLinear(
            Gravity.CENTER_HORIZONTAL,
            width = widthGroup,
            top = topMargin
        )
        mGroupCheckVisitingTargets.boundsLinear(
            Gravity.CENTER_HORIZONTAL,
            width = widthGroup,
            top = topMargin
        )
        mGroupCheckCamera.boundsLinear(
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
        mGroupCheckRoute
            .checkBoxTextPadding = checkBoxTextPadding
        mGroupCheckTransport
            .checkBoxTextPadding = checkBoxTextPadding
        mGroupCheckVisiting
            .checkBoxTextPadding = checkBoxTextPadding
        mGroupCheckVisitingTargets
            .checkBoxTextPadding = checkBoxTextPadding
        mGroupCheckCamera
            .checkBoxTextPadding = checkBoxTextPadding




        // Layout fields
        mGroupFieldName.layoutFields()
        mGroupFieldPassport.layoutFields()
        mGroupFieldContact.layoutFields()
        mGroupCheckRoute.layoutFields()
        mGroupCheckTransport.layoutFields()
        mGroupCheckVisiting.layoutFields()
        mGroupCheckVisitingTargets.layoutFields()
        mGroupCheckCamera.layoutFields()



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
            mGroupCheckRoute
        )
        layout.addView(
            mGroupCheckTransport
        )
        layout.addView(
            mGroupCheckVisiting
        )
        layout.addView(
            mGroupCheckVisitingTargets
        )
        layout.addView(
            mGroupCheckCamera
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

        val routeData = mGroupCheckRoute.getData()
        val transportData = mGroupCheckTransport.getData()
        val visitingData = mGroupCheckVisiting.getData()
        val targetData = mGroupCheckVisitingTargets.getData()
        val cameraData = mGroupCheckCamera.getData()

        if (routeData == null
            || transportData == null
            || visitingData == null
            || targetData == null
            || cameraData == null
        ) {
            Application.toast(
                R.string.need_to_select_one,
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
