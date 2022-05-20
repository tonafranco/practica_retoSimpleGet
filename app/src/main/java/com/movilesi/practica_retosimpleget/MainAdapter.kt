package com.movilesi.practica_retosimpleget

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.movilesi.practica_retosimpleget.databinding.ItemPokemonBinding
import org.json.JSONArray
import org.json.JSONObject

class MainAdapter(private val pokemons: JSONArray): RecyclerView.Adapter<MainAdapter.MainHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.MainHolder {
        val binding = ItemPokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainAdapter.MainHolder, position: Int) {
        holder.render(pokemons.getJSONObject(position))
    }

    override fun getItemCount(): Int = pokemons.length()

    class MainHolder(val binding: ItemPokemonBinding): RecyclerView.ViewHolder(binding.root){
        fun render(pokemon: JSONObject){
            binding.tvPokemonName.setText(pokemon.getString("name"))
        }
    }
}