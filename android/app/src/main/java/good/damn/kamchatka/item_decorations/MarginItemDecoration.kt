package good.damn.kamchatka.item_decorations

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MarginItemDecoration(
    private val margin: Int = 0,
    private val marginVertical: Int = 0
): RecyclerView.ItemDecoration() {

    //
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.left = margin
        outRect.right = margin

        outRect.top = marginVertical
        outRect.right = marginVertical
    }
}