package com.example.uniparking.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "stays")
data class Stay (
    @PrimaryKey(autoGenerate = true) val stayId : Int = 0,
    val vehicleLicensePlate: String,
    val checkInTime: Date,
    var checkOutTime: Date
) {

    fun getStayTime() : Long {
        return (checkOutTime.time - checkInTime.time)/(1000 * 60)
    }

}