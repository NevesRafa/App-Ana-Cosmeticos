package com.nevesrafael.anacosmeticos.telas.cria_envio

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.nevesrafael.anacosmeticos.FormularioCaixaDialog
import com.nevesrafael.anacosmeticos.database.AppDatabase
import com.nevesrafael.anacosmeticos.database.EnvioDao
import com.nevesrafael.anacosmeticos.databinding.ActivityCriaEnvioBinding
import com.nevesrafael.anacosmeticos.model.Caixa
import com.nevesrafael.anacosmeticos.model.Envio
import com.nevesrafael.anacosmeticos.model.Produto
import com.nevesrafael.anacosmeticos.model.ProdutoEnvio
import com.nevesrafael.anacosmeticos.telas.estoque_envio.EstoqueEnvioActivity

class CriaEnvioActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCriaEnvioBinding
    private lateinit var envioDao: EnvioDao
    private var envioId = 0
    private lateinit var caixaEnvioAdapter: CriaEnvioCaixaAdapter
    private lateinit var produtoEnvioAdapter: CriaEnvioProdutosAdapter

    private val produtosSelecionados = mutableListOf<ProdutoEnvio>()
    private val caixasCriadas = mutableListOf<Caixa>()

    companion object {
        const val REQUEST_CODE_PRODUTO_ENVIO = 123
        const val EXTRA_PRODUTO_ENVIO = "extra.produto.envio"
        const val EXTRA_PRODUTO_ENVIO_QUANTIDADE = "extra.produto.envio.quantidade"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCriaEnvioBinding.inflate(layoutInflater)
        envioDao = AppDatabase.instancia(this).envioDao()
        setContentView(binding.root)

        val tituloCaixa = SpannableString("Dados da Caixa")
        tituloCaixa.setSpan(UnderlineSpan(), 0, tituloCaixa.length, 0)
        binding.dadosCaixa.text = tituloCaixa

        val tituloProdutos = SpannableString("Produtos para Envio")
        tituloProdutos.setSpan(UnderlineSpan(), 0, tituloProdutos.length, 0)

        binding.produtosParaEnvio.text = tituloProdutos

        configuraBotaoSalvar()
        configuraFabProduto()
        configuraFabCaixa()
        configuraRecyclerViewListaCaixaEnvio()
        configuraRecyclerViewListaProdutoEnvio()
    }


    // esse cara é chamado quando volto de um startActivityForResult
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Aqui acontece quando eu volto do FAB de adicionar produtos no envio
        if (requestCode == REQUEST_CODE_PRODUTO_ENVIO && resultCode == Activity.RESULT_OK) {
            val produto = data?.getParcelableExtra<Produto>(EXTRA_PRODUTO_ENVIO)
            val produtoQuantidade = data?.getIntExtra(EXTRA_PRODUTO_ENVIO_QUANTIDADE, 0)

            if (produto != null && produtoQuantidade != null) {
                val produtoEnvio = ProdutoEnvio(0, 0, produto.id, produtoQuantidade)
                produtoEnvio.produto = produto

                //na verdade na linha 67 eu crio um novo objeto ProdutoEnvio, falo pra ele que:
                // - o id do produtoEnvio é 0
                // - o id do envioId é 0 tbm (até agora não foi salvo o envio)
                // - o id do produtoId é produto.id
                // - a quantidade é produtoQuantidade
                //
                //depois disso na linha 68 eu falo que o objeto produto referente aquele produtoEnvio é o produto que eu peguei lá da outra tela


                produtosSelecionados.add(produtoEnvio)
                produtoEnvioAdapter.atualiza(produtosSelecionados)
            }
        }
    }


    private fun configuraRecyclerViewListaProdutoEnvio() {
        produtoEnvioAdapter = CriaEnvioProdutosAdapter()
        binding.recyclerViewProdutosEnviados.adapter = produtoEnvioAdapter
        binding.recyclerViewProdutosEnviados.layoutManager = LinearLayoutManager(this)
    }


    private fun configuraRecyclerViewListaCaixaEnvio() {
        caixaEnvioAdapter = CriaEnvioCaixaAdapter()
        binding.recyclerViewCaixasEnvidas.adapter = caixaEnvioAdapter
        binding.recyclerViewCaixasEnvidas.layoutManager = LinearLayoutManager(this)
    }

    private fun configuraFabCaixa() {
        binding.fabAddNaListaCaixa.setOnClickListener {
            FormularioCaixaDialog(this).mostra(
                quandoClicaNoSalvar = { caixa ->
                    // guardar numa lista dentro da Cria Envio
                    caixasCriadas.add(caixa)
                    caixaEnvioAdapter.atualiza(caixasCriadas)
                }
            )
        }
    }

    private fun configuraFabProduto() {
        binding.fabAddNaListaEnvio.setOnClickListener {
            val intent = Intent(this, EstoqueEnvioActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_PRODUTO_ENVIO)
        }
    }

    private fun configuraBotaoSalvar() {
        binding.botaoSalvarCaixa.setOnClickListener {
            val novoEnvio = criaEnvio()
            envioDao.salvar(novoEnvio)
            Toast.makeText(this, "Envio criado com sucesso!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun criaEnvio(): Envio {

        val transportadora = binding.caixaTransportadora.text.toString()
        val valor = binding.caixaValorCaixa.text.toString().toIntOrNull() ?: 0
        val data = binding.caixaDataCaixa.text.toString()

        return Envio(
            id = envioId,
            transportadora = transportadora,
            valorEnvio = valor,
            dataDoEnvio = data
        )
    }
}