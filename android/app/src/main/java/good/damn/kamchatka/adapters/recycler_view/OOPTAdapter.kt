package good.damn.kamchatka.adapters.recycler_view

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import good.damn.kamchatka.models.SecurityZone
import good.damn.kamchatka.utils.HTTPUtils
import good.damn.kamchatka.views.holders.ViewHolderPark
import good.damn.kamchatka.views.special.details.listeners.OnSelectOOPTListener

class OOPTAdapter(
    private val mRecyclerViewHeight: Float,
    private val mZones: Array<SecurityZone?>
): RecyclerView.Adapter<ViewHolderPark>() {

    var onSelectOOPTListener: OnSelectOOPTListener? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolderPark {
        return ViewHolderPark.create(
            mRecyclerViewHeight,
            parent.context
        )
    }

    override fun getItemCount(): Int {
        return mZones.size
    }

    override fun onBindViewHolder(
        holder: ViewHolderPark,
        position: Int
    ) {
        val zone = mZones[position]
        val oopt = zone?.oopt ?: return
        holder.setName(
            oopt.name
        )

        holder.setType(
            oopt.desc
        )

        oopt.image_url?.let {
            HTTPUtils.loadImage(
                it
            ) { bitmap ->
                holder.setPreview(bitmap)
            }
        }

        holder.itemView.setOnClickListener {
            onSelectOOPTListener?.onSelectOOPT(
                zone
            )
        }

    }
}