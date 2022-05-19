package com.nevesrafael.anacosmeticos.telas.cadastro_produto

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.nevesrafael.anacosmeticos.R
import com.nevesrafael.anacosmeticos.dao.CategoriaDao
import com.nevesrafael.anacosmeticos.dao.FabricanteDao
import com.nevesrafael.anacosmeticos.dao.ProdutoDao
import com.nevesrafael.anacosmeticos.databinding.ActivityCadastroBinding
import com.nevesrafael.anacosmeticos.extensions.tentaCarregarImagem
import com.nevesrafael.anacosmeticos.model.Produto
import com.nevesrafael.anacosmeticos.model.TipoUnidadeMedida

class CadastroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCadastroBinding
    private var url: String? = null
    private val produtoDao =
        ProdutoDao() // deixa o dao como instância da classe pq vc nunca sabe se vai ter outro método pra usar!
    private val categoriaDao =
        CategoriaDao()
    private val fabricanteDao =
        FabricanteDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configuraBotaoSalvar()
        configuraMenuCategoria()
        configuraMenuFabricante()

        binding.cadastroImagem.setOnClickListener {
            FormularioImagemDialog(this).mostra(
                urlPadrao = url,
                quandoClicaNoConfirma = { imagem ->
                    url = imagem
                    binding.cadastroImagem.tentaCarregarImagem(url)
                }
            )
        }

        binding.fabCategoria.setOnClickListener {
            AdicionaCategoriaDialog(this).mostraDialogCategoria {
                configuraMenuCategoria()
            }
        }

        binding.fabFabricante.setOnClickListener {
            AdicionaFabricanteDialog(this).mostraDialogFabricante {
                configuraMenuFabricante()
            }
        }
    }

    private fun configuraMenuCategoria() {
        val listaCategoria = categoriaDao.buscaTodos()
        val listaCategoriaComoString =
            listaCategoria.map { categoria -> categoria.descricao }  // transforma a lista em uma lista de string atreves do map
        val adapter =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, listaCategoriaComoString)
        binding.categoria.setAdapter(adapter)
    }

    private fun configuraMenuFabricante() {
        val listaFabricante = fabricanteDao.buscaTodos()
        val listaFabricanteComoString =
            listaFabricante.map { fabricante -> fabricante.empresa }
        val adapter =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, listaFabricanteComoString)
        binding.fabricante.setAdapter(adapter)

    }

    private fun configuraBotaoSalvar() {
        binding.botaoSalvar.setOnClickListener {
            val produtoNovo = criaProduto()
            produtoDao.adiciona(produtoNovo)
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
        val tipoUndMedida = pegaUnidadeMedida()
        val undMedida = pegaCampoComoInt(binding.undMedida)
        val quantidade = pegaCampoComoInt(binding.quantidade)
        val valorCompra = pegaCampoComoDouble(binding.valorCompra)
        val valorVendaRs = pegaCampoComoDouble(binding.valorVendaRs)
        val valorVendaY = pegaCampoComoInt(binding.valorVendaIene)

        return Produto(
            nome = nome,
            categoria = categoria,
            fabricante = fabricante,
            tipoUndMedida = tipoUndMedida,
            undMedida = undMedida,
            quantidade = quantidade,
            valorCompra = valorCompra,
            valorVendaRs = valorVendaRs,
            valorVendaY = valorVendaY,
            imagem = url
        )
    }

    private fun pegaUnidadeMedida(): Int {
        val radioSelecionado = binding.radioGroup.checkedRadioButtonId

        val unidadeMedida = when (radioSelecionado) {
            R.id.radio_g -> TipoUnidadeMedida.G
            R.id.radio_ml -> TipoUnidadeMedida.ML
            R.id.radio_kg -> TipoUnidadeMedida.KG
            R.id.radio_l -> TipoUnidadeMedida.L
            else -> -1
        }

        return unidadeMedida
    }
}