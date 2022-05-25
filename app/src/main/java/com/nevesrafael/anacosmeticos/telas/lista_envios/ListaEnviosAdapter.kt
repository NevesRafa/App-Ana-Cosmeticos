package com.nevesrafael.anacosmeticos.telas.lista_envios

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nevesrafael.anacosmeticos.databinding.EnvioCaixaJapaoBinding
import com.nevesrafael.anacosmeticos.model.Caixa
import com.nevesrafael.anacosmeticos.utilidades.formataParaMoedaJaponesa

class ListaEnviosAdapter : RecyclerView.Adapter<ListaEnviosViewHolder>() {

    private val envios = mutableListOf<Caixa>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListaEnviosViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = EnvioCaixaJapaoBinding.inflate(inflater, parent, false)
        return ListaEnviosViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListaEnviosViewHolder, position: Int) {
        val caixa = envios[position]
        holder.vincula(caixa)
    }

    override fun getItemCount() = envios.size

    fun atualiza(envios: List<Caixa>) {
        this.envios.clear()
        this.envios.addAll(envios)
        notifyDataSetChanged()
    }


}


class ListaEnviosViewHolder(val binding: EnvioCaixaJapaoBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun vincula(caixa: Caixa) {
        binding.caixaDataEnvio.text = caixa.dataDoEnvio
        binding.caixaTransportadora.text = caixa.transportadora
        val valorEnvio = binding.caixaValorEnvio
        val valorEmMoeda = caixa.valorEnvio.formataParaMoedaJaponesa()
        valorEnvio.text = valorEmMoeda

    }


}
