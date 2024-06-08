package good.damn.kamchatka.fragments.ui.main_navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment

class PlacesFragment
: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val context = context ?: return null

        val layout = FrameLayout(
            context
        )

        layout.setBackgroundColor(
            0xffff0000.toInt()
        )

        return layout
    }

}