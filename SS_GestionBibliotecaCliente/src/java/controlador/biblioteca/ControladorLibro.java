/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador.biblioteca;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.biblioteca.Libro;
import modelo.biblioteca.dao.LibroDAO;

/**
 *
 * @author alexg
 */
@WebServlet(name = "ControladorLibro", urlPatterns = {"/ControladorLibro"})
public class ControladorLibro extends HttpServlet {

    LibroDAO dao = new LibroDAO();
    Libro l = new Libro();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControladorLibro</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorLibro at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if (accion.compareTo("Guardar") == 0) {
            String codigoLibro = request.getParameter("codigoLibro");
            String codigoAutor = request.getParameter("autorLibro");
            String isbn = request.getParameter("isbnLibro");
            String titulo = request.getParameter("tituloLibro");
            String valor = request.getParameter("valorPrestamoLibro");

            if (validarNumeroFloat(valor)) {
                float valorPrestamo = Float.parseFloat(valor);
                l.setCodigoLibro(codigoLibro);
                l.setCodigoAutor(codigoAutor);
                l.setISBN(isbn);
                l.setTitulo(titulo);
                l.setValorPrestamo(valorPrestamo);
                int r = dao.agregar(l);
                response.sendRedirect("vistas/biblioteca/Libro.jsp");
            } else {
                
                response.sendRedirect("vistas/biblioteca/Libro.jsp");
            }

        } else if (accion.compareTo("Borrar") == 0) {
            String id = request.getParameter("id");//id codigo del autor
            dao.borrar(id);
            response.sendRedirect("vistas/biblioteca/Libro.jsp");

        } else {
            response.sendRedirect("vistas/biblioteca/Libro.jsp");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private static boolean validarNumeroFloat(String numero) {
        boolean isValid = true;

        try {
            Double.parseDouble(numero);
        } catch (NumberFormatException nfe) {
            isValid = false;
        }

        return isValid;
    }
}
