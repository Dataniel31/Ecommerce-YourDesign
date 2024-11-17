package com.youdesign.YouDesign.Service;

import com.youdesign.YouDesign.Entity.Pokemon;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class PokemonService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String pokeApiUrl = "https://pokeapi.co/api/v2/pokemon/";

    public Pokemon getRandomPokemon() {
        Random random = new Random();
        int randomId = random.nextInt(898) + 1; // Hay 898 Pokémon en la PokeAPI

        String url = pokeApiUrl + randomId;

        // Hacer la solicitud
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);

        // Extraer el nombre y la URL de la imagen
        String name = (String) response.get("name");
        Map<String, Object> sprites = (Map<String, Object>) response.get("sprites");
        String imageUrl = (String) sprites.get("front_default");

        // Retornar el Pokémon
        return new Pokemon(name, imageUrl);
    }
}
