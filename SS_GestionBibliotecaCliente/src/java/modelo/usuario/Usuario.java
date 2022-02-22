/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.usuario;

/**
 *
 * @author mabel
 */
public class Usuario {
    private String codigo;
    private String nombre;
    private String correo;
    private String cedula;
    private int cambio;
    private String password;
    private String desde;
    private String hasta;
    private int sesion;

    
    public Usuario() {
    }

    public Usuario(String codigo, String nombre, String correo, String cedula, int cambio, String password, String desde, String hasta) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.correo = correo;
        this.cedula = cedula;
        this.cambio = cambio;
        this.password = password;
        this.desde = desde;
        this.hasta = hasta;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public int getCambio() {
        return cambio;
    }

    public void setCambio(int cambio) {
        this.cambio = cambio;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDesde() {
        return desde;
    }

    public void setDesde(String desde) {
        this.desde = desde;
    }

    public String getHasta() {
        return hasta;
    }

    public void setHasta(String hasta) {
        this.hasta = hasta;
    }

    public int getSesion() {
        return sesion;
    }

    public void setSesion(int sesion) {
        this.sesion = sesion;
    }
    
}
