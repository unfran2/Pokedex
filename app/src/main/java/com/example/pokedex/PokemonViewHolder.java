package com.example.pokedex;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pokedex.databinding.TarjetaPokemonBinding;

public class PokemonViewHolder extends RecyclerView.ViewHolder {

    private final TarjetaPokemonBinding binding;

    public PokemonViewHolder(TarjetaPokemonBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(Pokemon pokemon){
        binding.nombre.setText(pokemon.getName());
        Glide.with(binding.fotoPersonaje.getContext())
                .load(pokemon.getImageUrl()) // URL de la imagen
                .into(binding.fotoPersonaje);
    }
}
