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
import good.damn.kamchatka.Application
import good.damn.kamchatka.R
import good.damn.kamchatka.extensions.boundsLinear
import good.damn.kamchatka.extensions.height
import good.damn.kamchatka.utils.ViewUtils
import good.damn.kamchatka.views.button.ButtonCardBig
import good.damn.kamchatka.views.button.CardState

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
        val btnSingle = ButtonCardBig(
            context
        )
        val btnGroupHu = ButtonCardBig(
            context
        )
        val btnGroupCo = ButtonCardBig(
            context
        )

        btnSingle.apply {
            title = getString(
                R.string.perm_single
            )
            subtitle = getString(
                R.string.perm_single_s
            )
            setDrawableEndId(
                R.drawable.ic_single
            )
        }

        btnGroupHu.apply {
            title = getString(
                R.string.perm_group_hu
            )
            subtitle = getString(
                R.string.perm_group_hu_s
            )
            setDrawableEndId(
                R.drawable.ic_group
            )
        }

        btnGroupCo.apply {
            title = getString(
                R.string.perm_group_com
            )
            subtitle = getString(
                R.string.perm_group_com_s
            )
            setDrawableEndId(
                R.drawable.ic_case
            )
        }


        (Application.WIDTH * 0.90821f).toInt().let { width ->
            (Application.WIDTH * 0.2222f).toInt().let { height ->
                (Application.WIDTH * 0.0169f).let { top ->
                    btnSingle.boundsLinear(
                        Gravity.CENTER_HORIZONTAL,
                        width = width,
                        height = height,
                        top = Application.WIDTH * 0.1014f
                    )

                    btnGroupHu.boundsLinear(
                        Gravity.CENTER_HORIZONTAL,
                        width = width,
                        height = height,
                        top = top
                    )

                    btnGroupCo.boundsLinear(
                        Gravity.CENTER_HORIZONTAL,
                        width = width,
                        height = height,
                        top = top
                    )
                }
            }
        }

        (btnSingle.height() * 0.163f).let {
            btnSingle.radius = it
            btnGroupCo.radius = it
            btnGroupHu.radius = it
        }

        btnSingle.layoutIt()
        btnGroupHu.layoutIt()
        btnGroupCo.layoutIt()

        layout.apply {
            addView(btnSingle)
            addView(btnGroupHu)
            addView(btnGroupCo)
        }


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