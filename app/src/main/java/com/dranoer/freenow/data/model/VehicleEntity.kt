package com.dranoer.freenow.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vehicle_list_table")
data class VehicleEntity(

    @PrimaryKey
    val id: Long,
    val fleetType: String,
    val latitude: Double,
    val longitude: Double,
)