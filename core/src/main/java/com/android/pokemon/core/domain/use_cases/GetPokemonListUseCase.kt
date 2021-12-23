package com.android.pokemon.core.domain.use_cases

import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.android.pokemon.core.common.Resource
import com.android.pokemon.core.domain.model.PokemonItem
import com.android.pokemon.core.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetPokemonListUseCase @Inject constructor(private val repository: PokemonRepository
) {
     operator fun invoke(): Resource<Flow<PagingData<PokemonItem>>>  {
       return try {
            val pokemons = repository.getPokemonList().map { pagingData ->
                pagingData.map { data ->
                    val number = if (data.url.endsWith("/")){
                        data.url.dropLast(1).takeLastWhile { it.isDigit() }
                    }else{
                        data.url.takeLastWhile { it.isDigit() }
                    }
                    val url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${number}.png"
                    PokemonItem(pokemonName = data.name, pokemonImage = url) }
            }
            Resource.Success(pokemons)

        }catch(e: HttpException){
            Resource.Error(e.message?:"UnExpected Error occurred")

        }catch (e: IOException){
            Resource.Error("couldn't reach server, check your internet connection")

        }}





}