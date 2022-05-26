package com.nevesrafael.anacosmeticos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nevesrafael.anacosmeticos.databinding.ActivityEstoqueEnvioBinding

class EstoqueEnvioActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEstoqueEnvioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEstoqueEnvioBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}