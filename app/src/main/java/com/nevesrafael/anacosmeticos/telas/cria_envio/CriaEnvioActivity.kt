package com.nevesrafael.anacosmeticos.telas.cria_envio

import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import androidx.appcompat.app.AppCompatActivity
import com.nevesrafael.anacosmeticos.databinding.ActivityCriaEnvioBinding

class CriaEnvioActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCriaEnvioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCriaEnvioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val titulo = SpannableString("Dados da Caixa")
        titulo.setSpan(UnderlineSpan(), 0, titulo.length, 0)

        binding.dadosCaixa.text = titulo


    }
}