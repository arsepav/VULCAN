package good.damn.kamchatka

import android.animation.ValueAnimator
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import android.view.animation.AccelerateInterpolator
import android.widget.FrameLayout
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsControllerCompat
import good.damn.kamchatka.fragments.StackFragment
import good.damn.kamchatka.fragments.ui.MainContentFragment
import good.damn.kamchatka.fragments.ui.auth.SignInFragment
import good.damn.kamchatka.fragments.ui.SplashFragment
import good.damn.kamchatka.services.AuthService
import good.damn.kamchatka.services.TimeService
import good.damn.kamchatka.services.TokenService

class MainActivity
: AppCompatActivity(),
ViewTreeObserver.OnGlobalLayoutListener {

    private lateinit var mContainer: FrameLayout

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(
            savedInstanceState
        )

        setNavigationBarColor(
            Application.color(
                R.color.background
            )
        )

        setStatusBarColor(
            Application.color(
                R.color.background
            )
        )

        val context = this

        mContainer = FrameLayout(
            context
        )

        mContainer.setBackgroundColor(
            Application.color(
                R.color.background
            )
        )

        mContainer.id = View.generateViewId()
        mContainer.viewTreeObserver
            .addOnGlobalLayoutListener(
                this
            )

        setContentView(
            mContainer
        )

        val tokenService = TokenService(
            getSharedPreferences(
                Application.KEY_SHARED,
                Context.MODE_PRIVATE
            )
        )

        val splashFragment = SplashFragment()
        splashFragment.onEndAnimation = {
            splashFragment.popFragment()
            pushFragment(
                if (tokenService.isTokenExpired) {
                    SignInFragment()
                } else {
                    Application.TOKEN = tokenService.token
                    MainContentFragment()
                }
            )
        }

        pushFragment(
            splashFragment
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

        super.onWindowFocusChanged(hasFocus)
    }

    override fun onBackPressed() {
        if (supportFragmentManager
            .fragments.size <= 1
        ) {
            super.onBackPressed()
            return
        }

        popFragment()
    }

    fun popFragment() {
        val fragment = supportFragmentManager
            .fragments
            .last() ?: return

        (fragment as? StackFragment)?.let {
            it.hideFragment {
                removeFragment(
                    it
                )
            }
        }
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

        Log.d(TAG, "pushFragment: ")
        fragment.showFragment()
    }

    fun setStatusBarColor(
        @ColorInt color: Int
    ) {
        window.statusBarColor = color
    }

    fun setNavigationBarColor(
        @ColorInt color: Int
    ) {
        window.navigationBarColor = color
    }

}