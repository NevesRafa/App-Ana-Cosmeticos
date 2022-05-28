package com.nevesrafael.anacosmeticos

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import com.nevesrafael.anacosmeticos.databinding.FormularioCaixaBinding
import com.nevesrafael.anacosmeticos.model.Caixa

class FormularioCaixaDialog(private val context: Context) {

    fun mostra(quandoClicaNoSalvar: (Caixa) -> Unit) {

        val binding = FormularioCaixaBinding.inflate(LayoutInflater.from(context))




        AlertDialog.Builder(context)
            .setView(binding.root)
            .setPositiveButton("Salvar") { _, _ ->
                val caixa = Caixa(
                    id = 0,
                    envioId = 0,
                    comprimento = binding.listaEnvioComprimento.text.toString().toDouble(),
                    largura = binding.listaEnvioLargura.text.toString().toDouble(),
                    altura = binding.listaEnvioLargura.text.toString().toDouble(),
                    peso = binding.listaPesoAltura.text.toString().toDouble()
                )
                quandoClicaNoSalvar(caixa)
            }
            .setNegativeButton("Cancelar") { _, _ ->

            }
            .show()
    }
}