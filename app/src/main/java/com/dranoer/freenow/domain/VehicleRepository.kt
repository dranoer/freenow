package com.dranoer.freenow.domain

import com.dranoer.freenow.data.model.Response
import com.dranoer.freenow.data.remote.NetworkDataSource
import com.dranoer.freenow.data.remote.Resource
import javax.inject.Inject

class VehicleRepository @Inject constructor(
    private val networkDataSource: NetworkDataSource,
) {

    suspend fun getVehicles(): Resource<Response> {
        return networkDataSource.getVehicles()
    }
}