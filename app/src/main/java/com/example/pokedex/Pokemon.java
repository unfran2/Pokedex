package com.example.pokedex;


/**
 * Esta clase representa un Pokémon con información básica como nombre, URL, tipo, peso, altura e ID.
 */
public class Pokemon {

    private String name;
    private String url;
    private String type;

    private String id;
    private String weight;
    private String height;
    private String ImageUrl;

    public Pokemon() {
    }


    public Pokemon(String url, String name) {
        this.url = url;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWeight() {
        weight = getUrl()  + "weigt";
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }



    public String getId() {
        // Extraer el ID del Pokémon de la URL
        String[] urlParts = url.split("/");
        id = urlParts[urlParts.length - 1];
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // Método para construir la URL de la imagen
    public String getImageUrl() {
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" +  getId() + ".png";
    }
}
