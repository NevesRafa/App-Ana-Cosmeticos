package com.nevesrafael.anacosmeticos.telas.menu_cadastra

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nevesrafael.anacosmeticos.databinding.ActivityMenuCadastraBinding
import com.nevesrafael.anacosmeticos.telas.cadastro_produto.CadastroActivity

class MenuCadastraActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuCadastraBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuCadastraBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configuraBotaoNovoProduto()
        configuraBotaoNovaCategoria()
        configuraBotaoNovoFabricante()
    }

    private fun configuraBotaoNovoFabricante() {
        binding.fabNovoFabricante.setOnClickListener {
            val intent = Intent(this, AddFabricanteActivity::class.java)
            startActivity(intent)
        }
    }

    private fun configuraBotaoNovaCategoria() {
        binding.fabNovaCategoria.setOnClickListener {
            val intent = Intent(this, AddCategoriaActivity::class.java)
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