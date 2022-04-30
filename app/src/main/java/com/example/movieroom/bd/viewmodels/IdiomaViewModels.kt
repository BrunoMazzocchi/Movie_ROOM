package com.example.movieroom.bd.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.movieroom.bd.dao.MovieDB
import com.example.movieroom.bd.entities.IdiomaEntity
import com.example.movieroom.bd.repository.IdiomaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class IdiomaViewModels(application: Application): AndroidViewModel(application) {
    val lista : LiveData<List<IdiomaEntity>>
    private val repository: IdiomaRepository

    init {
        val idiomaDao =
            MovieDB.getDataBase(application).idiomaDao()
        repository = IdiomaRepository(idiomaDao)
        lista = repository.listado
    }

    fun agregarIdioma(idioma: IdiomaEntity){
        viewModelScope.launch(Dispatchers.IO){
            repository.addIdioma(idioma)
        }
    }
    fun actualizarIdioma(idioma: IdiomaEntity){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateIdioma(idioma)
        }
    }
    fun eliminarIdioma(idioma: IdiomaEntity){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteIdioma(idioma)
        }
    }

    fun eliminarTodo(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAll()
        }
    }
}