package com.nevesrafael.anacosmeticos.recycleview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nevesrafael.anacosmeticos.databinding.ProdutoItemBinding
import com.nevesrafael.anacosmeticos.extensions.tentaCarregarImagem
import com.nevesrafael.anacosmeticos.model.Produto
import com.nevesrafael.anacosmeticos.utilidades.formataParaMoedaBrasileira

class ListaProdutosAdapter(
    val quandoClicaNoProduto: (Produto) -> Unit
) : RecyclerView.Adapter<ListaProdutoViewHolder>() {

    private val produtos = mutableListOf<Produto>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListaProdutoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ProdutoItemBinding.inflate(inflater, parent, false)
        return ListaProdutoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListaProdutoViewHolder, position: Int) {
        val produto = produtos[position]
        holder.vincula(produto, this.quandoClicaNoProduto)
    }

// jeito chato
//    override fun getItemCount(): Int {
//        return produtos.size
//    }

    // jeito legal
    override fun getItemCount() = produtos.size

    fun atualiza(produtos: List<Produto>) {
        this.produtos.clear()
        this.produtos.addAll(produtos)
        notifyDataSetChanged()
    }

}

class ListaProdutoViewHolder(val binding: ProdutoItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun vincula(
        produto: Produto,
        quandoClicaNoProduto: (Produto) -> Unit
    ) {
        binding.produtoNome.text = produto.nome
        binding.produtoCategoria.text = produto.categoria
        val valor = binding.produtoValorReais
        val valorEmMoeda = formataParaMoedaBrasileira(produto.valorVendaRs)
        valor.text = valorEmMoeda

        binding.produtoImagem.tentaCarregarImagem(produto.imagem)

        // clique do card
        binding.root.setOnClickListener{
            quandoClicaNoProduto(produto)
        }


    }
}