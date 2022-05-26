package com.nevesrafael.anacosmeticos.database

import androidx.room.*
import com.nevesrafael.anacosmeticos.model.Envio

@Dao
interface EnvioDao {

    @Query("SELECT * FROM Envio")
    fun bucaTodos(): List<Envio>

    @Insert()
    fun salvar(envio: Envio)

    @Delete
    fun remove(envio: Envio)

    @Update
    fun altera(envio: Envio)
}