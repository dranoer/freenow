package com.dranoer.freenow.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.dranoer.freenow.Constants.ZOOM
import com.dranoer.freenow.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MapFragment : Fragment() {

    val viewModel: VehicleViewModel by activityViewModels()
    private val args: MapFragmentArgs by navArgs()

    /**
     * Setup map
     *
     * This is a callback for when the map is ready.
     * It has three functionality:
     *   - Add marker on each vehicle on the map
     *   - Zoom on the selected item coming from the previous screen
     *   - Setup map with zooming controller icons
     */
    private val callback = OnMapReadyCallback { googleMap ->
        googleMap.uiSettings.isZoomControlsEnabled = true
        val selectedPosition = LatLng(args.latitude.toDouble(), args.longitude.toDouble())
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(selectedPosition, ZOOM))

        viewModel.vehicles.observe(viewLifecycleOwner) {
            it.forEach { item ->
                googleMap.addMarker(
                    MarkerOptions()
                        .position(LatLng(item.latitude, item.longitude))
                        .title(item.id.toString())
                        .snippet(item.fleetType.name)
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.taxi_marker))
                )
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }
}