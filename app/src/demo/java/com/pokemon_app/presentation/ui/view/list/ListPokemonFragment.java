package com.pokemon_app.presentation.ui.view.list;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.SearchView;

import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.pokemon_app.MainActivity;
import com.pokemon_app.R;
import com.pokemon_app.domain.model.Generation;

import com.pokemon_app.domain.model.Pokemon;
import com.pokemon_app.domain.service.ConnectivityObserver;
import com.pokemon_app.interactions.GenericAction;
import com.pokemon_app.interactions.GenericStates;
import com.pokemon_app.presentation.adapter.GenerationCardAdapter;
import com.pokemon_app.presentation.adapter.PokeCardAdapter;
import com.pokemon_app.presentation.ui.view.GenericFragment;
import com.pokemon_app.presentation.ui.view.detail.DetailPokemonFragment;
import com.pokemon_app.presentation.viewmodel.ListPokemonViewModel;
import com.pokemon_app.utils.FragmentHelper;
import com.pokemon_app.utils.FragmentsTags;
import com.pokemon_app.utils.PokemonAlertDialogUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ListPokemonFragment extends GenericFragment implements PokeCardAdapter.OnPokemonCardClicked{
    private PokeCardAdapter pokeCardAdapter;
    private ListPokemonViewModel pokemonViewModel;

    private TextView  tvLoadingData;
    private LottieAnimationView progressBar;
    private RecyclerView recyclerView;
    DetailPokemonFragment detailPokemonFragment;
    private FragmentHelper fragmentHelper;

    List<Pokemon> pokemonList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_pokemon, container, false);
        initialize(view);
        return view;
    }

    private void initialize(View view) {
        progressBar = view.findViewById(R.id.loading_progress);
        tvLoadingData = view.findViewById(R.id.tvLoadingData);
        recyclerView = view.findViewById(R.id.pokemonRecyclerView);

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

                pokemonList = listState.getPokemons();

                showPokemonsList(listState.getPokemons(), listState.getError());
            }
        });

        // Iniciar carregamento dos pokémons
        pokemonViewModel.interaction(GenericAction.PokemonAction.LoadPokemons.INSTANCE);
    }

    private void showLoading(boolean isLoading) {
        if (isLoading) {
            progressBar.setVisibility(View.VISIBLE);
            tvLoadingData.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);

        } else {
            progressBar.setVisibility(View.GONE);
            tvLoadingData.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

    private void showPokemonsList(List<Pokemon> pokemons, String error) {
        if ((pokemons == null || pokemons.isEmpty()) && error != null) {
            PokemonAlertDialogUtils.showMessageAlert(getContext(), error);
        } else {
            pokeCardAdapter = new PokeCardAdapter(this, new ArrayList<>(pokemons));
            recyclerView.setAdapter(pokeCardAdapter);
        }
    }

    @Override
    public void onClick(Pokemon pokemon) {

        Bundle bundle = new Bundle();

        bundle.putSerializable(getContext().getString(R.string.pokemon_key), pokemon);

        detailPokemonFragment.setArguments(bundle);

        fragmentHelper.replaceFragment(R.id.mainFrag, detailPokemonFragment, true, FragmentsTags.TAG_FRAGMENT_DETAILS);
    }

    @Override
    public void onStart() {
        super.onStart();
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();

        if (actionBar != null) {
            actionBar.setTitle(getContext().getString(R.string.list_pokemon_app_name));
        }
    }

}
