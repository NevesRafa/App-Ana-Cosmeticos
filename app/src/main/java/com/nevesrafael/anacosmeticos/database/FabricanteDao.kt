package com.nevesrafael.anacosmeticos.database

import androidx.room.*
import com.nevesrafael.anacosmeticos.model.Fabricante

@Dao
interface FabricanteDao {

    @Query("SELECT * FROM Fabricante")
    fun buscarTodos(): List<Fabricante>

    @Insert()
    fun salvar(empresa: Fabricante)

    @Delete()
    fun remove(empresa: Fabricante)

    @Update()
    fun altera(empresa: Fabricante)
}