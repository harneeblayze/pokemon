package com.android.pokemon.core.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.android.pokemon.core.common.Constants.NETWORK_PAGE_SIZE
import com.android.pokemon.core.data.remote.PokemonApi
import com.android.pokemon.core.data.remote.dto.pokelistdto.PokemonListData
import retrofit2.HttpException
import java.io.IOException

private const val POKEMON_STARTING_PAGE_INDEX = 1


class PokemonPagingSource(private val service: PokemonApi): PagingSource<Int,PokemonListData>(){
    override fun getRefreshKey(state: PagingState<Int, PokemonListData>): Int? {
        // We need to get the previous key (or next key if previous is null) of the page
        // that was closest to the most recently accessed index.
        // Anchor position is the most recently accessed index.
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }

    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonListData> {
        val pageIndex = params.key ?: POKEMON_STARTING_PAGE_INDEX
        return try {
            val response = service.getPokemonList(params.loadSize, pageIndex)
            val pokemons = response.results
            val nextKey =
                if (pokemons.isEmpty()) {
                    null
                } else {
                    // By default, initial load size = 3 * NETWORK PAGE SIZE
                    // ensure we're not requesting duplicating items at the 2nd request
                    pageIndex + (params.loadSize / NETWORK_PAGE_SIZE)
                }

            LoadResult.Page(
                data = pokemons,
                prevKey = if (pageIndex == POKEMON_STARTING_PAGE_INDEX) null else pageIndex,
                nextKey = nextKey
            )
        }catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}