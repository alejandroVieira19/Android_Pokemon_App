package com.pokemon_app.presentation.ui.view.intro;


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

import com.pokemon_app.R;
import com.pokemon_app.databinding.FragmentPokemonIntroScreenBinding;
import com.pokemon_app.domain.service.ConnectivityObserver;
import com.pokemon_app.interactions.GenericAction;
import com.pokemon_app.interactions.GenericStates;
import com.pokemon_app.presentation.ui.view.GenericFragment;
import com.pokemon_app.presentation.ui.view.list.ListPokemonFragment;
import com.pokemon_app.presentation.ui.view.favorite.MyFavoritesPokemonFragment;
import com.pokemon_app.presentation.viewmodel.GenericPokemonViewModel;
import com.pokemon_app.utils.ActionBarHelper;
import com.pokemon_app.utils.FragmentHelper;
import com.pokemon_app.utils.FragmentsTags;
import com.pokemon_app.utils.PokemonAlertDialogUtils;



/**
 * Fragmento que exibe a tela de introdução do Pokémon.
 * Ele contém dois botões: "Get Started" e "My Favorites".
 * Ao clicar em cada um, ele substitui o fragmento atual com novos fragmentos.
 */

public class PokemonIntroductionScreen extends GenericFragment {
    private FragmentPokemonIntroScreenBinding binding;
    FragmentHelper fragmentHelper;

    ActionBarHelper actionBarHelper;

    GenericPokemonViewModel genericPokemonViewModel;


    /**
     * Cria e retorna a vista do fragmento, configurando os botões e o FragmentHelper.
     *
     * @param inflater O LayoutInflater para inflar a interface do fragmento
     * @param container O container onde o fragmento será inserido
     * @param savedInstanceState O estado salvo, se houver
     * @return A vista inflada do fragmento
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentPokemonIntroScreenBinding.inflate(inflater, container, false);

        binding.setIntroScreenFrag(this);

        actionBarHelper = new ActionBarHelper((AppCompatActivity) getActivity());

        fragmentHelper = new FragmentHelper(getActivity().getSupportFragmentManager());

        genericPokemonViewModel = new ViewModelProvider(requireActivity()).get(GenericPokemonViewModel.class);

        return binding.getRoot();
    }


    @Override
    public void onStart() {
        super.onStart();
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();

        if (actionBar != null) {
            actionBar.setTitle(getString(R.string.poke_explorer_app));
        }
    }
    private void setVisibilityInGetStartedBtn(int visibility) {
        binding.getStartedBtn.setVisibility(visibility);
    }

    /**
     * Método chamado quando o botão "Get Started" é clicado.
     * Substitui o fragmento atual pelo fragmento de lista de Pokémon.
     */
    public void onGetStartedClick() {
        AddArrowBackInFragment(R.id.mainFrag, new ListPokemonFragment(), true, FragmentsTags.TAG_FRAGMENT_LIST);
    }



    // TODO ----> TALVEZ TERÁ QUE SER ALGO MAIS GENERICO
    private void AddArrowBackInFragment(int id, Fragment classToUse, boolean isToAddInStack, String fragmentTag) {
        fragmentHelper.replaceFragment(id, classToUse, isToAddInStack, fragmentTag);
        if(isToAddInStack) {
            actionBarHelper.addArrowBackInFragment(isToAddInStack);
        }
    }
}