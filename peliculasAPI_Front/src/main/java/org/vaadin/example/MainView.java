package org.vaadin.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.vaadin.example.PeliculaService;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

@Route("")
//public class MainView extends VerticalLayout {
//
//    private final PeliculaService peliculaService;
//
//    @Autowired
//    public MainView(PeliculaService peliculaService) throws URISyntaxException, IOException, InterruptedException {
//        this.peliculaService = peliculaService;
//
//        // Título
//        add(new com.vaadin.flow.component.html.H1("Catálogo de Películas"));
//
//        /* --------- FUNCIONALIDAD BUSCAR POR NOMBRE --------- */
//        // Campo de texto para filtrar por nombre
//        TextField filtroNombre = new TextField("Buscar por nombre");
//        filtroNombre.setPlaceholder("Escribe el nombre de la película...");
//
//        // Botón para buscar
//        Button buscarButton = new Button("Buscar");
//        /* --------------------------------------------------- */
//
//
//        // Grid para mostrar las películas
//        Grid<Pelicula> grid = new Grid<>(Pelicula.class, false);
//        grid.addColumn(Pelicula::getNombre).setHeader("Nombre");
//        grid.addColumn(Pelicula::getGenero).setHeader("Género");
//        grid.addColumn(Pelicula::getAño).setHeader("Año");
//        grid.addColumn(Pelicula::getCalificacion).setHeader("Calificación");
//
//        // Obtener y mostrar las películas
//        grid.setItems(peliculaService.leePeliculas());
////        add(grid);
//
//        /* --------- FUNCIONALIDAD BUSCAR POR NOMBRE --------- */
//        // Evento para buscar por nombre
//        buscarButton.addClickListener(e -> {
//            String nombre = filtroNombre.getValue();
//            if (nombre.isEmpty()) {
//                try {
//                    grid.setItems(peliculaService.leePeliculas());
//                } catch (URISyntaxException ex) {
//                    throw new RuntimeException(ex);
//                } catch (IOException ex) {
//                    throw new RuntimeException(ex);
//                } catch (InterruptedException ex) {
//                    throw new RuntimeException(ex);
//                }
//            } else {
//                try {
//                    grid.setItems(peliculaService.leePeliculaPorNombre(nombre));
//                } catch (URISyntaxException ex) {
//                    throw new RuntimeException(ex);
//                } catch (IOException ex) {
//                    throw new RuntimeException(ex);
//                } catch (InterruptedException ex) {
//                    throw new RuntimeException(ex);
//                }
//            }
//        });
//
//        // Añadir componentes al layout
//        add(filtroNombre, buscarButton, grid);
//        /* --------------------------------------------------- */
//
//
//
//    }
//}


public class MainView extends VerticalLayout {

    private final Grid<Pelicula> grid = new Grid<>(Pelicula.class);

    private final PeliculaService service;
    private final RestTemplate restTemplate;

    public MainView(@Autowired PeliculaService service) throws URISyntaxException, IOException, InterruptedException {
        this.service = service;
        this.restTemplate = new RestTemplate();
        this.restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter(new ObjectMapper()));

        setSizeFull();
        configureGrid();
        // configureDatePickers();

        grid.setItems(service.leePeliculas());
//        updateGrid(); // Carga inicial de todos los registros
    }

    private void configureGrid() {
        // Configurar las columnas del Grid manualmente
        Grid<Pelicula> grid = new Grid<>(Pelicula.class, false);
        grid.addColumn(Pelicula::getNombre).setHeader("Nombre");
        grid.addColumn(Pelicula::getGenero).setHeader("Género");
        grid.addColumn(Pelicula::getAño).setHeader("Año");
        grid.addColumn(Pelicula::getCalificacion).setHeader("Calificación");

        // Ajustar ancho automático de las columnas
        grid.getColumns().forEach(col -> col.setAutoWidth(true));


    }


//    private void configureDatePickers() {
//        startDatePicker.addValueChangeListener(event -> filterGridByDate());
//        endDatePicker.addValueChangeListener(event -> filterGridByDate());
//    }


    private void updateGrid() {
        try {
            // Obtén los datos desde el servicio
            ArrayList<Pelicula> data = service.leePeliculas();

            // Asigna los datos al Grid
            grid.setItems(data);

        } catch (Exception e) {
            // Muestra una notificación si ocurre un error
            Notification.show("Error al cargar los datos: " + e.getMessage(), 3000, Notification.Position.MIDDLE);
        }
    }



}



