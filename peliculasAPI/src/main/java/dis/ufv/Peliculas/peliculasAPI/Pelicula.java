package dis.ufv.Peliculas.peliculasAPI;

public class Pelicula {
    private int id;
    private String nombre;
    private String genero;
    private int año;
    private double calificacion;

    public Pelicula(int id, String nombre, String genero, int año, double calificacion) {
        this.id = id;
        this.nombre = nombre;
        this.genero = genero;
        this.año = año;
        this.calificacion = calificacion;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }
    public int getAño() { return año; }
    public void setAño(int año) { this.año = año; }
    public double getCalificacion() { return calificacion; }
    public void setCalificacion(double calificacion) { this.calificacion = calificacion;}
}

