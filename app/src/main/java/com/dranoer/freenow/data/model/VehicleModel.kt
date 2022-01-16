package com.dranoer.freenow.data.model

import com.google.gson.annotations.SerializedName

data class Response(

    @field:SerializedName("poiList")
    val response: List<VehicleModel>,
)

data class VehicleModel(

    @field:SerializedName("id")
    val id: Long,

    @field:SerializedName("coordinate")
    val coordinate: Coordinate,

    @field:SerializedName("fleetType")
    val fleetType: String,

    @field:SerializedName("heading")
    val heading: Double,
)

data class Coordinate(

    @field:SerializedName("latitude")
    val latitude: Double,

    @field:SerializedName("longitude")
    val longitude: Double,
)