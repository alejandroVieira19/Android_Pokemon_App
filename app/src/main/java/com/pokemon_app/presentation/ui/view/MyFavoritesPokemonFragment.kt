package com.pokemon_app.presentation.ui.view

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.pokemon_app.R
import com.pokemon_app.databinding.FragmentMyFavoritesPokemonBinding
import com.pokemon_app.domain.model.Pokemon
import com.pokemon_app.interactions.GenericAction
import com.pokemon_app.interactions.GenericStates
import com.pokemon_app.presentation.adapter.PokeCardAdapter
import com.pokemon_app.presentation.viewmodel.FavoritesPokemonViewModel
import com.pokemon_app.utils.Config
import com.pokemon_app.utils.FragmentHelper
import com.pokemon_app.utils.FragmentsTags
import com.pokemon_app.utils.PokemonAlertDialogUtils

class MyFavoritesPokemonFragment : Fragment(), PokeCardAdapter.OnPokemonCardClicked {

    private var _binding: FragmentMyFavoritesPokemonBinding? = null

    private val binding get() = _binding!!

    private lateinit var _myFavoritesPokemonViewModel: FavoritesPokemonViewModel

    private var bundle : Bundle? = null

    private var detailPokemonFragment : DetailPokemonFragment ? = null

    private var fragmentHelper : FragmentHelper ? = null

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

        initializate()

        setRecyclerViewLayout()

        setPokemonLifeObserver()
    }

    private fun initializate(){
        _myFavoritesPokemonViewModel = ViewModelProvider(requireActivity()).get(FavoritesPokemonViewModel::class.java)
        bundle = Bundle()
        fragmentHelper = FragmentHelper(activity?.supportFragmentManager)
        detailPokemonFragment = DetailPokemonFragment()
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

        if(pokemons.isEmpty() && error == null) {
            binding.favoritesRelativeLayout.gravity = Gravity.CENTER
            binding.favoritesPokemonRecyclerView.visibility = View.GONE
            binding.tvFavoritesNoPokemonFound.visibility = View.VISIBLE
        } else if (error != null) {
            PokemonAlertDialogUtils.showMessageAlert(context, error)
        } else {
            binding.favoritesPokemonRecyclerView.visibility = View.VISIBLE
            binding.favoritesSearchBar.visibility = View.VISIBLE

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
            }
        }
    }

    override fun onClick(pokemon: Pokemon?) {
        bundle?.putSerializable(Config.POKEMON_NAME_KEY, pokemon)
        detailPokemonFragment?.arguments = bundle
        fragmentHelper?.replaceFragment(R.id.mainFrag, detailPokemonFragment, true, FragmentsTags.TAG_FRAGMENT_DETAILS)
    }

    override fun onStart() {
        super.onStart()

        val actionBar = (activity as AppCompatActivity).supportActionBar

        if (actionBar != null) {
            actionBar.title = Config.FAVORITES_POKEMON_APP_NAME
        }
    }
}