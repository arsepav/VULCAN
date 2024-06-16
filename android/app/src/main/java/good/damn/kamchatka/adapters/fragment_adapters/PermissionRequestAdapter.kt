package good.damn.kamchatka.adapters.fragment_adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import good.damn.kamchatka.models.permission.PermissionRequest
import good.damn.kamchatka.views.holders.ViewHolderPermission

class PermissionRequestAdapter(
    private val mData: Array<PermissionRequest>
): RecyclerView.Adapter<ViewHolderPermission>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolderPermission {
        return ViewHolderPermission.create(
            parent.context
        )
    }

    override fun getItemCount(): Int = mData.size

    override fun onBindViewHolder(
        holder: ViewHolderPermission,
        position: Int
    ) {
        val perm = mData[position]

        holder.setName(
            perm.name
        )

        var state = "Рассматривается"
        if (perm.approved) {
            state = "Одобрено"
        } else {
            if (perm.reviewed) {
                state = "Отклонено"
            }
        }

        holder.setState(
            state
        )
    }


}