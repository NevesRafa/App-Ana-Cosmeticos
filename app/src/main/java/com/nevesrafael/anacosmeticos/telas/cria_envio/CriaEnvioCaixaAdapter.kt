package com.nevesrafael.anacosmeticos.telas.cria_envio

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nevesrafael.anacosmeticos.databinding.CaixaDimensaoBinding
import com.nevesrafael.anacosmeticos.model.Caixa

class CriaEnvioCaixaAdapter : RecyclerView.Adapter<CriaEnvioCaixaViewHolder>() {

    private val listaCaixas = mutableListOf<Caixa>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CriaEnvioCaixaViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CaixaDimensaoBinding.inflate(inflater, parent, false)
        return CriaEnvioCaixaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CriaEnvioCaixaViewHolder, position: Int) {
        val caixa = listaCaixas[position]
        holder.vincula(caixa, position)
    }

    override fun getItemCount() = listaCaixas.size

    fun atualiza(produtos: List<Caixa>) {
        this.listaCaixas.clear()
        this.listaCaixas.addAll(produtos)
        notifyDataSetChanged()
    }
}

class CriaEnvioCaixaViewHolder(val binding: CaixaDimensaoBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun vincula(caixa: Caixa, position: Int) {

        binding.caixa.text = "Caixa ${position + 1}"
        binding.dimensoes.text =
            "Dimensões: ${caixa.comprimento} X ${caixa.largura} X ${caixa.altura}"
        binding.peso.text = "Peso: ${caixa.peso}"
    }

}
