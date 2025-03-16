package com.pokemon_app.presentation.ui.view;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pokemon_app.R;
import com.pokemon_app.databinding.FragmentPokemonIntroScreenBinding;
import com.pokemon_app.utils.ActionBarHelper;
import com.pokemon_app.utils.FragmentHelper;
import com.pokemon_app.utils.FragmentsTags;


/**
 * Fragmento que exibe a tela de introdução do Pokémon.
 * Ele contém dois botões: "Get Started" e "My Favorites".
 * Ao clicar em cada um, ele substitui o fragmento atual com novos fragmentos.
 */
public class PokemonIntroductionScreen extends Fragment {
    private FragmentPokemonIntroScreenBinding binding;
    FragmentHelper fragmentHelper;

    ActionBarHelper actionBarHelper;

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

        // serve para vincular a lógica do Fragmento com o layout de maneira dinâmica,
        binding.setIntroScreenFrag(this);

        // buscar a actividade para usar o actionBarHelper
        actionBarHelper = new ActionBarHelper((AppCompatActivity) getActivity());

        fragmentHelper = new FragmentHelper(getActivity().getSupportFragmentManager());

        return binding.getRoot();
    }

    /**
     * Método chamado quando o botão "Get Started" é clicado.
     * Substitui o fragmento atual pelo fragmento de lista de Pokémon.
     */
    public void onGetStartedClick() {
        AddArrowBackInFragment(R.id.mainFrag, new ListPokemonFragment(), true, FragmentsTags.TAG_FRAGMENT_LIST);
    }

    /**
     * Método chamado quando o botão "My Favorites" é clicado.
     * Substitui o fragmento atual pelo fragmento de favoritos de Pokémon.
     */
    public void onMyFavoritesClick() {
        AddArrowBackInFragment(R.id.mainFrag, new MyFavoritesPokemonFragment(), true, FragmentsTags.TAG_FRAGMENTS_FAVORITES);
    }

    // TODO ----> TALVEZ TERÁ QUE SER ALGO MAIS GENERICO
    private void AddArrowBackInFragment(int id, Fragment classToUse, boolean isToAddInStack, String fragmentTag) {
        fragmentHelper.replaceFragment(id, classToUse, isToAddInStack, fragmentTag);
        if(isToAddInStack) {
            actionBarHelper.addArrowBackInFragment(isToAddInStack);
        }
    }
}