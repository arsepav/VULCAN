package good.damn.kamchatka

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import android.widget.FrameLayout
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsControllerCompat
import good.damn.kamchatka.fragments.StackFragment
import good.damn.kamchatka.fragments.ui.SplashFragment

class MainActivity
: AppCompatActivity(),
ViewTreeObserver.OnGlobalLayoutListener {

    private lateinit var mContainer: FrameLayout

    private var mNavigationBarColor = Color.WHITE
    private var mStatusBarColor = Color.WHITE

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(
            savedInstanceState
        )

        mNavigationBarColor = Application.color(
            R.color.background
        )

        mStatusBarColor = mNavigationBarColor

        val context = this

        mContainer = FrameLayout(
            context
        )

        mContainer.id = View.generateViewId()
        mContainer.viewTreeObserver
            .addOnGlobalLayoutListener(
                this
            )

        setContentView(
            mContainer
        )

        pushFragment(
            SplashFragment()
        )
    }

    override fun onGlobalLayout() {
        Application.WIDTH = mContainer.width
        Application.HEIGHT = mContainer.height

        mContainer.viewTreeObserver
            .removeOnGlobalLayoutListener(
                this
            )
    }

    override fun onWindowFocusChanged(
        hasFocus: Boolean
    ) {

        val wic = WindowInsetsControllerCompat(
            window,
            window.decorView
        )

        wic.isAppearanceLightStatusBars = true
        wic.isAppearanceLightNavigationBars = true

        window.statusBarColor = mStatusBarColor
        window.navigationBarColor = mNavigationBarColor

        super.onWindowFocusChanged(hasFocus)
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

    fun setNavigationBarColor(
        @ColorInt color: Int
    ) {
        mNavigationBarColor = color
        window.decorView.requestFocus()
    }

}