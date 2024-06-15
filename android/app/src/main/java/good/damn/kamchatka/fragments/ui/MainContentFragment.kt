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
import good.damn.kamchatka.adapters.recycler_view.ParksAdapter
import good.damn.kamchatka.extensions.bottom
import good.damn.kamchatka.extensions.boundsFrame
import good.damn.kamchatka.extensions.boundsFrameRight
import good.damn.kamchatka.extensions.height
import good.damn.kamchatka.extensions.setTextPx
import good.damn.kamchatka.extensions.textSizeBounds
import good.damn.kamchatka.extensions.top
import good.damn.kamchatka.fragments.ui.main_content.AnthropInfoFragment
import good.damn.kamchatka.fragments.ui.main_content.LikesFragment
import good.damn.kamchatka.fragments.ui.main_content.maps.MapsFragment
import good.damn.kamchatka.fragments.ui.main_content.profile.ProfileFragment
import good.damn.kamchatka.item_decorations.MarginItemDecoration
import good.damn.kamchatka.layout_managers.ZoomCenterLayoutManager
import good.damn.kamchatka.models.view.Park
import good.damn.kamchatka.views.RoundedImageView
import good.damn.kamchatka.views.special.main_content.MainCardImage
import good.damn.kamchatka.views.textviews.ImageSpanTextView

class MainContentFragment
: ScrollableFragment() {

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

    override fun onCreateContentView(
        context: Context,
        measureUnit: Int
    ): View {

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
        val imageViewProfile = RoundedImageView(
            context
        )
        val imageViewLike = RoundedImageView(
            context
        )
        val imageViewKamchatka = AppCompatImageView(
            context
        )
        val textViewKamchatka = AppCompatTextView(
            context
        )
        val textViewKamchatka2 = ImageSpanTextView(
            context
        )
        val recyclerViewParks = RecyclerView(
            context
        )
        val cardImageZakaznik = MainCardImage(
            context
        )
        val cardImageNatureMon = MainCardImage(
            context
        )




        // Image drawable
        imageViewProfile.setImageDrawable(
            Application.drawable(
                R.mipmap.ic_launcher
            )
        )
        imageViewLike.setImageDrawable(
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
        imageViewProfile.radius = btnProfileWidth * 0.5f
        imageViewLike.radius = btnProfileWidth * 0.5f




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
            val b = (textViewKamchatka2.textSize)
                .toInt()
            it.setBounds(
                0,
                0,
                b,
                b
            )
            textViewKamchatka2.text = "${textViewKamchatka2.text}   "
            textViewKamchatka2.setImageSpan(
                it,
                textViewKamchatka2.text.length-1
            )
        }


        // Stroke colors
        imageViewProfile.setStrokeColor(
            Application.color(
                R.color.mountainsColor
            )
        )
        imageViewProfile.setStrokeOffsetColor(
            Application.color(
                R.color.background
            )
        )
        imageViewLike.setStrokeColor(
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
        imageViewLike.setStrokeAlpha(
            0.1f
        )


        // Scale
        imageViewLike.setImageScale(
            x = 0.55f,
            y = 0.55f
        )



        // Layout params
        imageViewProfile.boundsFrame(
            Gravity.START,
            width = btnProfileWidth.toInt(),
            height = btnProfileWidth.toInt(),
            left = measureUnit * 0.0700f,
            top = measureUnit * 0.0966f
        )
        textViewAppName.boundsFrame(
            Gravity.CENTER_HORIZONTAL,
            top = imageViewProfile.top() + textViewAppName.textSize * 0.3f
        )
        imageViewLike.boundsFrameRight(
            Gravity.END,
            width = btnProfileWidth.toInt(),
            height = btnProfileWidth.toInt(),
            top = imageViewProfile.top(),
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
        recyclerViewParks.boundsFrame(
            width = -1,
            height = (measureUnit * 0.5845f).toInt(),
            top = textViewKamchatka2.bottom() + measureUnit * 0.16625f
        )
        cardImageZakaznik.boundsFrame(
            Gravity.CENTER_HORIZONTAL,
            width = cardImageWidth,
            height = cardImageWidth,
            top = recyclerViewParks.bottom() + measureUnit * 0.0664f
        )
        cardImageNatureMon.boundsFrame(
            Gravity.CENTER_HORIZONTAL,
            width = cardImageWidth,
            height = cardImageWidth,
            top = cardImageZakaznik.bottom() + measureUnit * 0.0797f
        )



        // Adding views
        layout.addView(
            imageViewProfile
        )
        layout.addView(
            textViewAppName
        )
        layout.addView(
            imageViewLike
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
            recyclerViewParks
        )
        layout.addView(
            cardImageZakaznik
        )
        layout.addView(
            cardImageNatureMon
        )




        // Set up adapter
        recyclerViewParks.layoutManager = ZoomCenterLayoutManager(
            context,
            0.78f
        )
        recyclerViewParks.addItemDecoration(
            MarginItemDecoration(
                (measureUnit * 0.01f).toInt()
            )
        )

        recyclerViewParks.adapter = ParksAdapter(
            recyclerViewParks.height().toFloat(),
            arrayOf(
                Park(
                    Application.drawable(
                        R.drawable.klychevoskoy
                    ),
                    "Ключевской",
                    "Природный парк"
                ),
                Park(
                    Application.drawable(
                        R.drawable.naluchevo
                    ),
                    "Налычево",
                    "Природный парк"
                ),
                Park(
                    Application.drawable(
                        R.drawable.south_kamch
                    ),
                    "Южно-\nКамчатский",
                    "Природный парк"
                ),
                Park(
                    Application.drawable(
                        R.drawable.viluchinksiy
                    ),
                    "Вилючинский",
                    "Природный парк"
                ),
                Park(
                    Application.drawable(
                        R.drawable.bistrinskiy
                    ),
                    "Быстринский",
                    "Природный парк"
                )
            )
        )

        recyclerViewParks.post {
            recyclerViewParks.scrollBy(12,0)
        }



        // Setup listeners
        imageViewProfile.setOnClickListener(
            this::onClickImageViewProfile
        )
        imageViewLike.setOnClickListener(
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
        MapsFragment()
    )
}

private fun MainContentFragment.onClickBtnLike(
    view: View
) {
    pushFragment(
        LikesFragment()
    )
}

private fun MainContentFragment.onClickTextViewInfo(
    view: View
) {
    pushFragment(
        AnthropInfoFragment()
    )
}