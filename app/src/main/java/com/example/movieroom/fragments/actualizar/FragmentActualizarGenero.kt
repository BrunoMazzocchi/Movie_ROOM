
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
import com.example.movieroom.bd.viewmodels.GeneroViewModels
import com.example.movieroom.databinding.FragmentActualizarGeneroBinding
import kotlinx.android.synthetic.main.lista_genero.*

class FragmentActualizarGenero : Fragment() {
    lateinit var uBinding: FragmentActualizarGeneroBinding
    private val args by navArgs<FragmentActualizarGeneroArgs>()
    private lateinit var viewModel: GeneroViewModels

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        uBinding =
            FragmentActualizarGeneroBinding.inflate(layoutInflater)
        viewModel =
            ViewModelProvider(this).get(GeneroViewModels::class.java)

        with(uBinding){
            TvNombreGenero.setText(args.genero.nombre)

            BtnActualizar.setOnClickListener {
                GuardarCambios()
            }
        }
        setHasOptionsMenu(true)
        return uBinding.root
    }

    private fun GuardarCambios() {
        val nombre = uBinding.TvNombreGenero.text.toString()

        val gen =
            GeneroEntity(args.genero.idGenero, nombre, args.genero.activo)

        viewModel.actualizarGenero(gen)
        Toast.makeText(requireContext(), "Registro actualizado",
            Toast.LENGTH_LONG).show()
        findNavController().navigate(R.id.updateToListaGenero)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater:
    MenuInflater
    ) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        if (item.itemId == R.id.menuEliminar) {
            eliminarGenero()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun eliminarGenero() {
        val alerta = AlertDialog.Builder(requireContext())
        alerta.setPositiveButton("Si") { _, _ ->
            viewModel.eliminarGenero(args.genero)
            Toast.makeText(
                requireContext(),
                "Registro eliminado satisfactoriamente...",
                Toast.LENGTH_LONG
            ).show()
            findNavController().navigate(R.id.updateListaGenero)
        }
        alerta.setNegativeButton("No") { _, _ ->
            Toast.makeText(
                requireContext(),
                "Operación cancelada...",
                Toast.LENGTH_LONG
            ).show()
        }
        alerta.setTitle("Eliminando ${args.genero.nombre}")
        alerta.setMessage("¿Esta seguro de eliminar a ${args.genero.nombre}?")
        alerta.create().show()
    }
}