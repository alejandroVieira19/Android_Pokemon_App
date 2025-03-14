package com.pokemon_app.presentation.ui.view;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.SearchView;

import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.pokemon_app.R;
import com.pokemon_app.domain.model.Pokemon;
import com.pokemon_app.interactions.GenericAction;
import com.pokemon_app.interactions.GenericStates;
import com.pokemon_app.presentation.adapter.PokeCardAdapter;
import com.pokemon_app.presentation.viewmodel.ListPokemonViewModel;
import com.pokemon_app.utils.ActionBarHelper;

import java.util.ArrayList;
import java.util.List;


public class ListPokemonFragment extends Fragment {

    private PokeCardAdapter pokeCardAdapter;
    private ListPokemonViewModel pokemonViewModel;
    private SearchView searchBar;
    private TextView tvNoPokemonFound, tvLoadingData;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private ActionBarHelper actionBarHelper;
    private Bundle bundle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_pokemon, container, false);
        initialize(view);
        return view;
    }

    private void initialize(View view) {
        searchBar = view.findViewById(R.id.searchBarView);
        progressBar = view.findViewById(R.id.loading_progress);
        tvNoPokemonFound = view.findViewById(R.id.tvNoPokemonFound);
        tvLoadingData = view.findViewById(R.id.tvLoadingData);
        recyclerView = view.findViewById(R.id.pokemonRecyclerView);
        actionBarHelper = new ActionBarHelper((AppCompatActivity) getActivity());
        bundle = new Bundle();
        pokemonViewModel = new ViewModelProvider(requireActivity()).get(ListPokemonViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setRecyclerViewLayout();
        setPokemonLifeObserver();
    }
    private void setRecyclerViewLayout() {
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
    }

    private void setPokemonLifeObserver() {
        pokemonViewModel.getState().observe(getViewLifecycleOwner(), state -> {

            if (state instanceof GenericStates.ShowLoading) {
                showLoading(((GenericStates.ShowLoading) state).isLoading());

            } else if (state instanceof GenericStates.ListPokemons) {
                GenericStates.ListPokemons listState = (GenericStates.ListPokemons) state;
                showPokemonsList(listState.getPokemons(), listState.getError());

            } else if (state instanceof GenericStates.SearchPokemons) {
                updateSearchResult(((GenericStates.SearchPokemons) state).getFilteredPokemons());
            }

        });

        // Iniciar carregamento dos pokémons
        pokemonViewModel.interaction(new GenericAction.PokemonAction.LoadPokemons(10));
    }

    private void showLoading(boolean isLoading) {
        if (isLoading) {
            progressBar.setVisibility(View.VISIBLE);
            tvLoadingData.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
            searchBar.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.GONE);
            tvLoadingData.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            searchBar.setVisibility(View.VISIBLE);
        }
    }

    private void showPokemonsList(List<Pokemon> pokemons, String error) {
        if ((pokemons == null || pokemons.isEmpty()) && error != null) {
            Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
        } else {
            pokeCardAdapter = new PokeCardAdapter(getContext(), new ArrayList<>(pokemons));
            recyclerView.setAdapter(pokeCardAdapter);
            setSuggestionsInSearchBar(pokemons);
        }
    }

    // Atualizar resultados da busca
    private void updateSearchResult(List<Pokemon> filteredPokemons) {
        if (pokeCardAdapter != null) {
            pokeCardAdapter.updateList(filteredPokemons);
        }

        if (filteredPokemons.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            tvNoPokemonFound.setVisibility(View.VISIBLE);
        } else {
            tvNoPokemonFound.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

    private void setSuggestionsInSearchBar(List<Pokemon> pokemons) {
        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                pokemonViewModel.interaction(new GenericAction.ListPokemonAction.SearchPokemons(newText, pokemons));
                return true;
            }
        });
    }

    // Atualizar ActionBar ao exibir Fragment
    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden) {
            actionBarHelper.changeActionBarTitleAndShowArrowBack("PokeList", true);
        }
        super.onHiddenChanged(hidden);
    }
}
