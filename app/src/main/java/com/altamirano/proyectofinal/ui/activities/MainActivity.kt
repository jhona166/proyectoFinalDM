package com.altamirano.proyectofinal.ui.activities

import android.os.Bundle

import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import com.altamirano.proyectofinal.R
import com.altamirano.proyectofinal.databinding.ActivityMainBinding
import com.altamirano.proyectofinal.ui.fragments.login.LoginFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onBackPressed() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.containerFragments) as? NavHostFragment
        val fragment = currentFragment?.childFragmentManager?.fragments?.firstOrNull()

        if (fragment is LoginFragment) {
            super.onBackPressed()
        } else {
            showLogoutConfirmationDialog()
        }
    }

    private fun showLogoutConfirmationDialog() {
        AlertDialog.Builder(this)
            .setTitle("Cerrar Sesión")
            .setMessage("¿Quieres cerrar sesión?")
            .setPositiveButton("Sí") { dialog, _ ->
                dialog.dismiss()
                logout()
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun logout() {
        val sharedPreferences = getSharedPreferences("login_prefs", MODE_PRIVATE)
        sharedPreferences.edit().clear().apply()

        // Volver al fragmento de login
        supportFragmentManager.beginTransaction()
            .replace(R.id.containerFragments, LoginFragment())
            .commit()
    }
}