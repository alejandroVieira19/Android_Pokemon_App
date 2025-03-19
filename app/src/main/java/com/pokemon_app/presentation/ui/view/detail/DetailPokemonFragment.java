package com.pokemon_app.presentation.ui.view.detail;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

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
import com.pokemon_app.interactions.PokeDbEnum;
import com.pokemon_app.presentation.viewmodel.DetailPokemonViewModel;
import com.pokemon_app.utils.FragmentHelper;
import com.pokemon_app.utils.FragmentsTags;
import com.pokemon_app.utils.PokemonAlertDialogUtils;
import com.pokemon_app.utils.StringUtils;


public class DetailPokemonFragment extends Fragment {
    private FragmentDetailPokemonBinding binding;
    TextView aboutMeBtn, movesBtn;
    ImageView ivFavoritePokemonIcon;
    LinearLayout detailFragmentLayout;
    DetailPokemonViewModel pokemonViewModel;
    FragmentHelper fragmentHelper;

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
        Pokemon pokemon = null;

        if (bundle != null) {
            pokemon = (Pokemon) bundle.getSerializable(getContext().getString(R.string.pokemon_key));
            updatePokemonIsFavoriteImage(pokemon.isPokemonFavorite());
            setPokemonViewModelObserver(pokemon);
        }

        Pokemon finalPokemon = pokemon;
        ivFavoritePokemonIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PokemonAlertDialogUtils.ConfirmationCallback callback = new PokemonAlertDialogUtils.ConfirmationCallback() {
                    @Override
                    public void onConfirm() {
                        if (finalPokemon.isPokemonFavorite()) {
                            pokemonViewModel.interaction(new GenericAction.PokemonAction.DeleteFavoritePokemon(finalPokemon));
                        } else {
                            pokemonViewModel.interaction(new GenericAction.PokemonAction.SaveFavoritePokemon(finalPokemon));
                        }
                    }
                };
                if (finalPokemon.isPokemonFavorite()) {
                    PokemonAlertDialogUtils.
                            showAlertDialog(
                                    StringUtils.INSTANCE.buildMessage(getContext().getString(R.string.delete_question),
                                            finalPokemon.getPokemonName(),
                                            getContext().getString(R.string.as_your_pokeFavorite)), callback, getContext());
                } else {
                    PokemonAlertDialogUtils.
                            showAlertDialog(
                                    StringUtils.INSTANCE.buildMessage(getContext().getString(R.string.save_question),
                                            finalPokemon.getPokemonName(),
                                            getContext().getString(R.string.as_your_pokeFavorite)), callback, getContext());
                }
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();

        if (actionBar != null) {
            actionBar.setTitle(getContext().getString(R.string.detail_pokemon_app_name));
        }
    }

    private void setPokemonViewModelObserver(Pokemon pokemon) {

        pokemonViewModel.getState().observe(getViewLifecycleOwner(), state -> {

            if (state instanceof GenericStates.PokemonDetail) {

                GenericStates.PokemonDetail pokemonDetailState = (GenericStates.PokemonDetail) state;

                updateDetailView(pokemonDetailState, pokemon);
            } else if (state instanceof GenericStates.ShowLoadingForDB) {

                boolean isLoading = ((GenericStates.ShowLoadingForDB) state).isLoading();

                PokeDbEnum pokeDbEnum = ((GenericStates.ShowLoadingForDB) state).getPokeEnum();

                fragmentShowLoadingForDB(isLoading, pokeDbEnum);

            } else if (state instanceof GenericStates.PokemonDatabase) {

                updatePokemonIsFavoriteImage(((GenericStates.PokemonDatabase) state).getPokemonIsFavorite());

                String message = ((GenericStates.PokemonDatabase) state).getMessage();

                PokemonAlertDialogUtils.showMessageAlert(getContext(), message);
            }
        });

        pokemonViewModel.interaction(new GenericAction.DetailPokemonAction.PokemonDetail(pokemon));
    }

    private void fragmentShowLoadingForDB(Boolean loading, PokeDbEnum pokeEnum) {
        if (loading) {
            binding.detailRelativeLayout.setVisibility(View.VISIBLE);

            if (pokeEnum.equals(PokeDbEnum.SAVE)) {
                binding.loadingText.setText(getContext().getString(R.string.saving_in_db));
            } else {
                binding.loadingText.setText(getContext().getString(R.string.delete_from_db));
            }

        } else {
            binding.detailRelativeLayout.setVisibility(View.GONE);
        }
    }

    private void updateDetailView(GenericStates.PokemonDetail pokemonDetailState, Pokemon pokemon) {

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

    private void updatePokemonIsFavoriteImage(Boolean isPokemonFavorite) {
        if (isPokemonFavorite) {
            ivFavoritePokemonIcon.setImageResource(android.R.drawable.btn_star_big_on);
        } else {
            ivFavoritePokemonIcon.setImageResource(android.R.drawable.star_off);
        }
    }
}