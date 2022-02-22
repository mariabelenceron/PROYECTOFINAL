/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.biblioteca.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.bd.Conexion;
import modelo.biblioteca.Autor;
import modelo.biblioteca.Libro;
import modelo.usuario.Encrypt;

/**
 *
 * @author alexg
 */
public class LibroDAO {
    PreparedStatement ps;
    ResultSet rs;
    Conexion c =new Conexion();
    Connection con;
    
    public List listar(){
        List<Libro> lista = new ArrayList<>();
        String sql = "select * from libro";
        
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {                
                Libro l = new Libro();
                l.setCodigoLibro(rs.getString(1));
                l.setCodigoAutor(rs.getString(2));
                l.setISBN(rs.getString(3));
                l.setTitulo(rs.getString(4));
                l.setValorPrestamo(rs.getFloat(5));
                lista.add(l);  
            }
        } catch (Exception e) {
        }
        return lista;
    }
    
    public int agregar(Libro l){
        int respuesta =0;
        String sql = "INSERT INTO LIBRO (CODIGOLIBRO, CODIGOAUTOR, ISBNLIBRO, TITULOLIBRO, VALORPRESTAMOLIBRO) VALUES(?,?,?,?,?)";
        try{
            con = c.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, l.getCodigoLibro());
            ps.setString(2, l.getCodigoAutor());
            ps.setString(3, l.getISBN());
            ps.setString(4, l.getTitulo());
            ps.setFloat(5, l.getValorPrestamo());
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
    
    public int actualizar(Libro l){
        int respuesta =0;
        String sql = "UPDATE LIBRO CODIGOLIBRO=?, CODIGOAUTOR=?, ISBNLIBRO=?, TITULOLIBRO=?, VALORPRESTAMOLIBRO=? WHERE CODIGOLIBRO=?";
        try{
            con = c.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, l.getCodigoLibro());
            ps.setString(2, l.getCodigoAutor());
            ps.setString(3, l.getISBN());
            ps.setString(4, l.getTitulo());
            ps.setFloat(3, l.getValorPrestamo());
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
    
    public void borrar(String codigo){
        String sql = "DELETE FROM LIBRO WHERE CODIGOLIBRO='"+codigo+"'";
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            ps.executeQuery();
        } catch (Exception e) {
        }
    }
    
    public Libro buscarCodigo(String codigo) {
        String sql = "select * from LIBRO where codigolibro= '" + codigo + "'";
        Libro l = new Libro();
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                l.setCodigoLibro(rs.getString(1));
                l.setCodigoAutor(rs.getString(2));
                l.setISBN(rs.getString(3));
                l.setTitulo(rs.getString(4));
                l.setValorPrestamo(rs.getFloat(5));  
            }
        } catch (Exception e) {
        }
        return l;
    }
    
    public List buscarTitulo(String titulo){
        List<Libro> lista = new ArrayList<>();
        String sql = "select * from LIBRO where TITULOLIBRO like '%"+titulo+"%'";
        
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {                
                Libro l = new Libro();
                l.setCodigoLibro(rs.getString(1));
                l.setCodigoAutor(rs.getString(2));
                l.setISBN(rs.getString(3));
                l.setTitulo(rs.getString(4));
                l.setValorPrestamo(rs.getFloat(5));
                lista.add(l);  
            }
        } catch (Exception e) {
        }
        return lista;
    }
    
    public List listarTitulo(String titulo){
        List<Libro> lista = new ArrayList<>();
        String sql = "select * from libro where TITULOLIBRO="+titulo;
        
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {                
                Libro l = new Libro();
                l.setCodigoLibro(rs.getString(1));
                l.setCodigoAutor(rs.getString(2));
                l.setISBN(rs.getString(3));
                l.setTitulo(rs.getString(4));
                l.setValorPrestamo(rs.getFloat(5));
                lista.add(l);  
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
            sql = "SELECT COUNT(*)FROM LIBRO WHERE CODIGOLIBRO= '" + codigo + "'";
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
            return "L000" + contador;
        } else if (contador < 100) {
            return "L00" + contador;
        } else if (contador < 1000) {
            return "L0" + contador;
        } else {
            return "L" + contador;
        }
    }
    
    private int contadorFilas() {
        String sql = "SELECT COUNT(*)FROM LIBRO";
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
    
    
    public String listarCodigoLibros(List<Libro> l){
        String lista="[";
        for (int i = 0; i < l.size(); i++) {
            lista+="'"+l.get(i).getCodigoLibro()+"',";
        }
        return lista+"]";
    }
    public String listarCodigoNombres(List<Libro> l){
        String lista="[";
        for (int i = 0; i < l.size(); i++) {
            lista+="'"+l.get(i).getTitulo()+"',";
        }
        return lista+"]";
    }
}
