package good.damn.kamchatka.fragments.ui.main_content.visit_permission

import android.content.Context
import android.view.Gravity
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.viewpager2.widget.ViewPager2
import good.damn.kamchatka.Application
import good.damn.kamchatka.R
import good.damn.kamchatka.adapters.fragment_adapters.FragmentAdapter
import good.damn.kamchatka.extensions.boundsLinear
import good.damn.kamchatka.extensions.setTextPx
import good.damn.kamchatka.fragments.StackFragment
import good.damn.kamchatka.fragments.ui.main_content.profile.CreatePermissionFragment
import good.damn.kamchatka.fragments.ui.main_content.visit_permission.pager.TypePermissionFragment
import good.damn.kamchatka.models.Color
import good.damn.kamchatka.models.ShortOOPT
import good.damn.kamchatka.models.remote.json.Route
import good.damn.kamchatka.utils.ViewUtils
import good.damn.kamchatka.views.button.ButtonBack

class PermissionFragment
: StackFragment() {

    private var oopt: ShortOOPT? = null
    private var routes: Array<Route?>? = null

    override fun onCreateView(
        context: Context,
        measureUnit: Int
    ): View {
        val layout = ViewUtils.verticalLinearLayout(
            context
        )
        val btnBack = ButtonBack.createDefaultLinear(
            measureUnit,
            Application.color(
                R.color.titleColor
            ),
            context
        )
        val textViewObjectName = AppCompatTextView(
            context
        )
        val textViewObjectType = AppCompatTextView(
            context
        )
        val viewPager = ViewPager2(
            context
        )
        viewPager.id = View.generateViewId()
        viewPager.offscreenPageLimit = 2

        Application.font(
            R.font.open_sans_bold,
            context
        ).let {
            textViewObjectName.typeface = it
            textViewObjectType.typeface = it
        }

        Application.color(
            R.color.titleColor
        ).let {
            textViewObjectName.setTextColor(
                it
            )
            textViewObjectType.setTextColor(
                Color.parseFromHex(
                    it,
                    0.3f
                )
            )
        }

        oopt?.let {
            textViewObjectName.text = it.oopt.name
            textViewObjectType.text = it.type
        }

        textViewObjectName.setTextPx(
            measureUnit * 0.05314f
        )
        textViewObjectType.setTextPx(
            measureUnit * 0.03743f
        )

        ((measureUnit - measureUnit * 0.8961f) * 0.5f).let {
            left ->

            textViewObjectName.boundsLinear(
                Gravity.START,
                top = measureUnit * 0.07729f,
                left = left
            )
            textViewObjectType.boundsLinear(
                Gravity.START,
                top = measureUnit * 0.00657f,
                left = left
            )
        }


        viewPager.boundsLinear(
            Gravity.START,
            -1,
            -1
        )

        layout.addView(
            btnBack
        )
        layout.addView(
            textViewObjectName
        )
        layout.addView(
            textViewObjectType
        )
        layout.addView(
            viewPager
        )

        val typePerm = TypePermissionFragment()
        val req = CreatePermissionFragment()

        req.routes = routes

        typePerm.onSelectService = {
            viewPager.currentItem = 1
        }

        viewPager.adapter = FragmentAdapter(
            childFragmentManager,
            lifecycle,
            arrayOf(
                typePerm,
                req
            )
        )

        btnBack.setOnClickListener(
            this::onClickBtnBack
        )

        return layout
    }

    private fun onClickBtnBack(
        view: View
    ) {
        popFragment()
    }

    companion object {
        fun create(
            oopt: ShortOOPT,
            routes: Array<Route?>?,
        ): PermissionFragment {
            PermissionFragment().let {
                it.oopt = oopt
                it.routes = routes
                return it
            }
        }
    }

}