package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class PeliculaDAO {
    private String path;

    public PeliculaDAO(String path){
        this.path = path;
    }

    public ArrayList<Pelicula> dameTodos() throws SQLException {
        String sql = "SELECT * FROM peliculas ORDER BY id_genero, año DESC, titulo";

        Connection conn = new Utilidades().getConnection(path);
        Statement sentenciaSQL = conn.createStatement();
        ResultSet resultado = sentenciaSQL.executeQuery(sql);

        ArrayList<Pelicula> listaPeliculas = new ArrayList<>();

        while (resultado.next()) {
            DirectorDAO dao = new DirectorDAO(path);
            Director dir = dao.buscaPorId(resultado.getInt("id_director"));
            Genero gen = Genero.buscaPorId(resultado.getInt("id_genero"));
            Pelicula nuevo = new Pelicula(resultado.getInt("id"), resultado.getString("titulo"), dir, resultado.getInt("año"), resultado.getString("url_caratula"), gen, resultado.getInt("es_animacion"));
            
            listaPeliculas.add(nuevo);
        }
        sentenciaSQL.close();
        conn.close();
        return listaPeliculas;
        
    }

    public Pelicula buscaPorId(Integer id) throws SQLException{
        String sql = "SELECT * FROM peliculas where id = ?";
        
        Connection conn = new Utilidades().getConnection(path);

        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultado = statement.executeQuery();
            if (resultado.next()){
                DirectorDAO dao = new DirectorDAO(path);
                Director dir = dao.buscaPorId(resultado.getInt("id_director"));
                Genero gen = Genero.buscaPorId(resultado.getInt("id_genero"));
                Pelicula pelicula = new Pelicula(resultado.getInt("id"), resultado.getString("titulo"), dir, resultado.getInt("año"), resultado.getString("url_caratula"), gen, resultado.getInt("es_animacion"));
            
                statement.close();
                return pelicula;
            }
        } catch (SQLException err) {
            System.out.println("Se ha producido un error en la consulta de usuario por ID");
            err.printStackTrace();
        }
        conn.close();
        return null;
    }

    public Pelicula buscaPorNombre(String nombre) throws SQLException{
        DirectorDAO direc = new DirectorDAO(path);
        Integer id = direc.buscaPorNombre(nombre).getId();
        String sql = "SELECT * FROM peliculas where id_director = ?";
        Connection conn = new Utilidades().getConnection(path);

        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeQuery();
            ResultSet resultado = statement.executeQuery();
            if (resultado.next()){
                DirectorDAO dao = new DirectorDAO(path);
                Director dir = dao.buscaPorId(resultado.getInt("id_director"));
                Genero gen = Genero.buscaPorId(resultado.getInt("id_genero"));
                Pelicula pelicula = new Pelicula(resultado.getInt("id"), resultado.getString("titulo"), dir, resultado.getInt("año"), resultado.getString("url_caratula"), gen, resultado.getInt("es_animacion"));
                return pelicula;
            }
        } catch (SQLException err) {
            System.out.println("Se ha producido un error en la consulta");
            err.printStackTrace();
        }
        conn.close();
        return null;
    }

    public void borra(Integer id) throws SQLException{
        String sql = "DELETE from pelicula where id = ?";
        Connection conn = new Utilidades().getConnection(path);
        try {
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();
        conn.close();
        } catch (SQLException err) {
            System.out.println("No se ha podido borrar correctamente");
            err.printStackTrace();
        }
        conn.close();
    }

    public void modifica(Pelicula pelicula) throws SQLException {
        String sql = "UPDATE peliculas SET titulo = ?, id_director = ?, anio = ?, url_caratula = ?, id_genero = ?, es_animacion = ? WHERE id = ?";
        Connection conn = new Utilidades().getConnection(path);
        try{ 
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(7, pelicula.getId());
            statement.setString(1, pelicula.getTitulo());
            statement.setInt(2, pelicula.getDirector().getId());
            statement.setInt(3, pelicula.getAnio());
            statement.setString(4, pelicula.getUrl_caratula());
            statement.setInt(5, pelicula.getGenero().getId());
            statement.setInt(6, pelicula.getEs_animacion());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException err) {
            System.out.println("No se ha podido modificar");
            err.printStackTrace();
        }
        conn.close();
          
    }
}
