package com.example.movieroom.bd.repository

import androidx.lifecycle.LiveData
import com.example.movieroom.bd.dao.GeneroDao
import com.example.movieroom.bd.entities.GeneroEntity

class GeneroRepository(private val dao: GeneroDao) {
    val listado : LiveData<List<GeneroEntity>> =
        dao.getAllRealData()

    suspend fun addGenero(genero: GeneroEntity){
        dao.insert(genero)
    }

    suspend fun updateGenero(genero: GeneroEntity){
        dao.update(genero)
    }

    suspend fun deleteGenero(genero: GeneroEntity){
        dao.delete(genero)
    }

    suspend fun deleteAll(){
        dao.deleteAll()
    }
}