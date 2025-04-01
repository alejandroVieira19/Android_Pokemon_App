package com.pokemon_app.presentation.ui.view.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.pokemon_app.databinding.FragmentPokemonMovesDetailedBinding
import com.pokemon_app.interactions.GenericStates
import com.pokemon_app.presentation.adapter.MovesAdapter
import com.pokemon_app.presentation.ui.view.composable.detail.moves.PokemonMovesView
import com.pokemon_app.utils.FragmentsTags

class PokemonMovesDetailedFragment : Fragment() {

    private var _binding: FragmentPokemonMovesDetailedBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: MovesAdapter

    private lateinit var _composeView: ComposeView

    private var _pokemonDetailState : GenericStates.PokemonDetail ? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        _binding = FragmentPokemonMovesDetailedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(arguments != null) {
            val string  = requireArguments().getString(FragmentsTags.ARG_POKEMON_DETAIL)

            _pokemonDetailState = Gson().fromJson(string, GenericStates.PokemonDetail::class.java)
        }
        _composeView = binding.movesComposeView

        UpdatePokemonMovesUI(_pokemonDetailState?.pokemonMovesList)
    }

    private fun UpdatePokemonMovesUI(pokemonMovesList: List<String>?) {

        _composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                pokemonMovesList?.let { PokemonMovesView(it) }
            }
        }
    }

    fun newInstance(pokemonDetailState: GenericStates.PokemonDetail) = PokemonMovesDetailedFragment().apply {
        arguments = Bundle().apply { putString(FragmentsTags.ARG_POKEMON_DETAIL, Gson().toJson(pokemonDetailState, GenericStates.PokemonDetail::class.java))
        }
    }
}

