package good.damn.kamchatka.fragments.ui.main_content

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment

class PlacesFragment
: Fragment() {

    companion object {
        private const val TAG = "PlacesFragment"
    }
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.d(TAG, "onCreateView: ")
        
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