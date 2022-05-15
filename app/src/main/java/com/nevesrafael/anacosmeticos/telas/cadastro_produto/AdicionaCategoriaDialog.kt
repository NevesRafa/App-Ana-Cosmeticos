package com.nevesrafael.anacosmeticos.telas.cadastro_produto

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import com.nevesrafael.anacosmeticos.dao.CategoriaDao
import com.nevesrafael.anacosmeticos.databinding.AdicionaNoSeletorBinding
import com.nevesrafael.anacosmeticos.model.Categoria

class AdicionaCategoriaDialog(private val context: Context) {

    private val categoriaDao =
        CategoriaDao()

    fun mostraDialogCategoria(quandoClicaNoConfirmaCategoria: () -> Unit) {

        val binding = AdicionaNoSeletorBinding.inflate(LayoutInflater.from(context))

        //configura dialog
        AlertDialog.Builder(context)
            .setView(binding.root)
            .setPositiveButton("Salvar") { _, _ ->
                val descricao = binding.addSeletorTexto.text.toString()
                categoriaDao.adiciona(Categoria(descricao))
                quandoClicaNoConfirmaCategoria()
            }
            .setNegativeButton("Cancela") { _, _ ->

            }
            .show()
    }
}