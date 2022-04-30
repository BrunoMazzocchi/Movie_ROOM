package com.example.movieroom.fragments.actualizar

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.movieroom.R
import com.example.movieroom.bd.entities.GeneroEntity
import com.example.movieroom.bd.entities.IdiomaEntity
import com.example.movieroom.bd.viewmodels.GeneroViewModels
import com.example.movieroom.bd.viewmodels.IdiomaViewModels
import com.example.movieroom.databinding.FragmentActualizarIdiomaBinding


class FragmentActualizarIdioma : Fragment() {
    lateinit var binding: FragmentActualizarIdiomaBinding
    private val args by navArgs<FragmentActualizarIdiomaArgs>()
    private lateinit var viewModel: IdiomaViewModels

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            FragmentActualizarIdiomaBinding.inflate(layoutInflater)
        viewModel =
            ViewModelProvider(this).get(IdiomaViewModels::class.java)

        with(binding){
            TvNombreIdioma.setText(args.idioma.nombre)

            BtnActualizar.setOnClickListener {
                GuardarCambios()
            }
        }
        setHasOptionsMenu(true)
        return binding.root
    }

    private fun GuardarCambios() {
        val nombre = binding.TvNombreIdioma.text.toString()

        val idioma =
            IdiomaEntity(args.idioma.idIdioma, nombre, args.idioma.activo)

        viewModel.actualizarIdioma(idioma)
        Toast.makeText(requireContext(), "Registro actualizado",
            Toast.LENGTH_LONG).show()
        findNavController().navigate(R.id.updateToListaIdioma)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater:
    MenuInflater
    ) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        if (item.itemId == R.id.menuEliminar) {
            eliminarIdioma()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun eliminarIdioma() {
            val alerta = AlertDialog.Builder(requireContext())
        alerta.setPositiveButton("Si") { _, _ ->
            viewModel.eliminarIdioma(args.idioma)
            Toast.makeText(
                requireContext(),
                "Registro eliminado satisfactoriamente...",
                Toast.LENGTH_LONG
            ).show()
            findNavController().navigate(R.id.updateToListaIdioma)
        }
        alerta.setNegativeButton("No") { _, _ ->
            Toast.makeText(
                requireContext(),
                "Operación cancelada...",
                Toast.LENGTH_LONG
            ).show()
        }
        alerta.setTitle("Eliminando ${args.idioma.nombre}")
        alerta.setMessage("¿Esta seguro de eliminar a ${args.idioma.nombre}?")
        alerta.create().show()
    }

}