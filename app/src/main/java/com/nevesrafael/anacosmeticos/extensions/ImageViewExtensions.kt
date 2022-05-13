package com.nevesrafael.anacosmeticos.extensions

import android.widget.ImageView
import coil.load
import com.nevesrafael.anacosmeticos.R

fun ImageView.tentaCarregarImagem(url: String? = null) {
    load(url) {
        fallback(R.drawable.imagem_padrao)   // fallback: quando a imagem é nula.
        error(R.drawable.erro)   //  error: quando ocorre um erro ao tentar carregar a imagem.
        // placeholder()  // placeholder: enquanto a imagem está sendo carregada.
    }
}