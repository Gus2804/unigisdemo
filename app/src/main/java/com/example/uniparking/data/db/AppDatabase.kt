package com.example.uniparking.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.uniparking.data.db.dao.StayDao
import com.example.uniparking.data.db.dao.VehicleDao
import com.example.uniparking.data.db.entity.Stay
import com.example.uniparking.data.db.entity.Vehicle

@Database(entities = [Stay::class, Vehicle::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun stayDao() : StayDao
    abstract fun vehicleDao() : VehicleDao
}