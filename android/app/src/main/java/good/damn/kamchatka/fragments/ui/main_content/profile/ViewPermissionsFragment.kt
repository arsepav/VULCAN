package good.damn.kamchatka.fragments.ui.main_content.profile

import android.content.Context
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import good.damn.kamchatka.Application
import good.damn.kamchatka.R
import good.damn.kamchatka.adapters.fragment_adapters.PermissionRequestAdapter
import good.damn.kamchatka.fragments.StackFragment
import good.damn.kamchatka.item_decorations.MarginItemDecoration
import good.damn.kamchatka.services.PermissionService
import good.damn.kamchatka.utils.ViewUtils

class ViewPermissionsFragment
: StackFragment() {

    override fun onCreateView(
        context: Context,
        measureUnit: Int
    ): View {
        val layout = ViewUtils.verticalLinearLayout(
            context
        )

        val titleTextView = AppCompatTextView(
            context
        )

        val recyclerView = RecyclerView(
            context
        )

        titleTextView.setText(
            R.string.permission_requests_list
        )

        layout.addView(
            titleTextView
        )
        layout.addView(
            recyclerView
        )

        recyclerView.layoutManager = LinearLayoutManager(
            context
        )

        recyclerView.addItemDecoration(
            MarginItemDecoration(
                50
            )
        )

        PermissionService(
            context
        ).getPermissions {
            Application.ui {

                recyclerView.adapter = PermissionRequestAdapter(
                    it
                )

            }
        }

        return layout
    }

}