package com.nevesrafael.anacosmeticos

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nevesrafael.anacosmeticos.databinding.ActivityMenuCadastraBinding
import com.nevesrafael.anacosmeticos.telas.cadastro_produto.CadastroActivity

class MenuCadastra : AppCompatActivity() {

    private lateinit var binding: ActivityMenuCadastraBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuCadastraBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configuraBotaoNovoProduto()
        configuraBotaoNovaCategoria()
        configuraBotaoNovoFabricante()

        title = "Cadastra"

    }

    private fun configuraBotaoNovoFabricante() {
        binding.fabNovoFabricante.setOnClickListener {
            val intent = Intent(this, AddFabricante::class.java)
            startActivity(intent)
        }
    }

    private fun configuraBotaoNovaCategoria() {
        binding.fabNovaCategoria.setOnClickListener {
            val intent = Intent(this, AddCategoria::class.java)
            startActivity(intent)
        }
    }

    private fun configuraBotaoNovoProduto() {
        binding.fabNovoProduto.setOnClickListener {
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }
    }

}