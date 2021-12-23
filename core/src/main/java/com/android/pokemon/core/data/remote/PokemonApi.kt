package com.android.pokemon.core.data.remote

import com.android.pokemon.core.data.remote.dto.pokedetailsdto.PokemonDto
import com.android.pokemon.core.data.remote.dto.pokelistdto.PokemonListDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApi {
    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit:Int,
        @Query("offset") offset:Int
    ): PokemonListDto

    @GET("pokemon/{name}")
    suspend fun getPokemonDetails(
        @Path("name") name:String
    ):PokemonDto
}