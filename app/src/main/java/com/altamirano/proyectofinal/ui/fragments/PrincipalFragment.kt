package com.altamirano.proyectofinal.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.altamirano.proyectofinal.databinding.FragmentPrincipalBinding
import com.altamirano.proyectofinal.databinding.FragmentRickAndMortyBinding
import com.altamirano.proyectofinal.logic.Metodos
import com.altamirano.proyectofinal.logic.data.RamChars
import com.altamirano.proyectofinal.logic.ramLogic.RamLogic
import com.altamirano.proyectofinal.ui.activities.DetailsRamItem
import com.altamirano.proyectofinal.ui.adapters.RickAndMortyAdapter


import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class PrincipalFragment : Fragment() {

    private var _binding: FragmentPrincipalBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPrincipalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Aquí podrías agregar lógica adicional si es necesario,
        // pero por ahora, el fragmento solo muestra el layout.
    }

    override fun onDestroyView() {super.onDestroyView()
        _binding = null
    }
}

