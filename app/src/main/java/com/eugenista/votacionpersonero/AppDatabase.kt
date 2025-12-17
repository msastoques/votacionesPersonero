package com.eugenista.votacionpersonero

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.eugenista.votacionpersonero.data.dao.CandidatoDao
import com.eugenista.votacionpersonero.data.entities.Candidato

// 1. @Database: Anotación obligatoria para definir la clase como una DB de Room.
@Database(
    // Lista de entidades que contiene la DB (solo tenemos Candidato por ahora)
    entities = [Candidato::class],
    // Versión de la DB. Debe incrementarse si cambian las entidades.
    version = 1,
    // exportSchema = false es recomendado para proyectos pequeños.
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    // El DAO que creamos. Room lo implementará.
    abstract fun candidatoDao(): CandidatoDao

    // 2. Patrón Singleton (para tener solo una instancia de la DB)
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            // Si la instancia ya existe, la devuelve.
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "votacion_db" // Nombre del archivo de la base de datos
                )
                    // Esto es destructivo y solo para desarrollo/pruebas:
                    // .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}