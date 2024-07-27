package com.altamirano.proyectofinal.ui.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.altamirano.proyectofinal.R
import com.altamirano.proyectofinal.databinding.ActivityLoginBinding
import com.altamirano.proyectofinal.databinding.ActivityMainBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val splash = installSplashScreen()

        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        splash.setKeepOnScreenCondition{
            false
        }
        Log.d("LoginaActivity", "onCreate: finished")
    }
}