package com.example.myproyectofinal

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ProyectoDao {

    @Query("SELECT * FROM proyecto_table ORDER BY proyecto ASC")
    fun getAlphabetizedWords(): Flow<List<proyecto>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: proyecto)

    @Query("DELETE FROM proyecto_table")
    suspend fun deleteAll()
}