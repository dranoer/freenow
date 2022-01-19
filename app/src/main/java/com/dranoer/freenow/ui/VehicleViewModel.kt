package com.dranoer.freenow.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.dranoer.freenow.data.model.VehicleEntity
import com.dranoer.freenow.data.remote.Resource
import com.dranoer.freenow.domain.VehicleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@HiltViewModel
class VehicleViewModel @Inject constructor(
    var repository: VehicleRepository
) : ViewModel() {

    val vehicles: LiveData<List<VehicleEntity>> = repository.vehicles.asLiveData()

    @ExperimentalCoroutinesApi
    @JvmName("getVehiclesFromRemote")
    fun getVehicles() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())

        try {
            emit(repository.getVehicles())
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }
}