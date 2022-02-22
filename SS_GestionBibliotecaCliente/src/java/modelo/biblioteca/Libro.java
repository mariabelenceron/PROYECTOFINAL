/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.biblioteca;

/**
 *
 * @author 
 */
public class Libro {
    private String codigoLibro;
    private String codigoAutor;
    private String ISBN;
    private String titulo;
    private float valorPrestamo;

    public Libro() {
    }

    public Libro(String codigoLibro, String codigoAutor, String ISBN, String titulo, float valorPrestamo) {
        this.codigoLibro = codigoLibro;
        this.codigoAutor = codigoAutor;
        this.ISBN = ISBN;
        this.titulo = titulo;
        this.valorPrestamo = valorPrestamo;
    }

    public String getCodigoLibro() {
        return codigoLibro;
    }

    public void setCodigoLibro(String codigoLibro) {
        this.codigoLibro = codigoLibro;
    }

    public String getCodigoAutor() {
        return codigoAutor;
    }

    public void setCodigoAutor(String codigoAutor) {
        this.codigoAutor = codigoAutor;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public float getValorPrestamo() {
        return valorPrestamo;
    }

    public void setValorPrestamo(float valorPrestamo) {
        this.valorPrestamo = valorPrestamo;
    }
    
    
}
