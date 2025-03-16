package com.pokemon_app.presentation.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.transition.Visibility
import com.bumptech.glide.Glide
import com.pokemon_app.R
import com.pokemon_app.databinding.FragmentAboutDetailedPokemonBinding
import com.pokemon_app.interactions.GenericStates
import com.pokemon_app.presentation.viewmodel.DetailPokemonViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AboutMeDetailedPokemonFragment : Fragment() {

    private var _binding: FragmentAboutDetailedPokemonBinding? = null
    private val binding get() = _binding!!

    // Use o viewModels passando o Fragment pai
    private val _detailPokemonViewModel: DetailPokemonViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentAboutDetailedPokemonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _detailPokemonViewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is GenericStates.PokemonDetail -> {
                    binding.weight = state.pokemonWeight
                    binding.height = state.pokemonHeight
                    binding.types = state.pokemonTypeText
                    updatePokemonTypesImage(state.pokemonTypesImage)
                }
                else -> {}
            }
        }
    }

    private fun updatePokemonTypesImage(pokemonTypesImage: List<Int>?) {
        if(pokemonTypesImage?.size == 1) {
            binding.ivType2.visibility = View.GONE
            uploadImagewithGlide(binding.ivType1, pokemonTypesImage.get(0))
        } else {
            pokemonTypesImage?.let { uploadImagewithGlide(binding.ivType1, it.get(0))
            uploadImagewithGlide(binding.ivType2, it.get(1))}
        }
    }

    private fun uploadImagewithGlide(ivPokemonType: ImageView, get: Int) {
        Glide.with(ivPokemonType).load(get).into(ivPokemonType)
    }
}
