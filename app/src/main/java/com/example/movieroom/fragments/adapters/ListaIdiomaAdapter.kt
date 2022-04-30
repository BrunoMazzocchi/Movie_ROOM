package com.example.movieroom.fragments.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.movieroom.bd.entities.GeneroEntity
import com.example.movieroom.bd.entities.IdiomaEntity
import com.example.movieroom.databinding.ListaGeneroBinding
import com.example.movieroom.databinding.ListaIdiomaBinding
import com.example.movieroom.fragments.lista.FragmentListaGeneroDirections
import com.example.movieroom.fragments.lista.FragmentListaIdioma
import com.example.movieroom.fragments.lista.FragmentListaIdiomaDirections

class ListaIdiomaAdapter:
    RecyclerView.Adapter<ListaIdiomaAdapter.IdiomaHolder>(){
    private var listadoIdioma = emptyList<IdiomaEntity>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): IdiomaHolder {
        val binding = ListaIdiomaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return IdiomaHolder(binding)
    }

    override fun onBindViewHolder(holder: IdiomaHolder, position: Int) {
        holder.bind(
            listadoIdioma[position]
        )
    }

    override fun getItemCount(): Int = listadoIdioma.size

    fun setData(idiomas: List<IdiomaEntity>){
        this.listadoIdioma = idiomas
        notifyDataSetChanged()
    }

    inner class IdiomaHolder(val binding: ListaIdiomaBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(idioma: IdiomaEntity){
            with(binding){
                TvIdIdioma.text = idioma.idIdioma.toString()
                TvNombreIdioma.text = idioma.nombre

                ClFilaGen.setOnClickListener {
                    val action = FragmentListaIdiomaDirections.updateListaIdioma(idioma)
                    it.findNavController().navigate(action)
                }
            }
        }
    }
}