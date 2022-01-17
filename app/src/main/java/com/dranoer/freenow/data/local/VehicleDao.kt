package com.dranoer.freenow.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.dranoer.freenow.data.model.VehicleEntity

@Dao
interface VehicleDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertVehicle(vehicle: VehicleEntity): Long
}