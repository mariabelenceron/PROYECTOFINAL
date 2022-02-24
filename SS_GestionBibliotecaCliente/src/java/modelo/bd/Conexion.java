/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author alexg
 */
public class Conexion {
    Connection con;
    String url = "jdbc:oracle:thin:@localhost:1521:orcl";
    String user = "PROYECTO";
    String pass = "123";
    
    public Connection conectar() throws ClassNotFoundException, SQLException{
        Class.forName("oracle.jdbc.driver.OracleDriver");
        con = DriverManager.getConnection(url,user,pass);
        return con;
    }
}
