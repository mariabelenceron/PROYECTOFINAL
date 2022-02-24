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
import modelo.biblioteca.DetallePrestamo;

/**
 *
 * @author alexg
 */
public class DetallePrestamoDAO {

    PreparedStatement ps;
    ResultSet rs;
    Conexion c = new Conexion();
    Connection con;

    public int agregar(DetallePrestamo d) {
        int respuesta = 0;
        String sql = "INSERT INTO DETALLEPRESTAMO (CODIGOPRESTAMO, CODIGOLIBRO, CANTIDADPRESTAMO, FECHAENTREGAPRESTAMO) VALUES(?,?,?,?)";
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, d.getCodigoPrestamo());
            ps.setString(2, d.getCodigoLibro());
            ps.setInt(3, d.getCantidad());
            ps.setString(4, d.getFechaEntrega());
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

    public List buscarCodigoPrestamo(String codigo) {
        String sql = "select * from DETALLEPRESTAMO where CODIGOPRESTAMO = '" + codigo + "'";
        List<DetallePrestamo> lista = new ArrayList<>();
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                DetallePrestamo d = new DetallePrestamo();
                d.setCodigoPrestamo(rs.getString(1));
                d.setCodigoLibro(rs.getString(2));
                d.setCantidad(rs.getInt(3));
                d.setFechaEntrega(rs.getString(4));
                lista.add(d);
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

    public List buscarRangoFecha(String fInicio, String fFinal) {
        List<DetallePrestamo> lista = new ArrayList<>();
        String sql = "select * from detalleprestamo " +
                "where to_date(fechaentregaprestamo, 'YYYY-MM-DD') between TO_DATE('"+fInicio+"', 'YYYY-MM-DD') AND TO_DATE('"+fFinal+"', 'YYYY-MM-DD')";
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                DetallePrestamo d = new DetallePrestamo();
                d.setCodigoPrestamo(rs.getString(1));
                d.setCodigoLibro(rs.getString(2));
                d.setCantidad(rs.getInt(3));
                d.setFechaEntrega(rs.getString(4));
                lista.add(d);
            }
        } catch (Exception e) {
        }
        return lista;
    }

    public List buscarRangoFechaSinDupli(String fInicio, String fFinal) {
        List<DetallePrestamo> lista = new ArrayList<>();
        String sql = "select distinct codigoprestamo from detalleprestamo "
                +"where to_date(fechaentregaprestamo, 'YYYY-MM-DD') between TO_DATE('"+fInicio+"', 'YYYY-MM-DD') AND TO_DATE('"+fFinal+"', 'YYYY-MM-DD')";
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                DetallePrestamo d = new DetallePrestamo();
                d.setCodigoPrestamo(rs.getString(1));
                lista.add(d);
            }
        } catch (Exception e) {
        }
        return lista;
    }
    
}
