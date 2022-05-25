package com.nevesrafael.anacosmeticos.telas.lista_envios

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.nevesrafael.anacosmeticos.databinding.ActivityListaEnviosBinding
import com.nevesrafael.anacosmeticos.telas.cria_envio.CriaEnvioActivity

class ListaEnviosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListaEnviosBinding
    private lateinit var adapter: ListaEnviosAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaEnviosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configuraFabNovoEnvio()
        configuraRecyclerView()
    }

    override fun onResume() {
        super.onResume()
    }

    private fun configuraRecyclerView() {

        adapter = ListaEnviosAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun configuraFabNovoEnvio() {
        binding.fabNovoEnvio.setOnClickListener {
            val intent = Intent(this, CriaEnvioActivity::class.java)
            startActivity(intent)
        }
    }


}