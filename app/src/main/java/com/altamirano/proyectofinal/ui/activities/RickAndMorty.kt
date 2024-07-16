package com.altamirano.proyectofinal.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.altamirano.proyectofinal.databinding.ActivityRickAndMortyBinding
import com.altamirano.proyectofinal.ui.fragments.RickAndMortyFragment
import com.altamirano.proyectofinal.ui.utilities.FragmentsManager





class RickAndMorty : AppCompatActivity() {

    private lateinit var binding: ActivityRickAndMortyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRickAndMortyBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        FragmentsManager().replaceFragmet(supportFragmentManager,
            binding.frmContainer.id, RickAndMortyFragment()
        )



    }
}