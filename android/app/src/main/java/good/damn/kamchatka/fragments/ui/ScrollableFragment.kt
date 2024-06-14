package good.damn.kamchatka.fragments.ui

import android.content.Context
import android.view.View
import android.widget.ScrollView
import good.damn.kamchatka.fragments.StackFragment

abstract class ScrollableFragment
: StackFragment() {

    final override fun onCreateView(
        context: Context,
        measureUnit: Int
    ): View {
        val scrollView = ScrollView(
            context
        )

        scrollView.isVerticalScrollBarEnabled = false
        scrollView.isHorizontalScrollBarEnabled = false

        scrollView.addView(
            onCreateContentView(
                context,
                measureUnit
            )
        )

        return scrollView
    }


    abstract fun onCreateContentView(
        context: Context,
        measureUnit: Int
    ): View

}