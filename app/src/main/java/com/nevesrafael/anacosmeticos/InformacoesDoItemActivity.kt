package com.nevesrafael.anacosmeticos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nevesrafael.anacosmeticos.databinding.ActivityInformacoesDoItemBinding
import com.nevesrafael.anacosmeticos.model.Produto

class InformacoesDoItemActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_PRODUTO_RECEBIDO = "extra.produto.recebido"
    }

    private lateinit var binding: ActivityInformacoesDoItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInformacoesDoItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // A partir daqui pega o objeto enviado pela intent (ListProdutoActivity)

        val produto = intent.getParcelableExtra<Produto>(EXTRA_PRODUTO_RECEBIDO)
        /*
        Obs: para que o putExtra e o getParcelableExtra funcione, precizamos adicionar
             o @Parcelize no inicio da nossa lista de produtos e :Parcelable ao final da lista
             tambem precisa add no build.gradle o plugin do parcelize
         */

        if(produto != null){

        }

    }
}