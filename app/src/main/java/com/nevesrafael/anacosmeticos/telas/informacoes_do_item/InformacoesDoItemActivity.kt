package com.nevesrafael.anacosmeticos.telas.informacoes_do_item

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.nevesrafael.anacosmeticos.databinding.ActivityInformacoesDoItemBinding
import com.nevesrafael.anacosmeticos.extensions.tentaCarregarImagem
import com.nevesrafael.anacosmeticos.model.Produto
import com.nevesrafael.anacosmeticos.utilidades.formataParaMoedaBrasileira
import com.nevesrafael.anacosmeticos.utilidades.formataParaMoedaJaponesa

class InformacoesDoItemActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_PRODUTO_RECEBIDO = "extra.produto.recebido"
    }

    private lateinit var binding: ActivityInformacoesDoItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInformacoesDoItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide() // some so com o titulo

        // A partir daqui pega o objeto enviado pela intent (ListProdutoActivity)

        val produto = intent.getParcelableExtra<Produto>(EXTRA_PRODUTO_RECEBIDO)
        /*
        Obs: para que o putExtra e o getParcelableExtra funcione, precizamos adicionar
             o @Parcelize no inicio da classe Produto e :Parcelable ao final da classe Produto
             tambem precisa add no build.gradle o plugin do parcelize
         */

        if (produto != null) {
            binding.detalhesNome.text = produto.nome
            binding.detalhesCategoria.setText(produto.categoria, TextView.BufferType.EDITABLE)
            binding.detalhesFabricante.setText(produto.fabricante, TextView.BufferType.EDITABLE)
            binding.detalhesQuantidade.setText(produto.quantidade.toString(), TextView.BufferType.EDITABLE)
            binding.detalhesUndMedida.setText(produto.undMedida.toString(), TextView.BufferType.EDITABLE)
            binding.detalhesValorCompra.setText(formataParaMoedaBrasileira(produto.valorCompra), TextView.BufferType.EDITABLE)
            binding.detalhesValorVendaReais.setText(formataParaMoedaBrasileira(produto.valorVendaRs), TextView.BufferType.EDITABLE)
            binding.detalhesValorVendaIene.setText(produto.valorVendaY.formataParaMoedaJaponesa(), TextView.BufferType.EDITABLE)
            binding.detalhesImagem.tentaCarregarImagem(produto.imagem)
        }
    }
}