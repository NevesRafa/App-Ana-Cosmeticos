package com.nevesrafael.anacosmeticos.telas.menu_cadastra

import android.R
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.nevesrafael.anacosmeticos.database.AppDatabase
import com.nevesrafael.anacosmeticos.database.CategoriaDao
import com.nevesrafael.anacosmeticos.databinding.ActivityAddCategoriaBinding
import com.nevesrafael.anacosmeticos.model.Categoria

class AddCategoriaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddCategoriaBinding
    private lateinit var categoriaDao: CategoriaDao
    private var itemSelecionado: Categoria? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddCategoriaBinding.inflate(layoutInflater)
        categoriaDao = AppDatabase.instancia(this).categoriaDao()
        setContentView(binding.root)
        configuraMenuCategoria()
        configuraBotaoSalvar()
        configuraBotaoExcluir()
        configuraBotaoEditar()
    }

    private fun configuraMenuCategoria() {
        val listaCategoria = categoriaDao.buscarTodos()
        val listaCategoriaComoString =
            listaCategoria.map { categoria -> categoria.descricao }  // transforma a lista em uma lista de string atreves do map
        val adapter =
            ArrayAdapter(this, R.layout.simple_list_item_1, listaCategoriaComoString)
        binding.addCategoriaTexto.setAdapter(adapter)

        /**
         *  esse [AdapterView.OnItemClickListener] é chamado quando um item é selecionado naquele componente,
         *  tem esses 4 parametros aí mas a gente vai usar só o [position] que é a posição na lista.
         */
        binding.addCategoriaTexto.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                itemSelecionado = listaCategoria.getOrNull(position)
            }
    }

    private fun configuraBotaoSalvar() {
        binding.addCategoriaBotaoSalvar.setOnClickListener {
            val textoDigitado = binding.addCategoriaTexto.text.toString()
            if (textoDigitado.isEmpty()) {
                return@setOnClickListener
            }
            categoriaDao.salvar(Categoria(descricao = textoDigitado))
            Toast.makeText(this, "Nova categoria salva com sucesso", Toast.LENGTH_SHORT).show()
            configuraMenuCategoria()
            binding.addCategoriaTexto.text = null
            itemSelecionado = null
        }
    }

    private fun configuraBotaoExcluir() {
        binding.addCategoriaBotaoExcluir.setOnClickListener {
            itemSelecionado?.let {
                categoriaDao.remove(it)
                Toast.makeText(this, "Categoria removida com sucesso", Toast.LENGTH_SHORT).show()
                configuraMenuCategoria()
                binding.addCategoriaTexto.text = null
                itemSelecionado = null
            }
        }
    }

    private fun configuraBotaoEditar() {
        binding.addCategoriaBotaoEditar.setOnClickListener {

            val textoDigitado = binding.addCategoriaTexto.text.toString()

            if (textoDigitado.isNotEmpty() && itemSelecionado != null) {
                itemSelecionado?.descricao = textoDigitado

                categoriaDao.altera(itemSelecionado!!)
                Toast.makeText(this, "Categoria editada com sucesso", Toast.LENGTH_SHORT).show()
                configuraMenuCategoria()
                binding.addCategoriaTexto.text = null
                itemSelecionado = null
            }
        }
    }
}

