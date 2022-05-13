package com.nevesrafael.anacosmeticos.utilidades

import java.text.NumberFormat
import java.util.*

fun formataParaMoedaBrasileira(valor: Double): String {
    val formatador = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
    return formatador.format(valor)
}

fun Int.formataParaMoedaJaponesa():String{
    val formatador = NumberFormat.getCurrencyInstance(Locale("jp", "jp"))
    return formatador.format(this)
}

//fun Double.formataParaReal(): String {
//    val formatador = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
//    return formatador.format(this)
//}
//
//
//fun teste(){
//
//    val dinheiro = 200.0
//
//    val dinheiroEmReal = formataParaMoedaBrasileira(dinheiro)
//
//    val dinheiroEmReal2 = dinheiro.formataParaReal()
//}