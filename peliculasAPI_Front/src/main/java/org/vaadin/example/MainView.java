package org.vaadin.example;

import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;

@Route("")
public class MainView extends VerticalLayout {

    private final PeliculaService peliculaService;

    @Autowired
    public MainView(PeliculaService peliculaService) {
        this.peliculaService = peliculaService;

        // Título
        add(new com.vaadin.flow.component.html.H1("Catálogo de Películas"));

        /* --------- FUNCIONALIDAD BUSCAR POR NOMBRE --------- */
        // Campo de texto para filtrar por nombre
        TextField filtroNombre = new TextField("Buscar por nombre");
        filtroNombre.setPlaceholder("Escribe el nombre de la película...");
        /* --------------------------------------------------- */


        // Botón para buscar
        Button buscarButton = new Button("Buscar");

        // Grid para mostrar las películas
        Grid<Pelicula> grid = new Grid<>(Pelicula.class, false);
        grid.addColumn(Pelicula::getNombre).setHeader("Nombre");
        grid.addColumn(Pelicula::getGenero).setHeader("Género");
        grid.addColumn(Pelicula::getAño).setHeader("Año");
        grid.addColumn(Pelicula::getCalificacion).setHeader("Calificación");

        // Obtener y mostrar las películas
        grid.setItems(peliculaService.obtenerTodasLasPeliculas());
//        add(grid);

        /* --------- FUNCIONALIDAD BUSCAR POR NOMBRE --------- */
        // Evento para buscar por nombre
        buscarButton.addClickListener(e -> {
            String nombre = filtroNombre.getValue();
            if (nombre.isEmpty()) {
                grid.setItems(peliculaService.obtenerTodasLasPeliculas());
            } else {
                grid.setItems(peliculaService.buscarPeliculasPorNombre(nombre));
            }
        });

        // Añadir componentes al layout
        add(filtroNombre, buscarButton, grid);
        /* --------------------------------------------------- */


    }
}

