package com.dranoer.freenow.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dranoer.freenow.R

// ToDo 02 : to use a location object instead of separate lat and lng
@Entity(tableName = "vehicle_list_table")
data class VehicleEntity(
    @PrimaryKey
    val id: Long,
    val fleetType: FleetType,
    val latitude: Double,
    val longitude: Double,
)

enum class FleetType(val title: String, val icon: Int) {
    Pooling("Pooling", R.drawable.ic_pooling),
    Taxi("Taxi", R.drawable.ic_taxi),
    Unknown("Unknown", R.drawable.ic_unknown);

    companion object {
        fun mapFromName(name: String): FleetType {
            return when (name) {
                VehicleModel.POOLING -> Pooling
                VehicleModel.TAXI -> Taxi
                else -> Unknown
            }
        }
    }
}