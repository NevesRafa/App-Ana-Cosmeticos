package com.nevesrafael.anacosmeticos.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nevesrafael.anacosmeticos.model.Produto

@Database(entities = [Produto::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun produtoDao() : ProdutoDao


// Duvida??? Está um pouco diferente da documentação, o que muda???
    companion object {
        fun instancia(context: Context): AppDatabase{
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "anaCosmeticos.db"
            ).allowMainThreadQueries()
                .build()

        }
    }
}