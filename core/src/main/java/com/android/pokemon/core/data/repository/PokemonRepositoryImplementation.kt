package com.android.pokemon.core.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.android.pokemon.core.common.Constants.NETWORK_PAGE_SIZE
import com.android.pokemon.core.data.paging.PokemonPagingSource
import com.android.pokemon.core.data.remote.PokemonApi
import com.android.pokemon.core.data.remote.dto.pokedetailsdto.PokemonDto
import com.android.pokemon.core.data.remote.dto.pokelistdto.PokemonListData
import com.android.pokemon.core.data.remote.dto.pokelistdto.PokemonListDto
import com.android.pokemon.core.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokemonRepositoryImplementation @Inject constructor(private val api:PokemonApi): PokemonRepository {

    override  fun getPokemonList(): Flow<PagingData<PokemonListData>> {
        //return api.getPokemonList(limit, offset)
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { PokemonPagingSource(api) }
        ).flow
    }

    override suspend fun getPokemonDetails(name: String): PokemonDto {
        return api.getPokemonDetails(name)
    }
}