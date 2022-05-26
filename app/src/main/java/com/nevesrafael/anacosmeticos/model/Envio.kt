package com.nevesrafael.anacosmeticos.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Envio(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val transportadora: String,
    val dataDoEnvio: String,
    val valorEnvio: Int
)