package com.android.pokemon.adapter

import androidx.recyclerview.widget.DiffUtil
import com.android.pokemon.core.domain.model.PokemonItem

class PokemonComparator:DiffUtil.ItemCallback<PokemonItem>() {
    override fun areItemsTheSame(oldItem: PokemonItem, newItem: PokemonItem): Boolean =
        oldItem.pokemonImage == newItem.pokemonImage


    override fun areContentsTheSame(oldItem: PokemonItem, newItem: PokemonItem): Boolean =
        oldItem == newItem


}