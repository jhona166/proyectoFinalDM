package com.altamirano.proyectofinal.ui.fragments.login

import ManageUIStates
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.altamirano.proyectofinal.R
import com.altamirano.proyectofinal.databinding.FragmentLoginBinding
import com.altamirano.proyectofinal.ui.activities.MainActivity
import com.altamirano.proyectofinal.ui.viewmodels.login.LoginFragmentVM

import kotlinx.coroutines.launch


class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val loginFragmentVM: LoginFragmentVM by viewModels()
    private lateinit var manageUIState: ManageUIStates

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding =
            FragmentLoginBinding.bind(
                inflater.inflate(
                    R.layout.fragment_login,
                    container, false
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
        manageUIState = ManageUIStates(
            requireContext(),
            binding.lytLoading.mainLayout
        )
    }

    private fun initObservers() {
        loginFragmentVM.uiState.observe(viewLifecycleOwner) { state ->
            manageUIState.invoke(state)
        }

        loginFragmentVM.idUser.observe(viewLifecycleOwner) { id ->

            startActivity(
                Intent(
                    requireActivity(),
                    MainActivity::class.java
                )
            )
            requireActivity().finish()
        }
    }


    private fun initListeners() {
        binding.btnSigIn.setOnClickListener {
            findNavController().navigate(
                LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            )
        }

        binding.btnRecoveryAccount.setOnClickListener {
            findNavController().navigate(
                LoginFragmentDirections.actionLoginFragmentToRecoveryFragment()
            )
        }


        binding.btnLogin.setOnClickListener {
            lifecycleScope.launch {
                loginFragmentVM.getUserFromDB(
                    binding.etxtUser.text.toString(),
                    binding.etxtPassword.text.toString(),
                    requireContext()
                )
            }
        }
    }
}