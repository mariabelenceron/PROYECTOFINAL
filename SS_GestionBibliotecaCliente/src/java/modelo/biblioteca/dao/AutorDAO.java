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
 * @author
 */
public class AutorDAO {

    PreparedStatement ps;
    ResultSet rs;
    Conexion c = new Conexion();
    Connection con;
    
    public List listar() {
        List<Autor> lista = new ArrayList<>();
        String sql = "select * from autor";

        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Autor a = new Autor();
                a.setCodigo(rs.getString(1));
                a.setNombre(rs.getString(2));
                a.setApellido(rs.getString(3));
                lista.add(a);
            }
        } catch (Exception e) {
        }
        return lista;
    }

    public int agregar(Autor a) {
        int respuesta = 0;
        String sql = "INSERT INTO AUTOR (CODIGOAUTOR, NOMBREAUTOR, APELLIDOAUTOR) VALUES(?,?,?)";
       
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, a.getCodigo());
            ps.setString(2, a.getNombre());
            ps.setString(3, a.getApellido());
            respuesta = ps.executeUpdate();
            if (respuesta == 1) {
                respuesta = 1;
            } else {
                respuesta = 0;
            }
        } catch (Exception e) {
        }
        return respuesta;
    }

    public int actualizar(Autor a) {
        int respuesta = 0;
        String sql = "UPDATE AUTOR SET CODIGOAUTOR=?, NOMBREAUTOR=?, APELLIDOAUTOR=? WHERE CODIGOAUTOR=?";
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, a.getCodigo());
            ps.setString(2, a.getNombre());
            ps.setString(3, a.getApellido());
            respuesta = ps.executeUpdate();
            if (respuesta == 1) {
                respuesta = 1;
            } else {
                respuesta = 0;
            }
        } catch (Exception e) {
        }
        return respuesta;
    }

    public void borrar(String codigo) {
        String sql = "DELETE FROM AUTOR WHERE CODIGOAUTOR='" + codigo + "'";
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            ps.executeQuery();
        } catch (Exception e) {
        }
    }

    public Autor buscarCodigo(String codigo) {
        String sql = "select * from AUTOR where codigoautor= '" + codigo + "'";
        Autor a = new Autor();
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                a.setCodigo(rs.getString(1));
                a.setNombre(rs.getString(2));
                a.setApellido(rs.getString(3));
            }
        } catch (Exception e) {
        }
        return a;
    }

    public List buscarNombre(String nombre) {
        List<Autor> lista = new ArrayList<>();
        String sql = "select * from autor where NOMBREAUTOR like '%" + nombre + "%'";

        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Autor a = new Autor();
                a.setCodigo(rs.getString(1));
                a.setNombre(rs.getString(2));
                a.setApellido(rs.getString(3));
                lista.add(a);
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
            sql = "SELECT COUNT(*)FROM AUTOR WHERE CODIGOAUTOR= '" + codigo + "'";
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
            return "A000" + contador;
        } else if (contador < 100) {
            return "A00" + contador;
        } else if (contador < 1000) {
            return "A0" + contador;
        } else {
            return "A" + contador;
        }
    }
    
    private int contadorFilas() {
        String sql = "SELECT COUNT(*)FROM AUTOR";
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
