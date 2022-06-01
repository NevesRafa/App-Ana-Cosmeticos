package com.nevesrafael.anacosmeticos.telas.estoque_envio

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nevesrafael.anacosmeticos.databinding.ProdutosEnviadosBinding
import com.nevesrafael.anacosmeticos.model.Produto

class EstoqueEnvioAdapter(val quandoClicaNoProduto: (Produto) -> Unit) :
    RecyclerView.Adapter<EstoqueEnvioViewHolder>() {

    private val produtosEstoque = mutableListOf<Produto>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EstoqueEnvioViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ProdutosEnviadosBinding.inflate(inflater, parent, false)
        return EstoqueEnvioViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EstoqueEnvioViewHolder, position: Int) {
        val produto = produtosEstoque[position]
        holder.vincula(produto, quandoClicaNoProduto)
    }

    override fun getItemCount() = produtosEstoque.size

    fun atualiza(produtos: List<Produto>) {
        this.produtosEstoque.clear()
        this.produtosEstoque.addAll(produtos)
        notifyDataSetChanged()
    }
}

class EstoqueEnvioViewHolder(val binding: ProdutosEnviadosBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun vincula(produto: Produto, quandoClicaNoProduto: (Produto) -> Unit) {
        binding.enviadoNomeProduto.text = produto.nome
        binding.enviadoDescricaoProduto.text = produto.categoria
        binding.enviadoQuantidadeProduto.text = produto.estoque.toString()
        binding.enviadoUndMedidaProduto.text = produto.undMedida.toString()

        if (produto.tipoUndMedida == 0) {
            binding.enviadoTipoUndMedidaProduto.text = "ml"
        } else if (produto.tipoUndMedida == 1) {
            binding.enviadoTipoUndMedidaProduto.text = "g"
        } else if (produto.tipoUndMedida == 2) {
            binding.enviadoTipoUndMedidaProduto.text = "kg"
        } else {
            binding.enviadoTipoUndMedidaProduto.text = "L"
        }

        binding.root.setOnClickListener {
            quandoClicaNoProduto(produto)
        }
    }

}
