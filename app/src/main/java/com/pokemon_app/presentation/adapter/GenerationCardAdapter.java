package com.pokemon_app.presentation.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pokemon_app.R;
import com.pokemon_app.databinding.FragmentGenerationsRowBinding;
import com.pokemon_app.domain.model.Generation;

import java.util.ArrayList;
import java.util.List;

public class GenerationCardAdapter extends RecyclerView.Adapter<GenerationCardAdapter.ViewHolder> {



    public interface OnGenerationClicked {
        void onClick(int generationId);  // Passa o ID da geração
    }

    private List<Generation> generationList;
    private OnGenerationClicked listener;

    // Construtor
    public GenerationCardAdapter(List<Generation> generationList, OnGenerationClicked listener) {
        Log.d("GENERATIONS", generationList.toString());
        this.generationList = new ArrayList<>(generationList); // Garante mutabilidade
        this.listener = listener;
    }

   public class ViewHolder extends RecyclerView.ViewHolder {

        private final FragmentGenerationsRowBinding binding;

        TextView tvGeneration;

       public ViewHolder(FragmentGenerationsRowBinding binding) {
           super(binding.getRoot());
           this.binding = binding;
           binding.setGenerationCard(GenerationCardAdapter.this);
           tvGeneration = binding.tvGeneration;
       }

       public void bind(Generation generation) {
        tvGeneration.setText(generation.getTitle());

        tvGeneration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(generation.getNumber());
            }
        });

       }
    }
    @NonNull
    @Override
    public GenerationCardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FragmentGenerationsRowBinding binding = FragmentGenerationsRowBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }


    @Override
    public void onBindViewHolder(@NonNull GenerationCardAdapter.ViewHolder holder, int position) {
        Generation generation = generationList.get(position);
        holder.bind(generation);
    }



    @Override
    public int getItemCount() {
        return generationList.size();
    }
}
