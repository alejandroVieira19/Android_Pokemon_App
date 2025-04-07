package com.pokemon_app.presentation.ui.base

import android.os.Bundle
import android.view.View
import android.widget.ScrollView
import androidx.appcompat.widget.SearchView
import androidx.compose.ui.platform.ComposeView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pokemon_app.MainActivity
import com.pokemon_app.R
import com.pokemon_app.domain.model.Pokemon
import com.pokemon_app.domain.service.ConnectivityObserver.NetworkStatus
import com.pokemon_app.presentation.adapter.GenerationCardAdapter
import com.pokemon_app.presentation.ui.view.GenericFragment
import com.pokemon_app.presentation.ui.view.composable.detail.PokemonDetailLoading
import com.pokemon_app.presentation.ui.view.composable.geral.PokemonLoadingDTO
import com.pokemon_app.utils.FragmentsTags
import com.pokemon_app.utils.PokemonAlertDialogUtils

// DECIDI NÃO EXTENDER DO GENERIC FRAGMENT PORQUE O MY_FAVORITES NÃO NECESSITA.
// MAS TEVE QUE SER PORQUE NÃO POSSO EXTENDER MULTIPLAS CLASSES
abstract class BasePokemonListFragment: GenericFragment() {

    protected abstract fun getRecyclerViewId(): RecyclerView?

    protected abstract fun getSearchViewId(): androidx.appcompat.widget.SearchView?

    protected abstract fun getLandscapeMode(): Boolean

    protected abstract fun getPokemonList(): List<Pokemon>

    protected abstract fun filterPokemons(query: String?, pokemons: List<Pokemon>)

    protected abstract fun showPokemonsList(pokemons: List<Pokemon>, error: String?= null)

    protected abstract fun updateSearchResult(filteredPokemons: List<Pokemon>)

    protected open fun setRecyclerViewLayout() {
        val recyclerView = getRecyclerViewId()

        recyclerView?.setHasFixedSize(true)

        when (getLandscapeMode()) {
            false -> recyclerViewApplyLayoutManager(recyclerView, 2)
            else ->recyclerViewApplyLayoutManager(recyclerView, 3)
        }
    }

    protected fun setSearchViewListener(pokemons:List<Pokemon>) {

        val searchView = getSearchViewId()

        searchView?.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                filterPokemons(newText, pokemons)
                return true
            }
        })
    }

    protected fun detailFragmentLandScape(dto: detailFragmentLandScapeDTO) {

        dto.detailFragContainerView.setVisibility(dto.visibility);

        dto.scrollView.setVisibility(dto.visibility);

        dto.listLayoutParams.matchConstraintPercentWidth = dto.constraintWidthPercentage;

        dto.listLayout.setLayoutParams(dto.listLayoutParams);
    }

    protected fun showLoading(loadingDTO: LoadingPokemonDTO) {
        setComposableContent(loadingDTO.composeView, PokemonDetailLoading( loadingDTO.pokemonAnimationDTO))

        when(loadingDTO.isLoading) {
            true -> {
                setShowLoadingVisibility(View.GONE, View.VISIBLE, loadingDTO)
            }
            else -> {
                setShowLoadingVisibility(View.VISIBLE, View.GONE, loadingDTO)
            }
        }
    }

   /* protected fun updateNetworkLostConnectionTest(status: NetworkStatus) {
        val pokemonList = getPokemonList();
        if (status == NetworkStatus.Lost || status == NetworkStatus.Unavailable || status == NetworkStatus.Losing) {
            if (pokemonList == null) {
                val callback = PokemonAlertDialogUtils.ConfirmationCallback {
                    val bundle = Bundle()
                    bundle.putBoolean(FragmentsTags.ARG_POKEMON_LIST_EMPTY, true)

                    val activity = activity as MainActivity?

                    activity!!.sendDataToFragment(FragmentsTags.TAG_FRAGMENTS_INTRO, bundle)
                    fragmentHelper.popStackBack(FragmentsTags.TAG_FRAGMENT_LIST)
                }
                PokemonAlertDialogUtils.showAlertDialog(
                    getString(R.string.poke_list_connection_lost_error), callback,
                    context
                )
            } else {
                PokemonAlertDialogUtils.showMessageAlert(
                    context,
                    getString(R.string.poke_list_connection_lost)
                )
            }
        }
    }*/

    private fun recyclerViewApplyLayoutManager(recyclerView: RecyclerView?, length: Int) {
        recyclerView?.apply {
            layoutManager = GridLayoutManager(context, length)
        }
    }

    private fun setShowLoadingVisibility(visibilityType1: Int, visibilityType2: Int, loadingDTO: LoadingPokemonDTO) {
        loadingDTO.composeView.setVisibility(visibilityType2)
        loadingDTO.recyclerView.setVisibility(visibilityType1)
        loadingDTO.searchBar.setVisibility(visibilityType1)
    }

    protected fun setGenerationsCard(generationCardDTO: GenerationCardDTO) {
        generationCardDTO.generationRecyclerView.setHasFixedSize(true)

        generationCardDTO.generationRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            adapter = generationCardDTO.generationCardAdapter
        }

        setGenerationRecyclerViewVisibility(View.VISIBLE, generationCardDTO.generationRecyclerView)
    }

     protected fun setGenerationRecyclerViewVisibility(visibility: Int, generationRecyclerView: RecyclerView) {
        generationRecyclerView.setVisibility(visibility)
    }

}

data class GenerationCardDTO (
    val generationRecyclerView: RecyclerView,
    val generationCardAdapter: GenerationCardAdapter)


data class detailFragmentLandScapeDTO (
    val visibility: Int,
    val constraintWidthPercentage: Float,
    val detailFragContainerView: View,
    val listLayoutParams: ConstraintLayout.LayoutParams,
    val listLayout: View,
    val scrollView: ScrollView
)

data class LoadingPokemonDTO(
    val isLoading: Boolean,
    val composeView: ComposeView,
    val recyclerView: RecyclerView,
    val searchBar: SearchView,
    val loadingText: String,
    val pokemonAnimationDTO: PokemonLoadingDTO
    )