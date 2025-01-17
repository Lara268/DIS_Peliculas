package org.vaadin.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.tabs.TabSheet;
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
public class MainView extends VerticalLayout {

    /**
     * Construct a new Vaadin view.
     * <p>
     * Build the initial UI state for the user accessing the application.
     *
     * @param service
     *            The message service. Automatically injected Spring managed
     *            bean.
     */
    public MainView(@Autowired PeliculaService service) {
        TabSheet hojas_tabuladas = new TabSheet();

        // Pasar el servicio a DatosGenerales y DatosAgrupados
        PeliculasGenerales datosGenerales = new PeliculasGenerales(service);
        datosGenerales.setWidth("1000px");
        hojas_tabuladas.add("Datos generales", datosGenerales);

//        DatosAgrupados datosAgrupados = new DatosAgrupados(service);
//        datosAgrupados.setWidth("1000px");
//        hojas_tabuladas.add("Datos agrupados", datosAgrupados);

        add(hojas_tabuladas);
    }

}
