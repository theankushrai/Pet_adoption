package com.example.petadoption

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PetDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(petEntity: PetEntity)

    @Query("delete from pets_table")
    suspend fun deleteAll()

    @Query("select * from pets_table")
     fun getAllPets():LiveData<List<PetEntity>>
}