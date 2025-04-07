package com.pokemon_app.presentation.viewmodel
import android.content.Context
import android.view.View
import android.widget.ScrollView
import androidx.appcompat.widget.SearchView
import androidx.compose.ui.platform.ComposeView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.pokemon_app.interactions.GenericAction
import com.pokemon_app.interactions.GenericStates
import com.pokemon_app.presentation.adapter.GenerationCardAdapter
import com.pokemon_app.presentation.ui.base.GenerationCardDTO
import com.pokemon_app.presentation.ui.base.LoadingPokemonDTO
import com.pokemon_app.presentation.ui.base.detailFragmentLandScapeDTO
import com.pokemon_app.presentation.ui.view.composable.geral.PokemonLoadingDTO
import com.pokemon_app.utils.PokemonService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListPokemonViewModel @Inject constructor(
    private val pokemonService: PokemonService, private val context : Context
) : GenericPokemonViewModel(pokemonService, context) {

    override fun interaction(action: GenericAction) {
        when(action) {
            is GenericAction.ListPokemonAction.PokemonListByChosenGeneration -> loadPokemonListByGeneration(action.genNumber)
            else -> super.interaction(action)
        }

    }

    private fun loadPokemonListByGeneration(genNumber: Int) {
        genericStateLoading(true)
            viewModelScope.launch {
                try{

                    _pokemonsList.clear()

                    delay(3000)

                    val result = pokemonService.getPokemonByChosenGeneration(genNumber)

                    _state.value = GenericStates.ListPokemons(pokemons = result)

                    _pokemonsList.addAll(result)

                } catch (e:Exception) {
                    _state.value = GenericStates.ListPokemons(error= e.message)
                }finally {
                    genericStateLoading(false)
                }

            }
        }

    fun GetdetailFragmentLandScapeDTO(
        visibility: Int,
        widthPercentage: Float,
        detailFragContainerView: View,
        listLayoutParams: ConstraintLayout.LayoutParams,
        listLayout: View,
        scrollView: ScrollView
    ): detailFragmentLandScapeDTO {

        return detailFragmentLandScapeDTO(
            visibility = visibility,
            constraintWidthPercentage = widthPercentage,
            detailFragContainerView = detailFragContainerView,
            listLayoutParams = listLayoutParams,
            listLayout = listLayout,
            scrollView = scrollView
        )

    }

    fun GetPokemonLoadingAnimationDTO(string: String, int: Int, pokeball_animation: Int, textColor: Int): PokemonLoadingDTO {
       return PokemonLoadingDTO(string, int, pokeball_animation, textColor)
    }

    fun GetShowLoadingDTO(
        loading: Boolean,
        composeView: ComposeView,
        recyclerView: RecyclerView,
        searchBar: SearchView,
        message: String,
        integer: Int,
        pokeball_animation: Int,
        textColor: Int
    ): LoadingPokemonDTO {
        return LoadingPokemonDTO(
            isLoading = loading,
            composeView = composeView,
            recyclerView = recyclerView,
            searchBar = searchBar,
            loadingText = message,
            pokemonAnimationDTO = GetPokemonLoadingAnimationDTO(message, integer, pokeball_animation, textColor )
        )
    }

    fun GetGenerationCardDTO(recyclerView: RecyclerView, generationCardAdapter: GenerationCardAdapter): GenerationCardDTO {
        return GenerationCardDTO(
            recyclerView,
            generationCardAdapter
        )

    }
}
