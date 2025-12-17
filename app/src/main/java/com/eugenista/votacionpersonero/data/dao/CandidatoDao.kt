package com.eugenista.votacionpersonero.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.eugenista.votacionpersonero.data.entities.Candidato
import kotlinx.coroutines.flow.Flow // Necesario para obtener datos de forma reactiva

// @Dao le indica a Room que esta interfaz contiene los métodos de acceso a la base de datos.
@Dao
interface CandidatoDao {

    // 1. OBTENER TODOS LOS CANDIDATOS
    // @Query permite escribir código SQL personalizado.
    // Flow<List<Candidato>>: Obtiene la lista y se actualiza automáticamente
    // cuando la base de datos cambia (muy útil para UI).
    @Query("SELECT * FROM candidatos ORDER BY numero ASC")
    fun getAllCandidatos(): Flow<List<Candidato>>

    // 2. INSERTAR CANDIDATO
    // @Insert es una anotación simple para insertar un objeto.
    // suspend hace que la función se ejecute de manera segura en un Coroutine (hilo secundario).
    @Insert
    suspend fun insertCandidato(candidato: Candidato)

    // 3. ACTUALIZAR VOTOS O DATOS
    // @Update usa la clave primaria del objeto Candidato para saber qué fila actualizar.
    @Update
    suspend fun updateCandidato(candidato: Candidato)

    // 4. ELIMINAR TODOS (Función de conveniencia)
    @Query("DELETE FROM candidatos")
    suspend fun deleteAllCandidatos()
}