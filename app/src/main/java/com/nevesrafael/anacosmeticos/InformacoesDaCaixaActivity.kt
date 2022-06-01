package com.nevesrafael.anacosmeticos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nevesrafael.anacosmeticos.databinding.ActivityInformacoesDaCaixaBinding

class InformacoesDaCaixaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInformacoesDaCaixaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInformacoesDaCaixaBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}