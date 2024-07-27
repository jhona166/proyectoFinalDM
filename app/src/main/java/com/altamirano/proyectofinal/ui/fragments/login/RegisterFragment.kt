package com.altamirano.proyectofinal.ui.fragments.login


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.altamirano.proyectofinal.R

import com.altamirano.proyectofinal.databinding.FragmentRegisterBinding
import com.altamirano.proyectofinal.ui.core.ManageUIStates
import com.altamirano.proyectofinal.ui.viewmodels.login.RegisterFragmentVM
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth


class RegisterFragment : Fragment() {
    private lateinit var auth: FirebaseAuth

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

        auth = Firebase.auth

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

            auth.createUserWithEmailAndPassword(
                binding.etxtUser.text.toString(),
                binding.etxtPass.text.toString()
            )
                .addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("TAG", "createUserWithEmail:success")
                        val user = auth.currentUser
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("TAG", "createUserWithEmail:failure", task.exception)
                        Toast.makeText(
                            requireActivity(),
                            task.exception?.message.toString(),
                            Toast.LENGTH_SHORT,
                        ).show()
                    }
                }


        }
    }

    private fun createLocalUser() {
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