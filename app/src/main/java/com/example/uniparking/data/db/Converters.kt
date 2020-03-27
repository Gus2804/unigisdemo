package com.example.uniparking.data.db

import androidx.room.TypeConverter
import com.example.uniparking.data.db.entity.VehicleType
import java.util.*

class Converters {

    @TypeConverter
    fun vehicleTypeToInt(vehicleType: VehicleType) = vehicleType.id

    @TypeConverter
    fun intToVehicleType(id : Int) = when(id) {
        2 -> VehicleType.OFFICIAL
        1 -> VehicleType.RESIDENT
        else -> VehicleType.EXTERNAL
    }
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}