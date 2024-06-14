package good.damn.kamchatka.adapters.recycler_view

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import good.damn.kamchatka.models.view.Park
import good.damn.kamchatka.views.holders.ViewHolderPark

class ParksAdapter(
    private val mRecyclerViewHeight: Float,
    private val mParks: Array<Park>
): RecyclerView.Adapter<ViewHolderPark>() {

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
        return mParks.size
    }

    override fun onBindViewHolder(
        holder: ViewHolderPark,
        position: Int
    ) {
        val park = mParks[position]
        holder.setName(
            park.name
        )

        holder.setType(
            park.type
        )

        holder.setPreview(
            park.imagePreview
        )

    }
}