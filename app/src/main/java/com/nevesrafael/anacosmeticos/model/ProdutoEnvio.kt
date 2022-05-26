package com.nevesrafael.anacosmeticos.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class ProdutoEnvio(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val envioId: Int,
    val produtoId: Int,
    val quantidade: Int
)
