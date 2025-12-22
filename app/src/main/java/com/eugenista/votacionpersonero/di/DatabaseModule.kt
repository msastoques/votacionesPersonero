package com.eugenista.votacionpersonero.di

import android.content.Context
import com.eugenista.votacionpersonero.AppDatabase
import com.eugenista.votacionpersonero.data.dao.CandidatoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) // Este módulo vive tanto como la aplicación
object DatabaseModule {

    // 1. Proveer la Base de Datos
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getDatabase(context)
    }

    // 2. Proveer el DAO
    @Provides
    fun provideCandidatoDao(database: AppDatabase): CandidatoDao {
        return database.candidatoDao()
    }
}