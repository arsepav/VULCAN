package good.damn.kamchatka.fragments.ui

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import good.damn.kamchatka.Application
import good.damn.kamchatka.R
import good.damn.kamchatka.adapters.recycler_view.OOPTAdapter
import good.damn.kamchatka.extensions.bottom
import good.damn.kamchatka.extensions.boundsFrame
import good.damn.kamchatka.extensions.boundsFrameRight
import good.damn.kamchatka.extensions.height
import good.damn.kamchatka.extensions.setTextPx
import good.damn.kamchatka.extensions.textSizeBounds
import good.damn.kamchatka.extensions.top
import good.damn.kamchatka.fragments.ui.main_content.AnthropInfoFragment
import good.damn.kamchatka.fragments.ui.blocks.LikeFragment
import good.damn.kamchatka.fragments.ui.blocks.MonumentsFragment
import good.damn.kamchatka.fragments.ui.blocks.ZakaznikiFragment
import good.damn.kamchatka.fragments.ui.main_content.details.ParkDetailsFragment
import good.damn.kamchatka.fragments.ui.main_content.maps.GoogleMapFragment
import good.damn.kamchatka.fragments.ui.main_content.maps.MapsFragment
import good.damn.kamchatka.fragments.ui.main_content.profile.ProfileFragment
import good.damn.kamchatka.item_decorations.MarginItemDecoration
import good.damn.kamchatka.layout_managers.ZoomCenterLayoutManager
import good.damn.kamchatka.models.SecurityZone
import good.damn.kamchatka.models.ShortOOPT
import good.damn.kamchatka.services.GeoService
import good.damn.kamchatka.services.interfaces.OnGetSecurityZonesListener
import good.damn.kamchatka.views.RoundedImageView
import good.damn.kamchatka.views.special.details.listeners.OnSelectModelListener
import good.damn.kamchatka.views.special.main_content.MainCardImage

