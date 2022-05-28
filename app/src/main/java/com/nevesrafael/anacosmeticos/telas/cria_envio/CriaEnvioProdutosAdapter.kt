package com.nevesrafael.anacosmeticos.telas.cria_envio

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nevesrafael.anacosmeticos.databinding.ProdutosEnviadosBinding
import com.nevesrafael.anacosmeticos.model.Produto
import com.nevesrafael.anacosmeticos.model.TipoUnidadeMedida

class CriaEnvioProdutosAdapter : RecyclerView.Adapter<CriaEnvioProdutosViewHolder>() {

    private val produtoEnvio = mutableListOf<Produto>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CriaEnvioProdutosViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ProdutosEnviadosBinding.inflate(inflater, parent, false)
        return CriaEnvioProdutosViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CriaEnvioProdutosViewHolder, position: Int) {
        val produto = produtoEnvio[position]
        holder.vincula(produto)
    }

    override fun getItemCount() = produtoEnvio.size

    fun atualiza(produtos: List<Produto>) {
        this.produtoEnvio.clear()
        this.produtoEnvio.addAll(produtos)
        notifyDataSetChanged()
    }

}

class CriaEnvioProdutosViewHolder(val binding: ProdutosEnviadosBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun vincula(produto: Produto) {
        binding.enviadoNomeProduto.text = produto.nome
        binding.enviadoDescricaoProduto.text = produto.categoria
        //binding.enviadoQuantidadeProduto.text = produto.quantidade.toString()
        binding.enviadoUndMedidaProduto.text = produto.undMedida.toString()

        if (produto.tipoUndMedida == TipoUnidadeMedida.ML) {
            binding.enviadoTipoUndMedidaProduto.text = "ml"
        } else if (produto.tipoUndMedida == TipoUnidadeMedida.G) {
            binding.enviadoTipoUndMedidaProduto.text = "g"
        } else if (produto.tipoUndMedida == TipoUnidadeMedida.KG) {
            binding.enviadoTipoUndMedidaProduto.text = "kg"
        } else {
            binding.enviadoTipoUndMedidaProduto.text = "L"
        }
    }

}
