package com.nevesrafael.anacosmeticos.telas.lista_envios

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nevesrafael.anacosmeticos.databinding.EnvioCaixaJapaoBinding
import com.nevesrafael.anacosmeticos.model.Envio
import com.nevesrafael.anacosmeticos.utilidades.formataParaMoedaJaponesa

class ListaEnviosAdapter : RecyclerView.Adapter<ListaEnviosViewHolder>() {

    private val envios = mutableListOf<Envio>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListaEnviosViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = EnvioCaixaJapaoBinding.inflate(inflater, parent, false)
        return ListaEnviosViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListaEnviosViewHolder, position: Int) {
        val envio = envios[position]
        holder.vincula(envio)
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

    // pq seu metodo vincula espera uma lista de envios?
    //nao precisa dele pra vincular os dados
    // precisa, mas vc vai passar um por vez, cada item da sua lista é um ViewHolder diferente, vc precisa passar um Envio em cada
    //assim?
    // é zé. seu adapter precisa receber a lista toda
    // mas seu viewholder é pra cada item da lista
    //ahhhhhh

    fun vincula(envio: Envio) {
        binding.caixaTransportadora.text = envio.transportadora
        binding.caixaDataEnvio.text = envio.dataDoEnvio
        val valorEnvio = binding.caixaValorEnvio
        val valorEmMoeda = envio.valorEnvio.formataParaMoedaJaponesa()
        valorEnvio.text = valorEmMoeda

    }


}
