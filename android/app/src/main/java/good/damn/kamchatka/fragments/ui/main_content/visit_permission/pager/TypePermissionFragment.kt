package good.damn.kamchatka.fragments.ui.main_content.visit_permission.pager

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import good.damn.kamchatka.R
import good.damn.kamchatka.extensions.boundsLinear
import good.damn.kamchatka.utils.ViewUtils

class TypePermissionFragment
: Fragment() {

    var onSelectService: ((Byte)->Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val context = context ?: return null
        val layout = ViewUtils.verticalLinearLayout(
            context
        )
        val btnSingle = AppCompatButton(
            context
        )
        val btnGroupHu = AppCompatButton(
            context
        )
        val btnGroupCo = AppCompatButton(
            context
        )

        btnSingle.setText(
            R.string.perm_single
        )
        btnGroupHu.setText(
            R.string.perm_group_hu
        )
        btnGroupCo.setText(
            R.string.perm_group_com
        )

        btnSingle.boundsLinear(
            Gravity.CENTER_HORIZONTAL,
            -1,
            -2
        )

        btnGroupHu.boundsLinear(
            Gravity.CENTER_HORIZONTAL,
            -1,
            -2
        )

        btnGroupCo.boundsLinear(
            Gravity.CENTER_HORIZONTAL,
            -1,
            -2
        )

        layout.addView(
            btnSingle
        )
        layout.addView(
            btnGroupHu
        )
        layout.addView(
            btnGroupCo
        )


        btnSingle.setOnClickListener {
            onSelectService?.invoke(0)
        }

        btnGroupHu.setOnClickListener {
            onSelectService?.invoke(1)
        }

        btnGroupCo.setOnClickListener {
            onSelectService?.invoke(2)
        }

        return layout
    }


}