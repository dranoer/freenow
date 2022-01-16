package com.dranoer.freenow.data.remote

import com.dranoer.freenow.data.model.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {

    @GET("/")
    suspend fun getVehicles(
        @Query("p1Lat") p1Lat: Double,
        @Query("p1Lon") p1Lon: Double,
        @Query("p2Lat") p2Lat: Double,
        @Query("p2Lon") p2Lon: Double,
    ): Response
}