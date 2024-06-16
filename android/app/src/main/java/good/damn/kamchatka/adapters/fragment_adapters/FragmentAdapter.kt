package good.damn.kamchatka.adapters.fragment_adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class FragmentAdapter(
    manager: FragmentManager,
    lifecycle: Lifecycle,
    private val mFragments: Array<Fragment>
): FragmentStateAdapter(
    manager,
    lifecycle
) {

    override fun getItemCount(): Int {
        return mFragments.size
    }

    override fun createFragment(
        position: Int
    ): Fragment {
       return mFragments[position]
    }
}