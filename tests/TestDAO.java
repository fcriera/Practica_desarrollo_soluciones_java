package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import modelo.Director;
import modelo.DirectorDAO;
import modelo.Genero;
import modelo.Pelicula;
import modelo.PeliculaDAO;
import modelo.Utilidades;

public class TestDAO {
    @Test
    public void testJDBCConecta(){
        boolean laClaseJDBCExiste = false;
        try {
            Class.forName("org.sqlite.JDBC");
            System.out.println("Todo ha ido bien");
            laClaseJDBCExiste = true;
        } catch (ClassNotFoundException e) {
            System.out.println("SQLite JDBC no encontrado");
            e.printStackTrace();
        } 
        assertTrue(laClaseJDBCExiste);
    }
    @Test
    public void testCrearConexion() throws SQLException {
        Utilidades utils = new Utilidades();

        Connection conn = utils.getConnection("./data/Tests.sqlite");
        assertNotNull(conn);
        conn.close();

        conn = utils.getConnection("./lolailoylere/kakebo_tests.sqlite");
        assertNull(conn);
    }

    /////// TEST DIRECTORDAO
    @Test
    public void TestDameTodos() throws SQLException{
        DirectorDAO dao = new DirectorDAO("./data/Tests.sqlite");
        ArrayList<Director> directores = dao.dameTodos();
        
        assertEquals(3, directores.size());
        assertEquals("Quentin Tarantino", directores.get(2).getNombre());
        assertEquals("Mel Gibson", directores.get(1).getNombre());
        assertEquals("Ben Affleck", directores.get(0).getNombre());

        assertEquals("www.qtimg.com", directores.get(2).getUrl_foto());
        assertEquals("www.mgimg.com", directores.get(1).getUrl_foto());
        assertEquals("www.baimg.com", directores.get(0).getUrl_foto());

        assertEquals("www.qtarantino.com", directores.get(2).getUrl_web());
        assertEquals("www.mgibson.com", directores.get(1).getUrl_web());
        assertEquals("www.baffleck.com", directores.get(0).getUrl_web());
    } 

    @Test
    public void TestBuscaPorId() throws SQLException{
        DirectorDAO dao = new DirectorDAO("./data/Tests.sqlite");
        Director dir;
        dir = dao.buscaPorId(1);
        assertEquals("Quentin Tarantino", dir.getNombre());
        assertEquals("www.qtimg.com", dir.getUrl_foto());
        assertEquals("www.qtarantino.com", dir.getUrl_web());
        assertEquals(1, dir.getId(), 0.0001);

        dir = dao.buscaPorId(2);
        assertEquals("Mel Gibson", dir.getNombre());
        assertEquals("www.mgimg.com", dir.getUrl_foto());
        assertEquals("www.mgibson.com", dir.getUrl_web());

        dir = dao.buscaPorId(3);
        assertEquals("Ben Affleck", dir.getNombre());
        assertEquals("www.baimg.com", dir.getUrl_foto());
        assertEquals("www.baffleck.com", dir.getUrl_web());
    }
    @Test
    public void TestBuscaPorNombre() throws SQLException{
        DirectorDAO dao = new DirectorDAO("./data/Tests.sqlite");
        Director dir;
        dir = dao.buscaPorNombre("Quentin Tarantino");
        assertEquals("www.qtimg.com", dir.getUrl_foto());
        assertEquals("www.qtarantino.com", dir.getUrl_web());

        dir = dao.buscaPorNombre("Mel Gibson");
        assertEquals("www.mgimg.com", dir.getUrl_foto());
        assertEquals("www.mgibson.com", dir.getUrl_web());

        dir = dao.buscaPorNombre("Ben Affleck");
        assertEquals("www.baimg.com", dir.getUrl_foto());
        assertEquals("www.baffleck.com", dir.getUrl_web());
    }
    @Test
    public void TestBorra() throws SQLException{
        DirectorDAO dao = new DirectorDAO("./data/Tests.sqlite");
        dao.borra(1);
        ArrayList<Director> directores = dao.dameTodos();
        assertEquals(directores.size(), 2);
    }
    @Test
    public void TestModifica() {
        DirectorDAO dao = new DirectorDAO("./data/Tests.sqlite");
        
        try{
            Director dir = dao.buscaPorId(2);
            dir.setNombre("Charles Chaplin");
            dir.setUrl_foto("www.ccimg.com");
            dir.setUrl_web("www.cchaplin.com");
            dao.modifica(dir);
            assertEquals("Charles Chaplin", dir.getNombre());
        } catch (SQLException err){
            System.out.println("No se ha podido modificar");
            err.printStackTrace();
        }
    }    
        ///// TEST PELÍCULADAO
    @Test
    public void TestDameTodosPeliculas() throws SQLException{
        PeliculaDAO dao = new PeliculaDAO("./data/Tests.sqlite");
        ArrayList<Pelicula> peliculas = dao.dameTodos();
        
        assertEquals(2, peliculas.size());
        assertEquals("Kill Bill", peliculas.get(0).getTitulo());
        assertEquals("Accion", peliculas.get(0).getGenero().toString());

        assertEquals("El Gran Dictador", peliculas.get(1).getTitulo());
        assertEquals("Comedia", peliculas.get(1).getGenero().toString());

    }  
    
    @Test
    public void TestBuscaPorIdPeliculas() throws SQLException{
        PeliculaDAO dao = new PeliculaDAO("./data/Tests.sqlite");
        Pelicula pelicula;
        pelicula = dao.buscaPorId(1);
        assertEquals("Kill Bill", pelicula.getTitulo());
        assertEquals(2005, pelicula.getAnio());
        
    }
    
    @Test
    public void TestBuscaPorNombrePeliculas() throws SQLException{
        PeliculaDAO dao = new PeliculaDAO("./data/Tests.sqlite");
        Pelicula pelicula = dao.buscaPorNombre("Quentin Tarantino");
        assertEquals("Kill Bill", pelicula.getTitulo());
    }

    @Test
    public void TestBorraPelicula() throws SQLException{
        PeliculaDAO dao = new PeliculaDAO("./data/Tests.sqlite");
        dao.borra(1);
        ArrayList<Pelicula> peliculas = dao.dameTodos();
        assertEquals(peliculas.size(), 2);
    }
    
    @Test
    public void TestModificaPelicula() {
        PeliculaDAO dao = new PeliculaDAO("./data/Tests.sqlite");
        
        try{
            Pelicula pelicula = dao.buscaPorId(2);
            pelicula.setTitulo("Prueba");
            dao.modifica(pelicula);
            assertEquals("Prueba", pelicula.getTitulo());
        } catch (SQLException err){
            System.out.println("No se ha podido modificar");
            err.printStackTrace();
        }
    }
    
}
