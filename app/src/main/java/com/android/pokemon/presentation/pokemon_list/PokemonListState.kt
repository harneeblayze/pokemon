package com.android.pokemon.presentation.pokemon_list

import androidx.paging.PagingData
import com.android.pokemon.core.domain.model.PokemonItem


data class PokemonListState(val isLoading:Boolean = false,val error:String = "",
                            val pokemonListResults: List<PagingData<PokemonItem>> = emptyList())
