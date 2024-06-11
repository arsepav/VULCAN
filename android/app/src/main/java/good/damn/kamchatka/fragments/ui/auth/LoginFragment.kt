package good.damn.kamchatka.fragments.ui.auth

import android.content.Context
import android.icu.util.MeasureUnit
import android.view.View
import android.widget.LinearLayout
import good.damn.kamchatka.fragments.StackFragment

class LoginFragment
: LoginBaseFragment() {

    override fun onCreateContentView(
        context: Context,
        measureUnit: Int
    ): View {

        val layout = LinearLayout(
            context
        )

        return layout
    }

    override fun onClickBtnBack(
        view: View
    ) {
        pushFragment(
            SignInFragment()
        )
        removeFragment()
    }
}