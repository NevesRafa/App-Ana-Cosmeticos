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
import com.nevesrafael.anacosmeticos.telas.estoque_envio.EstoqueEnvioActivity

class CriaEnvioActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCriaEnvioBinding
    private lateinit var envioDao: EnvioDao
    private var envioId = 0
    private lateinit var adapter: CriaEnvioCaixaAdapter

    companion object {
        const val REQUEST_CODE_PRODUTO_ENVIO = 123
        const val EXTRA_PRODUTO_ENVIO = "extra.produto.envio"
        const val EXTRA_PRODUTO_ENVIO_QUANTIDADE = "extra.produto.envio.quantidade"
        const val REQUEST_CODE_CAIXA_ENVIO = 456
        const val EXTRA_CAIXA_ENVIO = "extra.caixa.comprimento"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCriaEnvioBinding.inflate(layoutInflater)
        envioDao = AppDatabase.instancia(this).envioDao()
        setContentView(binding.root)

        val titulo = SpannableString("Dados da Caixa")
        titulo.setSpan(UnderlineSpan(), 0, titulo.length, 0)

        binding.dadosCaixa.text = titulo

        configuraBotaoSalvar()
        configuraFabProduto()
        configuraFabCaixa()
        configuraRecyclerViewListaCaixaEnvio()
    }

    // esse cara é chamado quando volto de um startActivityForResult
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Aqui acontece quando eu volto do FAB de adicionar produtos no envio
        if (requestCode == REQUEST_CODE_PRODUTO_ENVIO && resultCode == Activity.RESULT_OK) {
            val produto = data?.getParcelableExtra<Produto>(EXTRA_PRODUTO_ENVIO)
            val produtoQuantidade = data?.getIntExtra(EXTRA_PRODUTO_ENVIO_QUANTIDADE, 0)

            if (produto != null) {
                Toast.makeText(
                    this,
                    "produto chegou: ${produto.nome} com estoque: $produtoQuantidade",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        // Aqui acontece quando eu volto do FAB de adicionar caixas no envio
        if (requestCode == REQUEST_CODE_CAIXA_ENVIO && resultCode == Activity.RESULT_OK) {
            val caixa = data?.getParcelableExtra<Caixa>(EXTRA_CAIXA_ENVIO)

        }
    }

    private fun configuraRecyclerViewListaCaixaEnvio() {
        adapter = CriaEnvioCaixaAdapter()
        binding.recyclerViewCaixasEnvidas.adapter = adapter
        binding.recyclerViewCaixasEnvidas.layoutManager = LinearLayoutManager(this)


    }

    private fun configuraFabCaixa() {
        binding.fabAddNaListaCaixa.setOnClickListener {
            FormularioCaixaDialog(this).mostra(quandoClicaNoSalvar = {
                val intent = Intent(this, CriaEnvioActivity::class.java)
                startActivityForResult(intent, REQUEST_CODE_CAIXA_ENVIO)
            })

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