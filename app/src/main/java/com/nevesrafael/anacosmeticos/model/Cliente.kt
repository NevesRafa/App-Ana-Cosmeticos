package com.nevesrafael.anacosmeticos.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Cliente(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nome: String,
    val rua: String,
    val estado: String,
    val cidade: String,
    val pais: String,
    val codPostal: String,
    val telefone: String,
    val aniversario: String
)