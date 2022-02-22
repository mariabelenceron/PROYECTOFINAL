/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.usuario.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.bd.Conexion;
import modelo.usuario.Auditoria;
import modelo.usuario.Usuario;

/**
 *
 * @author alexg
 */
public class AuditoriaDao {
    
    PreparedStatement ps;
    ResultSet rs;
    Conexion c = new Conexion();
    Connection con;
    
    public List listar() {
        List<Auditoria> lista = new ArrayList<>();
        String sql = "SELECT * FROM AUDITORIA ORDER BY HORA";

        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Auditoria u = new Auditoria();
                u.setUsuario(rs.getString(1));
                u.setPantalla(rs.getString(2));
                u.setHora(rs.getString(3));
                u.setCodigoUsuario(rs.getString(4));
                lista.add(u);
            }
        } catch (Exception e) {
        }
        return lista;
    }
    
    public int agregar(Auditoria u) {
        int respuesta = 0;
        String sql = "INSERT INTO AUDITORIA (USUARIO, PANTALLA, HORA, CODIGOUSUARIO) VALUES(?,?,?,?)";
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, u.getUsuario());
            ps.setString(2, u.getPantalla());
            ps.setString(3, u.getHora());
            ps.setString(4, u.getCodigoUsuario());
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
}
