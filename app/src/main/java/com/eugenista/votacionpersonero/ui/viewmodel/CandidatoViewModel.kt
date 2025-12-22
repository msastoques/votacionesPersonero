package com.eugenista.votacionpersonero.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eugenista.votacionpersonero.data.entities.Candidato
import com.eugenista.votacionpersonero.data.repository.CandidatoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CandidatoViewModel @Inject constructor(
    private val repository: CandidatoRepository
) : ViewModel() {

    // 1. Exponer la lista de candidatos como un StateFlow
    // Esto convierte el Flow del repositorio en un estado que Compose puede observar
    val candidatos: StateFlow<List<Candidato>> = repository.allCandidatos
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    // 2. Función para registrar un voto
    fun votarPorCandidato(candidato: Candidato) {
        viewModelScope.launch {
            // Creamos una copia del candidato con un voto más
            val candidatoActualizado = candidato.copy(votos = candidato.votos + 1)
            repository.update(candidatoActualizado)
        }
    }

    // 3. Función para agregar un nuevo candidato (para la configuración inicial)
    fun agregarCandidato(nombre: String, numero: Int, fotoUrl: String = "") {
        viewModelScope.launch {
            val nuevoCandidato = Candidato(
                nombre = nombre,
                numero = numero,
                fotoPath = fotoUrl
            )
            repository.insert(nuevoCandidato)
        }
    }

    // 4. Función para reiniciar la votación
    fun reiniciarVotacion() {
        viewModelScope.launch {
            repository.clearAll()
        }
    }
}