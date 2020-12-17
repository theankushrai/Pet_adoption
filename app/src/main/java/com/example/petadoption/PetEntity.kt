package com.example.petadoption

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pets_table")
data class PetEntity(@ColumnInfo(name="pet_name") val pname:String,
                     @ColumnInfo(name = "image_http") val image_http:String,
                     @ColumnInfo(name = "gender") val gender:String,
                     @ColumnInfo(name = "weight")val weight:String,
                     @ColumnInfo(name = "breed") val breed:String="unknown",
                     val image:Bitmap ,
                     @PrimaryKey(autoGenerate = true) var _id:Int =0)