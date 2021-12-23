package com.android.pokemon.presentation.pokemon_list

import android.util.Log
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.android.pokemon.core.common.Resource
import com.android.pokemon.core.domain.model.PokemonItem
import com.android.pokemon.core.domain.repository.PokemonRepository
import com.android.pokemon.core.domain.use_cases.GetPokemonListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel@Inject constructor(private val repository: PokemonRepository)
    :ViewModel(){



   fun getPokemons(): Flow<PagingData<PokemonItem>> {
        return repository.getPokemonList()
            .map { pagingData ->
                pagingData.map { data ->
                    val number = if (data.url.endsWith("/")){
                        data.url.dropLast(1).takeLastWhile { it.isDigit() }
                    }else{
                        data.url.takeLastWhile { it.isDigit() }
                    }
                    val url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${number}.png"
                    PokemonItem(pokemonName = data.name, pokemonImage =
                    url)
                }
            }
            .cachedIn(viewModelScope)
    }



    }
