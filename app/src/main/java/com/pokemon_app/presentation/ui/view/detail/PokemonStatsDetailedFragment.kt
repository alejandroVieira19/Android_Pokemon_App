package com.pokemon_app.presentation.ui.view.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.google.gson.Gson
import com.pokemon_app.R
import com.pokemon_app.databinding.FragmentPokemonMovesDetailedBinding
import com.pokemon_app.databinding.FragmentPokemonStatsDetailedBinding
import com.pokemon_app.interactions.GenericStates
import com.pokemon_app.presentation.adapter.MovesAdapter
import com.pokemon_app.presentation.viewmodel.DetailPokemonViewModel
import com.pokemon_app.utils.FragmentsTags


class PokemonStatsDetailedFragment : Fragment() {

    private var _binding: FragmentPokemonStatsDetailedBinding? = null
    private val binding get() = _binding!!

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
        updatePokemonMovesUI()
    }

    private fun updatePokemonMovesUI() {

        binding.apply {
                // HP
                hp.setTextColor(_pokemonDetailState?.pokemonTextColor!!)
                pokemonHpNumber.setTextColor(_pokemonDetailState?.pokemonTextColor!!)
                pokemonHpNumber.text = _pokemonDetailState?.pokemonHP.toString()
                progressBarHP.progress = _pokemonDetailState!!.pokemonHP

                // Ataque
                attack.setTextColor(_pokemonDetailState?.pokemonTextColor!!)
                 pokemonAttackNumber.setTextColor(_pokemonDetailState?.pokemonTextColor!!)
                pokemonAttackNumber.text = _pokemonDetailState?.pokemonAttack.toString()
                favoritesProgressAttack.progress = _pokemonDetailState!!.pokemonAttack

                // Defesa
                defense.setTextColor(_pokemonDetailState?.pokemonTextColor!!)
                pokemonDefenseNumber.setTextColor(_pokemonDetailState?.pokemonTextColor!!)
                pokemonDefenseNumber.text = _pokemonDetailState?.pokemonDefense.toString()
                favoritesProgressDefense.progress = _pokemonDetailState!!.pokemonDefense

                // Velocidade
                speed.setTextColor(_pokemonDetailState?.pokemonTextColor!!)
                 pokemonSpeedNumber.setTextColor(_pokemonDetailState?.pokemonTextColor!!)
                pokemonSpeedNumber.text = _pokemonDetailState?.pokemonSpeed.toString()
                favoritesProgressSpeed.progress = _pokemonDetailState!!.pokemonSpeed

        }
    }

    fun newInstance(pokemonDetailState: GenericStates.PokemonDetail) = PokemonStatsDetailedFragment().apply {
        arguments = Bundle().apply { putString(FragmentsTags.ARG_POKEMON_DETAIL, Gson().toJson(pokemonDetailState, GenericStates.PokemonDetail::class.java))
        }
    }

}



