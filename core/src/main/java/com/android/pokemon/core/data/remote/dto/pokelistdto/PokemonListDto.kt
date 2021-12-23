package com.android.pokemon.core.data.remote.dto.pokelistdto

import com.android.pokemon.core.domain.model.PokemonItem


data class PokemonListDto(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<PokemonListData>
)

fun PokemonListDto.toPokemonList():List<PokemonItem>{
   return results.map { PokemonItem(pokemonName = it.name, pokemonImage = it.url) }
}