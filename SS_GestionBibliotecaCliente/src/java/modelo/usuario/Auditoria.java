/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.usuario;

/**
 *
 * @author alexg
 */
public class Auditoria {
    private String usuario;
    private String pantalla;
    private String hora;
    private String codigoUsuario;

    public Auditoria() {
    }

    public Auditoria(String usuario, String pantalla, String hora, String codigoUsuario) {
        this.usuario = usuario;
        this.pantalla = pantalla;
        this.hora = hora;
        this.codigoUsuario = codigoUsuario;
    }

    

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPantalla() {
        return pantalla;
    }

    public void setPantalla(String pantalla) {
        this.pantalla = pantalla;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(String codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }
    
    
}
