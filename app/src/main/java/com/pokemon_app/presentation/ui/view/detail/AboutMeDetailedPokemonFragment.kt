package com.pokemon_app.presentation.ui.view.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.pokemon_app.databinding.FragmentAboutDetailedPokemonBinding
import com.pokemon_app.interactions.GenericStates
import com.pokemon_app.presentation.viewmodel.DetailPokemonViewModel
import com.pokemon_app.utils.FragmentsTags
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AboutMeDetailedPokemonFragment : Fragment() {

    private var _binding: FragmentAboutDetailedPokemonBinding? = null

    private val binding get() = _binding!!

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
        updateAboutMeUI()
    }

    private fun updateAboutMeUI() {
        binding.apply {

            tvHeight.setTextColor(ContextCompat.getColor(requireContext(), _pokemonDetailState?.pokemonTextColor!!))
            tvWeight.setTextColor(ContextCompat.getColor(requireContext(), _pokemonDetailState?.pokemonTextColor!!))
            tvType.setTextColor(ContextCompat.getColor(requireContext(), _pokemonDetailState?.pokemonTextColor!!))

            tvDetailPokemonHeight.setTextColor(ContextCompat.getColor(requireContext(), _pokemonDetailState?.pokemonTextColor!!))
            tvDetailPokemonWeight.setTextColor(ContextCompat.getColor(requireContext(), _pokemonDetailState?.pokemonTextColor!!))
            tvDetailType.setTextColor(ContextCompat.getColor(requireContext(), _pokemonDetailState?.pokemonTextColor!!))

            weight = _pokemonDetailState?.pokemonAboutMeDTO?.pokemonWeight
            height = _pokemonDetailState?.pokemonAboutMeDTO?.pokemonHeight
            types = _pokemonDetailState?.pokemonAboutMeDTO?.pokemonTypeConcatenate
            updatePokemonTypesImage(_pokemonDetailState?.pokemonAboutMeDTO?.pokemonTypesImage)
        }
    }



    private fun updatePokemonTypesImage(pokemonTypesImage: List<Int>?) {
        when(pokemonTypesImage?.size) {
            1 -> {
                binding.ivType2.visibility = View.GONE
                uploadImagewithGlide(binding.ivType1, pokemonTypesImage.get(0))
            }
            2 -> {
                pokemonTypesImage?.let { uploadImagewithGlide(binding.ivType1, it.get(0))
                    uploadImagewithGlide(binding.ivType2, it.get(1))}
            }
        }
    }

    private fun uploadImagewithGlide(ivPokemonType: ImageView, get: Int) {
        Glide.with(ivPokemonType).load(get).into(ivPokemonType)
    }

    fun newInstance(pokemonDetailState: GenericStates.PokemonDetail) = AboutMeDetailedPokemonFragment().apply {
        arguments = Bundle().apply { putString(FragmentsTags.ARG_POKEMON_DETAIL, Gson().toJson(pokemonDetailState, GenericStates.PokemonDetail::class.java))
            }
        }
    }

