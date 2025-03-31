package com.pokemon_app.presentation.ui.view.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.pokemon_app.databinding.FragmentAboutDetailedPokemonBinding
import com.pokemon_app.domain.model.PokemonAboutMeDTO
import com.pokemon_app.interactions.GenericStates
import com.pokemon_app.presentation.ui.view.composable.aboutme.PokemonAboutMeDetailedView
import com.pokemon_app.presentation.ui.view.composable.stats.PokemonStatsDetailedView
import com.pokemon_app.utils.FragmentsTags
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AboutMeDetailedPokemonFragment : Fragment() {

    private var _binding: FragmentAboutDetailedPokemonBinding? = null

    private val binding get() = _binding!!

    private lateinit var _composeView: ComposeView

    private var _pokemonDetailState : GenericStates.PokemonDetail ? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentAboutDetailedPokemonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(arguments != null) {
            val string  = requireArguments().getString(FragmentsTags.ARG_POKEMON_DETAIL)

            _pokemonDetailState = Gson().fromJson(string, GenericStates.PokemonDetail::class.java)
        }
        _composeView = binding.aboutMeComposeView

        _pokemonDetailState?.let { updateAboutMeUI(it.pokemonAboutMeDTO) }
    }

    private fun updateAboutMeUI(pokemonAboutMeDTO: PokemonAboutMeDTO) {

        _composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)

            setContent {
                pokemonAboutMeDTO?.let { PokemonAboutMeDetailedView(it) }
            }
        }

    }



    private fun updatePokemonTypesImage(pokemonTypesImage: List<Int>?) {

    }

    private fun uploadImagewithGlide(ivPokemonType: ImageView, get: Int) {
        Glide.with(ivPokemonType).load(get).into(ivPokemonType)
    }

    fun newInstance(pokemonDetailState: GenericStates.PokemonDetail) = AboutMeDetailedPokemonFragment().apply {
        arguments = Bundle().apply { putString(FragmentsTags.ARG_POKEMON_DETAIL, Gson().toJson(pokemonDetailState, GenericStates.PokemonDetail::class.java))
            }
        }
    }

