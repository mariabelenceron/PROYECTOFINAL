/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.biblioteca.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.bd.Conexion;
import modelo.biblioteca.Prestamo;

/**
 *
 * @author alexg
 */
public class PrestamoDAO {
    PreparedStatement ps;
    ResultSet rs;
    Conexion c =new Conexion();
    Connection con;
    
    public List listar() throws ClassNotFoundException{
        List<Prestamo> lista = new ArrayList<>();
        String sql = "select * from PRESTAMO";
        
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {                
                Prestamo p = new Prestamo();
                p.setCodigoPrestamo(rs.getString(1));
                p.setFechaPrestamo(rs.getString(2));
                p.setNombreCliente(rs.getString(3));
                p.setDescripcion(rs.getString(4));
                lista.add(p);  
            }
        } catch (SQLException e) {
        }
        return lista;
    }
    
    public int agregar(Prestamo p) throws ClassNotFoundException{
        int respuesta =0;
        String sql = "INSERT INTO PRESTAMO (CODIGOPRESTAMO, FECHAPRETAMO, NOMBRECLPRESTAMO, DESCRIPCIONPRESTAMO) VALUES(?,?,?,?)";
        
        try{
            con = c.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getCodigoPrestamo());
            ps.setString(2, p.getFechaPrestamo());
            ps.setString(3, p.getNombreCliente());
            ps.setString(4, p.getDescripcion());
            respuesta = ps.executeUpdate();
            if(respuesta == 1){
                respuesta = 1;
            }else{
                respuesta = 0;
            }
        }catch (SQLException e){
        }
        return respuesta;
    }
    
    public void borrar(String codigo) throws ClassNotFoundException{
        String sql = "DELETE FROM CUENTA WHERE CODIGOCUENTA='"+codigo+"'";
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            ps.executeQuery();
        } catch (SQLException e) {
        }
    }
    
    
    public Prestamo buscarCodigo(String codigo) throws ClassNotFoundException{
        String sql = "select * from PRESTAMO where CODIGOPRESTAMO= '"+codigo+"'";
        Prestamo p = new Prestamo();
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {                
                p.setCodigoPrestamo(rs.getString(1));
                p.setFechaPrestamo(rs.getString(2));
                p.setNombreCliente(rs.getString(3));
                p.setDescripcion(rs.getString(4));
            }
        } catch (SQLException e) {
        }
        return p;
    }
    
    public List buscarNombre(String nombre) throws ClassNotFoundException{
        List<Prestamo> lista = new ArrayList<>();
        String sql = "select * from cuenta where NOMBRECUENTA like '%"+nombre+"%'";
        
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {                
                Prestamo p = new Prestamo();
                p.setCodigoPrestamo(rs.getString(1));
                p.setFechaPrestamo(rs.getString(2));
                p.setNombreCliente(rs.getString(3));
                p.setDescripcion(rs.getString(4));
                lista.add(p); 
            }
        } catch (SQLException e) {
        }
        return lista;
    }
    
    /*******
    *crear codigo de forma automatica
    ********/
    public String condigo() throws ClassNotFoundException {
        String sql = "";
        int consContador = 0;
        int contador = contadorFilas();
        String codigo = "";
        do {
            contador++;
            codigo = crearCodigo(contador);
            sql = "SELECT COUNT(*)FROM PRESTAMO WHERE CODIGOPRESTAMO = '" + codigo + "'";
            try {
                con = c.conectar();
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    consContador = rs.getInt(1);
                }
            } catch (SQLException e) {
            }
        } while (consContador == 1);
        return codigo;
    }

    private String crearCodigo(int contador) {
        if (contador < 10) {
            return "P000" + contador;
        } else if (contador < 100) {
            return "P00" + contador;
        } else if (contador < 1000) {
            return "P0" + contador;
        } else {
            return "P" + contador;
        }
    }
    
    private int contadorFilas() throws ClassNotFoundException {
        String sql = "SELECT COUNT(*)FROM PRESTAMO";
        int contador = 0;
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                contador = rs.getInt(1);
            }
        } catch (SQLException e) {
        }
        return contador;
    }
}
