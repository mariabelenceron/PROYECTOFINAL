/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.contabilidad;

/**
 *
 * @author alexg
 */
public class Cuenta {
    private String codigoCuenta;
    private String codigoTipoCuenta;
    private String nombre;

    public Cuenta() {
    }

    public Cuenta(String codigoCuenta, String codigoTipoCuenta, String nombre) {
        this.codigoCuenta = codigoCuenta;
        this.codigoTipoCuenta = codigoTipoCuenta;
        this.nombre = nombre;
    }

    public String getCodigoCuenta() {
        return codigoCuenta;
    }

    public void setCodigoCuenta(String codigoCuenta) {
        this.codigoCuenta = codigoCuenta;
    }

    public String getCodigoTipoCuenta() {
        return codigoTipoCuenta;
    }

    public void setCodigoTipoCuenta(String codigoTipoCuenta) {
        this.codigoTipoCuenta = codigoTipoCuenta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
