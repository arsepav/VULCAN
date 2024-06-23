package good.damn.kamchatka.views.bottom_sheets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import good.damn.kamchatka.Application
import good.damn.kamchatka.extensions.size
import good.damn.kamchatka.views.special.details.CardItemDescription

class BottomSheetInfo
: BottomSheetDialogFragment() {

    var title: String? = null
    var desc: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val context = context ?: return null
        val card = CardItemDescription(
            context
        )

        card.about = title
        card.desc = desc

        card.size(
            Application.WIDTH,
            -2
        )

        card.layoutIt()

        return card
    }


    companion object {
        fun create(
            title: String?,
            desc: String?
        ): BottomSheetInfo {
            val b = BottomSheetInfo()
            b.title = title
            b.desc = desc
            return b
        }
    }

}