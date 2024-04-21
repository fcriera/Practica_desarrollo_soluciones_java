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
    @Test
    public void TestDameTodos() throws SQLException{
        DirectorDAO dao = new DirectorDAO("./data/Tests.sqlite");
        ArrayList<Director> directores = dao.dameTodos();
        assertEquals(3, directores.size());
        assertEquals("Quentin Tarantino", directores.get(0).getNombre());
        assertEquals("Mel Gibson", directores.get(1).getNombre());
        assertEquals("Ben Affleck", directores.get(2).getNombre());

        assertEquals("www.qtimg.com", directores.get(0).getUrl_foto());
        assertEquals("www.mgimg.com", directores.get(1).getUrl_foto());
        assertEquals("www.baimg.com", directores.get(2).getUrl_foto());

        assertEquals("www.qtarantino.com", directores.get(0).getUrl_web());
        assertEquals("www.mgibson.com", directores.get(1).getUrl_web());
        assertEquals("www.baffleck.com", directores.get(2).getUrl_web());
    }   
    @Test
    public void TestBuscaPorId() throws SQLException{
        DirectorDAO dao = new DirectorDAO("./data/Tests.sqlite");
        Director dir;
        dir = dao.buscaPorId(1);
        assertEquals("Quentin Tarantino", dir.getNombre());
        assertEquals("www.qtimg.com", dir.getUrl_foto());
        assertEquals("www.qtarantino.com", dir.getUrl_web());

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
    public void TestModifica() throws SQLException {
        DirectorDAO dao = new DirectorDAO("./data/Tests.sqlite");
        Director dir = new Director("Charles Chaplin", "www.ccimg.com", "cchaplin.com");
        dao.modifica(dir,1);
        assertEquals("Charles Chaplin", dir.getNombre());
    }
}