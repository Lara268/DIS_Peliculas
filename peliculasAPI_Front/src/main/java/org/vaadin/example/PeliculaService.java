package org.vaadin.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vaadin.flow.data.provider.DataProvider;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

@Service
public class PeliculaService {

//    PRIMER INTENTO
//    private static final String API_URL = "http://localhost:8082"; // Cambia el puerto si es diferente
//
//    public List<Pelicula> obtenerTodasLasPeliculas() {
//        RestTemplate restTemplate = new RestTemplate();
//        Pelicula[] peliculasArray = restTemplate.getForObject(API_URL, Pelicula[].class);
//        return Arrays.asList(peliculasArray);
//    }
//
//    public List<Pelicula> buscarPeliculasPorNombre(String nombre) {
//        return obtenerTodasLasPeliculas().stream()
//                .filter(pelicula -> pelicula.getNombre().toLowerCase().contains(nombre.toLowerCase()))
//                .collect(Collectors.toList());
//    }

//  SEGUNDO INTENTO
//    public DataProvider<Pelicula, Void> leePeliculaPorNombre(String nombre) throws URISyntaxException, IOException, InterruptedException {
//        API api = new API();
//        return api.getPeliculaPorNombre(nombre);
//    }
//    public String leePeliculaPorGenero(String tipo) throws URISyntaxException, IOException, InterruptedException {
//        API api = new API();
//        return api.getPeliculaPorGenero(tipo);
//    }
//
    public ArrayList<Pelicula> leePeliculas() throws URISyntaxException, IOException, InterruptedException {
        API api = new API();
        String resultsAPI = api.getPeliculas();
        Gson gson = new Gson();
        ArrayList<Pelicula> lista = gson.fromJson(resultsAPI,new TypeToken<ArrayList<Pelicula>>(){}.getType());
        return lista;
    }

}
