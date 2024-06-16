package good.damn.kamchatka.fragments.ui.main_content.profile

import android.content.Context
import android.os.Environment
import android.util.Log
import android.view.Gravity
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import good.damn.kamchatka.extensions.boundsFrame
import good.damn.kamchatka.fragments.StackFragment
import good.damn.kamchatka.services.ReportEcologyService
import good.damn.kamchatka.services.UploadService
import good.damn.kamchatka.utils.ViewUtils
import good.damn.kamchatka.views.layout.GroupCheckBox
import java.io.File
import java.io.FileOutputStream

class ReportEcologyFragment
: StackFragment() {

    private lateinit var mGroupCheckProb: GroupCheckBox

    override fun onCreateView(
        context: Context,
        measureUnit: Int
    ): View {
        val map = hashMapOf(
            3 to "Мусор / свалка",
            4 to "Кострище",
            5 to "Нелегальная вырубка леса",
            6 to "Загрязнение воды",
            7 to "Другое",
        )
        val layout = ViewUtils.verticalLinearLayout(
            context
        )
        mGroupCheckProb = GroupCheckBox(
            context
        )
        val viewAttachPhoto = AppCompatImageView(
            context
        )

        viewAttachPhoto.setBackgroundColor(
            0xffff0000.toInt()
        )

        viewAttachPhoto.boundsFrame(
            Gravity.START,
            width = -1,
            (measureUnit * 0.2f).toInt()
        )
        mGroupCheckProb.boundsFrame(
            Gravity.START,
            width = -1
        )

        layout.addView(
            mGroupCheckProb
        )

        layout.addView(
            viewAttachPhoto
        )


        viewAttachPhoto.setOnClickListener(
            this::onPickPhoto
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

        UploadService(
            context
        ).upload(
            file
        ) { fileId ->
            rep.report(
                "Some name",
                "Some description",
                fileId,
                3
            ) {

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
                    "$publ/green_report.png"
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