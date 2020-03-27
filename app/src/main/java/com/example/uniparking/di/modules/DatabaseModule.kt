package com.example.uniparking.di.modules

import android.app.Application
import androidx.room.Room
import com.example.uniparking.data.db.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application) : AppDatabase {
        return Room.databaseBuilder(
            application.applicationContext,
            AppDatabase::class.java, "uniparking-db"
        ).build()
    }

}