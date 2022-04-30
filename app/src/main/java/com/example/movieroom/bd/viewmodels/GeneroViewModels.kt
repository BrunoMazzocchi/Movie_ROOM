package com.example.movieroom.bd.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.movieroom.bd.dao.MovieDB
import com.example.movieroom.bd.entities.GeneroEntity
import com.example.movieroom.bd.repository.GeneroRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class GeneroViewModels(application: Application): AndroidViewModel(application) {
    val lista : LiveData<List<GeneroEntity>>
    private val repository: GeneroRepository

    init {
        val generoDao =
            MovieDB.getDataBase(application).generoDao()
        repository = GeneroRepository(generoDao)
        lista = repository.listado
    }

    fun agregarGenero(genero: GeneroEntity){
        viewModelScope.launch(Dispatchers.IO){
            repository.addGenero(genero)
        }
    }

    fun actualizarGenero(genero: GeneroEntity){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateGenero(genero)
        }
    }

    fun eliminarGenero(genero: GeneroEntity){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteGenero(genero)
        }
    }

    fun eliminarTodo(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAll()
        }
    }
}