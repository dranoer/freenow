package com.dranoer.freenow.data.local

import com.dranoer.freenow.data.model.VehicleEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val vehicleDao: VehicleDao) {

    val vehicles: Flow<List<VehicleEntity>> = vehicleDao.getVehicles()

    suspend fun saveVehicle(vehicle: VehicleEntity) {
        vehicleDao.insertVehicle(vehicle)
    }
}