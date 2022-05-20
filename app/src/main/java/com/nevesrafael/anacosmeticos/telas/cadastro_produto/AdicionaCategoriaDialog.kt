package com.nevesrafael.anacosmeticos.telas.cadastro_produto

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import com.nevesrafael.anacosmeticos.database.AppDatabase
import com.nevesrafael.anacosmeticos.databinding.AdicionaNoSeletorBinding
import com.nevesrafael.anacosmeticos.model.Categoria

class AdicionaCategoriaDialog(private val context: Context) {

    private val categoriaDao = AppDatabase.instancia(context).categoriaDao()

    fun mostraDialogCategoria(quandoClicaNoSalvarCategoria: () -> Unit) {

        val binding = AdicionaNoSeletorBinding.inflate(LayoutInflater.from(context))

        binding.addSeletorTexto.hint = "Categoria"

        //configura dialog
        AlertDialog.Builder(context)
            .setView(binding.root)
            .setPositiveButton("Salvar") { _, _ ->
                val descricao = binding.addSeletorTexto.text.toString()
                categoriaDao.salvar(Categoria(descricao = descricao))
                quandoClicaNoSalvarCategoria()
            }
            .setNegativeButton("Cancela") { _, _ ->

            }
            .show()
    }
}