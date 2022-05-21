package com.nevesrafael.anacosmeticos.telas.tela_inicial

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nevesrafael.anacosmeticos.MenuCadastra
import com.nevesrafael.anacosmeticos.databinding.ActivityTelaInicialBinding
import com.nevesrafael.anacosmeticos.telas.lista_produtos.ListaProdutosActivity

class TelaInicialActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTelaInicialBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTelaInicialBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configuraBotaoListaProdutos()
        configuraBotaoCadastra()
        supportActionBar?.hide()
//        val categoriaDao = AppDatabase.instancia(this).categoriaDao()
//        val fabricanteDao = AppDatabase.instancia(this).fabricanteDao()
//        carregaCategorias(categoriaDao)
//        carregaFabricantes(fabricanteDao)
    }

    private fun configuraBotaoCadastra() {
        binding.botaoCadastra.setOnClickListener {
            val intent = Intent(this, MenuCadastra::class.java)
            startActivity(intent)
        }
    }

    fun configuraBotaoListaProdutos() {
        binding.botaoListaProdutos.setOnClickListener {
            val intent = Intent(this, ListaProdutosActivity::class.java)
            startActivity(intent)
        }
    }


}