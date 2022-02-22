/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.biblioteca;

/**
 *
 * @author alexg
 */
public class DetallePrestamo {
    private String codigoPrestamo;
    private String codigoLibro;
    private int cantidad;
    private String fechaEntrega;

    public DetallePrestamo() {
    }

    public DetallePrestamo(String codigoPrestamo, String codigoLibro, int cantidad, String fechaEntrega) {
        this.codigoPrestamo = codigoPrestamo;
        this.codigoLibro = codigoLibro;
        this.cantidad = cantidad;
        this.fechaEntrega = fechaEntrega;
    }

    public String getCodigoPrestamo() {
        return codigoPrestamo;
    }

    public void setCodigoPrestamo(String codigoPrestamo) {
        this.codigoPrestamo = codigoPrestamo;
    }

    

    public String getCodigoLibro() {
        return codigoLibro;
    }

    public void setCodigoLibro(String codigoLibro) {
        this.codigoLibro = codigoLibro;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

}
