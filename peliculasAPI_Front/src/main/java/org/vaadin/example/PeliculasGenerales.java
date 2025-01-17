package org.vaadin.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class PeliculasGenerales extends VerticalLayout {

    private final Grid<Pelicula> grid = new Grid<>(Pelicula.class);
    private final DatePicker startDatePicker = new DatePicker("Fecha Inicio");
    private final DatePicker endDatePicker = new DatePicker("Fecha Fin");

    private final PeliculaService service;
    private final RestTemplate restTemplate;

    public PeliculasGenerales(@Autowired PeliculaService service) {
        this.service = service;
        this.restTemplate = new RestTemplate();
        this.restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter(new ObjectMapper()));

        setSizeFull();
        configureGrid();

        updateGrid(); // Carga inicial de todos los registros
    }

    private void configureGrid() {
        // Configurar columnas específicas del Grid
        grid.addColumn(Pelicula::getId).setHeader("ID").setAutoWidth(true);
        grid.addColumn(Pelicula::getNombre).setHeader("Nombre").setAutoWidth(true);
        grid.addColumn(Pelicula::getGenero).setHeader("Género").setAutoWidth(true);
        grid.addColumn(Pelicula::getAño).setHeader("Año").setAutoWidth(true);
        grid.addColumn(Pelicula::getCalificacion).setHeader("Calificación").setAutoWidth(true);

        // Ajustar ancho automático de las columnas
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        // Añadir el Grid al layout
        add(grid);
    }

    private void updateGrid() {
        try {
            // Obtén los datos desde el servicio
            List<Pelicula> data = service.leePeliculas();

            // Asigna los datos al Grid
            grid.setItems(data);

        } catch (Exception e) {
            // Muestra una notificación si ocurre un error
            Notification.show("Error al cargar los datos: " + e.getMessage(), 3000, Notification.Position.MIDDLE);
            e.printStackTrace(); // Log para depuración
        }
    }

    // Metodo futuro para filtrar por rango de fechas
    // (comentado hasta que implementes el servicio para filtrar por fechas)
    // private void filterGridByDate() {
    //     String startDate = startDatePicker.getValue() != null ? startDatePicker.getValue().toString() : "";
    //     String endDate = endDatePicker.getValue() != null ? endDatePicker.getValue().toString() : "";
    //
    //     try {
    //         List<Pelicula> filteredData = service.filtrarPeliculasPorFecha(startDate, endDate);
    //         grid.setItems(filteredData);
    //
    //         if (filteredData.isEmpty()) {
    //             Notification.show("No hay datos para el rango de fechas seleccionado.", 3000, Notification.Position.MIDDLE);
    //         }
    //     } catch (Exception e) {
    //         Notification.show("Error al filtrar los datos: " + e.getMessage(), 3000, Notification.Position.MIDDLE);
    //         e.printStackTrace();


//  }
}
