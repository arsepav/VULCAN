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
import com.google.android.gms.maps.model.LatLng

class DefinePointMapFragment
: SupportMapFragment(),
OnMapReadyCallback,
GoogleMap.OnMapClickListener {

    var onAcceptPosition: ((LatLng) -> Unit)? = null

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

    }

}