package com.nevesrafael.anacosmeticos.utilidades

import java.text.NumberFormat
import java.util.*

fun formataParaMoedaBrasileira(valor: Double): String {
    val formatador = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
    return formatador.format(valor)
}