class MainContentFragment
: ScrollableFragment(),
OnSelectModelListener<ShortOOPT>,
OnGetSecurityZonesListener {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigationBarColor(
            Application.color(
                R.color.background
            )
        )
    }

    override fun hasPreciseMeasurement(): Boolean {
        return false
    }

    private lateinit var mRecyclerViewOOPT: RecyclerView
    private lateinit var mImageViewProfile: RoundedImageView
    private lateinit var mImageViewLike: RoundedImageView

    override fun onCreateContentView(
        context: Context,
        measureUnit: Int
    ): View {

        GeoService(
            context
        ).let {
            it.setOnGetZonesListener(
                this
            )
            it.requestSecurityZones()
        }

        val btnProfileWidth = measureUnit * 0.1f
        val cardImageWidth = (measureUnit * 0.8961f)
            .toInt()

        // Allocating views
        val layout = FrameLayout(
            context
        )
        val textViewAppName = TextView(
            context
        )
        mImageViewProfile = RoundedImageView(
            context
        )
        mImageViewLike = RoundedImageView(
            context
        )
        val imageViewKamchatka = AppCompatImageView(
            context
        )
        val textViewKamchatka = AppCompatTextView(
            context
        )
        val textViewKamchatka2 = AppCompatTextView(
            context
        )
        mRecyclerViewOOPT = RecyclerView(
            context
        )
        val cardImageZakaznik = MainCardImage(
            context
        )
        val cardImageNatureMon = MainCardImage(
            context
        )


        cardImageZakaznik.mOnClickListener = View.OnClickListener {
            pushFragment(
                ZakaznikiFragment()
            )
        }

        cardImageNatureMon.mOnClickListener = View.OnClickListener {
            pushFragment(
                MonumentsFragment()
            )
        }


        // Image drawable
        mImageViewProfile.setImageDrawable(
            Application.drawable(
                R.mipmap.ic_launcher
            )
        )
        mImageViewLike.setImageDrawable(
            Application.drawable(
                R.drawable.ic_like
            )
        )
        imageViewKamchatka.setImageDrawable(
            Application.drawable(
                R.drawable.kamchatka
            )
        )
        cardImageZakaznik.setBackgroundResource(
            R.drawable.zakaznik
        )
        cardImageNatureMon.setBackgroundResource(
            R.drawable.nature_monument
        )



        // CornerRadius
        mImageViewProfile.radius = btnProfileWidth * 0.5f
        mImageViewLike.radius = btnProfileWidth * 0.5f




        // Text size
        textViewAppName.setTextPx(
            measureUnit * 0.0507f
        )
        textViewKamchatka.setTextPx(
            measureUnit * 0.06864f
        )
        textViewKamchatka2.setTextPx(
            measureUnit * 0.0381f
        )





        // Font
        textViewAppName.typeface = Application.font(
            R.font.open_sans_bold,
            context
        )
        textViewKamchatka.typeface = Application.font(
            R.font.open_sans_bold,
            context
        )
        textViewKamchatka2.typeface = Application.font(
            R.font.nunito_regular,
            context
        )
        cardImageZakaznik.typeface = Application.font(
            R.font.open_sans_bold,
            context
        )
        cardImageNatureMon.typeface = cardImageZakaznik
            .typeface




        // Text
        textViewAppName.setText(
            R.string.app_name
        )
        textViewKamchatka.setText(
            R.string.kamchatka
        )
        textViewKamchatka2.setText(
            R.string.maps_objects
        )
        cardImageZakaznik.title = "13"
        cardImageNatureMon.title = "63"
        cardImageZakaznik.subtitle = getString(
            R.string.nature_zakazniki
        )
        cardImageNatureMon.subtitle = getString(
            R.string.nature_monuments
        )


        // Image Span
        Application.drawable(
            R.drawable.ic_info
        )?.let {
            val b = (textViewKamchatka2.textSize * 1.35f)
                .toInt()
            it.setBounds(
                0,
                0,
                b,
                b
            )
            textViewKamchatka2.text = "${textViewKamchatka2.text}  "
            textViewKamchatka2.gravity = Gravity
                .CENTER_VERTICAL

            textViewKamchatka2.setCompoundDrawables(
                null,
                null,
                it,
                null
            )
        }


        // Stroke colors
        mImageViewProfile.setStrokeColor(
            Application.color(
                R.color.mountainsColor
            )
        )
        mImageViewProfile.setStrokeOffsetColor(
            Application.color(
                R.color.background
            )
        )
        mImageViewLike.setStrokeColor(
            Application.color(
                R.color.titleColor
            )
        )




        // Background colors
        layout.setBackgroundColor(
            Application.color(
                R.color.background
            )
        )





        // Text color
        val blackColor = Application.color(
            R.color.titleColor
        )
        textViewAppName.setTextColor(
            blackColor
        )
        textViewKamchatka.setTextColor(
            blackColor
        )
        textViewKamchatka2.setTextColor(
            blackColor
        )
        cardImageZakaznik.setTextColor(
            Color.WHITE
        )
        cardImageNatureMon.setTextColor(
            Color.WHITE
        )


        // Alpha
        mImageViewLike.setStrokeAlpha(
            0.1f
        )


        // Scale
        mImageViewLike.setImageScale(
            x = 0.55f,
            y = 0.55f
        )



        // Layout params
        mImageViewProfile.boundsFrame(
            Gravity.START,
            width = btnProfileWidth.toInt(),
            height = btnProfileWidth.toInt(),
            left = measureUnit * 0.0700f,
            top = measureUnit * 0.0966f
        )
        textViewAppName.boundsFrame(
            Gravity.CENTER_HORIZONTAL,
            top = mImageViewProfile.top() + textViewAppName.textSize * 0.3f
        )
        mImageViewLike.boundsFrameRight(
            Gravity.END,
            width = btnProfileWidth.toInt(),
            height = btnProfileWidth.toInt(),
            top = mImageViewProfile.top(),
            right = measureUnit * 0.0700f
        )
        imageViewKamchatka.boundsFrame(
            Gravity.CENTER_HORIZONTAL,
            width = (measureUnit * 0.4541f).toInt(),
            height = (measureUnit * 0.5072f).toInt(),
            top = measureUnit * 0.1425f + textViewAppName.bottom()
        )
        textViewKamchatka.boundsFrame(
            Gravity.CENTER_HORIZONTAL,
            top = imageViewKamchatka.bottom() + measureUnit * 0.03623f,
            height = textViewKamchatka.textSizeBounds()
        )
        textViewKamchatka2.boundsFrame(
            Gravity.CENTER_HORIZONTAL,
            top = textViewKamchatka.bottom() + measureUnit * 0.0099f,
            height = textViewKamchatka2.textSizeBounds()
        )
        mRecyclerViewOOPT.boundsFrame(
            width = -1,
            height = (measureUnit * 0.5845f).toInt(),
            top = textViewKamchatka2.bottom() + measureUnit * 0.16625f
        )
        cardImageZakaznik.boundsFrame(
            Gravity.CENTER_HORIZONTAL,
            width = cardImageWidth,
            height = cardImageWidth,
            top = mRecyclerViewOOPT.bottom() + measureUnit * 0.0664f
        )
        cardImageNatureMon.boundsFrame(
            Gravity.CENTER_HORIZONTAL,
            width = cardImageWidth,
            height = cardImageWidth,
            top = cardImageZakaznik.bottom() + measureUnit * 0.0797f
        )



        // Adding views
        layout.addView(
            mImageViewProfile
        )
        layout.addView(
            textViewAppName
        )
        layout.addView(
            mImageViewLike
        )
        layout.addView(
            imageViewKamchatka
        )
        layout.addView(
            textViewKamchatka
        )
        layout.addView(
            textViewKamchatka2
        )
        layout.addView(
            mRecyclerViewOOPT
        )
        layout.addView(
            cardImageZakaznik
        )
        layout.addView(
            cardImageNatureMon
        )




        // Set up adapter
        mRecyclerViewOOPT.layoutManager = ZoomCenterLayoutManager(
            context,
            0.78f
        )
        mRecyclerViewOOPT.addItemDecoration(
            MarginItemDecoration(
                (measureUnit * 0.01f).toInt()
            )
        )


        mRecyclerViewOOPT.post {
            mRecyclerViewOOPT.scrollBy(12,0)
        }



        // Setup listeners
        mImageViewProfile.setOnClickListener(
            this::onClickImageViewProfile
        )
        mImageViewLike.setOnClickListener(
            this::onClickBtnLike
        )
        imageViewKamchatka.setOnClickListener(
            this::onClickImageViewKamchatka
        )
        textViewKamchatka2.setOnClickListener(
            this::onClickTextViewInfo
        )

        return layout
    }

    override fun onSelectModel(
        zone: ShortOOPT
    ) {
        pushFragment(
            ParkDetailsFragment.create(
                zone
            )
        )
    }

    override fun onGetSecurityZones(
        zones: Array<SecurityZone?>
    ) {
        val type = getString(
            R.string.park
        )

        val oopts: Array<ShortOOPT?> = Array(zones.size) {
            val zone = zones[it] ?: return@Array null

            var name = zone.oopt.name
            if (name.length > 16) {
                name = "${name.substring(0,16)}…"
            }

            ShortOOPT(
                zone.oopt,
                type,
                shortName = name
            )
        }

        OOPTAdapter(
            mRecyclerViewOOPT.height().toFloat(),
            oopts
        ).let {
            it.onSelectOOPTListener = this
            mRecyclerViewOOPT.adapter = it
        }
    }

}

private fun MainContentFragment.onClickImageViewProfile(
    view: View
) {
    pushFragment(
        ProfileFragment()
    )
}

private fun MainContentFragment.onClickImageViewKamchatka(
    view: View
) {
    pushFragment(
        MapsFragment.create(
            GoogleMapFragment()
        )
    )
}

private fun MainContentFragment.onClickBtnLike(
    view: View
) {
    pushFragment(
        LikeFragment()
    )
}

private fun MainContentFragment.onClickTextViewInfo(
    view: View
) {
    pushFragment(
        AnthropInfoFragment()
    )
}