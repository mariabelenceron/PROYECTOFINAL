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
import modelo.biblioteca.Autor;
import modelo.biblioteca.dao.AutorDAO;

/**
 *
 * @author alexg
 */
@WebServlet(name = "ControladorAutor", urlPatterns = {"/ControladorAutor"})
public class ControladorAutor extends HttpServlet {

    AutorDAO dao = new AutorDAO();
    Autor a = new Autor();
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
            out.println("<title>Servlet ControladorAutor</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorAutor at " + request.getContextPath() + "</h1>");
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
        
        if(accion.compareTo("Guardar")==0){
            String codigo = request.getParameter("codigoAutor");
            String nombre = request.getParameter("nombreAutor");
            String apellido = request.getParameter("apellidoAutor");
            a.setCodigo(codigo);
            a.setNombre(nombre);
            a.setApellido(apellido);
            int r = dao.agregar(a);
            response.sendRedirect("vistas/biblioteca/Autor.jsp");
            
        }else if(accion.compareTo("Borrar")==0){         
            String id = request.getParameter("id");//id codigo del autor
            dao.borrar(id);
            response.sendRedirect("vistas/biblioteca/Autor.jsp");
            
        }else if(accion.compareTo("Actualizar")==0){
            String codigo = request.getParameter("codigoAutor1");
            String nombre = request.getParameter("nombreAutor1");
            String apellido = request.getParameter("apellidoAutor1");
            /*if(codigo.compareTo("A0002")==0){
                response.sendRedirect("vistas/biblioteca/Libro.jsp");
            }*/
            a.setCodigo(codigo);
            a.setNombre(nombre);
            a.setApellido(apellido);
            int r = dao.actualizar(a);
            response.sendRedirect("vistas/biblioteca/Autor.jsp");
        }
        else{
            response.sendRedirect("vistas/biblioteca/Autor.jsp");
        }
        
            
        
        /*List<Autor>datos =dao.listar();
        request.setAttribute("datos", datos);
        switch (accion){
            case "Listar":
                List<Autor>autores =dao.listar();
                request.setAttribute("autores", autores);
                request.getRequestDispatcher("vistas/biblioteca/Autor.jsp").forward(request, response);
                break;
            default:
                throw new AssertionError();
        }*/
        
        
        
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

}
