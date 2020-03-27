package com.example.uniparking.data.repository

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.uniparking.data.db.dao.StayDao
import com.example.uniparking.data.db.entity.Stay
import javax.inject.Inject

class StayRepository
@Inject
constructor(val stayDao: StayDao){

    fun getStays() : LiveData<List<Stay>> {
        return stayDao.getActualStays()
    }

    fun saveStay(stay: Stay) {
        AsyncTask.execute {
            stayDao.saveStay(stay)
        }
    }

    fun updateStay(stay: Stay) {
        AsyncTask.execute {
            stayDao.updateStay(stay)
        }
    }

}