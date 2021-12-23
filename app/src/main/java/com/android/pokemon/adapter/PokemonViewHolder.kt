package com.android.pokemon.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.android.pokemon.core.domain.model.PokemonItem
import com.android.pokemon.databinding.ItemPokemonBinding
import com.android.pokemon.utils.getProgressDrawable
import com.android.pokemon.utils.load
import com.bumptech.glide.Glide

class PokemonViewHolder(private val binding: ItemPokemonBinding, val context: Context):
    RecyclerView.ViewHolder(binding.root) {
        fun bind(pokemonItem: PokemonItem){
            binding.apply {
                /*Glide.with(context)
                    .load(pokemonItem.pokemonImage)
                    .into(sivPokeImage)*/
                sivPokeImage.load(pokemonItem.pokemonImage, getProgressDrawable(context))
                tvPokeName.text = pokemonItem.pokemonName
            }
        }
}