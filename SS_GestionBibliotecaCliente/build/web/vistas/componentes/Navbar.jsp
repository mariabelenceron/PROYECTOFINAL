<%-- 
    Document   : Navbar
    Created on : 29 nov. 2021, 11:18:43
    Author     : mabel
--%>

<%@page import="modelo.usuario.dao.UsuarioDao"%>
<%@page import="modelo.usuario.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%>
<!DOCTYPE html>
<html>
    <%
        HttpSession sesionN = request.getSession();
        Usuario usuario = (Usuario) sesionN.getAttribute("USUARIO");
        UsuarioDao userDao = new UsuarioDao();
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom">
            <div class="container">
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav ml-auto mt-2 mt-lg-0">
                        <form action="#" method="POST">
                            <li class="nav-item dropdown">
                                <a
                                    class="nav-link text-dark dropdown-toggle"
                                    href="#"
                                    id="navbarDropdown"
                                    role="button"
                                    data-toggle="dropdown"
                                    aria-haspopup="true"
                                    aria-expanded="false"

                                    ><i class="fas fa-user-circle"></i><%out.write(usuario.getNombre());%></a>
                                <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown">
                                    <button type="submit" name="cambiopass" class="dropdown-item"> <i class="uil uil-exchange"></i> Cambio contraseña</button>
                                    <button type="submit" name="cerrarsesion" class="dropdown-item"> <i class="fas fa-sign-out-alt"></i> Cerrar sesión</button>
                                </div>

                                <%
                                    if (request.getParameter("cerrarsesion") != null) {
                                        int cerrar = 0;
                                        sesionN.setAttribute("CERRAR", cerrar);
                                        //int num = usuario.getSesion() - 1;
                                        //userDao.limiteSesion(usuario.getCodigo(), num);
                                        response.sendRedirect("../index.jsp");
                                    }
                                    if (request.getParameter("cambiopass") != null) {
                                        response.sendRedirect("../vistas/cambiarPassword.jsp");
                                    }
                                %>


                            </li>
                        </form>
                    </ul>
                </div>
            </div>
        </nav><!-- Fin Navbar<a class="dropdown-item" href="../../index.jsp"> <i class="fas fa-sign-out-alt"></i> Cerrar sesión</a> -->
    </body>
</html>
