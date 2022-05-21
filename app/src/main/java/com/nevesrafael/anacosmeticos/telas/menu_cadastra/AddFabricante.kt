package com.nevesrafael.anacosmeticos.telas.menu_cadastra

import android.R
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.nevesrafael.anacosmeticos.database.AppDatabase
import com.nevesrafael.anacosmeticos.database.FabricanteDao
import com.nevesrafael.anacosmeticos.databinding.ActivityAddFabricanteBinding

class AddFabricante : AppCompatActivity() {

    private lateinit var binding: ActivityAddFabricanteBinding
    private lateinit var fabricanteDao: FabricanteDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddFabricanteBinding.inflate(layoutInflater)
        fabricanteDao = AppDatabase.instancia(this).fabricanteDao()
        setContentView(binding.root)
        configuraMenuFabricante()
        title = "Cadastra Fabricante"
    }

    private fun configuraMenuFabricante() {
        val listaFabricante = fabricanteDao.buscarTodos()
        val listaFabricanteComoString =
            listaFabricante.map { fabricante -> fabricante.empresa }
        val adapter =
            ArrayAdapter(this, R.layout.simple_list_item_1, listaFabricanteComoString)
        binding.fabricante.setAdapter(adapter)

    }
}