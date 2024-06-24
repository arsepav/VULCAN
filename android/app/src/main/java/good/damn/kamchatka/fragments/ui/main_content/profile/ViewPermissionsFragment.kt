package good.damn.kamchatka.fragments.ui.main_content.profile

import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import good.damn.kamchatka.Application
import good.damn.kamchatka.R
import good.damn.kamchatka.adapters.fragment_adapters.PermissionRequestAdapter
import good.damn.kamchatka.extensions.bottom
import good.damn.kamchatka.extensions.boundsFrame
import good.damn.kamchatka.extensions.boundsLinear
import good.damn.kamchatka.extensions.left
import good.damn.kamchatka.extensions.size
import good.damn.kamchatka.extensions.textSizeBounds
import good.damn.kamchatka.extensions.top
import good.damn.kamchatka.fragments.StackFragment
import good.damn.kamchatka.item_decorations.MarginItemDecoration
import good.damn.kamchatka.models.Color
import good.damn.kamchatka.models.permission.PermissionRequest
import good.damn.kamchatka.services.PermissionService
import good.damn.kamchatka.utils.ViewUtils
import good.damn.kamchatka.views.button.ButtonBack

class ViewPermissionsFragment
: StackFragment() {

    var permissions: Array<PermissionRequest>? = null

    override fun onCreateView(
        context: Context,
        measureUnit: Int
    ): View {
        val layout = FrameLayout(
            context
        )

        val layoutTitle = ViewUtils.verticalLinearLayout(
            context
        )

        val btnBack = ButtonBack.createDefaultLinear(
            measureUnit,
            Application.color(
                R.color.btnBackArrow
            ),
            context
        )

        val titleTextView = ViewUtils.titleNav(
            measureUnit,
            R.string.permission_requests_list,
            context
        )

        val recyclerView = RecyclerView(
            context
        )

        layoutTitle.setBackgroundColor(
            Color.parseFromHexId(
                R.color.background,
                0.9f
            )
        )

        layout.setBackgroundColor(
            Application.color(
                R.color.background
            )
        )

        val margin = measureUnit * 0.0917f

        titleTextView.boundsFrame(
            Gravity.START,
            left = btnBack.left(),
            top = margin
        )

        layoutTitle.size(
            width = -1,
            height = -2
        )

        layoutTitle.apply {
            addView(btnBack)
            addView(titleTextView)
        }

        layout.apply {
            addView(recyclerView)
            addView(layoutTitle)
        }

        titleTextView.setPadding(
            0,
            0,
            0,
            margin.toInt()
        )

        recyclerView.layoutManager = LinearLayoutManager(
            context
        )


        (measureUnit * 0.0483f).toInt().let {
            recyclerView.addItemDecoration(
                MarginItemDecoration(
                    margin = it,
                    marginVertical = it
                )
            )
        }


        layoutTitle.post {
            recyclerView.clipToPadding = false

            recyclerView.setPadding(
                0,
                layoutTitle.height,
                0,
                (Application.HEIGHT * 0.09f).toInt()
            )
        }


        permissions?.let {
            recyclerView.adapter = PermissionRequestAdapter(
                it
            )
        }

        btnBack.setOnClickListener(
            this::onClickBtnBack
        )

        return layout
    }


    companion object {
        fun create(
            perms: Array<PermissionRequest>
        ): ViewPermissionsFragment {
            val f = ViewPermissionsFragment()
            f.permissions = perms
            return f
        }
    }
}

private fun ViewPermissionsFragment.onClickBtnBack(
    view: View
) {
    popFragment()
}