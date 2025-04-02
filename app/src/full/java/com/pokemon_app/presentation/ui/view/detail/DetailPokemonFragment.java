package com.pokemon_app.presentation.ui.view.detail;


import static com.pokemon_app.utils.FragmentsTags.TAG_FRAGMENTS_POKEMON_ABOUT_ME;
import static com.pokemon_app.utils.FragmentsTags.TAG_FRAGMENTS_POKEMON_MOVES;
import static com.pokemon_app.utils.FragmentsTags.TAG_FRAGMENTS_POKEMON_STATS;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.compose.ui.platform.ComposeView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pokemon_app.R;
import com.pokemon_app.databinding.FragmentDetailPokemonBinding;
import com.pokemon_app.domain.model.Pokemon;
import com.pokemon_app.interactions.GenericAction;
import com.pokemon_app.interactions.GenericStates;
import com.pokemon_app.interactions.PokeDbEnum;
import com.pokemon_app.interactions.PokemonTabList;
import com.pokemon_app.presentation.ui.view.composable.detail.FavoritePokemonIcon;

import com.pokemon_app.presentation.ui.view.composable.detail.PokemonDetailTaBar;
import com.pokemon_app.presentation.ui.view.composable.detail.PokemonDetailTabBarProps;
import com.pokemon_app.presentation.ui.view.composable.detail.PokemonImage;
import com.pokemon_app.presentation.ui.view.composable.manager.ComposableProvider;
import com.pokemon_app.presentation.ui.view.composable.manager.ComposeViewManager;
import com.pokemon_app.presentation.viewmodel.DetailPokemonViewModel;
import com.pokemon_app.utils.FragmentHelper;
import com.pokemon_app.utils.FragmentsTags;
import com.pokemon_app.utils.PokemonAlertDialogUtils;
import com.pokemon_app.utils.StringUtils;

import java.util.List;


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
        binding.favoriteIconCompose.setOnClickListener(new View.OnClickListener() {
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

        replaceFragment(aboutMeDetailedPokemonFragment, false, TAG_FRAGMENTS_POKEMON_ABOUT_ME);

    }

    private void setPokemonFragmentTextColor(Integer pokemonTextColor) {

        binding.tvPokemonName.setTextColor(ContextCompat.getColor(getContext(), pokemonTextColor));

        createPokemonDetailTab(pokemonTextColor);

    }

    private void createPokemonDetailTab(Integer pokemonTextColor) {
        List<PokemonTabList> tabBarList = pokemonViewModel.getPokemonTabBarList();
        setComposableContent(binding.pokemonDetailTabBarCompose, new PokemonDetailTaBar(
                new PokemonDetailTabBarProps(
                        tabBarList,
                        tabBarList.get(0),
                        pokemonTextColor,

                        selectedTab -> {
                            switch (selectedTab) {
                                case MOVES:
                                    replaceFragment(pokemonMovesDetailedFragment, false, TAG_FRAGMENTS_POKEMON_MOVES);
                                    break;

                                case STATS:
                                    replaceFragment(pokemonStatsDetailedFragment, false, TAG_FRAGMENTS_POKEMON_STATS);
                                    break;

                                default:
                                    replaceFragment(aboutMeDetailedPokemonFragment, false, TAG_FRAGMENTS_POKEMON_ABOUT_ME);
                                    break;
                            }
                            return null;
                        })
        ));
    }

    private void pokemonTabBarPropertiesFunction(){

    }

    private void setPokemonName(String pokemonName) {
        binding.tvPokemonName.setText(pokemonName);
    }

    private void setPokemonBackGroundColor(Integer pokemonBackgroundColor) {
        binding.detailFragmentLayout.setBackgroundColor(pokemonBackgroundColor);
    }

    private void loadPokemonImage(ComposeView ivDetailPokemonImage, String pokemonDetailImageUrlBackground) {

        setComposableContent(
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

    private void updatePokemonIsFavoriteImage(Boolean isPokemonFavorite) {
        if (isPokemonFavorite) {
            setComposableContent(binding.favoriteIconCompose, new FavoritePokemonIcon(android.R.drawable.btn_star_big_on));
        } else {
            setComposableContent(binding.favoriteIconCompose, new FavoritePokemonIcon(android.R.drawable.star_off));
        }
    }

    private void setComposableContent(ComposeView composeView, ComposableProvider classToUse) {
        ComposeViewManager.INSTANCE.setComposableContent(
                composeView,
                classToUse
        );
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

