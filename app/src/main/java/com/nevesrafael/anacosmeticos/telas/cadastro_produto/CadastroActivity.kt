package com.nevesrafael.anacosmeticos.telas.cadastro_produto

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.nevesrafael.anacosmeticos.R
import com.nevesrafael.anacosmeticos.database.AppDatabase
import com.nevesrafael.anacosmeticos.database.CategoriaDao
import com.nevesrafael.anacosmeticos.database.FabricanteDao
import com.nevesrafael.anacosmeticos.database.ProdutoDao
import com.nevesrafael.anacosmeticos.databinding.ActivityCadastroBinding
import com.nevesrafael.anacosmeticos.extensions.tentaCarregarImagem
import com.nevesrafael.anacosmeticos.model.Produto
import com.nevesrafael.anacosmeticos.model.TipoUnidadeMedida

class CadastroActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_PRODUTO_ID_EDITAR = "extra.produto.id.editar"
    }

    private lateinit var binding: ActivityCadastroBinding
    private var url: String? = null
    private lateinit var produtoDao: ProdutoDao
    private var taEditando = false
    private var produtoId = 0
    private lateinit var categoriaDao: CategoriaDao
    private lateinit var fabricanteDao: FabricanteDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroBinding.inflate(layoutInflater)
        produtoDao = AppDatabase.instancia(this).produtoDao()
        categoriaDao = AppDatabase.instancia(this).categoriaDao()
        fabricanteDao = AppDatabase.instancia(this).fabricanteDao()
        setContentView(binding.root)
        configuraBotaoSalvar()
        tentaCarregarProduto()
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

    }

    private fun tentaCarregarProduto() {
        taEditando = intent.hasExtra(EXTRA_PRODUTO_ID_EDITAR)
        if (taEditando) {
            produtoId = intent.getIntExtra(EXTRA_PRODUTO_ID_EDITAR, 0)
            tentaBuscarProduto()
        }
    }

    private fun tentaBuscarProduto() {
        produtoDao.buscaPorId(produtoId)?.let {
            title = "Alterar produto"
            preencheCampo(it)
        }
    }

    private fun preencheCampo(produtoCarregado: Produto) {
        url = produtoCarregado.imagem
        with(binding) {
            cadastroImagem.tentaCarregarImagem(produtoCarregado.imagem)
            nome.setText(produtoCarregado.nome)
            categoria.setText(produtoCarregado.categoria)
            fabricante.setText(produtoCarregado.fabricante)
            undMedida.setText(produtoCarregado.undMedida.toString())
            quantidade.setText(produtoCarregado.estoque.toString())
            valorCompra.setText(produtoCarregado.valorCompra.toString())
            valorVendaRs.setText(produtoCarregado.valorVendaRs.toString())
            valorVendaIene.setText(produtoCarregado.valorVendaY.toString())

            if (produtoCarregado.tipoUndMedida == TipoUnidadeMedida.G) {
                radioG.isChecked = true
            } else if (produtoCarregado.tipoUndMedida == TipoUnidadeMedida.ML) {
                radioMl.isChecked = true
            } else if (produtoCarregado.tipoUndMedida == TipoUnidadeMedida.KG) {
                radioKg.isChecked = true
            } else {
                radioL.isChecked = true
            }
        }
    }

    private fun configuraBotaoSalvar() {
        binding.botaoSalvar.setOnClickListener {
            val produtoNovo = criaProduto()
            if (taEditando) {
                produtoDao.altera(produtoNovo)
            } else {
                produtoDao.salvar(produtoNovo)
            }
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
            id = produtoId,
            nome = nome,
            categoria = categoria,
            fabricante = fabricante,
            tipoUndMedida = tipoUndMedida,
            undMedida = undMedida,
            estoque = quantidade,
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

    private fun configuraMenuCategoria() {
        val listaCategoria = categoriaDao.buscarTodos()
        val listaCategoriaComoString =
            listaCategoria.map { categoria -> categoria.descricao }  // transforma a lista em uma lista de string atreves do map
        val adapter =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, listaCategoriaComoString)
        binding.categoria.setAdapter(adapter)
    }

    private fun configuraMenuFabricante() {
        val listaFabricante = fabricanteDao.buscarTodos()
        val listaFabricanteComoString =
            listaFabricante.map { fabricante -> fabricante.empresa }
        val adapter =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, listaFabricanteComoString)
        binding.fabricante.setAdapter(adapter)

    }


}