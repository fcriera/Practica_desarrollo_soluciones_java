package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import modelo.Director;

public class TestModelo {
    @Test
    public void testDirector() {
        Director dir = new Director("Tarantino", "www.tarantinoimg.com", "www.tarantino.com");
        assertEquals(dir.getNombre(), "Tarantino");
        assertEquals(dir.getUrl_foto(), "www.tarantinoimg.com");
        assertEquals(dir.getUrl_web(), "www.tarantino.com");
    }
    
}
