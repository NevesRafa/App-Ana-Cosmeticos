package com.nevesrafael.anacosmeticos.recycleview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nevesrafael.anacosmeticos.databinding.ProdutoItemBinding
import com.nevesrafael.anacosmeticos.extensions.tentaCarregarImagem
import com.nevesrafael.anacosmeticos.model.Produto
import com.nevesrafael.anacosmeticos.utilidades.formataParaMoedaBrasileira

class ListaProdutosAdapter(
    produtos: List<Produto>
) : RecyclerView.Adapter<ListaProdutosAdapter.ViewHolder>() {

    private val produtos = produtos.toMutableList()

    class ViewHolder(val binding: ProdutoItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun vincula(produto: Produto) {
            binding.produtoNome.text = produto.nome
            binding.produtoCategoria.text = produto.categoria
            val valor = binding.produtoValorReais
            val valorEmMoeda = formataParaMoedaBrasileira(produto.valorVendaRs)
            valor.text = valorEmMoeda

            binding.produtoImagem.tentaCarregarImagem(produto.imagem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ProdutoItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val produto = produtos[position]
        holder.vincula(produto)
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