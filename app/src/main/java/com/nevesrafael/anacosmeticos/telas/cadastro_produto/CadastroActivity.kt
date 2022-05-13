package com.nevesrafael.anacosmeticos.telas.cadastro_produto

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.nevesrafael.anacosmeticos.dao.ProdutoDao
import com.nevesrafael.anacosmeticos.databinding.ActivityCadastroBinding
import com.nevesrafael.anacosmeticos.extensions.tentaCarregarImagem
import com.nevesrafael.anacosmeticos.model.Produto

class CadastroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCadastroBinding
    private var url: String? = null
    private val dao =
        ProdutoDao() // deixa o dao como instância da classe pq vc nunca sabe se vai ter outro método pra usar!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configuraBotaoSalvar()
        configuraMenuCategoria()

        binding.cadastroImagem.setOnClickListener {
            FormularioImagemDialog(this).mostra(
                urlPadrao = url,
                quandoClicaNoConfirma = { imagem ->
                    url = imagem
                    binding.cadastroImagem.tentaCarregarImagem(url)
                }
            )
        }

    }

    private fun configuraMenuCategoria() {
        val items = listOf("Perfume", "Desodorante", "Creme de Corpo", "Creme para mãos")
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
        binding.categoria.setAdapter(adapter)
    }

    private fun configuraBotaoSalvar() {
        binding.botaoSalvar.setOnClickListener {
            val produtoNovo = criaProduto()
            dao.adiciona(produtoNovo)
            finish()
        }
    }

    fun pegaCampoComoInt(textView: TextView) = textView.text.toString().toIntOrNull() ?: 0

    fun pegaCampoComoDouble(textView: TextView) = textView.text.toString().toDoubleOrNull() ?: 0.0

    fun pegaCampoComoString(textView: TextView) = textView.text.toString()

    private fun criaProduto(): Produto {
        val nome = pegaCampoComoString(binding.nome)
        val categoria = pegaCampoComoString(binding.categoria)
        val fabricante = pegaCampoComoString(binding.fabricante)
        val undMedida = pegaCampoComoInt(binding.undMedida)
        val quantidade = pegaCampoComoInt(binding.quantidade)
        val valorCompra = pegaCampoComoDouble(binding.valorCompra)
        val valorVendaRs = pegaCampoComoDouble(binding.valorVendaRs)
        val valorVendaY = pegaCampoComoInt(binding.valorVendaIene)


        return Produto(
            nome = nome,
            categoria = categoria,
            fabricante = fabricante,
            undMedida = undMedida,
            quantidade = quantidade,
            valorCompra = valorCompra,
            valorVendaRs = valorVendaRs,
            valorVendaY = valorVendaY,
            imagem = url
        )
    }
}