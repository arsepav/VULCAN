package good.damn.kamchatka.extensions

import androidx.fragment.app.Fragment
import good.damn.kamchatka.MainActivity

fun Fragment.mainActivity(): MainActivity {
    return activity as MainActivity
}