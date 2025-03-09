package com.pokemon_app.ui.view;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.pokemon_app.R;


public class ListPokemonFragment extends Fragment {

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

        changeFragmentBtn = view.findViewById(R.id.button);


        changeFragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onButtonClickedToChangeFragment();
            }
        });

        inflater.inflate(R.layout.search_bar, searchBarContainer, true);
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        activity = (OnButtonClicked) context;
    }
}