<%@page import="modelo.usuario.Usuario"%>
<%@page import="modelo.usuario.dao.UsuarioDao"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@page session="true"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <link rel="shortcut icon" href="../../img/icono.svg">
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
        <div class="d-flex" id="content-wrapper">
            <!-- Sidebar -->
            <%@include file="componentes/SidebarGeneral.jsp" %>

            <div class="w-100">
                <!-- Navbar -->
                <%@include file="componentes/NavbarGeneral.jsp" %>

                <!-- Page Content -->
                <section id="content" class="bg-light w-100">
                    <div class="container">
                        <h1 class="text-center text-dark">Registrar Usuarios</h1>

                        <center>
                            <div class="mensaje-borrar"></div>
                        </center>

                        <%
                        UsuarioDao uDao = new UsuarioDao();
                        %>
                        <!-- Formulario  -->
                        <div class="col-12">
                            <form id="formularioRegistrarUsuario" action="../ControladorUsuario" method="POST" >
                                <div class="form-group row">
                                    <div class="col-6 mb-1" id="grupo_codigoUsuario">
                                        <label class="pb-0 col-auto col-form-label" for="codigoUsuario">Codigo:</label>
                                        <div class="col-auto">
                                            <input type="text" id="codigoUsuario" name="codigoUsuario" value = <%=uDao.condigo() %> class="form-control" placeholder="Ingrese el codigo" required>
                                        </div>
                                    </div>
                                    <div class="col-6 mb-1" id="grupo_cedulaUsuario">
                                        <label class="pb-0 col-auto col-form-label" for="cedulaUsuario">Cedula:</label>
                                        <div class="col-auto">
                                            <input type="text" id="cedulaUsuario" name="cedulaUsuario" class="form-control" placeholder="Ingrese el numero de cedula" required>
                                        </div>
                                    </div>
                                    <div class="col-12 mb-1" id="grupo_nombreUsuario">
                                        <label class="pb-0 col-auto col-form-label" for="nombreUsuario">Nombre Completo:</label>
                                        <div class="col-auto">
                                            <input type="text" id="nombreUsuario" name="nombreUsuario" class="form-control" placeholder="Ingrese el nombre completo" required>
                                        </div>
                                    </div>
                                    <div class="col-12 mb-1" id="grupo_correoUsuario">
                                        <label class="pb-0 col-auto col-form-label" for="correoUsuario">Correo:</label>
                                        <div class="col-auto">
                                            <input type="text"  id="correoUsuario" name="correoUsuario" class="form-control" placeholder="Ingrese el usuario" required>
                                        </div>
                                    </div>
                                    <div class="col-6 mb-1" id="grupo_sesionDesdeUsuario">
                                        <label class="pb-0 col-auto col-form-label" for="sesionDesdeUsuario">Sesion desde:</label>
                                        <div class="col-auto">
                                            <input type="text"  id="sesionDesdeUsuario" name="sesionDesdeUsuario" class="form-control" placeholder="00:00:00" required>
                                        </div>
                                    </div>
                                    <div class="col-6 mb-1" id="grupo_sesionHastaUsuario">
                                        <label class="pb-0 col-auto col-form-label" for="sesionHastaUsuario">Sesion Hasta:</label>
                                        <div class="col-auto">
                                            <input type="text" id="sesionHastaUsuario" name="sesionHastaUsuario" class="form-control" placeholder="00:00:00" required>
                                        </div>
                                    </div>
                                </div>

                                <hr>
                                <h4 class="text-muted">Permisos</h4>
                                <!-- Tabla tablaPermisos  -->
                                <div class="table-responsive">
                                    <table id="tablaPermisos" class="table table-striped table-bordered" cellspacing="0" width="50%">
                                        <thead>
                                            <tr>
                                                <th>Pantalla</th>
                                                <th>Permitir</th>
                                                <th>No permitir</th>
                                            </tr>
                                        </thead>

                                        <tbody>
                                            <tr>
                                                <td>Usuarios</td>
                                                <td>
                                                    <div class="form-check">
                                                        <input class="form-check-input" type="radio" name="checkUsuarios"  value ="1" id="checkUsuariosP">
                                                        <label class="form-check-label" for="checkUsuariosP"></label>
                                                    </div>
                                                </td>
                                                <td>
                                                    <div class="form-check">
                                                        <input class="form-check-input" type="radio" name="checkUsuarios" value ="0" id="checkUsuariosNP">
                                                        <label class="form-check-label" for="checkUsuariosNP"></label>
                                                    </div>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td colspan="3" class="table-active">CONTABILIDAD</td>
                                            </tr>
                                            <tr>
                                                <td>Tipo de cuenta</td>
                                                <td>
                                                    <div class="form-check">
                                                        <input class="form-check-input" type="radio" name="checkTipCue" value ="1" id="checkTipCueP">
                                                        <label class="form-check-label" for="checkTipCueP"></label>
                                                    </div>
                                                </td>
                                                <td>
                                                    <div class="form-check">
                                                        <input class="form-check-input" type="radio" name="checkTipCue" value ="0" id="checkTipCueNP">
                                                        <label class="form-check-label" for="checkTipCueNP"></label>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>Cuenta</td>
                                                <td>
                                                    <div class="form-check">
                                                        <input class="form-check-input" type="radio" name="checkCuenta" value ="1" id="checkCuentaP">
                                                        <label class="form-check-label" for="checkCuentaP"></label>
                                                    </div>
                                                </td>
                                                <td>
                                                    <div class="form-check">
                                                        <input class="form-check-input" type="radio" name="checkCuenta" value ="0" id="checkCuentaNP">
                                                        <label class="form-check-label" for="checkCuentaNP"></label>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>Comprobantes</td>
                                                <td>
                                                    <div class="form-check">
                                                        <input class="form-check-input" type="radio" name="checkComprobantes" value ="1" id="checkComprobantesP">
                                                        <label class="form-check-label" for="checkComprobantesP"></label>
                                                    </div>
                                                </td>
                                                <td>
                                                    <div class="form-check">
                                                        <input class="form-check-input" type="radio" name="checkComprobantes" value ="0" id="checkComprobantesNP">
                                                        <label class="form-check-label" for="checkComprobantesNP"></label>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>Reportes Contabilidad</td>
                                                <td>
                                                    <div class="form-check">
                                                        <input class="form-check-input" type="radio" name="checkReportesC" value="1" id="checkReportesCP">
                                                        <label class="form-check-label" for="checkReportesCP"></label>
                                                    </div>
                                                </td>
                                                <td>
                                                    <div class="form-check">
                                                        <input class="form-check-input" type="radio" name="checkReportesC" value="0" id="checkReportesCNP">
                                                        <label class="form-check-label" for="checkReportesCNP"></label>
                                                    </div>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td colspan="3">BIBLIOTECA</td>
                                            </tr>
                                            <tr>
                                                <td>Autor</td>
                                                <td>
                                                    <div class="form-check">
                                                        <input class="form-check-input" type="radio" name="checkAutor" value="1" id="checkAutorP">
                                                        <label class="form-check-label" for="checkAutorP"></label>
                                                    </div>
                                                </td>
                                                <td>
                                                    <div class="form-check">
                                                        <input class="form-check-input" type="radio" name="checkAutor" value="0" id="checkAutorNP">
                                                        <label class="form-check-label" for="checkAutorNP"></label>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>Libro</td>
                                                <td>
                                                    <div class="form-check">
                                                        <input class="form-check-input" type="radio" name="checkLibro" value="1" id="checkLibroP">
                                                        <label class="form-check-label" for="checkLibroP"></label>
                                                    </div>
                                                </td>
                                                <td>
                                                    <div class="form-check">
                                                        <input class="form-check-input" type="radio" name="checkLibro" value="0" id="checkLibroNP">
                                                        <label class="form-check-label" for="checkLibroNP"></label>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>Prestamos</td>
                                                <td>
                                                    <div class="form-check">
                                                        <input class="form-check-input" type="radio" name="checkPrestamos" value="1" id="checkPrestamosP">
                                                        <label class="form-check-label" for="checkPrestamosP"></label>
                                                    </div>
                                                </td>
                                                <td>
                                                    <div class="form-check">
                                                        <input class="form-check-input" type="radio" name="checkPrestamos" value="0" id="checkPrestamosNP">
                                                        <label class="form-check-label" for="checkPrestamosNP"></label>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>Reportes Biblioteca</td>
                                                <td>
                                                    <div class="form-check">
                                                        <input class="form-check-input" type="radio" name="checkReportesB" value="1" id="checkReportesBP">
                                                        <label class="form-check-label" for="checkReportesBP"></label>
                                                    </div>
                                                </td>
                                                <td>
                                                    <div class="form-check">
                                                        <input class="form-check-input" type="radio" name="checkReportesB" value="0" id="checkReportesBNP">
                                                        <label class="form-check-label" for="checkReportesBNP"></label>
                                                    </div>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div> <!-- Fin Tabla Autor  -->

                                <center>
                                    <div class="mensaje-error"></div>

                                    <a href="Usuarios.jsp" class="btn btn-secondary" id="btnCancelar">Cancelar</a>
                                    
                                    <button type="submit" name="accion" value="Registrar" id="btnRegistrar" class="btn btn-primary"> Registrar</button>
                                </center>
                            </form>
                        </div><!--Fin Formulario  -->

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

        <!-- Propio -->
        <script src="../js/UsuariosRegistrar.js"></script>
    </body>
</html>
