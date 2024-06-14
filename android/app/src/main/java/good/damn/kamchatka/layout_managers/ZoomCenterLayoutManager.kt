package good.damn.kamchatka.layout_managers

import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.abs

class ZoomCenterLayoutManager(
    context: Context
): LinearLayoutManager(
    context,
    HORIZONTAL,
    false
) {
    companion object {
        private const val TAG = "ZoomCenterLayoutManager"
    }

    private val minScale = 0.55f
    private var mHalfWidth = 0

    override fun onLayoutCompleted(
        state: RecyclerView.State?
    ) {
        super.onLayoutCompleted(state)
        mHalfWidth = width / 2
    }

    override fun scrollHorizontallyBy(
        dx: Int,
        recycler: RecyclerView.Recycler?,
        state: RecyclerView.State?
    ): Int {
        if (orientation == VERTICAL) {
            return 0
        }

        for (i in 0 until childCount) {
            val child = getChildAt(i) ?: continue
            // Getting middle X of child in global coord
            val childMidpoint = (
                getDecoratedRight(child) + getDecoratedLeft(child)
                ) * 0.5f

            val distance = abs(childMidpoint-mHalfWidth)
            val normDistance = 1.0f - distance / mHalfWidth
            val scale = minScale + normDistance * (1f-minScale)
            child.scaleX = scale
            child.scaleY = scale
        }
        return super.scrollHorizontallyBy(dx, recycler, state)
    }

}