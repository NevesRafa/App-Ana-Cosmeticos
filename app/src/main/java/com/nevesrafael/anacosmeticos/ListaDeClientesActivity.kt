package com.nevesrafael.anacosmeticos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nevesrafael.anacosmeticos.databinding.ActivityListaDeClientesBinding

class ListaDeClientesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListaDeClientesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaDeClientesBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}