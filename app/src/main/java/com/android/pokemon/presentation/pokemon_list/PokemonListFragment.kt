package com.android.pokemon.presentation.pokemon_list

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import com.android.pokemon.R
import com.android.pokemon.adapter.PokemonListAdapter
import com.android.pokemon.adapter.PokemonLoadStateAdapter
import com.android.pokemon.core.domain.model.PokemonItem
import com.android.pokemon.databinding.FragmentPokemonListBinding
import com.android.pokemon.utils.SpacesItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PokemonListFragment : Fragment() {
    private val viewModel: PokemonListViewModel by viewModels()
    private lateinit var pokeAdapter: PokemonListAdapter
    private lateinit var binding: FragmentPokemonListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentPokemonListBinding.inflate(inflater, container, false)
        pokeAdapter = PokemonListAdapter()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindUItoAdapter()
        collectUiState()
        //observeUiState()
        //check()

    }

   private fun collectUiState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getPokemons().collectLatest {pokemons ->
                pokeAdapter.submitData(pokemons)
            }
        }
    }


    private fun bindUItoAdapter(){
        binding.rvPoke.apply {
            adapter = pokeAdapter.withLoadStateHeaderAndFooter(
                header = PokemonLoadStateAdapter { pokeAdapter.retry() },
                footer = PokemonLoadStateAdapter { pokeAdapter.retry() }
            )
            layoutManager = GridLayoutManager(requireActivity(), 2)
            addItemDecoration(SpacesItemDecoration(30))
        }

    }


}