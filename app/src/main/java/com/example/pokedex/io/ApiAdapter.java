package com.example.pokedex.io;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiAdapter {

    private static Retrofit retrofit;

    // URL base de la API de Pokémon
    private static final String BASE_URL = "https://pokeapi.co/api/v2/";

    /**
     * Método para obtener la instancia de Retrofit.
     *
     * @return Una instancia configurada de Retrofit.
     */
    public static Retrofit getClient() {
        if (retrofit == null) {
            // Interceptor para loguear las peticiones y respuestas HTTP
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            // Crear un cliente OkHttp con el interceptor de logging
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build();
            // Configuración de Retrofit con la URL base, cliente OkHttp y convertidor JSON (Gson)
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
