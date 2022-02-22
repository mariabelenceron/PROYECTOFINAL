/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.contabilidad;

/**
 *
 * @author alexg
 */
public class Comprobante {
    private String codigoComprobante;
    private String fecha;
    private String observaciones;

    public Comprobante() {
    }

    public Comprobante(String codigoComprobante, String fecha, String observaciones) {
        this.codigoComprobante = codigoComprobante;
        this.fecha = fecha;
        this.observaciones = observaciones;
    }

    public String getCodigoComprobante() {
        return codigoComprobante;
    }

    public void setCodigoComprobante(String codigoComprobante) {
        this.codigoComprobante = codigoComprobante;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }    
}
