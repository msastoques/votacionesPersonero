package com.eugenista.votacionpersonero.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

// @Entity le dice a Room que esta clase representa una tabla en la DB.
// tableName especifica el nombre de la tabla.
@Entity(tableName = "candidatos")
data class Candidato(

    // @PrimaryKey indica que este campo es la clave primaria de la tabla.
    // autoGenerate = true hace que Room asigne un ID único e incremental automáticamente.
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    // Campos de datos del candidato
    val nombre: String,

    val numero: Int,

    // Almacena la ruta (Path o URI) de la imagen, no la imagen en sí.
    val fotoPath: String,

    // El contador de votos, con un valor inicial de 0.
    val votos: Int = 0
)