package com.nevesrafael.anacosmeticos.telas.cadastro_produto

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import com.nevesrafael.anacosmeticos.dao.FabricanteDao
import com.nevesrafael.anacosmeticos.databinding.AdicionaNoSeletorBinding
import com.nevesrafael.anacosmeticos.model.Fabricante

class AdicionaFabricanteDialog(private val context: Context) {

    private val fabricanteDao =
        FabricanteDao()

    fun mostraDialogFabricante(quandoClicaNoSalvarFabricante: () -> Unit){

        val binding = AdicionaNoSeletorBinding.inflate(LayoutInflater.from(context))

        AlertDialog.Builder(context)
            .setView(binding.root)
            .setPositiveButton("Salvar") { _, _ ->
                val empresa = binding.addSeletorTexto.text.toString()
                fabricanteDao.adiciona(Fabricante(empresa))
                quandoClicaNoSalvarFabricante()
            }
            .setNegativeButton("Cancela") { _, _ ->

            }
            .show()

    }
}