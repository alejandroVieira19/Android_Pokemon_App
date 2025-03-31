package com.pokemon_app.presentation.ui.view.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import com.google.gson.Gson
import com.pokemon_app.databinding.FragmentPokemonStatsDetailedBinding
import com.pokemon_app.domain.model.PokemonStatsDTO
import com.pokemon_app.interactions.GenericStates
import com.pokemon_app.presentation.ui.view.composable.stats.PokemonStatsDetailedView
import com.pokemon_app.utils.FragmentsTags


class PokemonStatsDetailedFragment : Fragment() {

    private var _binding: FragmentPokemonStatsDetailedBinding? = null
    private val binding get() = _binding!!

    private lateinit var _composeView: ComposeView

    private var _pokemonDetailState: GenericStates.PokemonDetail? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentPokemonStatsDetailedBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(arguments != null) {

            val string  = requireArguments().getString(FragmentsTags.ARG_POKEMON_DETAIL)

            _pokemonDetailState = Gson().fromJson(string, GenericStates.PokemonDetail::class.java)
        }

        _composeView = binding.composeView

        updatePokemonMovesUI(_pokemonDetailState?.pokemonStatsDTO)
    }

    private fun updatePokemonMovesUI(pokemonStatsDTO: PokemonStatsDTO?) {

        _composeView.apply {

            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)

            setContent {
                pokemonStatsDTO?.let { PokemonStatsDetailedView(it) }
            }
        }
    }

    fun newInstance(pokemonDetailState: GenericStates.PokemonDetail) = PokemonStatsDetailedFragment().apply {
        arguments = Bundle().apply { putString(FragmentsTags.ARG_POKEMON_DETAIL, Gson().toJson(pokemonDetailState, GenericStates.PokemonDetail::class.java))
        }
    }

}



