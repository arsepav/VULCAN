package good.damn.kamchatka.views.layout

import android.content.Context
import good.damn.kamchatka.models.Color
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.widget.AppCompatTextView
import good.damn.kamchatka.Application
import good.damn.kamchatka.R
import good.damn.kamchatka.extensions.boundsLinear
import good.damn.kamchatka.extensions.setTextColorId
import good.damn.kamchatka.extensions.setTextPx
import good.damn.kamchatka.views.checkboxes.CheckBoxRound
import good.damn.kamchatka.views.layout.models.GroupField
import org.intellij.lang.annotations.JdkConstants.FontStyle

class GroupCheckBox(
    context: Context
): LinearLayout(
    context
), View.OnClickListener {

    companion object {
        private const val TAG = "GroupTextField"
    }

    var fields: Array<GroupField>? = null
        set(v) {
            removeAllViews()
            field = v
            Log.d(TAG, "FIELDS_SET: $field")
            if (fields == null) {
                mCheckBoxes = null
                return
            }
            mCheckBoxes = Array(fields!!.size) {
                CheckBoxRound(
                    context
                )
            }

        }

    var interval: Float = 8f

    var titleBottomMargin: Float = 2f

    var titleTextSize: Float = 8f
        set(v) {
            mTextViewTitle.setTextPx(
                v
            )
            field = v
        }

    var checkBoxSize: Float = 1f

    var checkBoxRadius: Float = 1f

    var checkBoxStrokeWidth: Float = 2f

    var checkBoxTextPadding = 1f

    var textDrawable: Drawable? = null

    @StringRes
    var title: Int = 0
        set(v) {
            field = v
            if (v == 0) {
                return
            }

            mTextViewTitle.setText(
                v
            )
        }

    @ColorInt
    var textColor: Int = 0xffff0000.toInt()

    @ColorInt
    var checkBoxColor: Int = 0xffff0000.toInt()

    @FontStyle
    var typeface = Typeface.DEFAULT

    private val mTextViewTitle = AppCompatTextView(
        context
    )

    private var mCheckBoxes: Array<CheckBoxRound>? = null

    init {
        orientation = VERTICAL
        setBackgroundColor(0)

        mTextViewTitle.setTextColorId(
            R.color.titleColor
        )

        mTextViewTitle.typeface = Application.font(
            R.font.open_sans_bold,
            context
        )

        mTextViewTitle.gravity = Gravity
            .CENTER_VERTICAL

        mTextViewTitle.boundsLinear(
            Gravity.START,
            left = 0f,
            top = 0f
        )
    }

    fun layoutFields() {
        addView(
            mTextViewTitle
        )

        Log.d(TAG, "layoutFields: ${fields?.size} ${mCheckBoxes?.size}")
        if (fields == null || mCheckBoxes == null) {
            return
        }

        val width = layoutParams.width
        val height = (width * 0.06038f).toInt()
        val textSize = height * 0.6f

        if (textDrawable != null) {
            mTextViewTitle.text = "${mTextViewTitle.text}  "
            val imageSize = height
            textDrawable!!.setBounds(
                0,
                0,
                imageSize,
                imageSize
            )
            mTextViewTitle.setCompoundDrawables(
                null,
                null,
                textDrawable,
                null
            )
        }


        for (i in fields!!.indices) {
            val checkBox = mCheckBoxes!![i]
            val info = fields!![i]

            checkBox.setOnClickListener(
                this
            )

            checkBox.setStrokeColor(
                checkBoxColor
            )

            checkBox.setStrokeWidth(
                checkBoxStrokeWidth
            )

            checkBox.setFillColor(
                checkBoxColor
            )

            checkBox.setTypeface(
                typeface
            )

            checkBox.setTextSizePx(
                textSize
            )

            checkBox.text = info.text

            checkBox.radius = checkBoxRadius

            checkBox.checkBoxHeight = checkBoxSize
            checkBox.checkBoxWidth = checkBoxSize

            checkBox.textPadding = checkBoxTextPadding

            checkBox.boundsLinear(
                Gravity.CENTER_HORIZONTAL,
                width = width,
                height = checkBoxSize.toInt(),
                top = if (i == 0)
                    titleBottomMargin
                else interval
            )

            addView(
                checkBox
            )
        }
    }


    override fun onClick(
        v: View?
    ) {
        mCheckBoxes?.forEach {
            it.isChecked = false
            it.invalidate()
        }

        (v as? CheckBoxRound)?.let {
            it.isChecked = true
            invalidate()
        }
    }

    fun setTitle(
        title: String
    ) {
        mTextViewTitle.text = title
    }

    fun whoIsChecked(): String? {
        mCheckBoxes?.forEach {
            if (it.isChecked) {
                return it.text
            }
        }

        return null
    }

    fun getData(): HashMap<String, Boolean>? {
        val map = HashMap<String,Boolean>()
        var isCheckedOnce = false
        mCheckBoxes?.forEach {
            map[it.text] = it.isChecked
            if (it.isChecked && !isCheckedOnce) {
                isCheckedOnce = true
            }
        }
        if (isCheckedOnce) {
            return map
        }

        return null
    }
}