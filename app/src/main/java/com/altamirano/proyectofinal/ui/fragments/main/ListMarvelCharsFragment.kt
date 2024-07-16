/*package com.altamirano.proyectofinal.ui.fragments.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.torres.finalproject.R
import com.torres.finalproject.databinding.FragmentListMarvelCharsBinding
import com.torres.finalproject.logic.marvel.GetAllMarvelCharsUserCase
import com.torres.finalproject.ui.adapters.ListMarverCharsAdapter
import com.torres.finalproject.ui.core.ManageUIStates
import com.torres.finalproject.ui.viewmodels.main.ListMarvelCharsVM
import kotlinx.coroutines.launch


class ListMarvelCharsFragment : Fragment() {

    private lateinit var binding: FragmentListMarvelCharsBinding
    private lateinit var adapter: ListMarverCharsAdapter
    private val listMarvelCharsVM: ListMarvelCharsVM by viewModels()
    private lateinit var manageUIStates: ManageUIStates

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentListMarvelCharsBinding.bind(
            inflater.inflate(R.layout.fragment_list_marvel_chars, container, false)
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initVariables()
        initListeners()
        initObservers()

        initData()
    }


    private fun initVariables() {
        manageUIStates = ManageUIStates(requireActivity(), binding.lytLoading.mainLayout)
        adapter = ListMarverCharsAdapter()
        binding.rvListMarvelChars.adapter = adapter
        binding.rvListMarvelChars.layoutManager = LinearLayoutManager(
            requireActivity(),
            LinearLayoutManager.VERTICAL,
            false
        )
    }

    private fun initListeners() {

    }

    private fun initObservers() {
        listMarvelCharsVM.itemsMarvel.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        listMarvelCharsVM.uiState.observe(viewLifecycleOwner) {
            manageUIStates.invoke(it)
        }

    }

    private fun initData() {
        listMarvelCharsVM.initData()
        Log.d("TAG", "Iniciando datos")
    }

}

 */