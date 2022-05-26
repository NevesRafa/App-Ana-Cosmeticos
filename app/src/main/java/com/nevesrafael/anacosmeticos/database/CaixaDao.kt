package com.nevesrafael.anacosmeticos.database

import androidx.room.*
import com.nevesrafael.anacosmeticos.model.Caixa

@Dao
interface CaixaDao {

    @Query("SELECT * FROM Caixa")
    fun bucaTodos(): List<Caixa>

    @Insert()
    fun salvar(caixa: Caixa)

    @Delete
    fun remove(caixa: Caixa)

    @Update
    fun altera(caixa: Caixa)
}