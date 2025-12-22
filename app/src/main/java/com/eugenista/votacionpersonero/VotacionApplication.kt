package com.eugenista.votacionpersonero

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

// @HiltAndroidApp le dice a Hilt que genere todo el código
// necesario para la inyección de dependencias en tu aplicación.
@HiltAndroidApp
class VotacionApplication : Application() {
    // No necesitamos añadir código aquí por ahora, Hilt hace el trabajo pesado.
}