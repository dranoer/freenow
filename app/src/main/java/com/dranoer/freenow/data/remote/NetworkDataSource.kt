package com.dranoer.freenow.data.remote

import com.dranoer.freenow.Constants
import com.dranoer.freenow.data.model.Response
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class NetworkDataSource @Inject constructor(
    private val webService: WebService
) {

    suspend fun getVehicles(): Resource<Response> {
        return Resource.Success(
            webService.getVehicles(
                Constants.LATITUDE_ONE,
                Constants.LONGITUDE_ONE,
                Constants.LATITUDE_TWO,
                Constants.LONGITUDE_TWO
            )
        )
    }
}