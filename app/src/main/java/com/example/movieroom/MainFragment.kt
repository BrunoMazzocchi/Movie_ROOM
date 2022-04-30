

package com.example.movieroom

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.movieroom.databinding.FragmentMainBinding

class MainFragment: Fragment(){
    lateinit var binding: FragmentMainBinding
    override  fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        arguments?.let{

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        binding.BtnGenero.setOnClickListener{
            it.findNavController().navigate(R.id.goToListaGenero)
        }
        binding.BtnIdioma.setOnClickListener{
            it.findNavController().navigate(R.id.goToListaIdioma)
        }
    }
}