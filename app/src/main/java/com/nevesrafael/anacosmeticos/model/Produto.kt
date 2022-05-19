package com.nevesrafael.anacosmeticos.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity  // Define uma entidade de dados *Produto*, cada instância de *Produto* representa uma linha em uma tabela *Produto* no banco de dados do App.
@Parcelize
data class Produto(
    @PrimaryKey (autoGenerate = true) val id: Int = 0, // Duvida??? (No curso o professor usou long, posso usar int???)
    val nome: String,
    val categoria: String,
    val quantidade: Int,
    val fabricante: String,
    val tipoUndMedida: Int, // esse cara vai ser um dos tipos [TipoUnidadeMedida]
    val undMedida: Int,
    val valorCompra: Double,
    val valorVendaRs: Double,
    val valorVendaY: Int,
    val imagem: String? = null
) : Parcelable

object TipoUnidadeMedida {
    const val ML = 0
    const val G = 1
    const val KG = 2
    const val L = 3
}