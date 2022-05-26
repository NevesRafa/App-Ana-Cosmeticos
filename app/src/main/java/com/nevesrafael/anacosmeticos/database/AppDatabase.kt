package com.nevesrafael.anacosmeticos.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nevesrafael.anacosmeticos.model.*

@Database(
    entities = [Produto::class, Categoria::class, Fabricante::class, Caixa::class, Envio::class, ProdutoEnvio::class],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun produtoDao(): ProdutoDao
    abstract fun categoriaDao(): CategoriaDao
    abstract fun fabricanteDao(): FabricanteDao
    abstract fun caixaDao(): CaixaDao
    abstract fun envioDao(): EnvioDao
    abstract fun produtoEnvio(): ProdutoEnvioDao


    companion object {

        fun instancia(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "anaCosmeticos.db"
            ).allowMainThreadQueries()
                .build()

        }
    }
}