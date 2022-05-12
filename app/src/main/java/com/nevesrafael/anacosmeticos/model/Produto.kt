package com.nevesrafael.anacosmeticos.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Produto(
    val nome: String,
    val categoria: String,
    val quantidade: Int,
    val fabricante:String,
    val undMedida: Int,
    val valorCompra: Double,
    val valorVendaRs: Double,
    val valorVendaY: Int,
    val imagem: String? = null
):Parcelable