package dis.ufv.Peliculas.peliculasAPI;

import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
/* ---- AÑADIDO FRONT ----*/
@Service
/* ---------------------- */
public class LectorCSV {

    public ArrayList<Pelicula> leerCSV() {
        ArrayList<Pelicula> listaPeliculas = new ArrayList<>();
        String rutaArchivo = "/peliculas.csv"; // Ruta relativa al classpath
        try (InputStream is = LectorCSV.class.getResourceAsStream(rutaArchivo);
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {

            if (is == null) {
                throw new RuntimeException("Archivo peliculas.csv no encontrado en el classpath.");
            }

            String linea;
            // Leer la cabecera y descartarla
            br.readLine();

            // Leer línea por línea
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                Pelicula pelicula = new Pelicula(
                        Integer.parseInt(datos[0]),
                        datos[1],
                        datos[2],
                        Integer.parseInt(datos[3]),
                        Double.parseDouble(datos[4])
                );
                listaPeliculas.add(pelicula);
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error en el formato numérico: " + e.getMessage());
        }
        return listaPeliculas;
    }
}
