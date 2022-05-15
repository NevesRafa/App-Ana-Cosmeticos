package com.nevesrafael.anacosmeticos.dao

import com.nevesrafael.anacosmeticos.model.Categoria

class CategoriaDao {

    fun adiciona(categoria: Categoria) {
        listaDeCategorias.add(categoria)
    }

    fun buscaTodos() = listaDeCategorias.toList()

    // utilizou o companion object para que salva-se o item na tela
    companion object {
        private val listaDeCategorias = mutableListOf<Categoria>()
    }
}
