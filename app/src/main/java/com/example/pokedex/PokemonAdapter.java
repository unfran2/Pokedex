package com.example.pokedex;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokedex.databinding.TarjetaPokemonBinding;

import java.util.List;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonViewHolder> {

    private List<Pokemon> pokemonList;
    private final Context context;

    private final Seleccion listener;

    public PokemonAdapter(List<Pokemon> pokemonList, Context context, Seleccion listener) {
        this.pokemonList = pokemonList;
        this.context = context;
        this.listener = listener;
    }

    /**
     * Inflar la vista de cada tarjeta (ítem) para el RecyclerView.
     *
     * @return Un nuevo PokemonViewHolder que contiene la vista inflada.
     */
    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TarjetaPokemonBinding binding = TarjetaPokemonBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false
        );
        return new PokemonViewHolder(binding);
    }

    /**
     * Enlazo un elemento de la lista con su vista correspondiente.
     *
     * @param holder   El ViewHolder que contiene la vista del ítem.
     * @param position La posición del elemento en la lista.
     */
    @Override
    public void onBindViewHolder(@NonNull PokemonViewHolder holder, int position) {
        Pokemon pokemonActual = this.pokemonList.get(position);
        holder.bind(pokemonActual);

        holder.itemView.setOnClickListener(view -> {
            if (listener != null) {
                listener.pokemonSeleccion(pokemonActual, view);
            }
        });
    }



    @Override
    public int getItemCount() {
        return pokemonList.size();
    }

    /**
     * Actualizo la lista de Pokémon con nuevos datos.
     *
     * @param newPokemonList La nueva lista de Pokémon.
     */
    public void updateData(List<Pokemon> newPokemonList) {
        this.pokemonList = newPokemonList;
        notifyDataSetChanged();
    }
}

