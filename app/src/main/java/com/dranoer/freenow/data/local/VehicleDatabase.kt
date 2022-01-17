package com.dranoer.freenow.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dranoer.freenow.data.model.VehicleEntity

@Database(entities = [VehicleEntity::class], version = 1, exportSchema = false)
abstract class VehicleDatabase : RoomDatabase() {

    abstract fun vehicleDao(): VehicleDao
}