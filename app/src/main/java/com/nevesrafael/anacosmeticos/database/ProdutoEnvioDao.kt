package com.nevesrafael.anacosmeticos.database

import androidx.room.*
import com.nevesrafael.anacosmeticos.model.ProdutoEnvio

@Dao
interface ProdutoEnvioDao {

    @Query("SELECT * FROM ProdutoEnvio")
    fun bucaTodos(): List<ProdutoEnvio>

    @Insert()
    fun salvar(produtoEnvio: ProdutoEnvio)

    @Delete
    fun remove(produtoEnvio: ProdutoEnvio)

    @Update
    fun altera(produtoEnvio: ProdutoEnvio)
}