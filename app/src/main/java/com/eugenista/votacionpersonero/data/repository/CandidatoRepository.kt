package com.eugenista.votacionpersonero.data.repository

import com.eugenista.votacionpersonero.data.dao.CandidatoDao
import com.eugenista.votacionpersonero.data.entities.Candidato
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

// @Singleton asegura que solo haya una instancia del repositorio en toda la app.
@Singleton
class CandidatoRepository @Inject constructor(
    private val candidatoDao: CandidatoDao
) {

    // Obtener el flujo de candidatos (se actualiza solo cuando hay cambios)
    val allCandidatos: Flow<List<Candidato>> = candidatoDao.getAllCandidatos()

    // Insertar un nuevo candidato
    suspend fun insert(candidato: Candidato) {
        candidatoDao.insertCandidato(candidato)
    }

    // Actualizar un candidato (por ejemplo, para sumar un voto)
    suspend fun update(candidato: Candidato) {
        candidatoDao.updateCandidato(candidato)
    }

    // Borrar todos los candidatos (útil para resetear la elección)
    suspend fun clearAll() {
        candidatoDao.deleteAllCandidatos()
    }
}