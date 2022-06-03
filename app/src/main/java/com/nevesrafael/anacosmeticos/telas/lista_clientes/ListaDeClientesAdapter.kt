package com.nevesrafael.anacosmeticos.telas.lista_clientes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nevesrafael.anacosmeticos.databinding.ClienteBinding
import com.nevesrafael.anacosmeticos.model.Cliente

class ListaDeClientesAdapter : RecyclerView.Adapter<ListaDeClientesViewHolder>() {

    private val clientes = mutableListOf<Cliente>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListaDeClientesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ClienteBinding.inflate(inflater, parent, false)
        return ListaDeClientesViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ListaDeClientesViewHolder, position: Int) {
        val cliente = clientes[position]
        holder.vincula(cliente, position)
    }

    override fun getItemCount() = clientes.size

    fun atualiza(clientes: List<Cliente>) {
        this.clientes.clear()
        this.clientes.addAll(clientes)
        notifyDataSetChanged()
    }
}

class ListaDeClientesViewHolder(val binding: ClienteBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun vincula(cliente: Cliente, position: Int) {

        binding.nomeCliente.text = cliente.nome
        binding.numeroDoCliente.text = "${position + 1}"

    }
}
