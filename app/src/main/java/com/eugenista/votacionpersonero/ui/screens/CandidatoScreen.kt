package com.eugenista.votacionpersonero.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.eugenista.votacionpersonero.data.entities.Candidato
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.eugenista.votacionpersonero.ui.viewmodel.CandidatoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CandidatoItem(
    candidato: Candidato,
    onVoteClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "Candidato #${candidato.numero}", style = MaterialTheme.typography.labelSmall)
                Text(text = candidato.nombre, style = MaterialTheme.typography.headlineSmall)
                Text(text = "Votos: ${candidato.votos}", style = MaterialTheme.typography.bodyMedium)
            }

            Button(onClick = onVoteClick) {
                Text("Votar")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaCandidatosScreen(
    viewModel: CandidatoViewModel = viewModel() // Hilt proveerá el ViewModel
) {
    // Observamos la lista de candidatos desde el ViewModel
    val listaCandidatos by viewModel.candidatos.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Elecciones Personero 2026") })
        }
    ) { paddingValues ->
        if (listaCandidatos.isEmpty()) {
            // Mensaje si no hay candidatos todavía
            Box(modifier = Modifier.fillMaxSize().padding(paddingValues), contentAlignment = Alignment.Center) {
                Text("No hay candidatos registrados.")
            }
        } else {
            LazyColumn(modifier = Modifier.padding(paddingValues)) {
                items(listaCandidatos) { candidato ->
                    CandidatoItem(
                        candidato = candidato,
                        onVoteClick = { viewModel.votarPorCandidato(candidato) }
                    )
                }
            }
        }
    }
}