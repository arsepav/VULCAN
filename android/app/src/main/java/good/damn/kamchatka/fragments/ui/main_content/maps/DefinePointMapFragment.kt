package good.damn.kamchatka.fragments.ui.main_content.maps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import good.damn.kamchatka.Application
import good.damn.kamchatka.R
import good.damn.kamchatka.extensions.mainActivity

class DefinePointMapFragment
: SupportMapFragment(),
OnMapReadyCallback,
GoogleMap.OnMapClickListener {

    var onAcceptPosition: ((LatLng) -> Unit)? = null

    private var markerPos: Marker? = null

    private lateinit var map: GoogleMap

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getMapAsync(
            this
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        context?.let { context ->
            val layout = FrameLayout(
                context
            )

            val btnAccept = Button(
                context
            )

            btnAccept.text = "Подтвердить"

            layout.addView(
                super.onCreateView(inflater, container, savedInstanceState)
            )

            layout.addView(
                btnAccept,
                -2,
                -2
            )

            btnAccept.setOnClickListener(
                this::onClickBtnAcceptPosition
            )

            return layout
        }

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onMapReady(
        map: GoogleMap
    ) {
        this.map = map

        map.setOnMapClickListener(
            this
        )

    }

    override fun onMapClick(
        pos: LatLng
    ) {
        markerPos?.remove()

        markerPos = map.addMarker(
            MarkerOptions()
                .position(
                    pos
                ).icon(
                    BitmapDescriptorFactory.fromResource(
                        R.drawable.ic_x_mark_bmp
                    )
                )
        )

        context?.let { context ->
            markerPos?.apply {
                Application.toast(
                    "${position.latitude} ${position.longitude}",
                    context
                )
            }
        }
    }

    private fun onClickBtnAcceptPosition(
        view: View
    ) {
        if (markerPos == null) {
            Application.toast(
                R.string.select_position,
                view.context
            )
            return
        }

        onAcceptPosition?.invoke(
            markerPos!!.position
        )
    }

}