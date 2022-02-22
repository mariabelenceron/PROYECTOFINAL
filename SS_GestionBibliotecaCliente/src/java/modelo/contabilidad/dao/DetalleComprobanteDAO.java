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
import modelo.contabilidad.DetalleComprobante;

/**
 *
 * @author alexg
 */
public class DetalleComprobanteDAO {
    PreparedStatement ps;
    ResultSet rs;
    Conexion c =new Conexion();
    Connection con;
    
    public List listar(){
        List<DetalleComprobante> lista = new ArrayList<>();
        String sql = "select * from DETALLECOMPROBANTE";
        
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {                
                DetalleComprobante d = new DetalleComprobante();
                d.setCodigoComprobante(rs.getString(1));
                d.setCodigoCuenta(rs.getString(2));
                d.setDebeDetalle(rs.getFloat(3));
                d.setHaberDetalle(rs.getFloat(4));
                lista.add(d);  
            }
        } catch (Exception e) {
        }
        return lista;
    }
    
    public int agregar(DetalleComprobante d){
        int respuesta =0;
        String sql = "INSERT INTO DETALLECOMPROBANTE (CODIGOCOMPROBANTE, CODIGOCUENTA, DEBEDETCOM, HABERDETCOM) VALUES(?,?,?,?)";
        try{
            con = c.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, d.getCodigoComprobante());
            ps.setString(2, d.getCodigoCuenta());
            ps.setFloat(3, d.getDebeDetalle());
            ps.setFloat(4, d.getHaberDetalle());
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
    
    public int actualizar(DetalleComprobante com){
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
    
    
    public List buscarCodigoComprobante(String codigo){
        String sql = "select * from DETALLECOMPROBANTE where CODIGOCOMPROBANTE = '"+codigo+"'";
        List<DetalleComprobante> lista = new ArrayList<>();
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {                
                DetalleComprobante d = new DetalleComprobante();
                d.setCodigoComprobante(rs.getString(1));
                d.setCodigoCuenta(rs.getString(2));
                d.setDebeDetalle(rs.getFloat(3));
                d.setHaberDetalle(rs.getFloat(4));
                lista.add(d);  
            }
        } catch (Exception e) {
        }
        return lista;
    }
    
    /*public List buscarNombre(String nombre){
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
            sql = "SELECT COUNT(*)FROM CUENTA WHERE CODIGOCUENTA= '" + codigo + "'";
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
            return "C000" + contador;
        } else if (contador < 100) {
            return "C00" + contador;
        } else if (contador < 1000) {
            return "C0" + contador;
        } else {
            return "C" + contador;
        }
    }
    
    private int contadorFilas() {
        String sql = "SELECT COUNT(*)FROM CUENTA";
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
    
    public float sumaCuentaHaber(String codigo){
        float suma=0;
        String sql ="SELECT SUM(HABERDETCOM) FROM DETALLECOMPROBANTE WHERE CODIGOCUENTA='"+codigo+"'";
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                suma= rs.getFloat(1);
            }
        } catch (Exception e) {
        }
        return suma;
    }
    
    public float sumaCuentaDebe(String codigo){
        float suma=0;
        String sql ="SELECT SUM(DEBEDETCOM) FROM DETALLECOMPROBANTE WHERE CODIGOCUENTA='"+codigo+"'";
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                suma= rs.getFloat(1);
            }
        } catch (Exception e) {
        }
        return suma;
    }
}
