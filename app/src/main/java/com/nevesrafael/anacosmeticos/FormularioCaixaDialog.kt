package com.nevesrafael.anacosmeticos

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import com.nevesrafael.anacosmeticos.databinding.FormularioCaixaBinding

class FormularioCaixaDialog(private val context: Context) {

    fun mostra() {

        val binding = FormularioCaixaBinding.inflate(LayoutInflater.from(context))

        AlertDialog.Builder(context)
            .setView(binding.root)
            .setPositiveButton("Salvar") { _, _ ->

            }
            .setNegativeButton("Cancelar") { _, _ ->

            }
            .show()
    }
}