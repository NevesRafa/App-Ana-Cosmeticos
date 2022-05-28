package com.nevesrafael.anacosmeticos.telas.tela_inicial

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nevesrafael.anacosmeticos.databinding.ActivityTelaInicialBinding
import com.nevesrafael.anacosmeticos.telas.lista_envios.ListaEnviosActivity
import com.nevesrafael.anacosmeticos.telas.lista_produtos.ListaProdutosActivity
import com.nevesrafael.anacosmeticos.telas.menu_cadastra.MenuCadastraActivity

class TelaInicialActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTelaInicialBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTelaInicialBinding.inflate(layoutInflater)


        setContentView(binding.root)
        configuraBotaoListaProdutos()
        configuraBotaoCadastra()
        configuraBotaoEnvios()


       // Rodar somente na instalação do App para carregar categorias e fabricantes ja pre definidos
//        val categoriaDao = AppDatabase.instancia(this).categoriaDao()
//        val fabricanteDao = AppDatabase.instancia(this).fabricanteDao()
//        carregaCategorias(categoriaDao)
//        carregaFabricantes(fabricanteDao)

    }

    private fun configuraBotaoEnvios() {
        binding.botaoListaEnvios.setOnClickListener {
            val intent = Intent(this, ListaEnviosActivity::class.java)
            startActivity(intent)
        }
    }

    private fun configuraBotaoCadastra() {
        binding.botaoCadastra.setOnClickListener {
            val intent = Intent(this, MenuCadastraActivity::class.java)
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