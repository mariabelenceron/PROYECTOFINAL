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
import modelo.contabilidad.TipoCuenta;

/**
 *
 * @author alexg
 */
public class TipoCuentaDAO {
    PreparedStatement ps;
    ResultSet rs;
    Conexion c =new Conexion();
    Connection con;
    
    public List listar(){
        List<TipoCuenta> lista = new ArrayList<>();
        String sql = "select * from TIPOCUENTA";
        
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {                
                TipoCuenta tc = new TipoCuenta();
                tc.setCodigo(rs.getString(1));
                tc.setNombre(rs.getString(2));
                lista.add(tc);  
            }
        } catch (Exception e) {
        }
        return lista;
    }
    
    public int agregar(TipoCuenta tc){
        int respuesta =0;
        String sql = "INSERT INTO TIPOCUENTA (CODIGOTIPCUENTA, NOMBRETIPCUENTA) VALUES(?,?)";
        try{
            con = c.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, tc.getCodigo());
            ps.setString(2, tc.getNombre());
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
    
    public int actualizar(TipoCuenta tc){
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
        String sql = "DELETE FROM TIPOCUENTA WHERE CODIGOTIPCUENTA='"+codigo+"'";
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            ps.executeQuery();
        } catch (Exception e) {
        }
    }
    
    
    public TipoCuenta buscarCodigo(String codigo){
        String sql = "select * from TIPOCUENTA where CODIGOTIPCUENTA= '"+codigo+"'";
        TipoCuenta tc = new TipoCuenta();
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {                
                tc.setCodigo(rs.getString(1));
                tc.setNombre(rs.getString(2));
            }
        } catch (Exception e) {
        }
        return tc;
    }
    
    public List buscarNombre(String nombre){
        List<TipoCuenta> lista = new ArrayList<>();
        String sql = "select * from TIPOCUENTA where NOMBRETIPCUENTA like '%"+nombre+"%'";
        
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {                
                TipoCuenta tc = new TipoCuenta();
                tc.setCodigo(rs.getString(1));
                tc.setNombre(rs.getString(2));;
                lista.add(tc); 
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
            sql = "SELECT COUNT(*)FROM TIPOCUENTA WHERE CODIGOTIPCUENTA= '" + codigo + "'";
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
            return "TC00" + contador;
        } else if (contador < 100) {
            return "TC0" + contador;
        }else{
            return "TC" + contador;
        }
    }
    
    private int contadorFilas() {
        String sql = "SELECT COUNT(*)FROM TIPOCUENTA";
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
