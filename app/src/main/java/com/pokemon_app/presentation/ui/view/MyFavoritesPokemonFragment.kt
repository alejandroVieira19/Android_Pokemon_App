package com.pokemon_app.presentation.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pokemon_app.databinding.FragmentMyFavoritesPokemonBinding
import com.pokemon_app.domain.model.Pokemon
import com.pokemon_app.interactions.GenericAction
import com.pokemon_app.interactions.GenericStates
import com.pokemon_app.presentation.adapter.PokeCardAdapter
import com.pokemon_app.presentation.viewmodel.FavoritesPokemonViewModel
import com.pokemon_app.utils.PokemonAlertDialogUtils

class MyFavoritesPokemonFragment : Fragment(), PokeCardAdapter.OnPokemonCardClicked {

    private var _binding: FragmentMyFavoritesPokemonBinding? = null

    private val binding get() = _binding!!

    private lateinit var _myFavoritesPokemonViewModel: FavoritesPokemonViewModel

    private var bundle : Bundle? = null

    private var recycler_view: RecyclerView ? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
       _binding = FragmentMyFavoritesPokemonBinding.inflate(inflater, container, false)
        return binding.root
    }


    private fun setRecyclerViewLayout() {
        binding.favoritesPokemonRecyclerView.setHasFixedSize(true)
        val gridLayoutManager = GridLayoutManager(context, 2)
        binding.favoritesPokemonRecyclerView.setLayoutManager(gridLayoutManager)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _myFavoritesPokemonViewModel = ViewModelProvider(requireActivity()).get(FavoritesPokemonViewModel::class.java)

        setRecyclerViewLayout()

        setPokemonLifeObserver()
    }

    private fun setPokemonLifeObserver() {
        _myFavoritesPokemonViewModel.state.observe(viewLifecycleOwner) {state ->
            when(state) {
                is GenericStates.ListPokemons -> {
                    val listState = state
                    showPokemonsList(listState.pokemons, listState.error)
                }
                is GenericStates.ShowLoading -> {
                    state.isLoading?.let { showLoading(it) }
                }
                else -> {}
            }
        }
        _myFavoritesPokemonViewModel.interaction(GenericAction.FavoritePokemonAction.GetAllPokemons)
    }

    private fun showPokemonsList(pokemons: List<Pokemon>, error: String?) {
        if (pokemons.isEmpty() && error != null) {
            PokemonAlertDialogUtils.showMessageAlert(context, error)
        } else {

            val pokeCardAdapter = PokeCardAdapter(this, ArrayList<Pokemon>(pokemons))

            binding.favoritesPokemonRecyclerView.setAdapter(pokeCardAdapter)

            // TODO ----> COLOCAR SUGGESTION BAR
        }
    }

    private fun showLoading(isLoading:Boolean) {
        when(isLoading) {
            true -> {
                binding.favoritesProgressBar.visibility = View.VISIBLE
                binding.tvFavoritesLoadingData.visibility = View.VISIBLE
                binding.favoritesPokemonRecyclerView.visibility = View.GONE
                binding.favoritesSearchBar.visibility = View.GONE
            }
            else -> {
                binding.favoritesProgressBar.visibility = View.GONE
                binding.tvFavoritesLoadingData.visibility = View.GONE
                binding.favoritesPokemonRecyclerView.visibility = View.VISIBLE
                binding.favoritesSearchBar.visibility = View.VISIBLE
            }
        }
    }

    override fun onClick(pokemon: Pokemon?) {
        TODO("Not yet implemented")
    }
}