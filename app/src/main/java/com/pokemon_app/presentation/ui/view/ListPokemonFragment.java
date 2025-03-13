package com.pokemon_app.presentation.ui.view;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.pokemon_app.R;
import com.pokemon_app.domain.model.Pokemon;
import com.pokemon_app.presentation.adapter.PokeCardAdapter;
import com.pokemon_app.presentation.viewmodel.PokemonViewModel;

import java.util.ArrayList;


public class ListPokemonFragment extends Fragment {

    PokeCardAdapter pokeCardAdapter;
    private PokemonViewModel pokemonViewModel;

    RecyclerView recyclerView;

    RecyclerView.LayoutManager layoutManager;
    Button changeFragmentBtn;
    OnButtonClicked activity;

   public interface OnButtonClicked {
        void onButtonClickedToChangeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_pokemon, container, false);

        LinearLayout searchBarContainer = view.findViewById(R.id.searchBarContainer);

        inflater.inflate(R.layout.search_bar, searchBarContainer, true);

        recyclerView = view.findViewById(R.id.pokemonRecyclerView);
        recyclerView.setHasFixedSize(true);

        // Set GridLayoutManager with 2 columns
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(gridLayoutManager);


        // Initialize ViewModel
        pokemonViewModel = new ViewModelProvider(requireActivity()).get(PokemonViewModel.class);


        pokemonViewModel.getPokemonsData().observe(getViewLifecycleOwner(), pokemonData -> {

            if (pokemonData != null) {

                if (pokemonData.isLoading()) {

                    Log.d("Frag", "Carregando");
                } else {
                    if (pokemonData.getPokemons() != null) {
                        // Set the adapter with the pokemons data
                        pokeCardAdapter = new PokeCardAdapter(getContext(), (ArrayList<Pokemon>) pokemonData.getPokemons());
                        recyclerView.setAdapter(pokeCardAdapter);
                    } else if (pokemonData.getError() != null) {
                        // Show error if exists
                        Toast.makeText(getContext(), pokemonData.getError(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        pokemonViewModel.fetchAllPokemons(10); // Fetch 151 pokemons


        return view;
    }



    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        activity = (OnButtonClicked) context;
    }
}