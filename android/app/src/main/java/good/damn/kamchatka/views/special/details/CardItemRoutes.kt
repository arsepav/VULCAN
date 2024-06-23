package good.damn.kamchatka.views.special.details

import android.content.Context
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import good.damn.kamchatka.Application
import good.damn.kamchatka.R
import good.damn.kamchatka.adapters.recycler_view.RoutesAdapter
import good.damn.kamchatka.extensions.boundsLinear
import good.damn.kamchatka.extensions.height
import good.damn.kamchatka.extensions.setTextPx
import good.damn.kamchatka.item_decorations.MarginItemDecoration
import good.damn.kamchatka.models.remote.json.Route
import good.damn.kamchatka.views.special.details.listeners.OnSelectModelListener

class CardItemRoutes(
    context: Context
): CardItem(
    context
) {
    private var mOnSelectRouteListener: OnSelectModelListener<Route>? = null
    var routes: Array<Route?>? = null
        set(v) {
            field = v
            v?.let {
                val a = RoutesAdapter(
                    it,
                    mRecyclerViewRoutes.height()
                )
                a.onSelectRouteListener = mOnSelectRouteListener
                mRecyclerViewRoutes.adapter = a
            }
        }

    private lateinit var mRecyclerViewRoutes: RecyclerView

    override fun onCreateLinearLayout(
        layout: LinearLayout,
        measureUnit: Int,
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

        Application.font(
            R.font.open_sans_bold,
            context
        ).let {
            textViewRoute.typeface = it
        }

        (measureUnit * 0.04835f).let {
            textViewRoute.setTextPx(
                it
            )
        }

        Application.color(
            R.color.titleColor
        ).let {
            textViewRoute.setTextColor(
                it
            )
        }

        textViewRoute.boundsLinear(
            Gravity.START,
            left = left.toFloat(),
            top = measureUnit * 0.06159f
        )
        mRecyclerViewRoutes.boundsLinear(
            Gravity.START,
            width = measureUnit,
            top = measureUnit * 0.04347f,
            height = (measureUnit * 0.5555f).toInt()
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
                (measureUnit * 0.03985f).toInt()
            )
        )
    }
    
    fun setOnSelectRouteListener(
        l: OnSelectModelListener<Route>
    ) {
        mOnSelectRouteListener = l
    }
}