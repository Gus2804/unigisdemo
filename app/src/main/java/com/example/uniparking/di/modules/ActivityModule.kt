package com.example.uniparking.di.modules

import com.example.uniparking.ui.activity.OfficialsActivity
import com.example.uniparking.ui.activity.ParkedVehiclesActivity
import com.example.uniparking.ui.activity.ResidentsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeParkedVehiclesActivity() : ParkedVehiclesActivity

    @ContributesAndroidInjector
    abstract fun contributeResidentsActivity() : ResidentsActivity

    @ContributesAndroidInjector
    abstract fun contributeOfficialsActivity() : OfficialsActivity

}