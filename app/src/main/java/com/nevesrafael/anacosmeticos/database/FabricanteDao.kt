package com.nevesrafael.anacosmeticos.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.nevesrafael.anacosmeticos.model.Fabricante

@Dao
interface FabricanteDao {

    @Query("SELECT * FROM Fabricante")
    fun buscarTodos(): List<Fabricante>

    @Insert()
    fun salvar(empresa: Fabricante)
}