package com.dranoer.freenow.domain

import android.util.Log
import com.dranoer.freenow.Constants.TAG
import com.dranoer.freenow.data.local.LocalDataSource
import com.dranoer.freenow.data.mapper.mapToEntity
import com.dranoer.freenow.data.model.Response
import com.dranoer.freenow.data.model.VehicleEntity
import com.dranoer.freenow.data.remote.NetworkDataSource
import com.dranoer.freenow.data.remote.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class VehicleRepository @ExperimentalCoroutinesApi @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    private val localDataSource: LocalDataSource,
) {

    val vehicles: Flow<List<VehicleEntity>> = localDataSource.vehicles

    @ExperimentalCoroutinesApi
    suspend fun getVehicles(): Resource<Response> {

        val response = networkDataSource.getVehicles()
        when (response) {
            is Resource.Success -> {
                for (item in response.data.response) {
                    localDataSource.saveVehicle(mapToEntity(item))
                }
            }
            else -> {
                // Todo 04 : Turn to error
                Log.d(TAG, "Response was not successful so it has not saved in the local db")
            }
        }

        return response
    }
}