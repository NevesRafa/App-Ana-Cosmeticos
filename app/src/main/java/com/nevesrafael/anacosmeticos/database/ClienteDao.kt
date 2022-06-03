package com.nevesrafael.anacosmeticos.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.nevesrafael.anacosmeticos.model.Cliente

@Dao
interface ClienteDao {

    @Query("SELECT * FROM Cliente")
    fun buscarTodos(): List<Cliente>

    @Insert
    fun salvar(cliente: Cliente)


}