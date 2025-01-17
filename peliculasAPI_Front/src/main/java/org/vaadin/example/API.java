package org.vaadin.example;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class API {

    private static final String BASE_URL = "http://127.0.0.1:8082"; // URL base del backend
    private final HttpClient httpClient;

    public API() {
        this.httpClient = HttpClient.newHttpClient(); // Instancia reutilizable de HttpClient
    }

    // Obtener películas por nombre
    public String getPeliculaPorNombre(String nombre) throws URISyntaxException, IOException, InterruptedException {
        String endpoint = String.format("%s/pelicula/porNombre/%s", BASE_URL, nombre);
        return sendGetRequest(endpoint);
    }

    // Obtener películas por género
    public String getPeliculaPorGenero(String tipo) throws URISyntaxException, IOException, InterruptedException {
        String endpoint = String.format("%s/pelicula/porGenero/%s", BASE_URL, tipo);
        return sendGetRequest(endpoint);
    }

    // Obtener todas las películas
    public String getPeliculas() throws URISyntaxException, IOException, InterruptedException {
        String endpoint = String.format("%s/peliculas", BASE_URL);
        return sendGetRequest(endpoint);
    }

    // Metodo genérico para enviar una solicitud GET
    private String sendGetRequest(String endpoint) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(endpoint))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // Imprime el cuerpo de la respuesta para depuración
        System.out.println("Respuesta de la API: " + response.body());

        if (response.statusCode() != 200) {
            throw new RuntimeException("Error en la API: " + response.statusCode() + " - " + response.body());
        }

        return response.body();
    }
}