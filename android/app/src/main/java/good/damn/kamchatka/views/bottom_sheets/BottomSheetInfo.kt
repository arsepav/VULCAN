package good.damn.kamchatka.views.bottom_sheets

import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ScrollView
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.widget.NestedScrollView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import good.damn.kamchatka.Application
import good.damn.kamchatka.extensions.boundsFrame
import good.damn.kamchatka.extensions.boundsLinear
import good.damn.kamchatka.extensions.size
import good.damn.kamchatka.utils.HTTPUtils
import good.damn.kamchatka.utils.ViewUtils
import good.damn.kamchatka.views.RoundedImageView
import good.damn.kamchatka.views.special.details.CardItemDescription

class BottomSheetInfo
: BottomSheetDialogFragment() {

    var title: String? = null
    var desc: String? = null
    var imageUrl: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val context = context ?: return null

        val scrollView = NestedScrollView(
            context
        )
        val layout = ViewUtils.verticalLinearLayout(
            context
        )
        val imageView = AppCompatImageView(
            context
        )
        val card = CardItemDescription(
            context
        )

        imageUrl?.let { url ->
            HTTPUtils.loadImage(
                url
            ) { bitmap ->
                Log.d(TAG, "onCreateView: LOADED_IMAGE: $url ${bitmap?.height}")
                imageView.setImageBitmap(
                    bitmap
                )
            }
        }

        imageView.adjustViewBounds = true
        imageView.scaleType = ImageView
            .ScaleType
            .CENTER_CROP

        card.about = title
        card.desc = desc

        card.boundsLinear(
            Gravity.START,
            Application.WIDTH,
            -2,
            top = Application.HEIGHT * -0.08f
        )

        imageView.boundsFrame(
            Gravity.START,
            width = Application.WIDTH,
            height = (Application.WIDTH * 0.7f)
                .toInt()
        )

        card.layoutIt()


        layout.addView(
            imageView
        )

        layout.addView(
            card
        )

        scrollView.addView(
            layout
        )
        return scrollView
    }


    companion object {
        private const val TAG = "BottomSheetInfo"

        fun create(
            title: String?,
            desc: String?,
            imageUrl: String? = null
        ): BottomSheetInfo {
            val b = BottomSheetInfo()
            b.title = title
            b.desc = desc
            b.imageUrl = imageUrl
            return b
        }
    }

}