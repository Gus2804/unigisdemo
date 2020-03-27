package com.example.uniparking.ui.viewmodel

import androidx.lifecycle.*
import androidx.lifecycle.Observer
import com.example.uniparking.data.db.entity.Stay
import com.example.uniparking.data.db.entity.Vehicle
import com.example.uniparking.data.db.entity.VehicleType
import com.example.uniparking.data.repository.StayRepository
import com.example.uniparking.data.repository.VehicleRepository
import java.util.*
import javax.inject.Inject

class ParkedVehicleViewModel
@Inject
constructor(val stayRepository: StayRepository, val vehicleRepository: VehicleRepository) : ViewModel() {

    val stays = stayRepository.getStays()
    private val vehicles : LiveData<List<Vehicle>> = MutableLiveData<List<Vehicle>>()
    private val vehicleSaved = MutableLiveData<String>()
    val amount = MediatorLiveData<String>()

    private var vehicleLicense = ""

    val vehicleObserver = Observer<List<Vehicle>> {
        if(it.isEmpty()) {
            vehicleRepository.saveVehicle(Vehicle(vehicleLicense), vehicleSaved)
        } else {
            val vehicle = it[0]
            val date = Date()
            stayRepository.saveStay(
                Stay(vehicleLicensePlate = vehicle.licensePlate, checkInTime = date, checkOutTime = date)
            )
        }
    }
    val vehicleSavedObserver = Observer<String> {
        val date = Date()
        stayRepository.saveStay(
            Stay(vehicleLicensePlate = it, checkInTime = date, checkOutTime = date)
        )
    }

    fun prepareObservers(owner: LifecycleOwner) {
        vehicles.observe(owner, vehicleObserver)
        vehicleSaved.observe(owner, vehicleSavedObserver)
    }

    fun saveStay(vehicleLicense : String) {
        this.vehicleLicense = vehicleLicense
        if(stays.value?.any { stay -> stay.vehicleLicensePlate == vehicleLicense } == false) {
            vehicleRepository.getVehicle(vehicleLicense, vehicles)
        }
    }

    fun setCheckoutTime(stay: Stay) {
        stay.checkOutTime = Date()
        stayRepository.updateStay(stay)
        val vehicles : LiveData<List<Vehicle>> = MutableLiveData<List<Vehicle>>()
        vehicleRepository.getVehicle(stay.vehicleLicensePlate, vehicles)
        amount.addSource(vehicles) {
            if(it.isNotEmpty()) {
                val vehicle = it[0]
                if(vehicle.type == VehicleType.EXTERNAL) {
                    amount.value = "Por cobrar $%.2f".format(0.5 * stay.getStayTime())
                }
            }
        }
    }

}