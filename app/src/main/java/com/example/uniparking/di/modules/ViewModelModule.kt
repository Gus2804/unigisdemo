package com.example.uniparking.di.modules

import androidx.lifecycle.ViewModel
import com.example.uniparking.di.anotations.ViewModelKey
import com.example.uniparking.ui.viewmodel.ParkedVehicleViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ParkedVehicleViewModel::class)
    abstract fun bindParkedVehicleViewModel(viewModel: ParkedVehicleViewModel) : ViewModel

}