package com.example.movieroom.bd.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.movieroom.bd.entities.GeneroEntity
import com.example.movieroom.bd.entities.IdiomaEntity

interface MainDataBaseProvider{
    fun idiomaDao(): IdiomaDao
    fun generoDao(): GeneroDao
}

@Database(entities = [IdiomaEntity::class, GeneroEntity::class],
    version = 1
)

abstract class MovieDB: RoomDatabase(), MainDataBaseProvider {
    abstract override fun idiomaDao(): IdiomaDao
    abstract override fun generoDao(): GeneroDao
    companion object{
        @Volatile
        private var INSTANCE: MovieDB? = null

        fun getDataBase(context: Context): MovieDB {
            synchronized(this){
                var instance = INSTANCE
                if (instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MovieDB::class.java,
                        "movieDB"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}