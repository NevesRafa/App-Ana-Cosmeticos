package com.nevesrafael.anacosmeticos.dao

import com.nevesrafael.anacosmeticos.model.Produto

class ProdutoDao {

    fun adiciona(produto: Produto) {
        produtos.add(produto)
    }

    fun buscaTodos() = produtos.toList()

    // utilizou o companion object para que salva-se o item na tela
    companion object {
        private val produtos = mutableListOf<Produto>()
    }
}