/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador.contabilidad;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.contabilidad.Comprobante;
import modelo.contabilidad.DetalleComprobante;
import modelo.contabilidad.dao.ComprobanteDAO;
import modelo.contabilidad.dao.DetalleComprobanteDAO;

/**
 *
 * @author alexg
 */
@WebServlet(name = "ControladorComprobante", urlPatterns = {"/ControladorComprobante"})
public class ControladorComprobante extends HttpServlet {

    ComprobanteDAO comprobanteDAO = new ComprobanteDAO();
    Comprobante comprobante = new Comprobante();
    DetalleComprobanteDAO detalleDAO = new DetalleComprobanteDAO();
    DetalleComprobante detalle = new DetalleComprobante();
    
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
            out.println("<title>Servlet ControladorComprobante</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorComprobante at " + request.getContextPath() + "</h1>");
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
            /*CABECERA*/
            String codigo = request.getParameter("codigoComprobante");
            String fecha = request.getParameter("fechaComprobante");
            String observacion = request.getParameter("observacionesComprobante");
            comprobante.setCodigoComprobante(codigo);
            comprobante.setFecha(fecha);
            comprobante.setObservaciones(observacion);
            int r = comprobanteDAO.agregar(comprobante);
            
            /*Detalle comprobante*/
            String [] cuentas = request.getParameterValues("cuentaComprobante");
            String [] debe = request.getParameterValues("cDebeComprobante");
            String [] haber = request.getParameterValues("cHaberComprobante");
            comprobante.setCodigoComprobante(cuentas[0]);
            comprobante.setFecha(debe[0]);
            comprobante.setObservaciones(haber[0]);
            //int r = comprobanteDAO.agregar(comprobante);
            
            for (int i = 0; i < cuentas.length; i++) {
                detalle.setCodigoComprobante(codigo);
                detalle.setCodigoCuenta(cuentas[i]);
                detalle.setDebeDetalle(Float.parseFloat(debe[i]));
                detalle.setHaberDetalle(Float.parseFloat(haber[i]));
                r = detalleDAO.agregar(detalle);
            }
             
            response.sendRedirect("vistas/contabilidad/Comprobantes.jsp");
            
        }/*else if(accion.compareTo("Borrar")==0){         
            String id = request.getParameter("id");//id codigo del tipo cuenta
            dao.borrar(id);
            response.sendRedirect("vistas/contabilidad/Cuenta.jsp");
            
        }*/else{
            response.sendRedirect("vistas/contabilidad/Comprobantes.jsp");
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
