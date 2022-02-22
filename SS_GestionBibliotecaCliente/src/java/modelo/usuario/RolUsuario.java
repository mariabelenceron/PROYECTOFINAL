/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.usuario;

/**
 *
 * @author alexg
 */
public class RolUsuario {
    private String codigoRol;
    private String codigoUsuario;
    private String roles;

    public RolUsuario() {
    }

    public RolUsuario(String codigoRol, String codigoUsuario, String roles) {
        this.codigoRol = codigoRol;
        this.codigoUsuario = codigoUsuario;
        this.roles = roles;
    }

    public String getCodigoRol() {
        return codigoRol;
    }

    public void setCodigoRol(String codigoRol) {
        this.codigoRol = codigoRol;
    }

    public String getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(String codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
    
    
}
