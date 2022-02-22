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
import modelo.contabilidad.Cuenta;

/**
 *
 * @author
 */
public class CuentaDAO {
    PreparedStatement ps;
    ResultSet rs;
    Conexion c =new Conexion();
    Connection con;
    
    public List listar(){
        List<Cuenta> lista = new ArrayList<>();
        String sql = "select * from cuenta";
        
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {                
                Cuenta c = new Cuenta();
                c.setCodigoCuenta(rs.getString(1));
                c.setCodigoTipoCuenta(rs.getString(2));
                c.setNombre(rs.getString(3));
                lista.add(c);  
            }
        } catch (Exception e) {
        }
        return lista;
    }
    
    public int agregar(Cuenta cu){
        int respuesta =0;
        String sql = "INSERT INTO CUENTA (CODIGOCUENTA, CODIGOTIPCUENTA, NOMBRECUENTA) VALUES(?,?,?)";
        try{
            con = c.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, cu.getCodigoCuenta());
            ps.setString(2, cu.getCodigoTipoCuenta());
            ps.setString(3, cu.getNombre());
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
    
    public int actualizar(Cuenta cu){
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
    
    
    public Cuenta buscarCodigo(String codigo){
        String sql = "select * from CUENTA where CODIGOCUENTA= '"+codigo+"'";
        Cuenta cu = new Cuenta();
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {                
                cu.setCodigoCuenta(rs.getString(1));
                cu.setCodigoTipoCuenta(rs.getString(2));
                cu.setNombre(rs.getString(3));
            }
        } catch (Exception e) {
        }
        return cu;
    }
    
    public List buscarNombre(String nombre){
        List<Cuenta> lista = new ArrayList<>();
        String sql = "select * from cuenta where NOMBRECUENTA like '%"+nombre+"%'";
        
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {                
                Cuenta cu = new Cuenta();
                cu.setCodigoCuenta(rs.getString(1));
                cu.setCodigoTipoCuenta(rs.getString(2));
                cu.setNombre(rs.getString(3));
                lista.add(cu); 
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
    
    
    public String listarCodigoCuentas(List<Cuenta> c){
        String lista="[";
        for (int i = 0; i < c.size(); i++) {
            lista+="'"+c.get(i).getCodigoCuenta()+"',";
        }
        return lista+"]";
    }
    public String listarCodigoNombres(List<Cuenta> c){
        String lista="[";
        for (int i = 0; i < c.size(); i++) {
            lista+="'"+c.get(i).getNombre()+"',";
        }
        return lista+"]";
    }
    
    
    public List buscarTipoCuenta(String codigo){
        String sql = "select * from CUENTA where CODIGOTIPCUENTA= '"+codigo+"'";
        List<Cuenta> lista = new ArrayList<>();
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {                
                Cuenta c = new Cuenta();
                c.setCodigoCuenta(rs.getString(1));
                c.setCodigoTipoCuenta(rs.getString(2));
                c.setNombre(rs.getString(3));
                lista.add(c);  
            }
        } catch (Exception e) {
        }
        return lista;
    }
}
