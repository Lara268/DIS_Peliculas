package dis.ufv.Peliculas.peliculasAPI;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class LectorCSV {

    public ArrayList<Pelicula> leerCsv() {
        ArrayList<Pelicula> listaPeliculas = new ArrayList<>();
        String rutaArchivo = "src/main/resources/peliculas.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            // Leer la cabecera y descartarla
            br.readLine();

            // Leer línea por línea
            while ((linea = br.readLine()) != null) {
                // Dividir la línea por comas
                String[] datos = linea.split(",");
                // Crear un objeto Pelicula con los datos
                Pelicula pelicula = new Pelicula(
                        Integer.parseInt(datos[0]),    // id
                        datos[1],                     // nombre
                        datos[2],                     // genero
                        Integer.parseInt(datos[3]),   // año
                        Double.parseDouble(datos[4])  // calificación
                );
                listaPeliculas.add(pelicula);
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error en el formato numérico: " + e.getMessage());
        }

        System.out.println(listaPeliculas);
        return listaPeliculas;
    }
}
