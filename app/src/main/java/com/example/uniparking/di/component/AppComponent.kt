package com.example.uniparking.di.component

import android.app.Application
import com.example.uniparking.di.anotations.PerActivity
import com.example.uniparking.di.modules.ActivityModule
import com.example.uniparking.di.modules.DatabaseModule
import com.example.uniparking.di.modules.RepositoryModule
import com.example.uniparking.di.modules.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@PerActivity
@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    DatabaseModule::class,
    RepositoryModule::class,
    ActivityModule::class,
    ViewModelModule::class
])
interface AppComponent : AndroidInjector<DaggerApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}