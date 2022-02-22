/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.contabilidad;

/**
 *
 * @author alexg
 */
public class DetalleComprobante{
    //private String codigoDetalle;
    private String codigoComprobante;
    private String codigoCuenta;
    private float debeDetalle;
    private float haberDetalle;

    public DetalleComprobante() {
    }

    public DetalleComprobante(String codigoComprobante, String codigoCuenta, float debeDetalle, float haberDetalle) {
        this.codigoComprobante = codigoComprobante;
        this.codigoCuenta = codigoCuenta;
        this.debeDetalle = debeDetalle;
        this.haberDetalle = haberDetalle;
    }
    
    public String getCodigoComprobante() {
        return codigoComprobante;
    }

    public void setCodigoComprobante(String codigoComprobante) {
        this.codigoComprobante = codigoComprobante;
    }

    public String getCodigoCuenta() {
        return codigoCuenta;
    }

    public void setCodigoCuenta(String codigoCuenta) {
        this.codigoCuenta = codigoCuenta;
    }

    public float getDebeDetalle() {
        return debeDetalle;
    }

    public void setDebeDetalle(float debeDetalle) {
        this.debeDetalle = debeDetalle;
    }

    public float getHaberDetalle() {
        return haberDetalle;
    }

    public void setHaberDetalle(float haberDetalle) {
        this.haberDetalle = haberDetalle;
    }
}
