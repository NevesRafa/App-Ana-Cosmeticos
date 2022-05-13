package com.nevesrafael.anacosmeticos.telas.lista_produtos

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.nevesrafael.anacosmeticos.telas.cadastro_produto.CadastroActivity
import com.nevesrafael.anacosmeticos.telas.informacoes_do_item.InformacoesDoItemActivity
import com.nevesrafael.anacosmeticos.dao.ProdutoDao
import com.nevesrafael.anacosmeticos.recycleview.adapter.ListaProdutosAdapter
import com.nevesrafael.anacosmeticos.databinding.ActivityListaProdutosBinding

class ListaProdutosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListaProdutosBinding
    private val dao = ProdutoDao()
    private lateinit var  adapter: ListaProdutosAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaProdutosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configuraRecyclerView()
        configuraFab()
    }

    override fun onResume() {
        super.onResume()
        adapter.atualiza(dao.buscaTodos())
    }

    private fun configuraRecyclerView() {
        // inicializa o adapter
        adapter = ListaProdutosAdapter(quandoClicaNoProduto = {produto ->
            val intent = Intent(this, InformacoesDoItemActivity::class.java)  //intent para abrir tela Informacões

            intent.putExtra(InformacoesDoItemActivity.EXTRA_PRODUTO_RECEBIDO, produto)

            startActivity(intent)
        })
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun configuraFab(){
        binding.fab.setOnClickListener{
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }
    }
}