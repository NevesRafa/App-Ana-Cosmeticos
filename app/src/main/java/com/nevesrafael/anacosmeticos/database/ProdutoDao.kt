package com.nevesrafael.anacosmeticos.database

import androidx.room.Dao
import androidx.room.Query
import com.nevesrafael.anacosmeticos.model.Produto

// O código abaixo define o *ProdutoDao* (DAO), fornece os metodos que o restante do app usa para interagir com os dados na tabela *Produto*

@Dao
interface ProdutoDao {

    @Query("SELECT * FROM Produto")
    fun buscarTodos(): List<Produto>
}