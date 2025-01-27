package com.example.pokedex.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.pokedex.Pokemon;
import com.example.pokedex.PokemonAdapter;
import com.example.pokedex.R;
import com.example.pokedex.Seleccion;
import com.example.pokedex.databinding.FragmentPokedexBinding;
import com.example.pokedex.io.ApiAdapter;
import com.example.pokedex.io.ApiService;
import com.example.pokedex.io.response.PokemonResponse;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Este fragmento muestra una lista de Pokémon obtenida de una API.
 * Permite capturar Pokémon y almacenarlos en Firestore.
 */
public class PokedexFragment extends Fragment implements Seleccion {

    private PokemonAdapter adapter;
    private FragmentPokedexBinding bindeo;

    public PokedexFragment() {
    }


    /**
     * Crea e infla la vista del fragmento.
     *
     * @param inflater           El inflador de vistas.
     * @param container          El contenedor de vistas padre (si existe).
     * @param savedInstanceState Estado guardado previamente (si existe).
     * @return La vista inflada del fragmento.
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bindeo = FragmentPokedexBinding.inflate(inflater, container, false);
        bindeo.recyclerpokedex.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new PokemonAdapter(new ArrayList<>(), getContext(), this);
        bindeo.recyclerpokedex.setAdapter(adapter);
        mostrarPokemons();
      return bindeo.getRoot();
    }

    /**
     * Obtengo la lista de Pokémon desde la API y la muestro en el RecyclerView.
     */
    public void mostrarPokemons(){
        ApiService apiService = ApiAdapter.getClient().create(ApiService.class);

        Call<PokemonResponse> call = apiService.getPokemonList();
        call.enqueue(new Callback<PokemonResponse>() {
            @Override
            public void onResponse(@NonNull Call<PokemonResponse> call, @NonNull Response<PokemonResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Pokemon> pokemonList = response.body().getResults();
                    adapter.updateData(pokemonList); // Actualizar adaptador
                } else {
                    Log.e("API_ERROR", "Código HTTP: " + response.code());
                }
            }


            @Override
            public void onFailure(@NonNull Call<PokemonResponse> call, @NonNull Throwable t) {
                Log.e("API_ERROR", "Error en la petición", t);
            }
        });
    }

    /**
     * Manejo la acción de selección de un Pokémon.
     * Capturo el Pokémon y lo almaceno en Firestore.
     *
     * @param pokemonActual El Pokémon seleccionado.
     * @param view          La vista asociada al ítem seleccionado.
     */
    @Override
    public void pokemonSeleccion(Pokemon pokemonActual, View view) {
       FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("pokemon").add(pokemonActual)
                .addOnSuccessListener(runnable ->
                        Toast.makeText(getContext(), pokemonActual.getName() + " Capurado!", Toast.LENGTH_LONG).show())
                .addOnFailureListener(runnable ->
                        Toast.makeText(getContext(), "El pokemon no se pudo capturar.", Toast.LENGTH_LONG).show());
        view.setBackgroundResource(R.color.yellow);


}

}