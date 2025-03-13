package com.pokemon_app.presentation.ui.view;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.pokemon_app.presentation.adapter.PokeCardAdapter;
import com.pokemon_app.presentation.viewmodel.PokemonViewModel;
import java.util.ArrayList;
import java.util.List;


public class ListPokemonFragment extends Fragment {
    PokeCardAdapter pokeCardAdapter;
    PokemonViewModel pokemonViewModel;
    SearchView searchBar;
    TextView tvNoPokemonFound;
    ProgressBar progressBar;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list_pokemon, container, false);

        initializate(view);



        return view;
    }

    private void initializate(View view) {
        searchBar = (SearchView) view.findViewById(R.id.searchBarView);

        progressBar = view.findViewById(R.id.loading_progress);

        tvNoPokemonFound = view.findViewById(R.id.tvNoPokemonFound);

        recyclerView = view.findViewById(R.id.pokemonRecyclerView);

        layoutManager = new LinearLayoutManager(this.getActivity());

        // Initialize ViewModel
        pokemonViewModel = new ViewModelProvider(requireActivity()).get(PokemonViewModel.class);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        setRecyclerViewLayout(view);

        setPokemonLifeObserver();
    }

    private void setSuggestionsInSearchBar(List<Pokemon> pokemons) {

        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterPokemon(newText, pokemons);
                return true;
            }
            private void filterPokemon(String text, List<Pokemon> pokemons) {
                List<Pokemon> filteredPokemons = new ArrayList<>();
                for (Pokemon pokemon : pokemons) {
                    if (pokemon.getPokemonName().toLowerCase().contains(text.toLowerCase())) {
                        filteredPokemons.add(pokemon);
                    }
                }
                checkIfFilteredListIsEmpty(filteredPokemons);
                pokeCardAdapter.updateList(filteredPokemons);
            }
            private void checkIfFilteredListIsEmpty(List<Pokemon> filteredPokemons) {
                if(filteredPokemons.isEmpty()) {
                    recyclerView.setVisibility(View.GONE);
                    tvNoPokemonFound.setVisibility(View.VISIBLE);
                } else {
                    tvNoPokemonFound.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                }
            }
        });

    }
    private void setPokemonLifeObserver() {
        pokemonViewModel.getPokemonsData().observe(getViewLifecycleOwner(), pokemonData -> {

            if (pokemonData != null) {
                if (pokemonData.isLoading()) {
                    showLoading(true);
                } else {
                    showLoading(false);
                    if (pokemonData.getPokemons() != null) {
                        // associar a lista ao PokeCardAdapter
                        pokeCardAdapter = new PokeCardAdapter(getContext(), (ArrayList<Pokemon>) pokemonData.getPokemons());
                        recyclerView.setAdapter(pokeCardAdapter);
                        setSuggestionsInSearchBar(pokemonData.getPokemons());
                    } else if (pokemonData.getError() != null) {
                        Toast.makeText(getContext(), pokemonData.getError(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        pokemonViewModel.fetchAllPokemons(10); // Fetch 151 pokemons
    }

    private void showLoading(boolean isLoading) {
        if(isLoading) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    private void setRecyclerViewLayout(@NonNull View view) {

        recyclerView.setHasFixedSize(true);

        // definir que o layout será em grid ( em 2)
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);

        recyclerView.setLayoutManager(gridLayoutManager);
    }
}