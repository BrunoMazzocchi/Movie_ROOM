

package com.example.movieroom.fragments.agregar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.movieroom.R
import com.example.movieroom.bd.entities.GeneroEntity
import com.example.movieroom.bd.entities.IdiomaEntity
import com.example.movieroom.bd.viewmodels.GeneroViewModels
import com.example.movieroom.bd.viewmodels.IdiomaViewModels
import com.example.movieroom.databinding.FragmentAgregarGeneroBinding
import com.example.movieroom.databinding.FragmentAgregarIdiomaBinding

class FragmentAgregarIdioma : Fragment() {
    private lateinit var binding: FragmentAgregarIdiomaBinding
    private lateinit var viewModel: IdiomaViewModels

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            FragmentAgregarIdiomaBinding.inflate(layoutInflater)
        viewModel =
            ViewModelProvider(this).get(IdiomaViewModels::class.java)
        binding.BtnAgregar.setOnClickListener {
            guardarRegistro()
        }
        return binding.root
    }

    private fun guardarRegistro() {
        val nombre = binding.TvNombreIdioma.text.toString()
        val idioma = IdiomaEntity(0,nombre,true)

        viewModel.agregarIdioma(idioma)

        Toast.makeText(requireContext(), "Registro guardado",
            Toast.LENGTH_LONG).show()
        findNavController().navigate(R.id.goBackToIdioma)
    }
}
