/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador.usuario;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.usuario.Encrypt;
import modelo.usuario.RolUsuario;
import modelo.usuario.Usuario;
import modelo.usuario.dao.RolUsuarioDao;
import modelo.usuario.dao.UsuarioDao;

/**
 *
 * @author alexg
 */
@WebServlet(name = "ControladorUsuario", urlPatterns = {"/ControladorUsuario"})
public class ControladorUsuario extends HttpServlet {

    UsuarioDao userDao = new UsuarioDao();

    RolUsuarioDao rolDao = new RolUsuarioDao();

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
            out.println("<title>Servlet ControladorUsuario</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorUsuario at " + request.getContextPath() + "</h1>");
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

        if (accion.compareTo("Registrar") == 0) {

            String codigo = request.getParameter("codigoUsuario");
            String cedula = request.getParameter("cedulaUsuario");
            String nombre = request.getParameter("nombreUsuario");
            String correo = request.getParameter("correoUsuario");
            String desde = request.getParameter("sesionDesdeUsuario");
            String hasta = request.getParameter("sesionHastaUsuario");
            int cambio = 0;
            String[] parteCorreo = correo.split("[@]");
            String pass = parteCorreo[0];
            Encrypt encry = new Encrypt();
            String passEn = encry.getAES(pass);
            Usuario user = new Usuario(codigo, nombre, correo, cedula, cambio, passEn, desde, hasta);
            userDao.agregar(user);
            //roles
            String codigoRol = rolDao.condigo();
            String[] usu = request.getParameterValues("checkUsuarios");//1
            String[] tc = request.getParameterValues("checkTipCue");//2
            String[] cue = request.getParameterValues("checkCuenta");//3
            String[] com = request.getParameterValues("checkComprobantes");//4
            String[] rc = request.getParameterValues("checkReportesC");//5
            String[] aut = request.getParameterValues("checkAutor");//6
            String[] lib = request.getParameterValues("checkLibro");//7
            String[] pre = request.getParameterValues("checkPrestamos");//8
            String[] rb = request.getParameterValues("checkReportesB");//9*/

            //USU,1; TC,1; CUE,1; COM,1; RC,1; AUT,1; LIB,1; PRE,1; RB,1;
            String roles = "USU," + usu[0]
                    + ";TC," + tc[0] + ";CUE," + cue[0] + ";COM," + com[0] + ";RC," + rc[0]
                    + ";AUT," + aut[0] + ";LIB," + lib[0] + ";PRE," + pre[0] + ";RB," + rb[0] + ";";

            RolUsuario rol = new RolUsuario(codigoRol, codigo, roles);

            rolDao.agregar(rol);
            response.sendRedirect("vistas/Usuarios.jsp");

        }else if(accion.compareTo("Borrar")==0){         
            String id = request.getParameter("id");//id codigo del autor
            userDao.borrar(id);
            response.sendRedirect("vistas/Usuarios.jsp");
        }else if(accion.compareTo("Editar")==0){
            String id = request.getParameter("id");
            response.sendRedirect("vistas/UsuariosActualizar.jsp?idC="+id);
        }else if (accion.compareTo("Actualizar") == 0) {

            String codigo = request.getParameter("codigoUsuario");
            String cedula = request.getParameter("cedulaUsuario");
            String nombre = request.getParameter("nombreUsuario");
            String correo = request.getParameter("correoUsuario");
            String desde = request.getParameter("sesionDesdeUsuario");
            String hasta = request.getParameter("sesionHastaUsuario");
            int cambio = 0;
            String[] parteCorreo = correo.split("[@]");
            String pass = parteCorreo[0];
            Encrypt encry = new Encrypt();
            String passEn = encry.getAES(pass);
            Usuario user = new Usuario(codigo, nombre, correo, cedula, cambio, passEn, desde, hasta);
            userDao.actualizar(user);
            //roles
            String codigoRol = rolDao.condigo();
            String[] usu = request.getParameterValues("checkUsuarios");//1
            String[] tc = request.getParameterValues("checkTipCue");//2
            String[] cue = request.getParameterValues("checkCuenta");//3
            String[] com = request.getParameterValues("checkComprobantes");//4
            String[] rc = request.getParameterValues("checkReportesC");//5
            String[] aut = request.getParameterValues("checkAutor");//6
            String[] lib = request.getParameterValues("checkLibro");//7
            String[] pre = request.getParameterValues("checkPrestamos");//8
            String[] rb = request.getParameterValues("checkReportesB");//9*/

            //USU,1; TC,1; CUE,1; COM,1; RC,1; AUT,1; LIB,1; PRE,1; RB,1;
            String roles = "USU," + usu[0]
                    + ";TC," + tc[0] + ";CUE," + cue[0] + ";COM," + com[0] + ";RC," + rc[0]
                    + ";AUT," + aut[0] + ";LIB," + lib[0] + ";PRE," + pre[0] + ";RB," + rb[0] + ";";

            RolUsuario rol = new RolUsuario(codigoRol, codigo, roles);

            rolDao.agregar(rol);
            response.sendRedirect("vistas/Usuarios.jsp");

        }
        else {
            response.sendRedirect("vistas/Usuarios.jsp");
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

}
