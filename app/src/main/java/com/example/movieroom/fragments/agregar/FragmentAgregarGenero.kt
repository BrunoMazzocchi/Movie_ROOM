

package com.example.movieroom.fragments.agregar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.movieroom.R
import com.example.movieroom.bd.entities.GeneroEntity
import com.example.movieroom.bd.viewmodels.GeneroViewModels
import com.example.movieroom.databinding.FragmentAgregarGeneroBinding

class FragmentAgregarGenero : Fragment() {
    private lateinit var binding: FragmentAgregarGeneroBinding
    private lateinit var viewModel: GeneroViewModels

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            FragmentAgregarGeneroBinding.inflate(layoutInflater)
        viewModel =
            ViewModelProvider(this).get(GeneroViewModels::class.java)
        binding.BtnAgregar.setOnClickListener {
            guardarRegistro()
        }
        return binding.root
    }

    private fun guardarRegistro() {
        val nombre = binding.TvNombreGenero.text.toString()
        val genero = GeneroEntity(0,nombre,true)

        viewModel.agregarGenero(genero)

        Toast.makeText(requireContext(), "Registro guardado",
            Toast.LENGTH_LONG).show()
        findNavController().navigate(R.id.goBackToGenero)
    }
}
