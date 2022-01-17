package com.dranoer.freenow.data.local

import com.dranoer.freenow.data.model.VehicleEntity
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val vehicleDao: VehicleDao) {

    suspend fun saveVehicle(vehicle: VehicleEntity) {
        vehicleDao.insertVehicle(vehicle)
    }
}