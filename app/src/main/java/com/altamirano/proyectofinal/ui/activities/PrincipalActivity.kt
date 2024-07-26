package com.altamirano.proyectofinal.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentManager
import com.altamirano.proyectofinal.R
import com.altamirano.proyectofinal.databinding.PrincipalActivityBinding
import com.altamirano.proyectofinal.ui.fragments.PrincipalFragment
import com.altamirano.proyectofinal.ui.fragments.RickAndMortyFragment
import com.altamirano.proyectofinal.ui.fragments.login.LoginFragment
import com.altamirano.proyectofinal.ui.fragments.login.RecoveryFragment
import com.altamirano.proyectofinal.ui.utilities.FragmentsManager



class PrincipalActivity : AppCompatActivity() {
    private lateinit var binding: PrincipalActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("UCE", "Entrando a Create")
        binding = PrincipalActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        // Carga el PrincipalFragment inicialmente
        FragmentsManager().replaceFragmet(supportFragmentManager, binding.frmContainer.id, PrincipalFragment())

        // Configura el listener del BottomNavigationView
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.inicio -> {
                    Log.d("Navigation", "Inicio seleccionado")
                    FragmentsManager().replaceFragmet(supportFragmentManager, binding.frmContainer.id, RickAndMortyFragment())
                    true
                }
                R.id.favoritos -> {
                    Log.d("Navigation", "Favoritos seleccionado")
                    FragmentsManager().replaceFragmet(supportFragmentManager, binding.frmContainer.id, LoginFragment())
                    true
                }
                R.id.chatgpt -> {
                    Log.d("Navigation", "ChatGPT seleccionado")
                    FragmentsManager().replaceFragmet(supportFragmentManager, binding.frmContainer.id, RecoveryFragment())
                    true
                }
                else -> false
            }
        }
    }

    // ... otros métodos de la actividad
}