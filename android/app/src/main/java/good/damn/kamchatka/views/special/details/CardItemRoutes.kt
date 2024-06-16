package good.damn.kamchatka.views.special.details

import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import good.damn.kamchatka.R
import good.damn.kamchatka.adapters.recycler_view.RoutesAdapter
import good.damn.kamchatka.extensions.boundsLinear
import good.damn.kamchatka.extensions.height
import good.damn.kamchatka.item_decorations.MarginItemDecoration
import good.damn.kamchatka.models.remote.json.Route

class CardItemRoutes(
    context: Context
): CardItem(
    context
) {
    var routes: Array<Route?>? = null
        set(v) {
            field = v
            v?.let {
                mRecyclerViewRoutes.adapter = RoutesAdapter(
                    it,
                    mRecyclerViewRoutes.height()
                )
            }
        }

    private lateinit var mRecyclerViewRoutes: RecyclerView

    override fun onCreateLinearLayout(
        layout: LinearLayout,
        height: Int,
        width: Int,
        left: Int
    ) {
        val textViewRoute = TextView(
            context
        )
        mRecyclerViewRoutes = RecyclerView(
            context
        )

        textViewRoute.setText(
            R.string.routes
        )

        textViewRoute.boundsLinear(
            Gravity.START,
            left = left.toFloat()
        )
        mRecyclerViewRoutes.boundsLinear(
            Gravity.START,
            width = width,
            height = (height * 0.7854f).toInt()
        )

        layout.addView(textViewRoute)
        layout.addView(mRecyclerViewRoutes)



        mRecyclerViewRoutes.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )

        mRecyclerViewRoutes.addItemDecoration(
            MarginItemDecoration(
                (width * 0.03985f).toInt()
            )
        )
    }
}