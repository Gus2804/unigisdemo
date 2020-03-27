package com.example.uniparking.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.uniparking.data.db.entity.Vehicle
import com.example.uniparking.data.db.entity.VehicleType
import com.example.uniparking.data.repository.VehicleRepository
import javax.inject.Inject

class OfficialsViewModel
@Inject
constructor(val vehicleRepository: VehicleRepository) : ViewModel() {

    fun saveOfficial(license: String) {
        val vehicle = Vehicle(license, type = VehicleType.OFFICIAL)
        vehicleRepository.saveVehicle(vehicle)
    }

    val vehicles = vehicleRepository.getVehiclesByType(VehicleType.OFFICIAL)

}