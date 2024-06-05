package good.damn.kamchatka

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import good.damn.kamchatka.fragments.ui.AuthFragment
import good.damn.kamchatka.fragments.StackFragment

class MainActivity
: AppCompatActivity() {

    private lateinit var mContainer: FrameLayout

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(
            savedInstanceState
        )

        val context = this

        mContainer = FrameLayout(
            context
        )

        mContainer.id = View.generateViewId()

        setContentView(
            mContainer
        )

        pushFragment(
            AuthFragment()
        )
    }


    fun popFragment() {
        val fragment = supportFragmentManager
            .fragments
            .last() ?: return
        removeFragment(
            fragment as StackFragment
        )
    }

    fun removeFragment(
        fragment: StackFragment
    ) {
        supportFragmentManager
            .beginTransaction()
            .remove(fragment)
            .commit()
    }

    fun pushFragment(
        fragment: StackFragment
    ) {
        supportFragmentManager
            .beginTransaction()
            .add(mContainer.id, fragment)
            .commit()
    }

}