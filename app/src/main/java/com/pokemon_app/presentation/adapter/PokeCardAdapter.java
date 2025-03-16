package com.pokemon_app.presentation.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.pokemon_app.databinding.RowPokemonCardBinding;
import com.pokemon_app.domain.model.Pokemon;
import com.pokemon_app.utils.PokemonUtils;

import java.util.ArrayList;
import java.util.List;

public class PokeCardAdapter extends RecyclerView.Adapter<PokeCardAdapter.ViewHolder> {

    public interface OnPokemonCardClicked {
        void onClick(Pokemon pokemon);
    }
    private ArrayList<Pokemon> pokemonsList;
    private OnPokemonCardClicked callback;
    public PokeCardAdapter(OnPokemonCardClicked callback, ArrayList<Pokemon> list) {
        this.callback =  callback;
        pokemonsList = list;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private final RowPokemonCardBinding binding;
        ImageView ivPokemonImage;
        CardView pokeCardView;
        TextView tvPokemonName;
        public ViewHolder(RowPokemonCardBinding binding) {
            super(binding.getRoot());

            this.binding = binding;

            // GARANTIR QUE O FRAGMENTO ESTEJA VINCULADO AO LAYOUT!!!!!
            binding.setPokeCard(PokeCardAdapter.this);

            ivPokemonImage = binding.pokemonImage;
            tvPokemonName = binding.pokemonName;
            pokeCardView = binding.pokeCardView;
        }
        public void bind(Pokemon pokemon) {
            binding.setPokemon(pokemon);

            binding.executePendingBindings();

            tvPokemonName.setText(pokemon.getPokemonName());

            pokeCardView.setCardBackgroundColor(Color.parseColor(PokemonUtils.getColorForPokemonByType(pokemon.getPokemonType().get(0))));

            Glide.with(ivPokemonImage)
                    .load(pokemon.getPokemonImageUrlCard())
                    .centerCrop()
                    .into(binding.pokemonImage);
        }
    }

    public void onCardClick(Pokemon pokemon) {
        callback.onClick(pokemon);
    }
    public void updateList(List<Pokemon> newList) {
        pokemonsList = (ArrayList<Pokemon>) newList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PokeCardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowPokemonCardBinding binding = RowPokemonCardBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PokeCardAdapter.ViewHolder holder, int position) {
        Pokemon pokemon = pokemonsList.get(position);
        holder.bind(pokemon);
    }

    @Override
    public int getItemCount() {
        return pokemonsList.size();
    }
}
