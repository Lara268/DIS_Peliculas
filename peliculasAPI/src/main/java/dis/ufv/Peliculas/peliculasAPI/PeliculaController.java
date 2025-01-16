package dis.ufv.Peliculas.peliculasAPI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class PeliculaController {
    @GetMapping("/peliculas")
    public ArrayList<Pelicula> Peliculas(){
        return new LectorCSV().leerCsv();
    }

    @GetMapping("/pelicula/porNombre/{nombre}")
    public ResponseEntity<Pelicula> getPorNombre(@PathVariable String nombre){
        ArrayList<Pelicula> listaPeliculas = new LectorCSV().leerCsv();
        Pelicula encontrado = null;
        for (Pelicula pelicula : listaPeliculas) {
            if (pelicula.getNombre().equalsIgnoreCase(nombre)) {
                encontrado = pelicula;
            }
        }
        return new ResponseEntity<>(encontrado, HttpStatus.OK);
    }
    @GetMapping("/pelicula/porGenero/{genero}")
    public ArrayList<Pelicula>  getPortipo(@PathVariable String tipo){
        ArrayList<Pelicula> listaPeliculas = new LectorCSV().leerCsv();
        ArrayList<Pelicula> listaEncontrados = new ArrayList<>();
        for (Pelicula pelicula : listaPeliculas) {
//            if (Pelicula.getTipo1().equalsIgnoreCase(tipo) || pelicula.getTipo2().equalsIgnoreCase(tipo)) {
//                listaEncontrados.add(pelicula);
//            }
        }
        return listaEncontrados;
    }
}