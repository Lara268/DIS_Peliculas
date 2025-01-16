package dis.ufv.Peliculas.peliculasAPI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class PeliculaController {

    @GetMapping("")
    public ArrayList<Pelicula> peliculas(){
        ArrayList<Pelicula> listaPeliculas = new LectorCSV().leerCSV();
        return listaPeliculas;
    }

    @GetMapping("/peliculas/porNombre/{nombre}")
    public ResponseEntity<Pelicula> getPorNombre(@PathVariable String nombre){
        ArrayList<Pelicula> listaPeliculas = new LectorCSV().leerCSV();
        Pelicula encontrado = null;
        for (Pelicula pelicula : listaPeliculas) {
            if (pelicula.getNombre().equalsIgnoreCase(nombre)) {
                encontrado = pelicula;
            }
        }
        return new ResponseEntity<>(encontrado, HttpStatus.OK);
    }

    @GetMapping("/peliculas/porGenero/{genero}")
    public ArrayList<Pelicula>  getPorgenero(@PathVariable String genero){
        ArrayList<Pelicula> listaPeliculas = new LectorCSV().leerCSV();
        ArrayList<Pelicula> listaPeliculasEncontradas = new ArrayList<>();
        for (Pelicula pelicula : listaPeliculas) {
            if (pelicula.getGenero().equalsIgnoreCase(genero)) {
                listaPeliculasEncontradas.add(pelicula);
            }
        }
        return listaPeliculasEncontradas;
    }

}
