package com.nevesrafael.anacosmeticos.database

import androidx.room.*
import com.nevesrafael.anacosmeticos.model.Categoria

@Dao
interface CategoriaDao {

    @Query("SELECT * FROM Categoria")
    fun buscarTodos(): List<Categoria>

    @Insert()
    fun salvar(descricao: Categoria)

    @Delete()
    fun remove(descricao: Categoria)

    @Update()
    fun altera(descricao: Categoria)

}