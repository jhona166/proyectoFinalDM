
import android.content.Context
import android.view.View
import com.altamirano.proyectofinal.ui.core.UIStates
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class ManageUIStates(
    private val context: Context,
    private val view: View
) {

    fun invoke(states: UIStates) {
        when (states) {
            is UIStates.LoadingState -> {
                if (states.isLoading) {
                    view.visibility = View.VISIBLE
                } else {
                    view.visibility = View.GONE
                }
            }

            is UIStates.ErrorState -> {
                MaterialAlertDialogBuilder(
                    context
                )
                    .setTitle("Error")
                    .setMessage(states.message)
                    .setPositiveButton("Aceptar") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
            }

            is UIStates.SuccessState -> {
                MaterialAlertDialogBuilder(
                    context
                )
                    .setTitle("Exitoso")
                    .setMessage("La operación fue existosa.")
                    .setPositiveButton("Aceptar") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
            }
        }
    }

}