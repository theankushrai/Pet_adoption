package com.example.petadoption

import androidx.lifecycle.LiveData

class PetsRepository(private val petDao: PetDao) {
    val allPets:LiveData<List<PetEntity>> = petDao.getAllPets()

    suspend fun insert(petEntity:PetEntity){
        petDao.insert(petEntity)
    }
    suspend fun deleteAll(){
        petDao.deleteAll()
    }
}