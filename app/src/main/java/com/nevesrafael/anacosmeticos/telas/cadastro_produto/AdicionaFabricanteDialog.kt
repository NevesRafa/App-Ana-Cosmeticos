package com.nevesrafael.anacosmeticos.telas.cadastro_produto

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import com.nevesrafael.anacosmeticos.database.AppDatabase
import com.nevesrafael.anacosmeticos.databinding.AdicionaNoSeletorBinding
import com.nevesrafael.anacosmeticos.model.Fabricante

class AdicionaFabricanteDialog(private val context: Context) {

    private val fabricanteDao = AppDatabase.instancia(context).fabricanteDao()

    fun mostraDialogFabricante(quandoClicaNoSalvarFabricante: () -> Unit) {

        val binding = AdicionaNoSeletorBinding.inflate(LayoutInflater.from(context))

        binding.addSeletorTexto.hint = "Fabricante"

        AlertDialog.Builder(context)
            .setView(binding.root)
            .setPositiveButton("Salvar") { _, _ ->
                val empresa = binding.addSeletorTexto.text.toString()
                fabricanteDao.salvar(Fabricante(empresa = empresa))
                quandoClicaNoSalvarFabricante()
            }
            .setNegativeButton("Cancela") { _, _ ->

            }
            .show()

    }
}