package good.damn.kamchatka.fragments.ui.main_content.profile

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Environment
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import good.damn.kamchatka.Application
import good.damn.kamchatka.R
import good.damn.kamchatka.extensions.boundsFrame
import good.damn.kamchatka.extensions.boundsLinear
import good.damn.kamchatka.extensions.height
import good.damn.kamchatka.extensions.left
import good.damn.kamchatka.extensions.setTextColorId
import good.damn.kamchatka.extensions.setTextPx
import good.damn.kamchatka.fragments.StackFragment
import good.damn.kamchatka.fragments.ui.ScrollableFragment
import good.damn.kamchatka.services.ReportEcologyService
import good.damn.kamchatka.services.UploadService
import good.damn.kamchatka.utils.ViewUtils
import good.damn.kamchatka.views.RoundedImageView
import good.damn.kamchatka.views.button.ButtonBack
import good.damn.kamchatka.views.button.ButtonRound
import good.damn.kamchatka.views.layout.GroupCheckBox
import good.damn.kamchatka.views.layout.models.GroupField
import good.damn.kamchatka.views.text_fields.TextField
import java.io.File
import java.io.FileOutputStream

class ReportEcologyFragment
: ScrollableFragment(),
LocationListener {

    companion object {
        private const val TAG = "ReportEcologyFragment"
    }

    private lateinit var mGroupCheckProb: GroupCheckBox
    private lateinit var mEditTextDescription: EditText

    private val map = hashMapOf(
        3 to "Мусор / свалка",
        4 to "Кострище",
        5 to "Нелегальная вырубка леса",
        6 to "Загрязнение воды",
        7 to "Другое",
    )

    private var lat = 0.0
    private var long = 0.0

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
        mGroupCheckProb = GroupCheckBox(
            context
        )
        mEditTextDescription = EditText(
            context
        )

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

        mEditTextDescription.hint = "Комментарий"

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

        val btnAttachPhoto = ButtonRound(
            context
        )

        btnAttachPhoto.setText(
            R.string.send
        )

        btnAttachPhoto.setTextColorId(
            R.color.textColorBtn
        )

        btnAttachPhoto.typeface = Application.font(
            R.font.open_sans_semi_bold,
            context
        )

        btnAttachPhoto.setBackgroundColor(
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
            height = (measureUnit * 0.615f).toInt(),
            top = measureUnit * 0.111f,
            left = textViewTitle.left()
        )

        btnAttachPhoto.boundsLinear(
            Gravity.CENTER_HORIZONTAL,
            width = (measureUnit - 2 * textViewTitle.left()).toInt(),
            height = (measureUnit * 0.1352f).toInt(),
            top = measureUnit * 0.1316f
        )


        btnAttachPhoto.cornerRadius = btnAttachPhoto.height() * 0.3f

        btnAttachPhoto.setTextPx(
            btnAttachPhoto.height() * 0.3214f
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
            mEditTextDescription
        )
        layout.addView(
            btnAttachPhoto
        )

        mGroupCheckProb.layoutFields()


        btnAttachPhoto.setOnClickListener(
            this::onPickPhoto
        )

        btnClose.setOnClickListener(
            this::onClickBtnBack
        )

        return layout
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

                uploadFile(
                    fileOut
                )
            }
        }
    }

}

private fun ReportEcologyFragment.onClickBtnBack(
    view: View
) {
    popFragment()
}