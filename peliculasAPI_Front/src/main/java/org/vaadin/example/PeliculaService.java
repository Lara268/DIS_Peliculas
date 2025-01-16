package org.vaadin.example;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PeliculaService {

    private static final String API_URL = "http://localhost:8082"; // Cambia el puerto si es diferente

    public List<Pelicula> obtenerTodasLasPeliculas() {
        RestTemplate restTemplate = new RestTemplate();
        Pelicula[] peliculasArray = restTemplate.getForObject(API_URL, Pelicula[].class);
        return Arrays.asList(peliculasArray);
    }

    public List<Pelicula> buscarPeliculasPorNombre(String nombre) {
        return obtenerTodasLasPeliculas().stream()
                .filter(pelicula -> pelicula.getNombre().toLowerCase().contains(nombre.toLowerCase()))
                .collect(Collectors.toList());
    }

}
