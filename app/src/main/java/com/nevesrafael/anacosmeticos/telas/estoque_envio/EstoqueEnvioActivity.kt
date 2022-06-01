package com.nevesrafael.anacosmeticos.telas.estoque_envio

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.nevesrafael.anacosmeticos.database.AppDatabase
import com.nevesrafael.anacosmeticos.database.ProdutoDao
import com.nevesrafael.anacosmeticos.databinding.ActivityEstoqueEnvioBinding
import com.nevesrafael.anacosmeticos.databinding.DialogQuantidadeProdutoBinding
import com.nevesrafael.anacosmeticos.model.Produto
import com.nevesrafael.anacosmeticos.telas.cria_envio.CriaEnvioActivity

class EstoqueEnvioActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEstoqueEnvioBinding
    private lateinit var adapter: EstoqueEnvioAdapter
    private lateinit var produtoDao: ProdutoDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEstoqueEnvioBinding.inflate(layoutInflater)
        produtoDao = AppDatabase.instancia(this).produtoDao()
        setContentView(binding.root)
        configuraRecyclerViewProdutoEnvio()


    }


    override fun onResume() {
        super.onResume()
        adapter.atualiza(produtoDao.buscarTodos())
    }

    private fun configuraRecyclerViewProdutoEnvio() {
        adapter = EstoqueEnvioAdapter(quandoClicaNoProduto = { produtoClicado ->
            mostraDialogQuantidade(produtoClicado)
        })
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun mostraDialogQuantidade(produtoClicado: Produto) {
        val binding = DialogQuantidadeProdutoBinding.inflate(LayoutInflater.from(this))

        AlertDialog.Builder(this)
            .setView(binding.root)
            .setPositiveButton("Confirmar") { _, _ ->
                val quantidade = binding.quantidadeDialog.text.toString().toInt()

                mandaProdutoParaTelaCriaEnvio(produtoClicado, quantidade)
            }
            .setNegativeButton("Cancelar") { _, _ ->

            }
            .show()
    }


    private fun mandaProdutoParaTelaCriaEnvio(produtoClicado: Produto, quantidadeSelecionada: Int) {
        // criando o pacotinho
        val intentPraRetornar = Intent()
        intentPraRetornar.putExtra(CriaEnvioActivity.EXTRA_PRODUTO_ENVIO, produtoClicado)
        intentPraRetornar.putExtra(
            CriaEnvioActivity.EXTRA_PRODUTO_ENVIO_QUANTIDADE,
            quantidadeSelecionada
        )


        // falo que o resultado foi OK e mando o pacotinho
        setResult(Activity.RESULT_OK, intentPraRetornar)

        // finalizo a tela
        finish()
    }
}