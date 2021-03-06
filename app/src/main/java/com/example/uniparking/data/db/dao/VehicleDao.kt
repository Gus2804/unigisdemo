package com.example.uniparking.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.uniparking.data.db.entity.Vehicle
import com.example.uniparking.data.db.entity.VehicleType
import com.example.uniparking.data.db.relations.VehicleWithStays

@Dao
interface VehicleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveVehicle(vehicle: Vehicle)

    @Update
    fun updateVehicle(vehicle: Vehicle)

    @Transaction
    @Query("SELECT * FROM vehicles WHERE type = :type")
    fun getVehiclesByType(type : VehicleType) : LiveData<List<VehicleWithStays>>

    @Query("SELECT * FROM vehicles WHERE licensePlate = :license")
    fun getVehiclesByLicense(license : String) : List<Vehicle>

}