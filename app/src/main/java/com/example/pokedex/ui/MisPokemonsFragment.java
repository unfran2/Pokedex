package com.example.pokedex.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.pokedex.Pokemon;
import com.example.pokedex.PokemonAdapter;
import com.example.pokedex.Seleccion;
import com.example.pokedex.databinding.FragmentMispokemonsBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

/**
 * Este fragmento muestra una lista de los Pokémon capturados por el usuario.
 * Los datos se obtienen desde Firestore y se muestran en un RecyclerView.
 */
public class MisPokemonsFragment extends Fragment implements Seleccion {

    private FragmentMispokemonsBinding bindeo;
    private PokemonAdapter adapter;


    public MisPokemonsFragment() {
    }

    /**
     * Crea e infla la vista del fragmento.
     *
     * @param inflater           El inflador de vistas.
     * @return La vista inflada del fragmento.
     */
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        bindeo = FragmentMispokemonsBinding.inflate(inflater, container, false);
        bindeo.recyclermispokemons.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new PokemonAdapter(new ArrayList<>(), getContext(), this);
        bindeo.recyclermispokemons.setAdapter(adapter);
        mostrarMisPoke();
        return bindeo.getRoot();
    }

    /**
     * Obtengo y muestro la lista de Pokémon capturados desde Firestore.
     */
    private void mostrarMisPoke() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("pokemon")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<Pokemon> listaMios = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Pokemon pokemon = document.toObject(Pokemon.class);
                                listaMios.add(pokemon);
                            }
                            adapter.updateData(listaMios);

                        } else {
                            Toast.makeText(getContext(), "No se pudo leer la bd", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
    @Override
    public void pokemonSeleccion(Pokemon pokemon, View view) {

    }
}