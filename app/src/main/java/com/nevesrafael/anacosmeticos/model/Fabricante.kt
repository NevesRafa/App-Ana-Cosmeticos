package com.nevesrafael.anacosmeticos.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Fabricante(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var empresa: String
)