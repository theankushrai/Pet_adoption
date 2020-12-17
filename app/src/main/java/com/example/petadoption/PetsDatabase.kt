package com.example.petadoption

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = arrayOf(PetEntity::class),version = 1,exportSchema = false)
@TypeConverters(Convertor::class)
abstract class PetsDatabase: RoomDatabase() {

    abstract fun getPetsDao():PetDao
    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: PetsDatabase? = null

        fun getDatabase(context: Context): PetsDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PetsDatabase::class.java,
                    "pets_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}