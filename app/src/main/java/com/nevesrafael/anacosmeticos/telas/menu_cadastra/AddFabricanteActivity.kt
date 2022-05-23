package com.nevesrafael.anacosmeticos.telas.menu_cadastra

import android.R
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.nevesrafael.anacosmeticos.database.AppDatabase
import com.nevesrafael.anacosmeticos.database.FabricanteDao
import com.nevesrafael.anacosmeticos.databinding.ActivityAddFabricanteBinding
import com.nevesrafael.anacosmeticos.model.Fabricante

class AddFabricanteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddFabricanteBinding
    private lateinit var fabricanteDao: FabricanteDao
    private var itemSelecionado: Fabricante? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddFabricanteBinding.inflate(layoutInflater)
        fabricanteDao = AppDatabase.instancia(this).fabricanteDao()
        setContentView(binding.root)
        configuraMenuFabricante()
        configuraBotaoSalvar()
        configuraBotaoEditar()
        configuraBotaoExcluir()
    }

    private fun configuraMenuFabricante() {
        val listaFabricante = fabricanteDao.buscarTodos()
        val listaFabricanteComoString =
            listaFabricante.map { fabricante -> fabricante.empresa }
        val adapter =
            ArrayAdapter(this, R.layout.simple_list_item_1, listaFabricanteComoString)
        binding.addFabricanteTexto.setAdapter(adapter)

        binding.addFabricanteTexto.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                itemSelecionado = listaFabricante.getOrNull(position)
            }
    }

    private fun configuraBotaoSalvar() {
        binding.addFabricanteBotaoSalvar.setOnClickListener {
            val textoDigitado = binding.addFabricanteTexto.text.toString()
            if (textoDigitado.isEmpty()) {
                return@setOnClickListener
            }
            fabricanteDao.salvar(Fabricante(empresa = textoDigitado))
            Toast.makeText(this, "Nova categoria salva com sucesso", Toast.LENGTH_SHORT).show()
            configuraMenuFabricante()
            binding.addFabricanteTexto.text = null
            itemSelecionado = null
        }
    }

    private fun configuraBotaoEditar() {
        binding.addFabricanteBotaoEditar.setOnClickListener {
            val textoDigitado = binding.addFabricanteTexto.text.toString()

            if (textoDigitado.isNotEmpty() && itemSelecionado != null) {
                itemSelecionado?.empresa = textoDigitado

                fabricanteDao.altera(itemSelecionado!!)
                Toast.makeText(this, "Categoria editada com sucesso", Toast.LENGTH_SHORT).show()
                configuraMenuFabricante()
                binding.addFabricanteTexto.text = null
                itemSelecionado = null
            }
        }
    }

    private fun configuraBotaoExcluir() {

        binding.addFabricanteBotaoExcluir.setOnClickListener {
            itemSelecionado?.let {
                fabricanteDao.remove(it)
                Toast.makeText(this, "Categoria removida com sucesso", Toast.LENGTH_SHORT).show()
                configuraMenuFabricante()
                binding.addFabricanteTexto.text = null
                itemSelecionado = null
            }
        }

    }
}