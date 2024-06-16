package good.damn.kamchatka.adapters.recycler_view

import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import good.damn.kamchatka.models.ShortOOPT
import good.damn.kamchatka.models.remote.json.Route
import good.damn.kamchatka.views.holders.ViewHolderRoute
import good.damn.kamchatka.views.special.details.listeners.OnSelectModelListener

class RoutesAdapter(
    private val mRoutes: Array<Route?>,
    private val mRecyclerViewHeight: Int
): RecyclerView.Adapter<ViewHolderRoute>() {

    companion object {
        private const val TAG = "RoutesAdapter"
    }

    var onSelectRouteListener: OnSelectModelListener<Route>? = null


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
        val route = mRoutes[position] ?: return

        Log.d(TAG, "onBindViewHolder: ${route.name}")
        holder.setName(
            route.name
        )

        holder.setDangerRate(
            route.dangerRate.toInt()
        )

        holder.itemView.setOnClickListener {
            onSelectRouteListener?.onSelectModel(
                route
            )
        }

    }


}