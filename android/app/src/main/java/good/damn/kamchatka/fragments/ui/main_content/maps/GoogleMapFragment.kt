package good.damn.kamchatka.fragments.ui.main_content.maps

import good.damn.kamchatka.models.Color
import android.os.Bundle
import android.util.Log
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.Polygon
import com.google.android.gms.maps.model.RoundCap
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import good.damn.kamchatka.Application
import good.damn.kamchatka.MainActivity
import good.damn.kamchatka.R
import good.damn.kamchatka.fragments.StackFragment
import good.damn.kamchatka.fragments.ui.main_content.details.ParkDetailsFragment
import good.damn.kamchatka.models.RouteMap
import good.damn.kamchatka.models.SecurityZone
import good.damn.kamchatka.models.ShortOOPT
import good.damn.kamchatka.models.map.AntroColors
import good.damn.kamchatka.models.map.OOPTColors
import good.damn.kamchatka.models.remote.json.OOPTObject
import good.damn.kamchatka.services.GeoService
import good.damn.kamchatka.services.interfaces.OnGetObjectsListener
import good.damn.kamchatka.services.interfaces.OnGetRoutesListener
import good.damn.kamchatka.services.interfaces.OnGetSecurityZonesListener
import good.damn.kamchatka.views.bottom_sheets.BottomSheetInfo
import kotlin.random.Random

class GoogleMapFragment
: SupportMapFragment(),
OnMapReadyCallback,
GoogleMap.OnPolygonClickListener,
OnGetSecurityZonesListener,
OnGetRoutesListener,
OnGetObjectsListener,
GoogleMap.OnMarkerClickListener {

    companion object {
        private const val TAG = "MapsFragment"
    }

    var markerLatLng: LatLng? = null

    private val mZoneColors = OOPTColors
        .entries
        .toTypedArray()

    private val mOOPTColors = HashMap<Int, Int>()

    private var mGeoService: GeoService? = null
    private lateinit var map: GoogleMap

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(savedInstanceState)

        context?.let {
            mGeoService = GeoService(
                it
            )
            getMapAsync(
                this
            )
        }
    }

    override fun onMapReady(
        googleMap: GoogleMap
    ) {
        map = googleMap

        val kamchatka = LatLng(
            55.184952,
            158.394596
        )

        map.moveCamera(
            CameraUpdateFactory
                .newLatLngZoom(
                    kamchatka,
                    5.0f
                )
        )

        Log.d(TAG, "onMapReady: SPECIAL_MARKER: $markerLatLng")
        markerLatLng?.let {
            map.addMarker(
                MarkerOptions()
                    .position(it)
                    .icon(
                        BitmapDescriptorFactory.defaultMarker(
                            BitmapDescriptorFactory.HUE_CYAN
                        )
                    ).zIndex(
                        1.0f
                    )
            )

            map.moveCamera(
                CameraUpdateFactory
                    .newLatLngZoom(
                        it,
                        6.0f
                    )
            )
        }


        mGeoService?.setOnGetZonesListener(
            this
        )

        mGeoService?.setOnGetRoutesListener(
            this
        )
        mGeoService?.setOnGetObjectsListener(
            this
        )

        mGeoService?.requestSecurityZones()

        map.setOnPolygonClickListener(
            this
        )

        map.setOnMarkerClickListener(
            this
        )

    }

    override fun onPolygonClick(
        poly: Polygon
    ) {
        context?.let {
            val oopt = poly.tag as? SecurityZone ?: return
            pushFragment(
                ParkDetailsFragment.create(
                    ShortOOPT(
                        oopt.oopt,
                        it.getString(
                            R.string.park
                        ),
                        shortName = ""
                    )
                )
            )
        }
    }

    override fun onMarkerClick(
        marker: Marker
    ): Boolean {
        val ooptObj = marker.tag as? OOPTObject ?: return false

        val sheet = BottomSheetInfo()

        sheet.title = ooptObj.name
        sheet.desc = ooptObj.desc

        sheet.show(
            childFragmentManager,
            "bottomSheet"
        )

        return true
    }

    override fun onGetSecurityZones(
        zones: Array<SecurityZone?>
    ) {
        zones.forEach { zone ->
            if (zone == null) {
                return@forEach
            }

            val fillColor = mZoneColors[
                Random.nextInt(
                    mZoneColors.size
                )
            ].color

            val oopt = zone.oopt

            val dangerRate = oopt.dangerRate.toInt()

            mOOPTColors[zone.oopt.id] = fillColor


            map.addPolygon(
                zone.polygon
            ).apply {
                isClickable = true
                tag = zone
                this.fillColor = fillColor
                strokeColor = AntroColors.colors[
                    dangerRate
                ]
                strokeWidth = 11.0f
            }
        }
        mGeoService?.requestRoutes()
        mGeoService?.requestObjects()
    }

    override fun onGetRoutes(
        routes: Array<RouteMap?>
    ) {
        val cap = RoundCap()
        val errorColor = Color.parseFromHex(
            0xffff0000.toInt(),
            0.3f
        )
        routes.forEach { route ->
            if (route == null) {
                return@forEach
            }

            val dangerRate = route.route.dangerRate.toInt()

            map.addMarker(
                MarkerOptions()
                    .position(
                        route.route.coords[0]
                    )
            )?.apply {
                setIcon(
                    BitmapDescriptorFactory.fromResource(
                        AntroColors.markers[dangerRate]
                    )
                )
            }

            map.addPolyline(
                route.polyline
            ).apply {
                route.route.apply {
                    color = if (
                        ooptId == null
                    ) errorColor else mOOPTColors[ooptId]
                        ?: errorColor

                    width = 10.0f
                    startCap = cap
                    endCap = cap
                }
            }
        }
    }

    override fun onGetObjects(
        objects: Array<OOPTObject?>
    ) {
        objects.forEach { obj ->
            if (obj == null) {
                return@forEach
            }

            MarkerOptions().position(
                obj.coords
            ).icon(
                BitmapDescriptorFactory.defaultMarker(
                    BitmapDescriptorFactory.HUE_GREEN
                )
            ).zIndex(
                1.0f
            ).let { options ->
                map.addMarker(
                    options
                )?.let { marker ->
                    marker.tag = obj
                }
            }
        }
    }


    fun pushFragment(
        frag: StackFragment
    ) {
        (activity as? MainActivity)?.pushFragment(
            frag
        )
    }

}