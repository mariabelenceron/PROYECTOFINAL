/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.usuario.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.bd.Conexion;
import modelo.usuario.Encrypt;
import modelo.usuario.Usuario;

/**
 *
 * @author alexg
 */
public class UsuarioDao {

    PreparedStatement ps;
    ResultSet rs;
    Conexion c = new Conexion();
    Connection con;

    public List listar() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM USUARIO";

        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setCodigo(rs.getString(1));
                u.setNombre(rs.getString(2));
                u.setCorreo(rs.getString(3));
                u.setCedula(rs.getString(4));
                u.setDesde(rs.getString(7));
                u.setHasta(rs.getString(8));
                lista.add(u);
            }
        } catch (Exception e) {
        }
        return lista;
    }

    public int agregar(Usuario u) {
        int respuesta = 0;
        String sql = "INSERT INTO USUARIO (CODIGOUSUARIO, NOMBREUSUARIO, CORREOUSUARIO, CEDULAUSUARIO, CAMBIOUSUARIO, PASSWORDUSUARIO, SESION_DESDE, SESION_HASTA, SESION_ACTIVA)"
                + " VALUES(?,?,?,?,?,?,?,?,?)";
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, u.getCodigo());
            ps.setString(2, u.getNombre());
            ps.setString(3, u.getCorreo());
            ps.setString(4, u.getCedula());
            ps.setInt(5, u.getCambio());
            ps.setString(6, u.getPassword());
            ps.setString(7, u.getDesde());
            ps.setString(8, u.getHasta());
            ps.setInt(9,0);
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

    public int actualizar(Usuario u) {
        
        String sql = "UPDATE USUARIO SET NOMBREUSUARIO = '"+u.getNombre()+"', CORREOUSUARIO = '"+u.getCorreo()+"', CEDULAUSUARIO = '"+u.getCedula()+"', CAMBIOUSUARIO = '"+u.getCambio()+"', PASSWORDUSUARIO = '"+u.getPassword()+"', SESION_DESDE = '"+u.getDesde()+"', SESION_HASTA = '"+u.getHasta()+"', SESION_ACTIVA = '0' WHERE codigousuario='"+u.getCodigo()+"'";
        int respuesta = 0;
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
        } catch (Exception e) {
        }
        return respuesta;
    }

    public void borrar(String codigo) {
        String sql = "DELETE FROM USUARIO WHERE CODIGOUSUARIO='" + codigo + "'";
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            ps.executeQuery();
        } catch (Exception e) {
        }
    }

    public Usuario buscarID(String id) {
        String sql = "SELECT * FROM USUARIO where CODIGOUSUARIO= '" + id + "'";
        Usuario u = new Usuario();
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                u.setCodigo(rs.getString(1));
                u.setNombre(rs.getString(2));
                u.setCorreo(rs.getString(3));
                u.setCedula(rs.getString(4));
                u.setCambio(rs.getInt(5));
                u.setPassword(rs.getString(6));
                u.setDesde(rs.getString(7));
                u.setHasta(rs.getString(8));
                u.setSesion(rs.getInt(9));
            }
        } catch (Exception e) {
        }
        return u;
    }
    /*public Usuario buscarCodigo(String codigo) {
        String sql = "SELECT * FROM USUARIO where CODIGOUSUARIO= '" + codigo + "'";
        Usuario u = new Usuario();
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                u.setCodigo(rs.getString(1));
                u.setCorreo(rs.getString(2));
                u.setPassword(rs.getString(3));
            }
        } catch (Exception e) {
        }
        return u;
    }*/
    public Usuario buscarCorreo(String correo) {
        String sql = "SELECT * FROM USUARIO where CORREOUSUARIO= '" + correo + "'";
        Usuario u = new Usuario();
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                u.setCodigo(rs.getString(1));
                u.setNombre(rs.getString(2));
                u.setCorreo(rs.getString(3));
                u.setCedula(rs.getString(4));
                u.setCambio(rs.getInt(5));
                u.setPassword(rs.getString(6));
                u.setDesde(rs.getString(7));
                u.setHasta(rs.getString(8));
                u.setSesion(rs.getInt(9));
            }
        } catch (Exception e) {
        }
        return u;
    }

    public boolean login(String user, String pass) {
        Encrypt encry = new Encrypt();
        String passEn = encry.getAES(pass);
        String sql = "SELECT COUNT(*) FROM USUARIO where CORREOUSUARIO='" + user + "' and passwordusuario='" + passEn + "'";
        int respuesta = 0;
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                respuesta = rs.getInt(1);
            }
        } catch (Exception e) {
        }

        return respuesta == 1;
    }

    public List buscarNombre(String nombre) {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM USUARIO WHERE USERUSUARIO like '%" + nombre + "%'";

        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Usuario u = new Usuario();
                ps.setString(1, u.getCodigo());
                ps.setString(2, u.getNombre());
                ps.setString(3, u.getCorreo());
                ps.setString(4, u.getCedula());
                ps.setInt(5, u.getCambio());
                ps.setString(6, u.getPassword());
                ps.setString(7, u.getDesde());
                ps.setString(8, u.getHasta());
                lista.add(u);
            }
        } catch (Exception e) {
        }
        return lista;
    }

    public boolean accesoHoraAGCA(String correo) {
        String sql = "SELECT * FROM USUARIO where CORREOUSUARIO= '" + correo + "'";
        Usuario u = new Usuario();
        String sesion_desde = "";
        String sesion_hasta = "";
        boolean respuesta = false;
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ps.setString(1, u.getCodigo());
                ps.setString(2, u.getNombre());
                ps.setString(3, u.getCorreo());
                ps.setString(4, u.getCedula());
                ps.setInt(5, u.getCambio());
                ps.setString(6, u.getPassword());
                ps.setString(7, u.getDesde());
                ps.setString(8, u.getHasta());
            }
        } catch (Exception e) {
        }

        Date hora_actual = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("hh:mm:ss");
        String hora = formateador.format(hora_actual);
        try {
            //0 para iguales
            // 1 para mayor
            // -1 para menor

            Date hora_inicial = formateador.parse(u.getDesde().trim());
            Date hora_final = formateador.parse(u.getHasta().trim());
            Date horaDate = formateador.parse(hora.trim());
            boolean comparcionInicial = horaDate.compareTo(hora_inicial) == 1 || horaDate.compareTo(hora_inicial) == 0;
            boolean comparacionFinal = horaDate.compareTo(hora_final) == -1;

            if (comparcionInicial && comparacionFinal) {
                respuesta = true;
            }

        } catch (ParseException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }
    
    public void cambioPass(String codigo, String pass){
        Encrypt encry = new Encrypt();
        String passEn = encry.getAES(pass);
        String sql = "UPDATE usuario SET passwordusuario='" + passEn + "', cambiousuario=1  where codigousuario='" + codigo + "'";
        int respuesta = 0;
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
        } catch (Exception e) {
        }
    }
    
    public void limiteSesion(String codigo, int num){
        String sql = "UPDATE usuario SET sesion_activa=" + num + "  where codigousuario='" + codigo + "'";
        int respuesta = 0;
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
        } catch (Exception e) {
        }
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
            sql = "SELECT COUNT(*)FROM USUARIO WHERE CODIGOUSUARIO= '" + codigo + "'";
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
            return "U000" + contador;
        } else if (contador < 100) {
            return "U00" + contador;
        } else if (contador < 1000) {
            return "U0" + contador;
        } else {
            return "U" + contador;
        }
    }
    
    private int contadorFilas() {
        String sql = "SELECT COUNT(*)FROM USUARIO";
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
