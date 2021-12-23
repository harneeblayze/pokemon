package com.android.pokemon.di


import com.android.pokemon.core.data.remote.PokemonApi
import com.android.pokemon.core.data.remote.RemoteServiceFactory
import com.android.pokemon.core.data.repository.PokemonRepositoryImplementation
import com.android.pokemon.core.domain.repository.PokemonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providePokemonApi():PokemonApi = RemoteServiceFactory.buildPokemonApiRemoteService()

    @Provides
    @Singleton
    fun providePokemonRepository(pokemonApi: PokemonApi):PokemonRepository = PokemonRepositoryImplementation(pokemonApi)

}