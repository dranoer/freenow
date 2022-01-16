package com.dranoer.freenow.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dranoer.freenow.data.model.VehicleModel
import com.dranoer.freenow.data.remote.Resource
import com.dranoer.freenow.domain.VehicleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VehicleViewModel @Inject constructor(
    var repository: VehicleRepository
) : ViewModel() {

    private val _vehicles = MutableLiveData<List<VehicleModel>>()
    val vehicles: LiveData<List<VehicleModel>> = _vehicles

    fun getVehicles() {
        viewModelScope.launch {
            val result = repository.getVehicles()

            when (result) {
                is Resource.Loading -> {
                    // ToDo
                }
                is Resource.Success -> {
                    Log.d("nazanin", "the request has been successful")
                    _vehicles.value = result.data.response
                }
                is Resource.Failure -> {
                    // ToDo
                }
            }
        }
    }
}