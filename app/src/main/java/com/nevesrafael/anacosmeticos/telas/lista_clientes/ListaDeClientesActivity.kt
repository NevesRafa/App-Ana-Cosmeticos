package com.nevesrafael.anacosmeticos.telas.lista_clientes

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.nevesrafael.anacosmeticos.InformacoesDoClienteActivity
import com.nevesrafael.anacosmeticos.database.AppDatabase
import com.nevesrafael.anacosmeticos.database.ClienteDao
import com.nevesrafael.anacosmeticos.databinding.ActivityListaDeClientesBinding
import com.nevesrafael.anacosmeticos.telas.cadastra_cliente.CadastraClienteActivity

class ListaDeClientesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListaDeClientesBinding
    private lateinit var adapter: ListaDeClientesAdapter
    private lateinit var clienteDao: ClienteDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaDeClientesBinding.inflate(layoutInflater)
        clienteDao = AppDatabase.instancia(this).clienteDao()
        setContentView(binding.root)
        configuraBotaoCriaCliente()
        configuraRecyclerView()

    }

    override fun onResume() {
        super.onResume()
        adapter.atualiza(clienteDao.buscarTodos())
    }

    private fun configuraRecyclerView() {

        adapter = ListaDeClientesAdapter(quandoClicarNoCliente = { cliente ->
            val intent = Intent(this, InformacoesDoClienteActivity::class.java)
            intent.putExtra(InformacoesDoClienteActivity.EXTRA_CLIENTE_ID_RECEBIDO, cliente.id)
            startActivity(intent)
        })
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

    }


    private fun configuraBotaoCriaCliente() {
        binding.fabNovoCliente.setOnClickListener {
            val intent = Intent(this, CadastraClienteActivity::class.java)
            startActivity(intent)
        }
    }


}