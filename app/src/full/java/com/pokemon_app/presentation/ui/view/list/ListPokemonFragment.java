package com.pokemon_app.presentation.ui.view.list;


import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.content.res.Configuration;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.compose.ui.platform.ComposeView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.SearchView;

import android.widget.ScrollView;
import android.widget.TextView;

import com.pokemon_app.R;
import com.pokemon_app.domain.model.Generation;

import com.pokemon_app.domain.model.Pokemon;
import com.pokemon_app.interactions.GenericAction;
import com.pokemon_app.interactions.GenericStates;
import com.pokemon_app.presentation.adapter.GenerationCardAdapter;
import com.pokemon_app.presentation.adapter.PokeCardAdapter;
import com.pokemon_app.presentation.ui.base.BasePokemonListFragment;
import com.pokemon_app.presentation.ui.base.GenerationCardDTO;
import com.pokemon_app.presentation.ui.view.detail.DetailPokemonFragment;
import com.pokemon_app.presentation.viewmodel.ListPokemonViewModel;
import com.pokemon_app.utils.FragmentHelper;
import com.pokemon_app.utils.FragmentsTags;
import com.pokemon_app.utils.PokemonAlertDialogUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class ListPokemonFragment extends BasePokemonListFragment
        implements PokeCardAdapter.OnPokemonCardClicked, GenerationCardAdapter.OnGenerationClicked{
    private PokeCardAdapter pokeCardAdapter;
    private ListPokemonViewModel pokemonViewModel;
    private SearchView searchBar;
    private TextView tvNoPokemonFound;
    private ComposeView composeView;
    private ConstraintLayout.LayoutParams listLayoutParams;
    View detailFragContainerView, listLayout;
    ScrollView scrollView;
    private Boolean isLandScape = false;
    private RecyclerView recyclerView, generationRecyclerView;
    DetailPokemonFragment detailPokemonFragment;
    private FragmentHelper fragmentHelper;
    List<Pokemon> pokemonList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            view = inflater.inflate(R.layout.fragment_list_pokemon_land, container, false);
        } else {
            view = inflater.inflate(R.layout.fragment_list_pokemon, container, false);
        }
        initialize(view);
        return view;
    }

    private void initialize(View view) {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            isLandScape = true;
        }

        searchBar = view.findViewById(R.id.searchBarView);

        tvNoPokemonFound = view.findViewById(R.id.tvNoPokemonFound);

        recyclerView = view.findViewById(R.id.pokemonRecyclerView);

        composeView = view.findViewById(R.id.listFragLoadingDataComposeView);

        generationRecyclerView = view.findViewById(R.id.recyclerGeneration);

        listLayout = view.findViewById(R.id.layout_include_list);

        detailFragContainerView = view.findViewById(R.id.layout_include_detail);

        fragmentHelper = new FragmentHelper(getActivity().getSupportFragmentManager());
        detailPokemonFragment = new DetailPokemonFragment();
        pokemonViewModel = new ViewModelProvider(requireActivity()).get(ListPokemonViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setRecyclerViewLayout();
        setPokemonLifeObserver();
    }
    private void setPokemonLifeObserver() {
        pokemonViewModel.getState().observe(getViewLifecycleOwner(), state -> {
            if (state instanceof GenericStates.ShowLoading) {

                showLoading(pokemonViewModel.GetShowLoadingDTO( ( (GenericStates.ShowLoading) state).isLoading(),
                        composeView, recyclerView, searchBar, getString(R.string.loading_data),
                        getResources().getInteger(R.integer.pokemon_animation_size),
                        R.raw.pokeball_animation,
                        R.color.my_color_brown
                        ));


            } else if (state instanceof GenericStates.ListPokemons) {
                GenericStates.ListPokemons listState = (GenericStates.ListPokemons) state;

                pokemonList = listState.getPokemons();

                showPokemonsList(listState.getPokemons(), listState.getError());

            } else if (state instanceof GenericStates.SearchPokemons) {
                updateSearchResult(((GenericStates.SearchPokemons) state).getFilteredPokemons());

            } else if (state instanceof GenericStates.NetworkConnection) {
                //updateNetworkLostConnectionTest(((GenericStates.NetworkConnection) state).getStatus());
            }
        });

        pokemonViewModel.interaction(GenericAction.PokemonAction.LoadPokemons.INSTANCE);
    }
    @Override
    public void onFragmentDataReceive(@NonNull Bundle data) {
        super.onFragmentDataReceive(data);
    }

    @Override
    public void onClick(Pokemon pokemon) {

        Bundle bundle = new Bundle();

        DetailPokemonFragment detailPokemonFragment = new DetailPokemonFragment();

        bundle.putSerializable(getContext().getString(R.string.pokemon_key), pokemon);

        detailPokemonFragment.setArguments(bundle);

        if (isLandScape) {

            scrollView = getView().findViewById(R.id.scroll);

            fragmentHelper.replaceFragment(R.id.layout_include_detail, detailPokemonFragment, false, FragmentsTags.TAG_FRAGMENT_DETAILS);

            listLayoutParams = (ConstraintLayout.LayoutParams) listLayout.getLayoutParams();

            detailFragmentLandScape((pokemonViewModel.GetdetailFragmentLandScapeDTO(GONE, 1f,
                    detailFragContainerView,listLayoutParams, listLayout,scrollView)));

        } else {
            fragmentHelper.replaceFragment(R.id.mainFrag, detailPokemonFragment, true, FragmentsTags.TAG_FRAGMENT_DETAILS);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();

        if (actionBar != null) {
            actionBar.setTitle(getContext().getString(R.string.list_pokemon_app_name));
        }
    }

    @Override
    public void onClick(int generationId) {
        setGenerationRecyclerViewVisibility(GONE, recyclerView);

        if(isLandScape && detailFragContainerView.getVisibility() == VISIBLE) {
            detailFragmentLandScape(pokemonViewModel.GetdetailFragmentLandScapeDTO(GONE, 1f,
                    detailFragContainerView,listLayoutParams, listLayout,scrollView));
        }
        pokemonViewModel.interaction(new GenericAction.ListPokemonAction.PokemonListByChosenGeneration(generationId));
    }


//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    @Override
    protected void filterPokemons(@Nullable String query, @NonNull List<Pokemon> pokemons) {
        pokemonViewModel.interaction(new GenericAction.PokemonAction.SearchPokemons(query, pokemons));
    }

    @Override
    protected RecyclerView getRecyclerViewId() {
        return recyclerView;
    }

    @Override
    protected SearchView getSearchViewId() {
        return searchBar;
    }

    @Override
    protected boolean getLandscapeMode() {
        return isLandScape;
    }

    @Override
    protected void showPokemonsList(@NonNull List<Pokemon> pokemons, String error) {
        if ((pokemons == null || pokemons.isEmpty()) && error != null) {
            PokemonAlertDialogUtils.showMessageAlert(getContext(), error);
        } else {
            pokeCardAdapter = new PokeCardAdapter(this, new ArrayList<>(pokemons));

            recyclerView.setAdapter(pokeCardAdapter);

            setGenerationsCard(pokemonViewModel.GetGenerationCardDTO(generationRecyclerView, new GenerationCardAdapter(Arrays.asList(Generation.values()), this)));

            setSearchViewListener(pokemons);
        }
    }

    @Override
    protected void updateSearchResult(List<Pokemon> filteredPokemons) {
        if (pokeCardAdapter != null) {
            pokeCardAdapter.updateList(filteredPokemons);
        }

        if (filteredPokemons.isEmpty()) {
            recyclerView.setVisibility(GONE);
            tvNoPokemonFound.setVisibility(VISIBLE);
        } else {
            tvNoPokemonFound.setVisibility(GONE);
            recyclerView.setVisibility(VISIBLE);
        }
    }

    @NonNull
    @Override
    protected List<Pokemon> getPokemonList() {
        return pokemonList;
    }
}
