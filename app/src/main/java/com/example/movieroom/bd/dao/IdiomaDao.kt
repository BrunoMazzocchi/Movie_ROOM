package com.example.movieroom.bd.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.movieroom.bd.entities.IdiomaEntity

@Dao
interface IdiomaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(idioma: IdiomaEntity)

    @Query("SELECT * FROM TblIdioma")
    suspend fun getAll(): List<IdiomaEntity>

    @Query("SELECT * FROM TblIdioma")
    fun getAllRealData(): LiveData<List<IdiomaEntity>>

    @Update
    fun update(idioma: IdiomaEntity)

    @Delete
    fun delete(idioma: IdiomaEntity)

    @Query("DELETE FROM TblIdioma")
    suspend fun deleteAll()
}