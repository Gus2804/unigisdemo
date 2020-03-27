package com.example.uniparking.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.uniparking.data.db.entity.Vehicle
import com.example.uniparking.data.db.entity.VehicleType

@Dao
interface VehicleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveVehicle(vehicle: Vehicle)

    @Query("SELECT * FROM vehicles WHERE type = :type")
    fun getVehiclesByType(type : VehicleType) : LiveData<List<Vehicle>>

    @Query("SELECT * FROM vehicles WHERE licensePlate = :license")
    fun getVehiclesByLicense(license : String) : List<Vehicle>

}