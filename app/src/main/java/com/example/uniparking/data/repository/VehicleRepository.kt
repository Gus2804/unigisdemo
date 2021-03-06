package com.example.uniparking.data.repository

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.uniparking.data.db.dao.VehicleDao
import com.example.uniparking.data.db.entity.Vehicle
import com.example.uniparking.data.db.entity.VehicleType
import com.example.uniparking.data.db.relations.VehicleWithStays
import javax.inject.Inject

class VehicleRepository
@Inject
constructor(val vehicleDao : VehicleDao){

    fun getVehicle(license: String, result : LiveData<List<Vehicle>>) {
        if(result is MutableLiveData) {
            AsyncTask.execute {
                result.postValue(vehicleDao.getVehiclesByLicense(license))
            }
        }
    }

    fun saveVehicle(vehicle: Vehicle, saved : MutableLiveData<String>? = null) {
        AsyncTask.execute {
            vehicleDao.saveVehicle(vehicle)
            saved?.postValue(vehicle.licensePlate)
        }
    }

    fun updateVehicle(vehicle: Vehicle) {
        AsyncTask.execute {
            vehicleDao.updateVehicle(vehicle)
        }
    }

    fun getVehiclesByType(vehicleType: VehicleType) : LiveData<List<VehicleWithStays>> {
        return vehicleDao.getVehiclesByType(vehicleType)
    }

}