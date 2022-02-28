package com.example.rickyandmortyapi.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.rickyandmortyapi.R
import com.example.rickyandmortyapi.domain.model.Character

class Adapter(private val charactersList: MutableList<Character>):
    RecyclerView.Adapter<Adapter.ViewHolder>(){

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val name: TextView = view.findViewById(R.id.characterNameTextView)
        val status: TextView = view.findViewById(R.id.statusTv)
        val species: TextView = view.findViewById(R.id.speciesTv)
        val image: ImageView = view.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = charactersList[position]

        val characterName = character.name
        val characterImage = character.image
        val characterStatus = character.status
        val characterSpecies = character.species

        holder.name.text = characterName
        holder.species.text = characterSpecies
        holder.status.text = characterStatus
        Glide.with(holder.itemView.context)
            .load(characterImage)
            .into(holder.image)
    }

    override fun getItemCount(): Int = charactersList.size

    fun updateCharacters(characters: List<Character>){
        charactersList.addAll(characters)
        notifyDataSetChanged()
    }
}