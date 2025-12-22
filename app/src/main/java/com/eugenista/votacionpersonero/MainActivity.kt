package com.eugenista.votacionpersonero

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.eugenista.votacionpersonero.ui.screens.ListaCandidatosScreen
import com.eugenista.votacionpersonero.ui.theme.VotacionPersoneroTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint // 👈 OBLIGATORIO para que Hilt funcione aquí
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VotacionPersoneroTheme {
                ListaCandidatosScreen()
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    VotacionPersoneroTheme {
        Greeting("Android")
    }
}