package com.android.pokemon.core.domain.repository

import androidx.paging.PagingData
import com.android.pokemon.core.data.remote.dto.pokedetailsdto.PokemonDto
import com.android.pokemon.core.data.remote.dto.pokelistdto.PokemonListData
import com.android.pokemon.core.data.remote.dto.pokelistdto.PokemonListDto
import kotlinx.coroutines.flow.Flow


interface PokemonRepository {
     fun getPokemonList(): Flow<PagingData<PokemonListData>>
    suspend fun getPokemonDetails(name:String): PokemonDto
}