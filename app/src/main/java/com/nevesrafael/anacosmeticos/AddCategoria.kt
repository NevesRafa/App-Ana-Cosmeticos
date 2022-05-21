package com.nevesrafael.anacosmeticos

import android.R
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.nevesrafael.anacosmeticos.database.AppDatabase
import com.nevesrafael.anacosmeticos.database.CategoriaDao
import com.nevesrafael.anacosmeticos.databinding.ActivityAddCategoriaBinding

class AddCategoria : AppCompatActivity() {

    private lateinit var binding: ActivityAddCategoriaBinding
    private lateinit var categoriaDao: CategoriaDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddCategoriaBinding.inflate(layoutInflater)
        categoriaDao = AppDatabase.instancia(this).categoriaDao()
        setContentView(binding.root)
        configuraMenuCategoria()
        title = "Cadastra Categoria"
    }

    private fun configuraMenuCategoria() {
        val listaCategoria = categoriaDao.buscarTodos()
        val listaCategoriaComoString =
            listaCategoria.map { categoria -> categoria.descricao }  // transforma a lista em uma lista de string atreves do map
        val adapter =
            ArrayAdapter(this, R.layout.simple_list_item_1, listaCategoriaComoString)
        binding.categoria.setAdapter(adapter)
    }
}

