package com.dranoer.freenow.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dranoer.freenow.Constants.TAG
import com.dranoer.freenow.R
import com.dranoer.freenow.data.remote.Resource
import com.dranoer.freenow.databinding.FragmentVehicleListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VehicleListFragment : Fragment() {

    private var _binding: FragmentVehicleListBinding? = null
    private val binding get() = _binding!!

    val viewModel: VehicleViewModel by activityViewModels()

    private lateinit var navHost: Fragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVehicleListBinding.inflate(inflater, container, false)
        navHost =
            requireActivity().supportFragmentManager.findFragmentById(R.id.nav_host_fragment)!!

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupVehicleList()
        setupViewState()
    }

    /**
     * Setup view
     *
     * This function observe the response which is coming from remote.
     * It has three states: Loading, Success and Failure
     */
    private fun setupViewState() {
        viewModel.getVehicles().observe(viewLifecycleOwner) { result ->
            when (result) {
                is Resource.Loading -> {
                    binding.progressbar.isVisible = true
                }
                is Resource.Success -> {
                    binding.progressbar.isGone = true
                }
                is Resource.Failure -> {
                    binding.progressbar.isGone = true
                    Log.e(TAG, "${result.exception.message}")
                }
            }
        }
    }

    /**
     * Setup view
     *
     * This function setup the list of vehicles which is coming from local database.
     * It has two functionality:
     *   - Instantiate the adapter for recyclerview
     *   - Fill adapter with local data
     */
    private fun setupVehicleList() {
        val recyclerView = binding.recyclerview
        val adapter = VehicleAdapter(onClickListener = { id, lat, lng ->
            val action = VehicleListFragmentDirections.actionVehicleListToMap(
                id,
                lat.toString(),
                lng.toString()
            )
            navHost.findNavController().navigate(action)
        })
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.vehicles.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }
}