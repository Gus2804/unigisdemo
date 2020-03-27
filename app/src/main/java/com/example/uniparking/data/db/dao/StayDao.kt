package com.example.uniparking.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.example.uniparking.data.db.entity.Stay

@Dao
interface StayDao {

    @Insert(onConflict = REPLACE)
    fun saveStay(stay: Stay)

    @Update
    fun updateStay(stay: Stay)

    @Query("SELECT * FROM stays WHERE checkInTime = checkOutTime")
    fun getActualStays() : LiveData<List<Stay>>

}