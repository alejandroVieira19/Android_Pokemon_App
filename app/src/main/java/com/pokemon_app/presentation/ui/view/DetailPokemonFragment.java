package com.pokemon_app.presentation.ui.view;

import static com.pokemon_app.utils.Config.POKEMON_NAME_KEY;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pokemon_app.R;
import com.pokemon_app.databinding.FragmentDetailPokemonBinding;
import com.pokemon_app.domain.model.Pokemon;
import com.pokemon_app.interactions.GenericAction;
import com.pokemon_app.interactions.GenericStates;
import com.pokemon_app.presentation.viewmodel.DetailPokemonViewModel;
import com.pokemon_app.utils.Config;
import com.pokemon_app.utils.FragmentHelper;
import com.pokemon_app.utils.FragmentsTags;
import com.pokemon_app.utils.PokemonAlertDialogUtils;


public class DetailPokemonFragment extends Fragment implements PokemonAlertDialogUtils.ConfirmationCallback {
    private FragmentDetailPokemonBinding binding;
    TextView aboutMeBtn, movesBtn;
    ImageView ivFavoritePokemonIcon;
    LinearLayout detailFragmentLayout;
    Pokemon pokemon;
    DetailPokemonViewModel pokemonViewModel;

    FragmentHelper fragmentHelper;

    PokemonAlertDialogUtils pokemonAlertDialogUtils;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       binding = FragmentDetailPokemonBinding.inflate(inflater, container, false);

       // GARANTIR QUE O FRAGMENTO ESTEJA VINCULADO AO LAYOUT!!!!!
       binding.setDetailPokemonFragment(this);

       fragmentHelper = new FragmentHelper(getActivity().getSupportFragmentManager());

        fragmentHelper.replaceFragment(R.id.fragmentContainerView, new AboutMeDetailedPokemonFragment(), false, FragmentsTags.TAG_FRAGMENTS_POKEMON_ABOUT_ME);

        initializateDetailFrag();

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();


        if (bundle != null) {
            pokemon = (Pokemon) bundle.getSerializable(POKEMON_NAME_KEY);
            updatePokemonIsFavoriteImage();
            setPokemonViewModelObserver(pokemon);}
    }

    @Override
    public void onStart() {
        super.onStart();
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();

        if (actionBar != null) {
            actionBar.setTitle(Config.DETAIL_POKEMON_APP_NAME);
        }
    }

    private void setPokemonViewModelObserver(Pokemon pokemon) {

        pokemonViewModel.getState().observe(getViewLifecycleOwner(), state -> {

           if(state instanceof GenericStates.PokemonDetail) {

               GenericStates.PokemonDetail pokemonDetailState = (GenericStates.PokemonDetail) state;

               updateDetailView(pokemonDetailState);
           } else if (state instanceof GenericStates.PokemonFavorite) {
               Log.d("setPokemonViewModelObserver","o Pokemon foi marcado na db");
               Log.d("setPokemonViewModelObserver", String.valueOf(pokemon.isPokemonFavorite()));
           }
       });

        pokemonViewModel.interaction(new GenericAction.DetailPokemonAction.PokemonDetail(pokemon));
    }

    private void updateDetailView(GenericStates.PokemonDetail pokemonDetailState) {

        binding.detailFragmentLayout.setBackgroundColor(pokemonDetailState.getPokemonBackgroundColor());

        Glide.with(binding.ivDetailPokemonImage)
                .load(pokemon.getPokemonDetailImageUrlBackground())
                .centerCrop().into(binding.ivDetailPokemonImage);

        binding.tvPokemonName.setText(pokemon.getPokemonName());
    }

    private void initializateDetailFrag() {

        aboutMeBtn = binding.textAboutMe;

        detailFragmentLayout = binding.detailFragmentLayout;

        movesBtn = binding.movesBtn;

        ivFavoritePokemonIcon = binding.ivDetailFavoritePokemonIcon;

        pokemonAlertDialogUtils = new PokemonAlertDialogUtils(getContext(), this);

        pokemonViewModel = new ViewModelProvider(requireActivity()).get(DetailPokemonViewModel.class);

    }

    public void onMovesClick() {
        aboutMeBtn.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.card_background_transparent));
        movesBtn.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.card_background_blur));
        fragmentHelper.replaceFragment(R.id.fragmentContainerView, new PokemonMovesDetailedFragment(), false, FragmentsTags.TAG_FRAGMENTS_POKEMON_MOVES);
    }

    public void onAboutMeClick() {
        movesBtn.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.card_background_transparent));
        aboutMeBtn.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.card_background_blur));
        fragmentHelper.replaceFragment(R.id.fragmentContainerView, new AboutMeDetailedPokemonFragment(), false, FragmentsTags.TAG_FRAGMENTS_POKEMON_ABOUT_ME);
    }

    public void onFavoritePokemonToggle() {
        if(!pokemon.isPokemonFavorite()) {
            pokemonAlertDialogUtils.showAlertDialog("Are you sure you want to save " + pokemon.getPokemonName() + " as your pokefavorite");
        } else {
            pokemonAlertDialogUtils.showAlertDialog("Are you sure you want to remove " + pokemon.getPokemonName() + " as your pokefavorite");
        }
    }

    @Override
    public void onConfirm() {
        if(!pokemon.isPokemonFavorite()) {
            pokemonViewModel.interaction(new GenericAction.PokemonAction.SaveFavoritePokemon(pokemon));
        } else {
            // TODO ----> PARA APAGAR
            //pokemonViewModel.interaction(new GenericAction.DetailPokemonAction.SaveFavoritePokemon(pokemon));
        }

        updatePokemonIsFavoriteImage();
    }

    private void updatePokemonIsFavoriteImage() {
        if(pokemon.isPokemonFavorite()) {
            ivFavoritePokemonIcon.setImageResource(android.R.drawable.btn_star_big_on);
        } else {
            ivFavoritePokemonIcon.setImageResource(android.R.drawable.star_off);
        }
    }
}