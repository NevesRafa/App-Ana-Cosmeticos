package com.nevesrafael.anacosmeticos.telas.menu_cadastra

import android.R
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.nevesrafael.anacosmeticos.database.AppDatabase
import com.nevesrafael.anacosmeticos.database.CategoriaDao
import com.nevesrafael.anacosmeticos.databinding.ActivityAddCategoriaBinding
import com.nevesrafael.anacosmeticos.model.Categoria

class AddCategoria : AppCompatActivity() {

    private lateinit var binding: ActivityAddCategoriaBinding
    private lateinit var categoriaDao: CategoriaDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddCategoriaBinding.inflate(layoutInflater)
        categoriaDao = AppDatabase.instancia(this).categoriaDao()
        setContentView(binding.root)
        configuraMenuCategoria()
        configuraBotaoSalvar()
        configuraBotaoExcluir()
        title = "Cadastra Categoria"
    }

    private fun configuraMenuCategoria() {
        val listaCategoria = categoriaDao.buscarTodos()
        val listaCategoriaComoString =
            listaCategoria.map { categoria -> categoria.descricao }  // transforma a lista em uma lista de string atreves do map
        val adapter =
            ArrayAdapter(this, R.layout.simple_list_item_1, listaCategoriaComoString)
        binding.addCategoriaTexto.setAdapter(adapter)
    }

    private fun configuraBotaoSalvar() {
        binding.addCategoriaBotaoSalvar.setOnClickListener {
            val descricao = binding.addCategoriaTexto.text.toString()
            categoriaDao.salvar(Categoria(descricao = descricao))
            Toast.makeText(this, "Nova categoria salva com sucesso", Toast.LENGTH_SHORT).show()
        }
    }

    private fun configuraBotaoExcluir() {
        binding.addCategoriaBotaoExcluir.setOnClickListener {
            val descricao = binding.addCategoriaTexto.text.toString()
            categoriaDao.remove(Categoria(descricao = descricao))
            Toast.makeText(this, "Categoria removida com sucesso", Toast.LENGTH_SHORT).show()
            finish()

        }
    }
}

