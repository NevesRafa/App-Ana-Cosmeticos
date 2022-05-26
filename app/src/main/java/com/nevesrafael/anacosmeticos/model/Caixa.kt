package com.nevesrafael.anacosmeticos.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Caixa(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val envioId: Int,
    val comprimento: Double,
    val largura: Double,
    val altura: Double,
    val peso: Double
)
