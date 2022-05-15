package com.nevesrafael.anacosmeticos.dao


import com.nevesrafael.anacosmeticos.model.Fabricante

class FabricanteDao {

    fun adiciona(empresa: Fabricante) {
        listaDeEmpresas.add(empresa)
    }

    fun buscaTodos() = listaDeEmpresas.toList()


    companion object {
        private val listaDeEmpresas = mutableListOf<Fabricante>()
    }
}