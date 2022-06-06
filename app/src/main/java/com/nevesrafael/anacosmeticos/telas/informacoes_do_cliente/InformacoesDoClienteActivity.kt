package com.nevesrafael.anacosmeticos.telas.informacoes_do_cliente

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.nevesrafael.anacosmeticos.R
import com.nevesrafael.anacosmeticos.database.AppDatabase
import com.nevesrafael.anacosmeticos.database.ClienteDao
import com.nevesrafael.anacosmeticos.databinding.ActivityInformacoesDoClienteBinding
import com.nevesrafael.anacosmeticos.model.Cliente
import com.nevesrafael.anacosmeticos.telas.cadastra_cliente.CadastraClienteActivity


class InformacoesDoClienteActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_CLIENTE_ID_RECEBIDO = "extra.cliente.id.recebido"
    }

    private lateinit var binding: ActivityInformacoesDoClienteBinding
    private var clienteId: Int = 0
    private var cliente: Cliente? = null
    private lateinit var clienteDao: ClienteDao


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInformacoesDoClienteBinding.inflate(layoutInflater)
        clienteDao = AppDatabase.instancia(this).clienteDao()
        setContentView(binding.root)
        clienteId = intent.getIntExtra(EXTRA_CLIENTE_ID_RECEBIDO, 0)
        configuraBotaoWhats()
    }

    private fun configuraBotaoWhats() {
        binding.ligarWhatsapp.setOnClickListener {
            val numWhatsComMask = binding.telefone.text.toString()
            val numWhatsSemMask = numWhatsComMask.replace("+", "")
                .replace("(", "")
                .replace(")", "")
                .replace("-", "")

            val url =
                "https://api.whatsapp.com/send?phone=$numWhatsSemMask"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        cliente = clienteDao.buscaPorId(clienteId)
        cliente?.let { cliente ->
            binding.nome.setText(cliente.nome, TextView.BufferType.EDITABLE)
            binding.rua.setText(cliente.rua, TextView.BufferType.EDITABLE)
            binding.cidade.setText(cliente.cidade, TextView.BufferType.EDITABLE)
            binding.estado.setText(cliente.estado, TextView.BufferType.EDITABLE)
            binding.pais.setText(cliente.pais, TextView.BufferType.EDITABLE)
            binding.telefone.setText(cliente.telefone, TextView.BufferType.EDITABLE)
            binding.aniversario.setText(cliente.aniversario, TextView.BufferType.EDITABLE)
            binding.codPostal.setText(cliente.codPostal, TextView.BufferType.EDITABLE)

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_informacoes_cliente, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_informacoes_cliente_editar -> {
                Intent(this, CadastraClienteActivity::class.java).apply {
                    putExtra(CadastraClienteActivity.EXTRA_CLIENTE_ID_EDITAR, clienteId)
                    startActivity(this)
                }
            }
            R.id.menu_informacoes_cliente_excluir -> {
                cliente?.let { clienteDao.remove(it) }
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}