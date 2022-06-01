package com.nevesrafael.anacosmeticos.telas.lista_envios

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nevesrafael.anacosmeticos.databinding.EnvioCaixaJapaoBinding
import com.nevesrafael.anacosmeticos.model.Envio
import com.nevesrafael.anacosmeticos.utilidades.formataParaMoedaJaponesa

class ListaEnviosAdapter(
    val quandoClicaNoEnvio: (Envio) -> Unit
) : RecyclerView.Adapter<ListaEnviosViewHolder>() {

    private val envios = mutableListOf<Envio>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListaEnviosViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = EnvioCaixaJapaoBinding.inflate(inflater, parent, false)
        return ListaEnviosViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListaEnviosViewHolder, position: Int) {
        val envio = envios[position]
        holder.vincula(envio, quandoClicaNoEnvio)
    }

    override fun getItemCount() = envios.size

    fun atualiza(envios: List<Envio>) {
        this.envios.clear()
        this.envios.addAll(envios)
        notifyDataSetChanged()
    }


}


class ListaEnviosViewHolder(val binding: EnvioCaixaJapaoBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun vincula(envio: Envio, quandoClicaNoEnvio: (Envio) -> Unit) {
        binding.caixaTransportadora.text = envio.transportadora
        binding.caixaDataEnvio.text = envio.dataDoEnvio
        val valorEnvio = binding.caixaValorEnvio
        val valorEmMoeda = envio.valorEnvio.formataParaMoedaJaponesa()
        valorEnvio.text = valorEmMoeda

        //click
        binding.root.setOnClickListener { quandoClicaNoEnvio(envio) }

    }


}
