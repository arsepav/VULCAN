package good.damn.kamchatka.adapters.fragment_adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import good.damn.kamchatka.R
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

        holder.setTitle(
            perm.name
        )
        holder.setSubtitle(
            "Природный парк"
        )

        var state = "Рассматривается"
        var id = R.drawable.ic_reviewing
        if (perm.approved) {
            state = "Одобрено"
            id = R.drawable.ic_approved
        } else {
            if (perm.reviewed) {
                state = "Отклонено"
                id = R.drawable.ic_x_mark
            }
        }

        holder.setDrawableEnd(
            id
        )

        holder.setState(
            state
        )
    }


}