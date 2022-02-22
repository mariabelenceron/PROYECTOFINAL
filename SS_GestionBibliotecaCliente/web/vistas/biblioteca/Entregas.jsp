<%@page import="modelo.usuario.Auditoria"%>
<%@page import="modelo.usuario.dao.AuditoriaDao"%>
<%@page import="modelo.biblioteca.Libro"%>
<%@page import="modelo.biblioteca.dao.LibroDAO"%>
<%@page import="modelo.biblioteca.dao.AutorDAO"%>
<%@page import="modelo.biblioteca.Autor"%>
<%@page import="modelo.biblioteca.DetallePrestamo"%>
<%@page import="java.util.List"%>
<%@page import="modelo.biblioteca.dao.DetallePrestamoDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <link rel="shortcut icon" href="../../img/icono.svg">
        <%@include file="../componentes/linkStyles.jsp" %>

        <title>Biblioteca</title>
    </head>

    <body>
        <div class="d-flex" id="content-wrapper">
            <!-- Sidebar -->
            <%@include file="../componentes/Sidebar.jsp" %>

            <div class="w-100">
                <!-- Navbar -->
                <%@include file="../componentes/Navbar.jsp" %>

                <%  
                    AuditoriaDao auDao = new AuditoriaDao();
                    Auditoria au = new Auditoria(usuario.getNombre(), "ENTREGAS", hora + ":" + minutos + ":" + segundos, usuario.getCodigo());
                    auDao.agregar(au);
                %>
                <!-- Page Content -->
                <section id="content" class="bg-light w-100">
                    <div class="container" id="contenedorPadre">
                        <h1 class="text-center text-dark">Entregas</h1>

                        <center class="d-flex justify-content-between mb-3">
                            <form class="d-flex">
                                <div class="" id="grupo_fechaInicio">
                                    <label class="pb-0 col-auto col-form-label" for="fechaInicio">Fecha Inicio</label>
                                    <div class="col-auto">
                                        <input type="date" id="fechaInicio" name="fechaInicio" value="2021-11-30" class="form-control">
                                    </div>
                                </div>
                                <div class="" id="grupo_fechaFinal">
                                    <label class="pb-0 col-auto col-form-label" for="fechaFinal">Fecha Final</label>
                                    <div class="col-auto">
                                        <input type="date" id="fechaFinal" name="fechaFinal" value="2021-11-30" class="form-control">
                                    </div>
                                </div>
                                <div class="mt-2">
                                    <label class="pb-0 col-auto col-form-label"></label>
                                    <div class="col-auto">
                                        <button type="submit" class="btn btn-primary btn-administacion" value="Buscar"><i class="uil uil-search"></i></button>
                                    </div>
                                </div>
                            </form>
                            <button class="btn btn-primary btn-administacion" id="btnCrearPdf"><i class="uil uil-print"></i></button> 
                        </center>
                        <%
                            String fechaInicio = request.getParameter("fechaInicio");
                            String fechaFinal = request.getParameter("fechaFinal");
                            AutorDAO autorDao = new AutorDAO();
                            LibroDAO libroDAO = new LibroDAO();
                            DetallePrestamoDAO prestamoDAO = new DetallePrestamoDAO();
                            List<DetallePrestamo> prestamo = prestamoDAO.buscarRangoFecha(fechaInicio, fechaFinal);
                            int contador = 0;
                            for (int i = 0; i < prestamo.size(); i++) {
                                contador += prestamo.get(i).getCantidad();
                            }

                        %>

                        <!-- Tabla Entregas  -->
                        <h5 class="text-dark">Libros a entregar: <span id="cantidadEnt"><%=contador%></span></h5>

                        <div class="table-responsive">
                            <table id="tablaEntregas" class="table table-striped table-bordered" cellspacing="0" width="100%">
                                <thead>
                                    <tr>
                                        <th>Código del Libro</th>
                                        <th>Titulo</th>
                                        <th>Autor</th>
                                        <th>Cantidad</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%for (int i = 0; i < prestamo.size(); i++) {%>
                                    <tr>
                                        <td><%=prestamo.get(i).getCodigoLibro()%></td>
                                        <%
                                            Libro l = new Libro();
                                            l = libroDAO.buscarCodigo(prestamo.get(i).getCodigoLibro());
                                        %>
                                        <td><%=l.getTitulo()%></td>
                                        <%Autor a = new Autor();
                                            a = autorDao.buscarCodigo(l.getCodigoAutor());
                                        %>
                                        <td><%=a.getNombre() + " " + a.getApellido()%></td>
                                        <td><%=prestamo.get(i).getCantidad()%></td>
                                    </tr>
                                    <%}%>
                                </tbody>
                            </table>
                        </div>
                    </div> <!-- Fin Tabla Entregas  -->
                </section><!-- Fin Page Content -->
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

        <!--Generar pdf-->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/html2pdf.js/0.10.1/html2pdf.bundle.min.js" integrity="sha512-GsLlZN/3F2ErC5ifS5QtgpiJtWd43JWSuIgh7mbzZ8zBps+dvLusV+eNQATqgA/HdeKFVgA5v3S/cIrLF7QnIg==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

        <!-- Propio -->
        <script src="../../js/biblioteca/Entregas.js"></script>
    </body>

</html>
