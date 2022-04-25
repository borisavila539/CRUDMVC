
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;



public class PersonaDAO {
    conexion conectar = new conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public List listar(){
        List<persona>datos = new ArrayList<>();
        String sql="select * from persona";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            rs =ps.executeQuery();
            while (rs.next()) {
                persona p = new persona();
                p.setId(rs.getInt(1));
                p.setNombre(rs.getString(2));
                p.setCorreo(rs.getString(3));
                p.setTelefono(rs.getString(4));
                datos.add(p);
                
            }
        } catch (Exception e) {
        }
        return datos;
    }
    public int agregar(persona p){
        String sql = "insert into persona(nombre,correo,telefono) values(?,?,?)";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getCorreo());
            ps.setString(3, p.getTelefono());
            ps.executeUpdate();
            
        } catch (Exception e) {
        }
        return 1;
    }
    public int Actualizar(persona p){
        String sql = "update persona set nombre=?, correo=?, telefono=? where id=?";
        try {
            con= conectar.getConnection();
            ps=con.prepareStatement(sql);
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getCorreo());
            ps.setString(3, p.getTelefono());
            ps.setInt(4, p.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return 1;
    }
    
    public int delete(int id){
        String sql = "delete from persona where id="+id;
        try {
            con=conectar.getConnection();
            ps=con.prepareStatement(sql);
            ps.executeUpdate();
                    
        } catch (Exception e) {
        }
        
     return 1;   
    }
}
