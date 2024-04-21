package tests;

import static org.junit.Assert.assertEquals;

import java.security.GeneralSecurityException;

import org.junit.Test;

import modelo.Director;
import modelo.Genero;
import modelo.Pelicula;

public class TestModelo {
    @Test
    public void testDirector() {
        Director dir = new Director("Tarantino", "www.tarantinoimg.com", "www.tarantino.com", 1);
        assertEquals(dir.getNombre(), "Tarantino");
        assertEquals(dir.getUrl_foto(), "www.tarantinoimg.com");
        assertEquals(dir.getUrl_web(), "www.tarantino.com");
    }
    @Test
    public void testPelicula(){
        Director dir = new Director("Tarantino", "www.tarantinoimg.com", "www.tarantino.com", 1);
        Genero gen = Genero.Comedia;
        Pelicula pelicula = new Pelicula(1, "El Gran Dictador", dir, 1940, "www.gd.com", gen ,1);
        assertEquals(pelicula.getId(), 1);
        assertEquals(pelicula.getTitulo(), "El Gran Dictador");
        assertEquals(pelicula.getDirector(), dir);
        assertEquals(pelicula.getUrl_caratula(), "www.gd.com");
        assertEquals(pelicula.getGenero().toString(), "Comedia");
        assertEquals(pelicula.getEs_animacion(), 1);
    }
}
