package com.dranoer.freenow.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dranoer.freenow.databinding.FragmentVehicleListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VehicleListFragment : Fragment() {

    private var _binding: FragmentVehicleListBinding? = null
    private val binding get() = _binding!!

    val viewModel: VehicleViewModel by viewModels()

    companion object {
        fun newInstance() = VehicleListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentVehicleListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getVehicles()
    }
}