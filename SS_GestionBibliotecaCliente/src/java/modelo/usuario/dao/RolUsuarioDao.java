/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.usuario.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import modelo.bd.Conexion;
import modelo.usuario.RolUsuario;
import modelo.usuario.Usuario;

/**
 *
 * @author alexg
 */
public class RolUsuarioDao {
    PreparedStatement ps;
    ResultSet rs;
    Conexion c =new Conexion();
    Connection con;
    
    /*public List listar(){
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM USUARIO";
        
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {                
                Usuario u = new Usuario();
                u.setCodigo(rs.getString(1));
                u.setUsuario(rs.getString(2));
                u.setPassword(rs.getString(3));
                lista.add(u);  
            }
        } catch (Exception e) {
        }
        return lista;
    }*/
    
    public int agregar(RolUsuario ru){
        int respuesta =0;
        String sql = "INSERT INTO ROLUSUARIO (CODIGOROL, CODIGOUSERROLU, ROLESROLU)"
                + " VALUES(?,?,?)";
        try{
            con = c.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, ru.getCodigoRol());
            ps.setString(2, ru.getCodigoUsuario());
            ps.setString(3, ru.getRoles());
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
    
    public void borrar(String codigo){
        String sql = "DELETE FROM ROLUSUARIO WHERE CODIGOUSUARIO='"+codigo+"'";
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            ps.executeQuery();
        } catch (Exception e) {
        }
    }
    
    
    public RolUsuario buscarCodigoUsuario(String codigo){
        String sql = "SELECT * FROM ROLUSUARIO where CODIGOUSERROLU= '"+codigo+"'";
        RolUsuario ru = new RolUsuario();
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {                
                ru.setCodigoRol(rs.getString(1));
                ru.setCodigoUsuario(rs.getString(2));
                ru.setRoles(rs.getString(3));
            }
        } catch (Exception e) {
        }
        return ru;
    }
    
    public HashMap<String,Integer> roles(String codigo){
        HashMap<String,Integer> roles = new HashMap<String,Integer>();
        RolUsuario ru = buscarCodigoUsuario(codigo);
        //ejemplo
        //USU,1;TC,1;CUE,1;COM,1;RC,1;AUT,1;LIB,1;PRE,1;RB,1;
        String[] rol = ru.getRoles().split(";");
        for (int i = 0; i < rol.length; i++) {
            String[] rolAux = rol[i].split(",");
            roles.put(rolAux[0],Integer.parseInt(rolAux[1]));   
        }
        return roles;
    }
    
    public boolean login(String user, String pass){
        String sql = "SELECT COUNT(*) FROM USUARIO where userusuario='"+user+"' and passwordusuario='"+pass+"'";
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
        
        return respuesta==1;

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
            sql = "SELECT COUNT(*)FROM ROLUSUARIO WHERE CODIGOROL= '" + codigo + "'";
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
            return "RU00" + contador;
        } else if (contador < 100) {
            return "RU0" + contador;
        } else {
            return "RU" + contador;
        }
    }
    
    private int contadorFilas() {
        String sql = "SELECT COUNT(*)FROM ROLUSUARIO";
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
    /*public List buscarNombre(String nombre){
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM USUARIO WHERE USERUSUARIO like '%"+nombre+"%'";
        
        try {
            con = c.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {                
                Usuario u = new Usuario();
                u.setCodigo(rs.getString(1));
                u.setUsuario(rs.getString(2));
                u.setPassword(rs.getString(3));
                lista.add(u); 
            }
        } catch (Exception e) {
        }
        return lista;
    }*/
}
