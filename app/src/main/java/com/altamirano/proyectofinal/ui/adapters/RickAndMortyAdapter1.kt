package com.altamirano.proyectofinal.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.altamirano.proyectofinal.R
import com.altamirano.proyectofinal.databinding.Listadoram1Binding

import com.altamirano.proyectofinal.databinding.ListadoramBinding
import com.altamirano.proyectofinal.logic.data.RamChars




import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import java.util.Collections

class RickAndMortyAdapter1(
    private var fnClick: (RamChars) -> Unit,
    private var fnSave: (RamChars) -> Boolean
) : RecyclerView.Adapter<RickAndMortyAdapter1.RamViewHolder>() {

    var items: List<RamChars> = listOf()

    class RamViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var binding: Listadoram1Binding = Listadoram1Binding.bind(view)

        fun render(
            item: RamChars, fnClick: (RamChars) -> Unit, fnSave: (RamChars) -> Boolean
        ) {
            binding.txtNombre.text = item.nombre
            binding.txtEstado.text = item.estado
            binding.textEspecie.text = item.especie
            binding.textUbicacion.text = item.ubicacion
            binding.textOrigen.text = item.origen
            binding.textEpisode.text = item.episode

            Picasso.get().load(item.imagen).into(binding.imgRam)
            itemView.setOnClickListener {
                fnClick(item)
                Snackbar.make(
                    binding.imgRam,
                    item.nombre,
                    Snackbar.LENGTH_SHORT
                ).show()
            }
            binding.btnFav.setOnClickListener {
                val checkInsert = fnSave(item)
                if (checkInsert) {
                    Snackbar.make(
                        binding.imgRam,
                        "Se agregó a favoritos",
                        Snackbar.LENGTH_SHORT
                    ).show()
                } else {
                    Snackbar.make(
                        binding.imgRam,
                        "No se pudo agregar. Ya está agregado",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RickAndMortyAdapter1.RamViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return RamViewHolder(
            inflater.inflate(
                R.layout.listadoram,
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RickAndMortyAdapter1.RamViewHolder, position: Int) {
        holder.render(items[position], fnClick, fnSave)
    }

    override fun getItemCount(): Int = items.size

    fun updateListItems(newItems: List<RamChars>) {
        val updatedItems = items.toMutableList()
        updatedItems.addAll(newItems)
        Collections.shuffle(updatedItems)
        this.items = updatedItems
        notifyDataSetChanged()
    }

    fun replaceListItems(newItems: List<RamChars>) {
        val shuffledItems = newItems.toMutableList()
        Collections.shuffle(shuffledItems)
        this.items = shuffledItems
        notifyDataSetChanged()
    }
}