package com.example.uniparking.di.modules

import com.example.uniparking.ui.activity.ParkedVehiclesActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeParkedVehiclesActivity() : ParkedVehiclesActivity

}