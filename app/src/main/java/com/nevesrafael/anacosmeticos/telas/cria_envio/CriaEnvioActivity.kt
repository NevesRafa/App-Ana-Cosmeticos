package com.nevesrafael.anacosmeticos.telas.cria_envio

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.nevesrafael.anacosmeticos.EstoqueEnvioActivity
import com.nevesrafael.anacosmeticos.FormularioCaixaDialog
import com.nevesrafael.anacosmeticos.database.AppDatabase
import com.nevesrafael.anacosmeticos.database.EnvioDao
import com.nevesrafael.anacosmeticos.databinding.ActivityCriaEnvioBinding
import com.nevesrafael.anacosmeticos.model.Envio

class CriaEnvioActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCriaEnvioBinding
    private lateinit var envioDao: EnvioDao
    private var envioId = 0

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

    }

    private fun configuraFabCaixa() {
        binding.fabAddNaListaCaixa.setOnClickListener {
            FormularioCaixaDialog(this).mostra()
        }
    }

    private fun configuraFabProduto() {
        binding.fabAddNaListaEnvio.setOnClickListener {
            val intent = Intent(this, EstoqueEnvioActivity::class.java)
            startActivity(intent)
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