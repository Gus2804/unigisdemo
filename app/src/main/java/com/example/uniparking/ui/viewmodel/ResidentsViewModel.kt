package com.example.uniparking.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.uniparking.data.db.entity.Vehicle
import com.example.uniparking.data.db.entity.VehicleType
import com.example.uniparking.data.repository.VehicleRepository
import javax.inject.Inject

class ResidentsViewModel
@Inject
constructor(val vehicleRepository: VehicleRepository) : ViewModel() {

    fun saveResident(license: String, phone: String) {
        val vehicle = Vehicle(license, type = VehicleType.RESIDENT, phoneNumber = phone)
        vehicleRepository.saveVehicle(vehicle)
    }

    val vehicles = vehicleRepository.getVehiclesByType(VehicleType.RESIDENT)

}