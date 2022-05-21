package com.nevesrafael.anacosmeticos.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.nevesrafael.anacosmeticos.model.Categoria

@Dao
interface CategoriaDao {

    @Query("SELECT * FROM Categoria")
    fun buscarTodos(): List<Categoria>

    @Insert()
    fun salvar(descricao: Categoria)

    @Delete()
    fun remove(descricao: Categoria)

}