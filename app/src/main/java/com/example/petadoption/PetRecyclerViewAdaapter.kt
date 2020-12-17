package com.example.petadoption

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PetRecyclerViewAdaapter(private val context: Context,val listener:PetsRVAdapter):RecyclerView.Adapter<PetRecyclerViewAdaapter.PetsViewHolder>() {

    private  val allPets=ArrayList<PetEntity>()

    inner class PetsViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val nameTextView:TextView= itemView.findViewById(R.id.petTextViewMain)
        val genderTextView:TextView=itemView.findViewById(R.id.petGenderTextViewMain)
        val deleteButton:ImageView=itemView.findViewById(R.id.deleteButton)
        val breedTextView:TextView=itemView.findViewById(R.id.breedTextViewMain)
        val petImageView:ImageView=itemView.findViewById(R.id.petImageView)
        val weightTextView:TextView=itemView.findViewById(R.id.weightTextViewMain)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetsViewHolder {
        val viewHolder=PetsViewHolder(LayoutInflater.from(context).inflate(R.layout.pet_item,parent,false))
        viewHolder.deleteButton.setOnClickListener{
            listener.onItemClick(allPets[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: PetsViewHolder, position: Int) {
        val currentPet=allPets[position]
        holder.nameTextView.text=currentPet.pname
        holder.genderTextView.text=currentPet.gender
        holder.petImageView.setImageBitmap(currentPet.image)
        holder.breedTextView.text=currentPet.breed
        holder.weightTextView.text=currentPet.weight
    }

    override fun getItemCount(): Int {
        return allPets.size
    }
    fun onUpdateList(petsList:List<PetEntity>){
        allPets.clear()
        allPets.addAll(petsList)
        notifyDataSetChanged()
    }
}
interface PetsRVAdapter{
    fun onItemClick(petEntity: PetEntity)
}