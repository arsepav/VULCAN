package good.damn.kamchatka.fragments.ui

import android.content.Context
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import good.damn.kamchatka.R
import good.damn.kamchatka.fragments.StackFragment
import good.damn.kamchatka.views.TextField

class AuthFragment
: StackFragment() {

    companion object {
        private const val TAG = "AuthFragment"
    }


    override fun onCreateView(
        context: Context
    ): View {
        val layout = LinearLayout(
            context
        )

        val editTextEmail = TextField(
            context
        )

        val editTextPassword = TextField(
            context
        )

        val btnLogin = Button(
            context
        )

        editTextPassword.transformationMethod =
            PasswordTransformationMethod()

        layout.orientation = LinearLayout
            .VERTICAL

        editTextEmail.setHint(
            R.string.email
        )

        editTextPassword.setHint(
            R.string.password
        )

        btnLogin.setText(
            R.string.log_in
        )

        layout.addView(
            editTextEmail,
            -1,
            -2
        )

        layout.addView(
            editTextPassword,
            -1,
            -2
        )

        layout.addView(
            btnLogin,
            -1,
            -2
        )

        btnLogin.setOnClickListener(
            this::onClickBtnLogIn
        )

        return layout
    }

}

private fun AuthFragment.onClickBtnLogIn(
    view: View
) {
    pushFragment(
        MainNavigationFragment()
    )
    removeFragment()
}