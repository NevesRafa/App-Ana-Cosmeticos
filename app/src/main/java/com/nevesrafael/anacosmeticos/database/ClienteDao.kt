package com.nevesrafael.anacosmeticos.database

import androidx.room.*
import com.nevesrafael.anacosmeticos.model.Cliente

@Dao
interface ClienteDao {

    @Query("SELECT * FROM Cliente")
    fun buscarTodos(): List<Cliente>

    @Insert
    fun salvar(cliente: Cliente)

    @Delete
    fun remove(cliente: Cliente)

    @Update
    fun altera(cliente: Cliente)

    @Query("SELECT * FROM Cliente WHERE id = :id")
    fun buscaPorId(id: Int): Cliente?


}