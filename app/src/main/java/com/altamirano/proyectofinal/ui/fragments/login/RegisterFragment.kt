package com.altamirano.proyectofinal.ui.fragments.login

import ManageUIStates
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.altamirano.proyectofinal.R

import com.altamirano.proyectofinal.databinding.FragmentRegisterBinding
import com.altamirano.proyectofinal.ui.viewmodels.login.RegisterFragmentVM
import com.google.android.material.dialog.MaterialAlertDialogBuilder



class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private val registerFragmentVM: RegisterFragmentVM by viewModels()
    private lateinit var manageUIStates: ManageUIStates

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.bind(
            inflater.inflate(
                R.layout.fragment_register,
                container,
                false
            )
        )
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initVariables()
        initListeners()
        initObservers()
    }

    private fun initVariables() {
        manageUIStates = ManageUIStates(
            requireActivity(),
            binding.lytLoading.mainLayout
        )
    }

    private fun initObservers() {

        registerFragmentVM.uiState.observe(viewLifecycleOwner) { states ->
            manageUIStates.invoke(states)
        }

    }

    private fun initListeners() {
        binding.btnBack.setOnClickListener {
            findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
        }

        binding.btnSave.setOnClickListener {

            MaterialAlertDialogBuilder(requireActivity())
                .setTitle("Informacion")
                .setMessage("¿Esta usted seguro de que desea guardar la información registrada anterioremente?")
                .setPositiveButton("Si") { dialog, _ ->
                    registerFragmentVM.saveUser(
                        binding.etxtUser.text.toString(),
                        binding.etxtPass.text.toString(),
                        requireContext()
                    )
                    dialog.dismiss()
                }
                .setNegativeButton("No") { dialog, _ ->
                    dialog.cancel()
                }
                .show()


        }
    }

}