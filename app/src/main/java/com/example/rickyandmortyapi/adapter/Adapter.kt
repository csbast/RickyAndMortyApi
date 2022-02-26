package com.example.rickyandmortyapi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickyandmortyapi.R
import com.example.rickyandmortyapi.model.Character

class Adapter(private val charactersList: List<Character>):
    RecyclerView.Adapter<Adapter.ViewHolder>(){

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val name: TextView = view.findViewById(R.id.characterNameTextView)
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

        holder.name.text = characterName
        Glide.with(holder.itemView.context)
            .load(characterImage)
            .into(holder.image)
    }

    override fun getItemCount(): Int = charactersList.size
}