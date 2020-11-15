package com.example.petadoption

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PetViewModel(application: Application) :AndroidViewModel(application) {
    val allpets:LiveData<List<PetEntity>>
    private val repository:PetsRepository

    init {
        val dao=PetsDatabase.getDatabase(application).getPetsDao()
        repository=PetsRepository(dao)
        allpets=repository.allPets
    }
    fun insert(petEntity: PetEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(petEntity)
    }
    fun deleteAll()=viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAll()
    }

}