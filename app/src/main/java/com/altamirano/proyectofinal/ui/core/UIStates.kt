package com.altamirano.proyectofinal.ui.core

sealed class UIStates {
    class SuccessState(val condition: Boolean) : UIStates()
    class ErrorState(val message: String) : UIStates()
    class LoadingState(val isLoading: Boolean) : UIStates()
}