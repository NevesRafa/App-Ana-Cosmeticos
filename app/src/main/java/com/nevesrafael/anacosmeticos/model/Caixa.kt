package com.nevesrafael.anacosmeticos.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Caixa(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val envioId: Int,
    val comprimento: Double,
    val largura: Double,
    val altura: Double,
    val peso: Double
) : Parcelable
