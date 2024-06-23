package good.damn.kamchatka.fragments.ui.main_content.profile

import android.Manifest
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Environment
import android.text.InputType
import android.util.Log
import android.view.Gravity
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import good.damn.kamchatka.Application
import good.damn.kamchatka.R
import good.damn.kamchatka.extensions.boundsLinear
import good.damn.kamchatka.extensions.height
import good.damn.kamchatka.extensions.left
import good.damn.kamchatka.extensions.setTextColorId
import good.damn.kamchatka.extensions.setTextPx
import good.damn.kamchatka.fragments.ui.ScrollableFragment
import good.damn.kamchatka.services.ReportEcologyService
import good.damn.kamchatka.services.UploadService
import good.damn.kamchatka.utils.ViewUtils
import good.damn.kamchatka.views.RoundedImageView
import good.damn.kamchatka.views.button.ButtonBack
import good.damn.kamchatka.views.button.ButtonRound
import good.damn.kamchatka.views.layout.GroupCheckBox
import good.damn.kamchatka.views.layout.models.GroupField
import good.damn.kamchatka.views.text_fields.TextFieldRound
import java.io.File
import java.io.FileOutputStream


class ReportEcologyFragment
: ScrollableFragment(),
LocationListener {

    companion object {
        private const val TAG = "ReportEcologyFragment"
    }

    private lateinit var mGroupCheckProb: GroupCheckBox
    private lateinit var mEditTextDescription: TextFieldRound
    private lateinit var mImageViewAttach: RoundedImageView

    private val map = hashMapOf(
        3 to "Мусор / свалка",
        4 to "Кострище",
        5 to "Нелегальная вырубка леса",
        6 to "Загрязнение воды",
        7 to "Другое",
    )

    private var lat = 0.0
    private var long = 0.0

    private var mFileImage: File? = null

    private var locationManager: LocationManager? = null

    override fun onLocationChanged(
        location: Location
    ) {
        // Lat lng
        lat = location.latitude
        long = location.longitude
    }

    override fun onCreateContentView(
        context: Context,
        measureUnit: Int
    ): View {

        locationManager = context.getSystemService(
            Context.LOCATION_SERVICE
        ) as LocationManager?

        fun requestLocation() {
            try {
                locationManager?.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    0L,
                    0f,
                    this
                )
            } catch(ex: SecurityException) {
                Log.d(TAG, "Security Exception, no location available")
            }
        }

        val locationPermissionRequest = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            when {
                permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                    requestLocation()
                }
                permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                    requestLocation()
                } else -> {
                // No location access granted.
            }
            }
        }

        locationPermissionRequest.launch(arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION))








        val layout = ViewUtils.verticalLinearLayout(
            context
        )
        val btnClose = ButtonBack.createDefaultLinear(
            measureUnit,
            Application.color(
                R.color.btnBackArrow
            ),
            context
        )
        val textViewTitle = ViewUtils.titleNav(
            measureUnit,
            R.string.ecology_problem,
            context
        )
        val textViewOptionAttach = ViewUtils.titleOption(
            measureUnit,
            R.string.attach_photo,
            context
        )
        mImageViewAttach = RoundedImageView(
            context
        )
        val textViewOptionComment = ViewUtils.titleOption(
            measureUnit,
            R.string.comment,
            context
        )
        val btnSendReport = ButtonRound(
            context
        )
        mGroupCheckProb = GroupCheckBox(
            context
        )
        mEditTextDescription = TextFieldRound(
            context
        )

        mEditTextDescription.setStrokeColor(
            Application.color(
                R.color.signInStrokeColor3
            )
        )
        mEditTextDescription.setHint(
            R.string.text_comment
        )
        mEditTextDescription.gravity = Gravity.START or Gravity.TOP
        mEditTextDescription.maxLines = 15
        mEditTextDescription.inputType =
            InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_MULTI_LINE

        mGroupCheckProb.apply {
            checkBoxSize = measureUnit * 0.0603f
            checkBoxColor = Application.color(
                R.color.titleColor
            )
            title = R.string.select_problem
            titleTextSize = measureUnit * 0.0483f
            titleBottomMargin = measureUnit * 0.0483f
            textColor = checkBoxColor
            checkBoxRadius = checkBoxSize * 0.5f
            checkBoxStrokeWidth = checkBoxSize * 0.03f
            checkBoxTextPadding = measureUnit * 0.05f
        }


        mImageViewAttach.setImageDrawable(
            R.drawable.ic_pick_image
        )

        mGroupCheckProb.fields = arrayOf(
            GroupField(
                map[3]!!
            ),
            GroupField(
                map[4]!!
            ),
            GroupField(
                map[5]!!
            ),
            GroupField(
                map[6]!!
            ),
            GroupField(
                map[7]!!
            )
        )

        btnSendReport.setText(
            R.string.send
        )

        btnSendReport.setTextColorId(
            R.color.textColorBtn
        )

        btnSendReport.typeface = Application.font(
            R.font.open_sans_semi_bold,
            context
        )

        btnSendReport.setBackgroundColor(
            Application.color(
                R.color.titleColor
            )
        )

        textViewTitle.boundsLinear(
            Gravity.START,
            left = measureUnit * 0.0483f,
            top = measureUnit * 0.0842f
        )
        mGroupCheckProb.boundsLinear(
            Gravity.START,
            width = measureUnit,
            top = measureUnit * 0.111f,
            left = textViewTitle.left()
        )

        textViewOptionAttach.boundsLinear(
            Gravity.START,
            left = textViewTitle.left(),
            top = measureUnit * 0.0833f
        )

        mImageViewAttach.boundsLinear(
            Gravity.CENTER_HORIZONTAL,
            size = (measureUnit * 0.169f).toInt(),
            top = measureUnit * 0.0724f
        )

        textViewOptionComment.boundsLinear(
            Gravity.START,
            left = textViewTitle.left(),
            top = measureUnit * 0.07f
        )

        mEditTextDescription.boundsLinear(
            Gravity.CENTER_HORIZONTAL,
            width = (measureUnit * 0.888f).toInt(),
            height = (measureUnit * 0.1932f).toInt(),
            top = measureUnit * 0.0507f
        )

        btnSendReport.boundsLinear(
            Gravity.CENTER_HORIZONTAL,
            width = (measureUnit - 2 * textViewTitle.left()).toInt(),
            height = (measureUnit * 0.1352f).toInt(),
            top = measureUnit * 0.1316f
        )


        btnSendReport.cornerRadius = btnSendReport.height() * 0.3f

        btnSendReport.setTextPx(
            btnSendReport.height() * 0.3214f
        )

        mEditTextDescription.cornerRadius = mEditTextDescription
            .height() * 0.1625f

        mEditTextDescription.setStrokeWidth(
            mEditTextDescription.height() * 0.0125f
        )


        layout.addView(
            btnClose
        )
        layout.addView(
            textViewTitle
        )
        layout.addView(
            mGroupCheckProb
        )
        layout.addView(
            textViewOptionAttach
        )
        layout.addView(
            mImageViewAttach
        )
        layout.addView(
            textViewOptionComment
        )

        layout.addView(
            mEditTextDescription
        )
        layout.addView(
            btnSendReport
        )
        mGroupCheckProb.layoutFields()


        mImageViewAttach.setOnClickListener(
            this::onPickPhoto
        )

        btnSendReport.setOnClickListener(
            this::onSendReport
        )

        btnClose.setOnClickListener(
            this::onClickBtnBack
        )

        return layout
    }


    private fun onSendReport(
        view: View
    ) {
        val context = view.context
        if (mFileImage == null) {
            Application.toast(
                R.string.pick_image_please,
                context
            )
            return
        }

        mFileImage?.let { file ->
            if (!file.exists()) {
                Application.toast(
                    R.string.pick_image_please,
                    context
                )
                return@let
            }

            uploadFile(
                file
            )
        }
    }



    private fun uploadFile(
        file: File
    ) {
        val context = context ?: return

        val rep = ReportEcologyService(
            context
        )

        val a = mGroupCheckProb.whoIsChecked()
        if (a == null) {
            Application.toast(
                R.string.need_to_select_one,
                context
            )
            return
        }

        var id = 3

        map.forEach {
            if (it.value == a) {
                id = it.key
                return@forEach
            }
        }

        UploadService(
            context
        ).upload(
            file
        ) { fileId ->
            rep.report(
                a,
                mEditTextDescription.text.toString(),
                fileId,
                id,
                lat,
                long
            ) {
                Application.ui {
                    if (it.code == 200) {
                        Application.toast(
                            R.string.success,
                            context
                        )
                        popFragment()
                        return@ui
                    }

                    Application.toast(
                        "${context.getString(R.string.error)}: ${it.code} ${it.body}",
                        context
                    )
                }
            }
        }
    }

    private fun onPickPhoto(
        view: View
    ) {
        pickImage { uri ->
            if (uri == null) {
                mFileImage = null
                mImageViewAttach.setImageDrawable(
                    R.drawable.ic_pick_image
                )
                mImageViewAttach.requestLayout()
                return@pickImage
            }

            context?.contentResolver?.let {
                val stream = it.openInputStream(
                    uri
                ) ?: return@let

                val publ = Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOCUMENTS
                )
                val fileOut = File(
                    "$publ/${System.currentTimeMillis()}.png"
                )

                if (fileOut.exists()) {
                    fileOut.delete()
                }

                if (fileOut.createNewFile()) {
                    Log.d("ReportEcologyProblem",
                        "onPickPhoto: CREATED TEMP FILE")
                }

                val out = FileOutputStream(
                    fileOut
                )

                val b = ByteArray(1024*1024)
                var n: Int
                while (true) {
                    n = stream.read(b)
                    if (n == -1) {
                        break
                    }
                    out.write(b, 0, n)
                }

                stream.close()
                out.close()

                mFileImage = fileOut

                mImageViewAttach.setImageDrawable(
                    R.drawable.ic_done
                )
                mImageViewAttach.requestLayout()
            }
        }
    }

}

private fun ReportEcologyFragment.onClickBtnBack(
    view: View
) {
    popFragment()
}