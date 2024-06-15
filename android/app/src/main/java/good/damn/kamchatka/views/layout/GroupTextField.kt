package good.damn.kamchatka.views.layout

import android.content.Context
import good.damn.kamchatka.models.Color
import android.graphics.Typeface
import android.util.Log
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.StringRes
import good.damn.kamchatka.Application
import good.damn.kamchatka.R
import good.damn.kamchatka.extensions.boundsLinear
import good.damn.kamchatka.extensions.setTextColorId
import good.damn.kamchatka.extensions.setTextPx
import good.damn.kamchatka.views.layout.models.GroupField
import good.damn.kamchatka.views.text_fields.TextFieldRound
import org.intellij.lang.annotations.JdkConstants.FontStyle

class GroupTextField(
    context: Context
): LinearLayout(
    context
) {

    companion object {
        private const val TAG = "GroupTextField"
    }

    var fields: Array<GroupField>? = null
        set(v) {
            removeAllViews()
            field = v
            Log.d(TAG, "FIELDS_SET: $field")
            if (fields == null) {
                mTextFields = null
                return
            }
            mTextFields = Array(fields!!.size) {
                TextFieldRound(
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

    @ColorInt
    var fieldColor: Int = 0xffff0000.toInt()

    @FontStyle
    var typeface = Typeface.DEFAULT

    private val mTextViewTitle = TextView(
        context
    )

    private var mTextFields: Array<TextFieldRound>? = null

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

        Log.d(TAG, "layoutFields: ${fields?.size} ${mTextFields?.size}")
        if (fields == null || mTextFields == null) {
            return
        }

        val width = layoutParams.width

        val heightField = (width * 0.1135f).toInt()
        val corner = heightField * 0.255f
        val strokeWidth = heightField * 0.02427f
        val textSize = heightField * 0.1125f
        val paddingLeft = (width * 0.04851f).toInt()
        val paddingDrawable = paddingLeft
        val iconSize = (textSize*3.5f).toInt()

        val hintColor = Color.parseFromHexId(
            R.color.accentColor,
            0.3f
        )

        for (i in fields!!.indices) {
            val textField = mTextFields!![i]
            val info = fields!![i]

            textField.setStrokeColor(
                fieldColor
            )

            textField.setStrokeWidth(
                strokeWidth
            )

            textField.setPadding(
                paddingLeft,
                0,
                0,
                0
            )

            if (info.drawableId != 0) {
                val d = Application.drawable(
                    info.drawableId
                )

                d?.setBounds(
                    0,
                    0,
                    iconSize,
                    iconSize
                )
                textField.compoundDrawablePadding = paddingDrawable
                textField.setCompoundDrawables(
                    d,
                    null,
                    null,
                    null
                )
            }

            textField.textSize = textSize

            textField.typeface = typeface

            textField.setHintTextColor(
                hintColor
            )

            textField.setHint(
                info.hintId
            )

            textField.boundsLinear(
                Gravity.CENTER_HORIZONTAL,
                width = width,
                height = heightField,
                top = if (i == 0)
                    titleBottomMargin
                else interval
            )

            textField.cornerRadius = corner

            addView(
                textField
            )
        }
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
        Log.d(TAG, "layoutFields: ${fields?.size} ${mTextFields?.size}")
    }

    fun setTitle(
        @StringRes id: Int
    ) {
        mTextViewTitle.setText(
            id
        )
    }

    fun setTitle(
        title: String
    ) {
        mTextViewTitle.text = title
    }

}