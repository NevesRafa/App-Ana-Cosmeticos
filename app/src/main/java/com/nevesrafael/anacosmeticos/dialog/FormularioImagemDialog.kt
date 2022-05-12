package com.nevesrafael.anacosmeticos.dialog

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import com.nevesrafael.anacosmeticos.databinding.FormularioImagemBinding
import com.nevesrafael.anacosmeticos.extensions.tentaCarregarImagem

class FormularioImagemDialog(private val context: Context) {

    fun mostra(urlPadrao: String? = null, quandoClicaNoConfirma: (imagem: String) -> Unit) {

        val binding = FormularioImagemBinding.inflate(LayoutInflater.from(context))

        // Quando o usuario retorna para a tela do alertDialog as informacoes retornam junto,
        // imagem e texto (caso tenha ocorrido algum erro na url o usuario pode fazer a correção
        urlPadrao?.let {
            binding.formularioImagem.tentaCarregarImagem(it)
            binding.formularioText.setText(it)
        }

        // Configuração do bortão carregar
        binding.formularioBotao.setOnClickListener {
            val url = binding.formularioText.text.toString()
            binding.formularioImagem.tentaCarregarImagem(url)
        }

        // Configuração AlertDialog e botão confirmar
        AlertDialog.Builder(context)
            .setView(binding.root)
            .setPositiveButton("Confirmar") { _, _ ->
                val url = binding.formularioText.text.toString()
                quandoClicaNoConfirma(url)
            }
            .setNegativeButton("Cancela") { _, _ ->

            }
            .show()
    }
}