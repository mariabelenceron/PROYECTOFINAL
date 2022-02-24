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
import modelo.biblioteca.Autor;

/**
 *
 * @author
 */
public class AutorDAO {

    PreparedStatement ps;
    ResultSet rs;
    Conexion c = new Conexion();
    Connection con;
    
    public List listar() throws ClassNotFoundException {
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
        } catch (SQLException e) {
        }
        return lista;
    }

    public int agregar(Autor a) throws ClassNotFoundException {
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
        } catch (SQLException e) {
        }
        return respuesta;
    }

    public int actualizar(Autor a) throws ClassNotFoundException {
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
        } catch (SQLException e) {
        }
        return respuesta;
    }

    public void borrar(String codigo) throws ClassNotFoundException {
        String sql = "DELETE FROM AUTOR WHERE CODIGOAUTOR='" + codigo + "'";
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            ps.executeQuery();
        } catch (SQLException e) {
        }
    }

    public Autor buscarCodigo(String codigo) throws ClassNotFoundException {
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
        } catch (SQLException e) {
        }
        return a;
    }

    public List buscarNombre(String nombre) throws ClassNotFoundException {
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
            sql = "SELECT COUNT(*)FROM AUTOR WHERE CODIGOAUTOR= '" + codigo + "'";
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
            return "A000" + contador;
        } else if (contador < 100) {
            return "A00" + contador;
        } else if (contador < 1000) {
            return "A0" + contador;
        } else {
            return "A" + contador;
        }
    }
    
    private int contadorFilas() throws ClassNotFoundException {
        String sql = "SELECT COUNT(*)FROM AUTOR";
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
