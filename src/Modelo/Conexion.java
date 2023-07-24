package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    Connection con;

    public Connection getConnection() {
        try {
            String myBD = "jdbc:mysql://localhost:3306/vision_exteriors";
            con = DriverManager.getConnection(myBD, "root", "");
            return con;
            
        } catch (SQLException e) {
            System.out.println(e.toString());
            System.out.println("Conexion fallida");
        }
        return null;
        
    }
    
    public void CloseConnection() {
    	try {
    	con.close();
    	}catch(Exception e) {
    		System.out.println("Error en el cierre de la conexion");
    	}
    }

}