package good.damn.kamchatka.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorInt
import androidx.fragment.app.Fragment
import good.damn.kamchatka.MainActivity

abstract class StackFragment
: Fragment() {

    companion object {
        private const val TAG = "StackFragment"
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

        val view = onCreateView(
            context
        )

        view.isClickable = true

        return view
    }

    fun removeFragment() {
        mainActivity().removeFragment(
            this
        )
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
        context: Context
    ): View
}

private fun StackFragment
    .mainActivity(): MainActivity {
    return activity as MainActivity
}