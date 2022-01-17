package com.dranoer.freenow.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dranoer.freenow.data.model.VehicleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface VehicleDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertVehicle(vehicle: VehicleEntity): Long

    @Query("SELECT * FROM vehicle_list_table")
    fun getVehicles(): Flow<List<VehicleEntity>>
}