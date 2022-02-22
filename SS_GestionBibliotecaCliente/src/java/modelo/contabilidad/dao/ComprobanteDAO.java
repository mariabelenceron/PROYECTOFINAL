/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.contabilidad.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.bd.Conexion;
import modelo.contabilidad.Comprobante;

/**
 *
 * @author alexg
 */
public class ComprobanteDAO {
    PreparedStatement ps;
    ResultSet rs;
    Conexion c =new Conexion();
    Connection con;
    
    public List listar(){
        List<Comprobante> lista = new ArrayList<>();
        String sql = "select * from COMPROBANTE";
        
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {                
                Comprobante c = new Comprobante();
                c.setCodigoComprobante(rs.getString(1));
                c.setFecha(rs.getString(2));
                c.setObservaciones(rs.getString(3));
                lista.add(c);  
            }
        } catch (Exception e) {
        }
        return lista;
    }
    
    public int agregar(Comprobante com){
        int respuesta =0;
        String sql = "INSERT INTO COMPROBANTE (CODIGOCOMPROBANTE, FECHACOMPROBANTE, OBSERVACIONCOMPROBANTE) VALUES(?,?,?)";
        try{
            con = c.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, com.getCodigoComprobante());
            ps.setString(2, com.getFecha());
            ps.setString(3, com.getObservaciones());
            respuesta = ps.executeUpdate();
            if(respuesta == 1){
                respuesta = 1;
            }else{
                respuesta = 0;
            }
        }catch (Exception e){
        }
        return respuesta;
    }
    
    public int actualizar(Comprobante com){
        int respuesta =0;
        /*String sql = "UPDATE AUTOR SET CODIGOAUTOR=?, NOMBREAUTOR=?, APELLIDOAUTOR=? WHERE CODIGOAUTOR=?";
        try{
            con = c.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, cu.getCodigoCuenta());
            ps.setString(2, cu.getCodigoTipoCuenta());
            ps.setString(3, cu.getNombre());;
            respuesta = ps.executeUpdate();
            if(respuesta == 1){
                respuesta = 1;
            }else{
                respuesta = 0;
            }
        }catch (Exception e){
        }*/
        return respuesta;
    }
    
    public void borrar(String codigo){
        String sql = "DELETE FROM CUENTA WHERE CODIGOCUENTA='"+codigo+"'";
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            ps.executeQuery();
        } catch (Exception e) {
        }
    }
    
    
    public Comprobante buscarCodigo(String codigo){
        String sql = "select * from COMPROBANTE where CODIGOCOMPROBANTE= '"+codigo+"'";
        Comprobante com = new Comprobante();
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {                
                com.setCodigoComprobante(rs.getString(1));
                com.setFecha(rs.getString(2));
                com.setObservaciones(rs.getString(3));
            }
        } catch (Exception e) {
        }
        return com;
    }
    
    public List buscarNombre(String nombre){
        List<Comprobante> lista = new ArrayList<>();
        String sql = "select * from cuenta where NOMBRECUENTA like '%"+nombre+"%'";
        
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {                
                Comprobante com = new Comprobante();
                com.setCodigoComprobante(rs.getString(1));
                com.setFecha(rs.getString(2));
                com.setObservaciones(rs.getString(3));
                lista.add(com); 
            }
        } catch (Exception e) {
        }
        return lista;
    }
    
    /*******
    *crear codigo de forma automatica
    ********/
    public String condigo() {
        String sql = "";
        int consContador = 0;
        int contador = contadorFilas();
        String codigo = "";
        do {
            contador++;
            codigo = crearCodigo(contador);
            sql = "SELECT COUNT(*)FROM COMPROBANTE WHERE CODIGOCOMPROBANTE = '" + codigo + "'";
            try {
                con = c.conectar();
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    consContador = rs.getInt(1);
                }
            } catch (Exception e) {
            }
        } while (consContador == 1);
        return codigo;
    }

    private String crearCodigo(int contador) {
        if (contador < 10) {
            return "CM000" + contador;
        } else if (contador < 100) {
            return "CM00" + contador;
        } else if (contador < 1000) {
            return "CM0" + contador;
        } else {
            return "CM" + contador;
        }
    }
    
    private int contadorFilas() {
        String sql = "SELECT COUNT(*)FROM COMPROBANTE";
        //"select * from AUTOR where codigoautor= '"+codigo+"'";
        int contador = 0;
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                contador = rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return contador;
    }
    
}
