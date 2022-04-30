
package com.example.movieroom.fragments.lista

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieroom.R
import com.example.movieroom.bd.viewmodels.GeneroViewModels
import com.example.movieroom.bd.viewmodels.IdiomaViewModels
import com.example.movieroom.databinding.FragmentListaIdiomaBinding
import com.example.movieroom.fragments.adapters.ListaIdiomaAdapter

class FragmentListaIdioma : Fragment() {
    lateinit var binding: FragmentListaIdiomaBinding
    private lateinit var viewModels: IdiomaViewModels

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListaIdiomaBinding.inflate(inflater, container, false)
        val adapter = ListaIdiomaAdapter()
        val recyclerView = binding.RcvListaIdioma

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModels = ViewModelProvider(this).get(IdiomaViewModels::class.java)
        viewModels.lista.observe(viewLifecycleOwner, {gen -> adapter.setData(gen) })

        setHasOptionsMenu(true)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }
    private fun setupViews(){
        with(binding){
            BtnAgregar.setOnClickListener{
                it.findNavController().navigate(R.id.addIdioma)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menuEliminar){
            eliminarTodo()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun eliminarTodo(){
        val alerta = AlertDialog.Builder(requireContext())
        alerta.setPositiveButton("Si") { _, _ ->
            viewModels.eliminarTodo()
            Toast.makeText(
                requireContext(),
                "Registros eliminados satisfactoriamente...",
                Toast.LENGTH_LONG
            ).show()
        }
        alerta.setNegativeButton("No") { _, _ ->
            Toast.makeText(
                requireContext(),
                "Operación cancelada...",
                Toast.LENGTH_LONG
            ).show()
        }
        alerta.setTitle("Eliminando todos los registro")
        alerta.setMessage("¿Esta seguro de eliminar los registros?")
        alerta.create().show()
    }
}