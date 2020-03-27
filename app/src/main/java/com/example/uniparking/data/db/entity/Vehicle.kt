package com.example.uniparking.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vehicles")
data class Vehicle (
    @PrimaryKey var licensePlate : String,
    var type : VehicleType = VehicleType.EXTERNAL,
    var phoneNumber : String? = null
)