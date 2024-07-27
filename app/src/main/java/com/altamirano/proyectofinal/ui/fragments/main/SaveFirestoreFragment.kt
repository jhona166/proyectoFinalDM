package com.altamirano.proyectofinal.ui.fragments.main


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.altamirano.proyectofinal.R
import com.altamirano.proyectofinal.databinding.FragmentSaveFirestoreBinding
import com.altamirano.proyectofinal.ui.core.ManageUIStates
import com.altamirano.proyectofinal.ui.entities.users.UserLogin
import com.altamirano.proyectofinal.ui.viewmodels.login.SaveFirestoreVM
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class SaveFirestoreFragment : Fragment() {
    private lateinit var binding: FragmentSaveFirestoreBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var manageUIStates: ManageUIStates

    private val saveFirestoreVM: SaveFirestoreVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSaveFirestoreBinding.bind(
            inflater.inflate(
                R.layout.fragment_save_firestore,
                container,
                false
            )
        )
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = Firebase.auth

        manageUIStates = ManageUIStates(requireActivity(), binding.lytloading.mainLayout)

        initListeners()
        initObservers()

    }

    private fun initObservers() {
        saveFirestoreVM.userUI.observe(viewLifecycleOwner) { state ->
            manageUIStates.invoke(state)
        }

        saveFirestoreVM.userLogin.observe(viewLifecycleOwner) {
            binding.txtData.text = it.name
        }
    }


    private fun initListeners() {
        binding.btnSave.setOnClickListener {

            val user = UserLogin(
                auth.currentUser!!.uid,
                binding.etName.text.toString(),
                binding.etLastName.text.toString()
            )
            saveFirestoreVM.saveUserFirestore(user)

        }

        binding.btnGet.setOnClickListener {
            saveFirestoreVM.getUserByIdFireStore(auth.currentUser!!.uid)
        }
    }
}