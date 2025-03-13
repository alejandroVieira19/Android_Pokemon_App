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

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.SearchView;

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
    SearchView searchBarContainer;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    OnButtonClicked activity;

    public interface OnButtonClicked {
        void onButtonClickedToChangeFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        activity = (OnButtonClicked) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_pokemon, container, false);

        searchBarContainer = (SearchView) view.findViewById(R.id.searchBarView);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setRecyclerViewLayout(view);

        setPokemonLifeObserver();

    }

    private void setSuggestionsInSearchBar(List<Pokemon> pokemons) {

        searchBarContainer.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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
                pokeCardAdapter.updateList(filteredPokemons);
            }
        });

    }

    private void setPokemonLifeObserver() {
        // Initialize ViewModel
        pokemonViewModel = new ViewModelProvider(requireActivity()).get(PokemonViewModel.class);

        pokemonViewModel.getPokemonsData().observe(getViewLifecycleOwner(), pokemonData -> {

            if (pokemonData != null) {
                if (pokemonData.isLoading()) {
                    // TODO ----> COLOCAR O LOADING PROGRESSOR
                } else {
                    if (pokemonData.getPokemons() != null) {
                        // associar a lista ao PokeCardAdapter
                        pokeCardAdapter = new PokeCardAdapter(getContext(), (ArrayList<Pokemon>) pokemonData.getPokemons());
                        recyclerView.setAdapter(pokeCardAdapter);
                        setSuggestionsInSearchBar(pokemonData.getPokemons());
                    } else if (pokemonData.getError() != null) {
                        // Show error if exists
                        Toast.makeText(getContext(), pokemonData.getError(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        pokemonViewModel.fetchAllPokemons(10); // Fetch 151 pokemons
    }
    private void setRecyclerViewLayout(@NonNull View view) {
        recyclerView = view.findViewById(R.id.pokemonRecyclerView);
        recyclerView.setHasFixedSize(true);

        // definir que o layout será em grid ( em 2)
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);

        // criar um layoutManager para definir como os dados serão exibidos na tela
        //usando o this.getActivity, que simplesmente passa o contexto da actividade ( a tela onde esta o recycler view (list_frag.xml)).
        layoutManager = new LinearLayoutManager(this.getActivity());

        recyclerView.setLayoutManager(gridLayoutManager);
    }
}