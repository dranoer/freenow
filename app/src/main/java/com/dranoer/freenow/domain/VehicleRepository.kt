package com.dranoer.freenow.domain

import android.util.Log
import com.dranoer.freenow.data.local.LocalDataSource
import com.dranoer.freenow.data.model.Response
import com.dranoer.freenow.data.model.VehicleEntity
import com.dranoer.freenow.data.remote.NetworkDataSource
import com.dranoer.freenow.data.remote.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

class VehicleRepository @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    private val localDataSource: LocalDataSource,
) {

    @ExperimentalCoroutinesApi
    suspend fun getVehicles(): Resource<Response> {

        val response = networkDataSource.getVehicles()
        when (response) {
            is Resource.Success -> {
                for (item in response.data.response) {
                    localDataSource.saveVehicle(
                        VehicleEntity(
                            id = item.id,
                            fleetType = item.fleetType,
                            latitude = item.coordinate.latitude,
                            longitude = item.coordinate.longitude
                        )
                    )
                }
            }
            else -> {
                Log.d("nazanin", "Response was not successful so it has not saved in the local db")
            }
        }

        return response
    }
}