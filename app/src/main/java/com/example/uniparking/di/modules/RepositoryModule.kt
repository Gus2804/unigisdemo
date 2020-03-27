package com.example.uniparking.di.modules

import com.example.uniparking.data.db.AppDatabase
import com.example.uniparking.data.repository.StayRepository
import com.example.uniparking.data.repository.VehicleRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideStayRepository(database: AppDatabase) = StayRepository(database.stayDao())

    @Provides
    @Singleton
    fun provideVehicleRepository(database: AppDatabase) = VehicleRepository(database.vehicleDao())

}