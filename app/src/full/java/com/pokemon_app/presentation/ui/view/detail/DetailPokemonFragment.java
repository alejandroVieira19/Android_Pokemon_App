package com.pokemon_app.presentation.ui.view.detail;



import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.compose.ui.platform.ComposeView;
import androidx.compose.ui.platform.ViewCompositionStrategy;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.pokemon_app.R;
import com.pokemon_app.databinding.FragmentDetailPokemonBinding;
import com.pokemon_app.domain.model.Pokemon;
import com.pokemon_app.interactions.GenericAction;
import com.pokemon_app.interactions.GenericStates;
import com.pokemon_app.interactions.PokeDbEnum;
import com.pokemon_app.presentation.ui.view.composable.detail.PokeDetailKt;

import com.pokemon_app.presentation.ui.view.composable.detail.PokemonDetailImage;
import com.pokemon_app.presentation.ui.view.composable.detail.PokemonImage;
import com.pokemon_app.presentation.ui.view.composable.manager.ComposeViewManager;
import com.pokemon_app.presentation.viewmodel.DetailPokemonViewModel;
import com.pokemon_app.utils.FragmentHelper;
import com.pokemon_app.utils.FragmentsTags;
import com.pokemon_app.utils.PokemonAlertDialogUtils;
import com.pokemon_app.utils.StringUtils;


public class DetailPokemonFragment extends Fragment {
    private FragmentDetailPokemonBinding binding;
    DetailPokemonViewModel pokemonViewModel;

    AboutMeDetailedPokemonFragment aboutMeDetailedPokemonFragment;

    PokemonMovesDetailedFragment pokemonMovesDetailedFragment;

    PokemonStatsDetailedFragment pokemonStatsDetailedFragment;
    FragmentHelper fragmentHelper;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDetailPokemonBinding.inflate(inflater, container, false);

        // GARANTIR QUE O FRAGMENTO ESTEJA VINCULADO AO LAYOUT!!!!!
        binding.setDetailPokemonFragment(this);

        fragmentHelper = new FragmentHelper(getActivity().getSupportFragmentManager());



        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        Pokemon pokemon = null;

        pokemonViewModel = new ViewModelProvider(requireActivity()).get(DetailPokemonViewModel.class);

        if (bundle != null) {
            pokemon = (Pokemon) bundle.getSerializable(getContext().getString(R.string.pokemon_key));
            updatePokemonIsFavoriteImage(pokemon.isPokemonFavorite());
            setPokemonViewModelObserver(pokemon);
        }



        Pokemon finalPokemon = pokemon;
        binding.ivDetailFavoritePokemonIcon.setOnClickListener(new View.OnClickListener() {
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
            binding.allDataInLayout.setVisibility(View.INVISIBLE);
            binding.detailRelativeLayout.setVisibility(View.VISIBLE);

            if (pokeEnum.equals(PokeDbEnum.SAVE)) {
                binding.loadingText.setText(getContext().getString(R.string.saving_in_db));
            } else {
                binding.loadingText.setText(getContext().getString(R.string.delete_from_db));
            }

        } else {
            binding.detailRelativeLayout.setVisibility(View.GONE);
            binding.allDataInLayout.setVisibility(View.VISIBLE);
        }
    }

    private void updateDetailView(GenericStates.PokemonDetail pokemonDetailState, Pokemon pokemon) {

        setPokemonBackGroundColor(pokemonDetailState.getPokemonBackgroundColor());

        setPokemonFragmentTextColor(pokemonDetailState.getPokemonTextColor());

        loadPokemonImage(binding.pokemonDetailImageCompose, pokemon.getPokemonDetailImageUrlBackground());

        setPokemonName(pokemon.getPokemonName());

        instanciateFragments(pokemonDetailState);

        replaceFragment(aboutMeDetailedPokemonFragment, false, FragmentsTags.TAG_FRAGMENTS_POKEMON_ABOUT_ME);

    }

    private void setPokemonFragmentTextColor(Integer pokemonTextColor) {
        binding.tvPokemonName.setTextColor(ContextCompat.getColor(getContext(),pokemonTextColor));
        binding.textAboutMe.setTextColor(ContextCompat.getColor(getContext(),pokemonTextColor));
        binding.movesBtn.setTextColor(ContextCompat.getColor(getContext(),pokemonTextColor));
        binding.statsBtn.setTextColor(ContextCompat.getColor(getContext(),pokemonTextColor));
    }

    private void setPokemonName(String pokemonName) {
        binding.tvPokemonName.setText(pokemonName);
    }

    private void setPokemonBackGroundColor(Integer pokemonBackgroundColor) {
        binding.detailFragmentLayout.setBackgroundColor(pokemonBackgroundColor);
    }

    private void loadPokemonImage(ComposeView ivDetailPokemonImage, String pokemonDetailImageUrlBackground) {

        ComposeViewManager.INSTANCE.setComposableContent(
                ivDetailPokemonImage,
                new PokemonImage(pokemonDetailImageUrlBackground)
        );
    }

    private void replaceFragment(Fragment fragment, boolean addToBackStack, String tag) {
        fragmentHelper.replaceFragment(R.id.fragmentContainerView, fragment, addToBackStack, tag);
    }

    private void instanciateFragments(GenericStates.PokemonDetail pokemonDetailState) {

        aboutMeDetailedPokemonFragment = new AboutMeDetailedPokemonFragment().newInstance(pokemonDetailState);

        pokemonMovesDetailedFragment = new PokemonMovesDetailedFragment().newInstance(pokemonDetailState);

        pokemonStatsDetailedFragment = new PokemonStatsDetailedFragment().newInstance(pokemonDetailState);

    }

    public void onMovesClick() {
        updateSelectedButton(binding.movesBtn, binding.textAboutMe, binding.statsBtn);
        replaceFragment(pokemonMovesDetailedFragment, false,  FragmentsTags.TAG_FRAGMENTS_POKEMON_MOVES);

    }

    public void onAboutMeClick() {
        updateSelectedButton(binding.textAboutMe, binding.movesBtn, binding.statsBtn);
        replaceFragment(aboutMeDetailedPokemonFragment, false, FragmentsTags.TAG_FRAGMENTS_POKEMON_ABOUT_ME);
    }

    public void onStatsClick() {
        updateSelectedButton(binding.statsBtn, binding.movesBtn, binding.textAboutMe);
        replaceFragment(pokemonStatsDetailedFragment, false, FragmentsTags.TAG_FRAGMENTS_POKEMON_STATS);
    }

    private void updateSelectedButton(View selected, View... others) {
        selected.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.card_background_blur));
        for (View other : others) {
            other.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.card_background_transparent));
        }
    }

    private void updatePokemonIsFavoriteImage(Boolean isPokemonFavorite) {
        if (isPokemonFavorite) {
            binding.ivDetailFavoritePokemonIcon.setImageResource(android.R.drawable.btn_star_big_on);
        } else {
            binding.ivDetailFavoritePokemonIcon.setImageResource(android.R.drawable.star_off);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();

        if (actionBar != null) {
            actionBar.setTitle(getContext().getString(R.string.detail_pokemon_app_name));
        }
    }
}

