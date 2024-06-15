package good.damn.kamchatka.fragments.ui

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import good.damn.kamchatka.Application
import good.damn.kamchatka.R
import good.damn.kamchatka.extensions.size
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

        scrollView.setBackgroundColor(
            Application.color(
                R.color.background
            )
        )

        scrollView.size(
            -1,
            -1
        )

        return scrollView
    }


    abstract fun onCreateContentView(
        context: Context,
        measureUnit: Int
    ): View

}