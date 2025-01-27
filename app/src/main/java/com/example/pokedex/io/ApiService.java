package com.example.pokedex.io;


import com.example.pokedex.io.response.PokemonResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    /**
     * Realiza una solicitud GET para obtener una lista de Pokémon.
     *
     * @return Un objeto `Call` que contiene la respuesta de tipo `PokemonResponse`.
     */
    @GET("pokemon?offset=0&limit=150")
    Call<PokemonResponse> getPokemonList();

}
