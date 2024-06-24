package good.damn.kamchatka.adapters.recycler_view

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import good.damn.kamchatka.models.ShortOOPT
import good.damn.kamchatka.utils.URLUtils
import good.damn.kamchatka.views.holders.ViewHolderOOPT
import good.damn.kamchatka.views.special.details.listeners.OnSelectModelListener

class OOPTAdapter(
    private val mRecyclerViewHeight: Float,
    private val mZones: Array<ShortOOPT?>
): RecyclerView.Adapter<ViewHolderOOPT>() {

    var onSelectOOPTListener: OnSelectModelListener<ShortOOPT>? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolderOOPT {
        return ViewHolderOOPT.create(
            mRecyclerViewHeight,
            parent.context
        )
    }

    override fun getItemCount(): Int {
        return mZones.size
    }

    override fun onBindViewHolder(
        holder: ViewHolderOOPT,
        position: Int
    ) {
        val zone = mZones[position]
        val oopt = zone?.oopt ?: return
        holder.setName(
            zone.shortName
        )

        holder.setType(
            zone.type
        )

        holder.itemView.setOnClickListener {
            onSelectOOPTListener?.onSelectModel(
                zone
            )
        }

        if (zone.image != null) {
            holder.setPreview(
                zone.image!!
            )
            return
        }

        oopt.image_url?.let {
            URLUtils.loadImage(
                it,
                holder.imageSize,
                holder.imageSize,
                holder.itemView.context.cacheDir
            ) { bitmap, isCached ->
                if (!isCached) {
                    zone.image = bitmap
                }
                holder.setPreview(bitmap)
            }
        }
    }
}