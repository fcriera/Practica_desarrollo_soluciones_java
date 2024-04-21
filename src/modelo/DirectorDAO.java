package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DirectorDAO {
    private String path;

    public DirectorDAO(String path){
        this.path = path;
    }

    public ArrayList<Director> dameTodos() throws SQLException {
        String sql = "SELECT * FROM directores ORDER BY nombre";

        Connection conn = new Utilidades().getConnection(path);
        Statement sentenciaSQL = conn.createStatement();
        ResultSet resultado = sentenciaSQL.executeQuery(sql);

        ArrayList<Director> listaDirectores = new ArrayList<>();

        while (resultado.next()) {
            Director nuevo = new Director(resultado.getString("nombre"), resultado.getString("url_foto"), resultado.getString("url_web"), resultado.getInt("id"));
            
            listaDirectores.add(nuevo);
        }
        sentenciaSQL.close();
        conn.close();
        return listaDirectores;
        
    }

    public Director buscaPorId(Integer id) throws SQLException{
        String sql = "SELECT * FROM directores where id = ?";
        
        Connection conn = new Utilidades().getConnection(path);

        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()){
                Director dir = new Director(result.getString("nombre"), result.getString("url_foto"), result.getString("url_web"), result.getInt("id"));
                statement.close();
                return dir;
            }
        } catch (SQLException err) {
            System.out.println("Se ha producido un error en la consulta de usuario por ID");
            err.printStackTrace();
        }
        conn.close();
        return null;
    }

    public Director buscaPorNombre(String nombre) throws SQLException{
        String sql = "SELECT * FROM directores where nombre = ?";
        
        Connection conn = new Utilidades().getConnection(path);

        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, nombre);
            statement.executeQuery();
            ResultSet result = statement.executeQuery();
            if (result.next()){
                Director dir = new Director(result.getString("nombre"), result.getString("url_foto"), result.getString("url_web"), result.getInt("id"));
                return dir;
            }
        } catch (SQLException err) {
            System.out.println("Se ha producido un error en la consulta de usuario por nombre");
            err.printStackTrace();
        }
        conn.close();
        return null;
    }

    public void borra(Integer id) throws SQLException{
        String sql = "DELETE from directores where id = ?";
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

    public void modifica(Director director) throws SQLException {
        String sql = "UPDATE directores SET nombre = ?, url_foto = ?, url_web = ? WHERE id = ?";
        Connection conn = new Utilidades().getConnection(path);
        System.out.println(director.getNombre());
        try{ 
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(4, director.getId());
            statement.setString(1, director.getNombre());
            statement.setString(2, director.getUrl_foto());
            statement.setString(3, director.getUrl_web());  
            statement.executeUpdate();
            statement.close();
        } catch (SQLException err) {
            System.out.println("No se ha podido modificar");
            err.printStackTrace();
        }
        conn.close();
          
    }
}
