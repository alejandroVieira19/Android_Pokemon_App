package com.pokemon_app.presentation.ui.view;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pokemon_app.R;
import com.pokemon_app.databinding.FragmentDetailPokemonBinding;
import com.pokemon_app.utils.FragmentHelper;
import com.pokemon_app.utils.PokemonUtils;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class DetailPokemonFragment extends Fragment {

    private FragmentDetailPokemonBinding binding;

    AboutMeDetailedPokemonFragment aboutMeDetailedPokemonFragment;

    TextView aboutMeBtn, movesBtn;

    ImageView ivFavoritePokemonIcon;
    FragmentHelper fragmentHelper;

    LinearLayout detailFragmentLayout;

    @SuppressLint("ResourceAsColor")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       binding = FragmentDetailPokemonBinding.inflate(inflater, container, false);

       // GARANTIR QUE O FRAGMENTO ESTEJA VINCULADO AO LAYOUT!!!!!
       binding.setDetailPokemonFragment(this);

       Log.d("Fragment", "Created");

       fragmentHelper = new FragmentHelper(getActivity().getSupportFragmentManager());

       aboutMeBtn = binding.textAboutMe;

       detailFragmentLayout = binding.detailFragmentLayout;

       movesBtn = binding.movesBtn;

       ivFavoritePokemonIcon = binding.ivDetailFavoritePokemonIcon;

       // TODO ---> verificar quantos types são e depois colocar lógica para apresentar as imagens na UI
        detailFragmentLayout.setBackgroundColor(Color.parseColor(PokemonUtils.getColorForPokemonByType("grass")));

       return binding.getRoot();
    }



    public void onMovesClick() {
        aboutMeBtn.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.card_background_transparent));
        movesBtn.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.card_background_blur));
    }

    public void onAboutMeClick() {
        movesBtn.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.card_background_transparent));
        aboutMeBtn.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.card_background_blur));
    }

    public void onFavoritePokemonToggle() {
        ivFavoritePokemonIcon.setImageResource(android.R.drawable.btn_star_big_on);

        // ter uma variavel no meu objecto que será IsFavorite, se tiver a false, entao coloca a estrela e fará o codigo de adicionar a DB

    }

}