package good.damn.kamchatka.adapters.recycler_view

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import good.damn.kamchatka.models.remote.json.Route
import good.damn.kamchatka.views.holders.ViewHolderRoute

class RoutesAdapter(
    private val mRoutes: Array<Route?>,
    private val mRecyclerViewHeight: Int
): RecyclerView.Adapter<ViewHolderRoute>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolderRoute {
        return ViewHolderRoute.create(
            mRecyclerViewHeight,
            parent.context
        )
    }

    override fun getItemCount() = mRoutes.size


    override fun onBindViewHolder(
        holder: ViewHolderRoute,
        position: Int
    ) {
        val route = mRoutes[position]

        holder.setName(
            route?.name
        )

        holder.setDangerRate(
            route?.dangerRate?.toInt() ?: 0
        )

    }


}