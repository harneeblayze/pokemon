package com.android.pokemon.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.android.pokemon.core.domain.model.PokemonItem
import com.android.pokemon.databinding.ItemPokemonBinding

class PokemonListAdapter : PagingDataAdapter<PokemonItem, PokemonViewHolder>(PokemonComparator()) {
    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem!=null){
            holder.bind(currentItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val binding = ItemPokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonViewHolder(binding, parent.context)
    }

}