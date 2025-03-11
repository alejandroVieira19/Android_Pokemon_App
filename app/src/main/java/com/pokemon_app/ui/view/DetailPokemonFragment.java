package com.pokemon_app.ui.view;

import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.pokemon_app.R;
import com.pokemon_app.databinding.FragmentDetailPokemonBinding;
import com.pokemon_app.utils.FragmentHelper;

import org.w3c.dom.Text;


public class DetailPokemonFragment extends Fragment {

    private FragmentDetailPokemonBinding binding;

    TextView aboutMeBtn;

    TextView movesBtn;
    FragmentHelper fragmentHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       binding = FragmentDetailPokemonBinding.inflate(inflater, container, false);

       // GARANTIR QUE O FRAGMENTO ESTEJA VINCULADO AO LAYOUT!!!!!
       binding.setDetailPokemonFragment(this);

       Log.d("Fragment", "Created");

       fragmentHelper = new FragmentHelper(getActivity().getSupportFragmentManager());

       aboutMeBtn = binding.textAboutMe;

       movesBtn = binding.movesBtn;

       return binding.getRoot();
    }


    public void onMovesClick() {
        aboutMeBtn.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.card_background_transparent));
        movesBtn.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.card_background_blur));

        Log.d("Tocou 1", "clicou");
        Log.d("Tocou2", requireContext().getPackageName());
    }

    public void onAboutMeClick() {
        movesBtn.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.card_background_transparent));
        aboutMeBtn.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.card_background_blur));
    }

}