package good.damn.kamchatka.fragments

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import androidx.annotation.ColorInt
import androidx.fragment.app.Fragment
import good.damn.kamchatka.Application
import good.damn.kamchatka.MainActivity
import kotlin.math.log

abstract class StackFragment
: Fragment(),
ValueAnimator.AnimatorUpdateListener {

    companion object {
        private const val TAG = "StackFragment"
    }

    private val mAnimator = ValueAnimator()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val context = context
        if (context == null) {
            Log.d(TAG, "onCreateView: NULL_CONTEXT = NULL_VIEW")
            return null
        }

        mAnimator.duration = 200
        mAnimator.addUpdateListener(
            this
        )

        val measureUnit = Application.WIDTH

        val view = onCreateView(
            context,
            measureUnit
        )
        view.alpha = 0.0f

        view.isClickable = true

        return view
    }

    override fun onAnimationUpdate(
        animation: ValueAnimator
    ) {
        view?.alpha = animation.animatedValue as Float
    }
    
    fun removeFragment() {
        mainActivity().removeFragment(
            this
        )
    }

    fun hideFragment(
        completion: () -> Unit
    ) {
        mAnimator.setFloatValues(
            1.0f, 0.0f
        )

        mAnimator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {}
            override fun onAnimationRepeat(animation: Animator) {}
            override fun onAnimationCancel(animation: Animator) { onAnimationEnd(animation) }
            override fun onAnimationEnd(animation: Animator) {
                completion()
            }
        })

        mAnimator.start()
    }

    fun showFragment() {
        mAnimator.setFloatValues(
            0.0f, 1.0f
        )
        
        mAnimator.start()
    }

    fun popFragment() {
        mainActivity().popFragment()
    }

    fun pushFragment(
        fragment: StackFragment
    ) {
        mainActivity().pushFragment(
            fragment
        )
    }

    fun statusBarColor(
        @ColorInt color: Int
    ) {
        mainActivity().setStatusBarColor(
            color
        )
    }

    fun navigationBarColor(
        @ColorInt color: Int
    ) {
        mainActivity().setNavigationBarColor(
            color
        )
    }

    abstract fun onCreateView(
        context: Context,
        measureUnit: Int
    ): View
}

private fun StackFragment
    .mainActivity(): MainActivity {
    return activity as MainActivity
}