//package org.vaadin.example;
//
//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;
//import com.vaadin.flow.data.provider.DataProvider;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//import java.net.URISyntaxException;
//import java.util.ArrayList;
//
//@Service
//public class PeliculaService {
//
//    public ArrayList<Pelicula> leePeliculas() throws URISyntaxException, IOException, InterruptedException {
//        API api = new API();
//        String resultsAPI = api.getPeliculas();
//        Gson gson = new Gson();
//        ArrayList<Pelicula> lista = gson.fromJson(resultsAPI,new TypeToken<ArrayList<Pelicula>>(){}.getType());
//        return lista;
//    }
//
//}

package org.vaadin.example;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PeliculaService {

    public List<Pelicula> leePeliculas() throws URISyntaxException, IOException, InterruptedException {
        API api = new API();
        String resultsAPI;

        try {
            resultsAPI = api.getPeliculas(); // Llama a la API
            System.out.println("JSON recibido desde la API: " + resultsAPI); // Log del JSON recibido
        } catch (Exception e) {
            System.err.println("Error al obtener datos desde la API: " + e.getMessage());
            throw new RuntimeException("No se pudo conectar con la API.", e);
        }

        Gson gson = new Gson();
        JsonElement jsonElement;

        try {
            jsonElement = JsonParser.parseString(resultsAPI); // Parsear el JSON
        } catch (Exception e) {
            System.err.println("Error al parsear el JSON: " + e.getMessage());
            throw new RuntimeException("El JSON recibido tiene un formato inválido.", e);
        }

        if (jsonElement.isJsonArray()) {
            // Si es un array, deserializa normalmente
            return gson.fromJson(jsonElement, new TypeToken<ArrayList<Pelicula>>() {}.getType());
        } else if (jsonElement.isJsonObject()) {
            // Si es un objeto único, crea una lista con un solo elemento
            Pelicula pelicula = gson.fromJson(jsonElement, Pelicula.class);
            ArrayList<Pelicula> lista = new ArrayList<>();
            lista.add(pelicula);
            return lista;
        } else {
            throw new RuntimeException("Formato inesperado del JSON: " + resultsAPI);
        }
    }
}

