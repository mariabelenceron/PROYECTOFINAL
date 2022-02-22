<%-- 
    Document   : sidebarBiblioteca
    Created on : 29 nov. 2021, 11:07:37
    Author     : mabel
--%>

<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%>
<!DOCTYPE html>
<html>
    <%
        HttpSession sesion = request.getSession();
        HashMap<String, Integer> roles = (HashMap) sesion.getAttribute("ROLES");
        Calendar calendario = new GregorianCalendar();
        int hora = calendario.get(Calendar.HOUR_OF_DAY);
        int minutos = calendario.get(Calendar.MINUTE);
        int segundos = calendario.get(Calendar.SECOND);
        
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div id="sidebar-container" class="bg-primary">
            <!-- LOGO  -->
            <div class="logo d-flex">
                <a href="../Home.jsp">
                    <h4 class="text-light font-weight-bold mb-0"><i class="fas fa-book-open"></i> BIBLIOTECA</h4>
                </a>
            </div><!-- FIN LOGO  -->

            <!-- MENU  -->
            <div class="menu">
                <div class="hover-sidebar">
                    <a href="../Home.jsp" class="d-block text-light p-3 border-0"
                       ><i class="fas fa-home lead mr-2"></i> Inicio</a
                    >
                </div>

                <%
                    ////USU,1; TC,1; CUE,1; COM,1; RC,1; AUT,1; LIB,1; PRE,1; RB,1;
                    if (roles.get("USU") == 1) {
                %>

                <div class="hover-sidebar">
                    <a href="../Usuarios.jsp" class="d-block text-light p-3 border-0"
                       ><i class="fas fa-users lead mr-2"></i> Usuarios</a
                    >
                </div>
                <%}%>

                <!-- CONTABILIDAD -->
                <div class="subtitle-left">
                    <h5 class="subTitles">Contabilidad</h5>
                    <%
                        ////USU,1; TC,1; CUE,1; COM,1; RC,1; AUT,1; LIB,1; PRE,1; RB,1;
                        if (roles.get("TC") == 1) {
                    %>
                    <div class="hover-sidebar">
                        <a href="../contabilidad/TipoCuenta.jsp" class="d-block text-light p-3 border-0">
                            <i class="ion-pricetags lead mr-2"></i>
                            Tipo de cuenta
                        </a>
                    </div>
                    <%}%>
                    <%
                        ////USU,1; TC,1; CUE,1; COM,1; RC,1; AUT,1; LIB,1; PRE,1; RB,1;
                        if (roles.get("CUE") == 1) {
                    %>
                    <div class="hover-sidebar">
                        <a href="../contabilidad/Cuenta.jsp" class="d-block text-light p-3 border-0">
                            <i class="ion-pricetag lead mr-2"></i>
                            Cuenta
                        </a>
                    </div>
                    <%}%>
                    <%
                        ////USU,1; TC,1; CUE,1; COM,1; RC,1; AUT,1; LIB,1; PRE,1; RB,1;
                        if (roles.get("COM") == 1) {
                    %>
                    <div class="hover-sidebar">
                        <a href="../contabilidad/Comprobantes.jsp" class="d-block text-light p-3 border-0">
                            <i class="fas fa-receipt lead mr-2"></i>
                            Comprobantes
                        </a>
                    </div>
                    <%}%>
                    <%
                        ////USU,1; TC,1; CUE,1; COM,1; RC,1; AUT,1; LIB,1; PRE,1; RB,1;
                        if (roles.get("RC") == 1) {
                    %>
                    <ul class="d-block text-light p-3 border-0 m-0">
                        <li class="dropdown list-unstyled">
                            <a
                                class="dropdown-toggle d-block text-light border-0 "
                                href="#"
                                id="navbarDropdown"
                                role="button"
                                data-toggle="dropdown"
                                aria-haspopup="true"
                                aria-expanded="false"
                                ><i class="uil uil-folder lead mr-0"></i> Reportes</a>
                            <div class="dropdown-menu dropdown-menu-bottom" aria-labelledby="navbarDropdown">
                                <a href="../contabilidad/BalanceGeneral.jsp" class="dropdown-item"
                                   ><i class="uil uil-chart-growth lead mr-2"></i> Balance General</a
                                >
                                <a href="../contabilidad/EstadoResultado.jsp" class="dropdown-item"
                                   ><i class="fas fa-window-restore lead mr-2"></i> Estado Resultados</a
                                >
                            </div>
                        </li>
                    </ul>
                    <%}%>
                </div><!-- FIN CONTABILIDAD -->

                <!-- BIBLIOTECA -->
                <div class="subtitle-left">
                    <h5 class="subTitles">Biblioteca</h5>
                    <div class="hover-sidebar">
                        <%
                            ////USU,1; TC,1; CUE,1; COM,1; RC,1; AUT,1; LIB,1; PRE,1; RB,1;
                            if (roles.get("AUT") == 1) {
                        %>
                        <a href="../biblioteca/Autor.jsp" class="d-block text-light p-3 border-0">
                            <i class="fas fa-user-alt lead mr-2"></i>
                            Autor
                        </a>
                    </div>
                    <%}%>
                    <%
                        ////USU,1; TC,1; CUE,1; COM,1; RC,1; AUT,1; LIB,1; PRE,1; RB,1;
                        if (roles.get("LIB") == 1) {
                    %>
                    <div class="hover-sidebar">
                        <a href="../biblioteca/Libro.jsp" class="d-block text-light p-3 border-0">
                            <i class="fas fa-book lead mr-2"></i>
                            Libro
                        </a>
                    </div>
                    <%}%>
                    <%
                        ////USU,1; TC,1; CUE,1; COM,1; RC,1; AUT,1; LIB,1; PRE,1; RB,1;
                        if (roles.get("PRE") == 1) {
                    %>
                    <div class="hover-sidebar">
                        <a href="../biblioteca/Prestamos.jsp" class="d-block text-light p-3 border-0">
                            <i class="fuil uil-books lead mr-2"></i>
                            Prestamos
                        </a>
                    </div>
                    <%}%>
                    <%
                        ////USU,1; TC,1; CUE,1; COM,1; RC,1; AUT,1; LIB,1; PRE,1; RB,1;
                        if (roles.get("RB") == 1) {
                    %>
                    <ul class="d-block text-light p-3 border-0 m-0">
                        <li class="dropdown list-unstyled">
                            <a
                                class="dropdown-toggle d-block text-light border-0 "
                                href="#"
                                id="navbarDropdown"
                                role="button"
                                data-toggle="dropdown"
                                aria-haspopup="true"
                                aria-expanded="false"
                                ><i class="uil uil-folder lead mr-0"></i> Reportes</a>
                            <div class="dropdown-menu dropdown-menu-bottom" aria-labelledby="navbarDropdown">
                                <a href="../biblioteca/Entregas.jsp" class="dropdown-item"
                                   ><i class="uil uil-parcel lead mr-2"></i> Entregas</a
                                >
                                <a href="../biblioteca/ReporteCruzado.jsp" class="dropdown-item"
                                   ><i class="fas fa-poll-h lead mr-2"></i> Reporte Cruzado</a
                                >
                            </div>
                        </li>
                    </ul>
                    <%}%>
                </div><!-- FIN BIBLIOTECA -->
                <div class="hover-sidebar">
                    <a href="../Auditoria.jsp" class="d-block text-light p-3 border-0"
                       ><i class="fas fa-poll-h lead mr-2"></i> Auditoria</a
                    >
                </div>
            </div><!-- FIN MENU  -->
        </div><!-- Fin Sidebar -->
    </body>
</html>
