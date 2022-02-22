/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.biblioteca;

/**
 *
 * @author alexg
 */
public class Prestamo {
    private String codigoPrestamo;
    private String fechaPrestamo;
    private String nombreCliente;
    private String descripcion;
    
    public Prestamo() {
    }

    public Prestamo(String codigoPrestamo, String fechaPrestamo, String nombreCliente, String descripcion) {
        this.codigoPrestamo = codigoPrestamo;
        this.fechaPrestamo = fechaPrestamo;
        this.nombreCliente = nombreCliente;
        this.descripcion = descripcion;
    }

    

    public String getCodigoPrestamo() {
        return codigoPrestamo;
    }

    public void setCodigoPrestamo(String codigoPrestamo) {
        this.codigoPrestamo = codigoPrestamo;
    }

    public String getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(String fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
