package com.example.uniparking.data.db.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.uniparking.data.db.entity.Stay
import com.example.uniparking.data.db.entity.Vehicle

data class VehicleWithStays(
    @Embedded val vehicle: Vehicle,
    @Relation(
        parentColumn = "licensePlate",
        entityColumn = "vehicleLicensePlate"
    )
    val stays: List<Stay>
)