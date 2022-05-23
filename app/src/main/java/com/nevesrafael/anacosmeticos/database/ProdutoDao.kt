package com.nevesrafael.anacosmeticos.database

import androidx.room.*
import com.nevesrafael.anacosmeticos.model.Produto

// O código abaixo define o *ProdutoDao* (DAO), fornece os metodos que o restante do app usa para interagir com os dados na tabela *Produto*

@Dao
interface ProdutoDao {

    @Query("SELECT * FROM Produto")
    fun buscarTodos(): List<Produto>

    @Insert()
    fun salvar(produto: Produto)

    @Query("SELECT * FROM Produto WHERE id = :id")
    fun buscaPorId(id: Int): Produto?

    @Update
    fun altera(produto: Produto)

    @Delete
    fun remove(produto: Produto)

//    @Query("SELECT * FROM Produto WHERE quantidade > 0")
//    fun produtosComEstoque(): List<Produto>
}