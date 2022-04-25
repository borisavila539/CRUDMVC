
package modelo;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;


public class conexion {
    java.sql.Connection con;
    public java.sql.Connection getConnection(){
        String url = "jdbc:mysql://localhost:3306/bd_ejemplo";
        String user = "root";
        String pass = "123456789";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (java.sql.Connection) DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            System.out.println(e.toString() + "Error de conexion");
        }
        return con;
    }
}
