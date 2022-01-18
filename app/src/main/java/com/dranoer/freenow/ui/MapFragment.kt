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
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MapFragment : Fragment() {

    val viewModel: VehicleViewModel by activityViewModels()
    val args: MapFragmentArgs by navArgs()

    private val callback = OnMapReadyCallback { googleMap ->
        val selectedPosition = LatLng(args.latitude.toDouble(), args.longitude.toDouble())
        googleMap.addMarker(MarkerOptions().position(selectedPosition).title(args.id.toString()))
        googleMap.uiSettings.isZoomControlsEnabled = true
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(selectedPosition, ZOOM))

        viewModel.vehicles.observe(viewLifecycleOwner) {
            for (item in it) {
                googleMap.addMarker(
                    MarkerOptions()
                        .position(LatLng(item.latitude, item.longitude))
                        .title(item.id.toString())
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