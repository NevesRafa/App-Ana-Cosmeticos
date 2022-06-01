package com.nevesrafael.anacosmeticos.telas.informacoes_do_item

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.nevesrafael.anacosmeticos.R
import com.nevesrafael.anacosmeticos.database.AppDatabase
import com.nevesrafael.anacosmeticos.database.ProdutoDao
import com.nevesrafael.anacosmeticos.databinding.ActivityInformacoesDoItemBinding
import com.nevesrafael.anacosmeticos.extensions.tentaCarregarImagem
import com.nevesrafael.anacosmeticos.model.Produto
import com.nevesrafael.anacosmeticos.model.TipoUnidadeMedida
import com.nevesrafael.anacosmeticos.telas.cadastro_produto.CadastroActivity
import com.nevesrafael.anacosmeticos.utilidades.formataParaMoedaBrasileira
import com.nevesrafael.anacosmeticos.utilidades.formataParaMoedaJaponesa

class InformacoesDoItemActivity : AppCompatActivity() {

    private var produtoId: Int = 0
    private var produto: Produto? = null

    companion object {
        const val EXTRA_PRODUTO_RECEBIDO_ID = "extra.produto.recebido.id"
    }

    private lateinit var produtoDao: ProdutoDao

    private lateinit var binding: ActivityInformacoesDoItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInformacoesDoItemBinding.inflate(layoutInflater)
        produtoDao = AppDatabase.instancia(this).produtoDao()
        setContentView(binding.root)
        produtoId = intent.getIntExtra(EXTRA_PRODUTO_RECEBIDO_ID, 0)

//        supportActionBar?.hide() // some so com o titulo

        // A partir daqui pega o objeto enviado pela intent (ListProdutoActivity)


        /*
        Obs: para que o putExtra e o getParcelableExtra funcione, precizamos adicionar
             o @Parcelize no inicio da classe Produto e :Parcelable ao final da classe Produto
             tambem precisa add no build.gradle o plugin do parcelize
         */
    }

    override fun onResume() {
        super.onResume()
        produto = produtoDao.buscaPorId(produtoId)
        produto?.let { produto ->
            binding.detalhesNome.text = produto.nome
            binding.detalhesCategoria.setText(produto.categoria, TextView.BufferType.EDITABLE)
            binding.detalhesFabricante.setText(produto.fabricante, TextView.BufferType.EDITABLE)
            binding.detalhesQuantidade.setText(
                produto.estoque.toString(),
                TextView.BufferType.EDITABLE
            )
            binding.detalhesValorCompra.setText(
                formataParaMoedaBrasileira(produto.valorCompra),
                TextView.BufferType.EDITABLE
            )
            binding.detalhesValorVendaReais.setText(
                formataParaMoedaBrasileira(produto.valorVendaRs),
                TextView.BufferType.EDITABLE
            )
            binding.detalhesValorVendaIene.setText(
                produto.valorVendaY.formataParaMoedaJaponesa(),
                TextView.BufferType.EDITABLE
            )
            binding.detalhesImagem.tentaCarregarImagem(produto.imagem)
            preencheUnidadeDeMedida(produto.tipoUndMedida, produto.undMedida)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detalhes_produto, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_detalhes_editar -> {
                Intent(this, CadastroActivity::class.java).apply {
                    putExtra(CadastroActivity.EXTRA_PRODUTO_ID_EDITAR, produtoId)
                    startActivity(this)
                }
            }
            R.id.menu_detalhes_excluir -> {
                produto?.let { produtoDao.remove(it) }
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun preencheUnidadeDeMedida(tipo: Int, quantidade: Int) {
        val nomenclatura = when (tipo) {
            TipoUnidadeMedida.KG -> "kg"
            TipoUnidadeMedida.ML -> "ml"
            TipoUnidadeMedida.G -> "g"
            else -> "L"
        }

        binding.detalhesUndMedida.setText("$quantidade$nomenclatura", TextView.BufferType.EDITABLE)
    }
}