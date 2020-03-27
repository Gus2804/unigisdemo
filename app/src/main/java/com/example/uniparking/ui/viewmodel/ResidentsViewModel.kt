package com.example.uniparking.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.uniparking.data.db.entity.VehicleType
import com.example.uniparking.data.repository.VehicleRepository
import javax.inject.Inject

class ResidentsViewModel
@Inject
constructor(val vehicleRepository: VehicleRepository) : ViewModel() {

    val vehicles = vehicleRepository.getVehiclesByType(VehicleType.RESIDENT)

}