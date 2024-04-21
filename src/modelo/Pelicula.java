package modelo;

public class Pelicula {
    private int id;
    private String titulo;
    private Director director;
    private int anio;
    private String url_caratula;
    private Genero genero;
    private int es_animacion;

    public Pelicula(int id, String titulo, Director director, int anio, String url_caratula, Genero genero, int es_animacion){
        this.id = id;
        this.titulo = titulo;
        this.director = director;
        this.anio = anio;
        this.url_caratula = url_caratula;
        this.genero = genero;
        this.es_animacion = es_animacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getUrl_caratula() {
        return url_caratula;
    }

    public void setUrl_caratula(String url_caratula) {
        this.url_caratula = url_caratula;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public int getEs_animacion() {
        return es_animacion;
    }

    public void setEs_animacion(int es_animacion) {
        this.es_animacion = es_animacion;
    }
}
