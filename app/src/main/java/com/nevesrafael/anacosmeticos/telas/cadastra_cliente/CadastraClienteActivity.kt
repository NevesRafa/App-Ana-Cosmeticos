package com.nevesrafael.anacosmeticos.telas.cadastra_cliente

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.nevesrafael.anacosmeticos.database.AppDatabase
import com.nevesrafael.anacosmeticos.database.ClienteDao
import com.nevesrafael.anacosmeticos.databinding.ActivityCadastraClienteBinding
import com.nevesrafael.anacosmeticos.model.Cliente

class CadastraClienteActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_CLIENTE_ID_EDITAR = "extra.cliente.id.editar"
    }

    private lateinit var binding: ActivityCadastraClienteBinding
    private var clienteId = 0
    private lateinit var clienteDao: ClienteDao
    private var taEditando = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastraClienteBinding.inflate(layoutInflater)
        clienteDao = AppDatabase.instancia(this).clienteDao()
        setContentView(binding.root)
        configuraBotaoSalvar()
        tentaCarregarCliente()
    }

    private fun tentaCarregarCliente() {
        taEditando = intent.hasExtra(EXTRA_CLIENTE_ID_EDITAR)
        if (taEditando) {
            clienteId = intent.getIntExtra(EXTRA_CLIENTE_ID_EDITAR, 0)
            tentaBuscarCliente()
        }
    }

    private fun tentaBuscarCliente() {
        clienteDao.buscaPorId(clienteId)?.let {
            title = "Altera Cliente"
            preencheCampo(it)
        }
    }

    private fun preencheCampo(clienteCarregado: Cliente) {

        with(binding) {
            nome.setText(clienteCarregado.nome)
            rua.setText(clienteCarregado.rua)
            cidade.setText(clienteCarregado.cidade)
            estado.setText(clienteCarregado.estado)
            pais.setText(clienteCarregado.pais)
            telefone.setText(clienteCarregado.telefone)
            codPostal.setText(clienteCarregado.codPostal)
            aniversario.setText(clienteCarregado.aniversario)


        }

    }

    private fun configuraBotaoSalvar() {
        binding.botaoSalvar.setOnClickListener {
            val novoCliente = criaCliente()
            if (taEditando) {
                clienteDao.altera(novoCliente)
                Toast.makeText(this, "Cliente alterado com sucesso!", Toast.LENGTH_LONG).show()
            } else {
                clienteDao.salvar(novoCliente)
                Toast.makeText(this, "Cliente salvo com sucesso!", Toast.LENGTH_LONG).show()
            }
            finish()
        }
    }

    private fun criaCliente(): Cliente {

        val nome = binding.nome.text.toString()
        val rua = binding.rua.text.toString()
        val cidade = binding.cidade.text.toString()
        val estado = binding.estado.text.toString()
        val pais = binding.pais.text.toString()
        val telefone = binding.telefone.text.toString()
        val codPostal = binding.codPostal.text.toString()
        val aniversario = binding.aniversario.text.toString()

        return Cliente(
            id = clienteId,
            nome = nome,
            rua = rua,
            cidade = cidade,
            estado = estado,
            pais = pais,
            telefone = telefone,
            codPostal = codPostal,
            aniversario = aniversario
        )

    }
}