<%-- 
    Document   : Home
    Created on : 23 nov. 2021, 18:36:35
    Author     : mabel
--%>

<%@page import="java.time.LocalDateTime"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>

        <link rel="shortcut icon" href="../img/icono.svg">
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.2/css/bootstrap.css">
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.24/css/dataTables.bootstrap4.min.css">
        <!-- Styles -->
        <link rel="stylesheet" href="../css/internalPagesStyle.css" />
        <!-- Icons -->
        <link href="http://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css" rel="stylesheet"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />
        <link rel="stylesheet" href="https://unicons.iconscout.com/release/v3.0.6/css/line.css">

        <title>Biblioteca</title>
    </head>

    <body>
        <%
            HttpSession sesionH = request.getSession();
            int cerrar1 = 1;
            if (sesionH.getAttribute("CERRAR") != null) {
                cerrar1 = (Integer) sesionH.getAttribute("CERRAR");
                if (cerrar1 == 0) {
                    response.sendRedirect("../index.jsp");
                }
            }

        %>
        <div class="d-flex" id="content-wrapper">
            <!-- Sidebar -->
            <%@include file="componentes/SidebarGeneral.jsp" %>

            <div class="w-100">
                <!-- Navbar -->
                <%@include file="componentes/NavbarGeneral.jsp" %>

                <!-- Contenido de la pagina -->
                <div id="content" class="w-100">
                    <section class="bg-light py-3">
                        <div class="container">
                            <div class="row">
                                <div class="col-lg-9 col-md-8">
                                    <h1 class="font-weight-bold mb-0">
                                        <%                                        //String usuario1 = (String) sesionH.getAttribute("USUARIO");
                                            out.write("Bienvenid@  " + usuario.getNombre());
                                        %>
                                    </h1>
                                </div>
                            </div>
                        </div>
                    </section>
                    <!-- Contenedor Cartas -->
                    <section class="py-3">
                        <div class="container">
                            <div class="d-flex justify-content-center">
                                <div class="">
                                    <img style="height: 487px;" src="../img/home.svg" alt="">
                                </div>
                                <div class="">
                                    <div class=" mb-3 p-5">
                                        <div class="d-flex justify-content-center g-0">
                                            <div class="align-self-center ml-5">
                                                <i class="fas fa-calendar-alt carta-icono"></i>
                                            </div>
                                            <div class="carta-detalles">
                                                <div class="card-body">
                                                    <h6 class="text-muted text-center">
                                                        Fecha Actual
                                                    </h6>
                                                    <h3 class="font-weight-bold text-center" id="fechaActual">
                                                        <%
                                                            java.util.Calendar fecha = java.util.Calendar.getInstance();
                                                            out.println(fecha.get(java.util.Calendar.DATE) + "/"
                                                                    + fecha.get(java.util.Calendar.MONTH) + "/"
                                                                    + fecha.get(java.util.Calendar.YEAR));
                                                        %>
                                                    </h3>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div><!-- Fin Carta -->
                            </div><!-- Fin Cartas -->
                        </div>
                    </section><!-- Fin Contenedor Cartas -->
                </div>
            </div>
        </div>
        <!-- Bootstrap -->
        <script
            src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"
        ></script>
        <script
            src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
            integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
            crossorigin="anonymous"
        ></script>
        <script
            src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"
        ></script>
        <script
            src="https://cdn.jsdelivr.net/npm/chart.js@2.9.3/dist/Chart.min.js"
            integrity="sha256-R4pqcOYV8lt7snxMQO/HSbVCFRPMdrhAFMH+vr9giYI="
            crossorigin="anonymous"
        ></script>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>

    </body>
</html>

