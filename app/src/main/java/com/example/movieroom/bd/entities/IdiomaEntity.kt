package com.example.movieroom.bd.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "TblIdioma")
data class IdiomaEntity(
    @PrimaryKey(autoGenerate = true)
    val idIdioma: Int,

    @ColumnInfo(name = "nombre")
    val nombre: String,

    @ColumnInfo(name = "activo")
    val activo: Boolean
): Parcelable
