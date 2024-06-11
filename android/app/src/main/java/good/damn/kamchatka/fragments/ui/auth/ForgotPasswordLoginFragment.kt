package good.damn.kamchatka.fragments.ui.auth

import android.content.Context
import android.view.View
import android.widget.LinearLayout

class ForgotPasswordLoginFragment
: LoginBaseFragment() {

    override fun onClickBtnBack(
        view: View
    ) {
        pushFragment(
            LoginFragment()
        )
        removeFragment()
    }

    override fun onCreateContentFrameView(
        context: Context,
        measureUnit: Int
    ): View {
        val layout = LinearLayout(
            context
        )

        return layout
    }


}