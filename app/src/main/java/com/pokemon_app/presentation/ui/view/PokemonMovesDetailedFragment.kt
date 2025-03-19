package com.pokemon_app.presentation.ui.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.pokemon_app.databinding.FragmentPokemonMovesDetailedBinding
import com.pokemon_app.interactions.GenericStates
import com.pokemon_app.presentation.adapter.MovesAdapter
import com.pokemon_app.presentation.viewmodel.DetailPokemonViewModel

class PokemonMovesDetailedFragment : Fragment() {

    private var _binding: FragmentPokemonMovesDetailedBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: MovesAdapter

    private val _detailPokemonViewModel: DetailPokemonViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        _binding = FragmentPokemonMovesDetailedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        adapter = MovesAdapter()

        binding.pokemonMovesDetailsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.pokemonMovesDetailsRecyclerView.adapter = adapter

        _detailPokemonViewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is GenericStates.PokemonDetail -> {
                    state.pokemonMovesList?.let { adapter.submitList(it) }
                }
                else -> {_detailPokemonViewModel.refreshPokemonDetail()}
            }
        }
    }
}
