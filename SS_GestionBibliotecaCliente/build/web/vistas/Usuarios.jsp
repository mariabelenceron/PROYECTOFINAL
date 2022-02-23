<%@page import="modelo.usuario.Auditoria"%>
<%@page import="modelo.usuario.dao.AuditoriaDao"%>
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
                <%  
                    AuditoriaDao auDao = new AuditoriaDao();
                    Auditoria au = new Auditoria(usuario.getNombre(), "USUARIOS", hora + ":" + minutos + ":" + segundos, usuario.getCodigo());
                    auDao.agregar(au);
                %>
                <!-- Page Content -->
                <section id="content" class="bg-light w-100">
                    <div class="container">
                        <h1 class="text-center text-dark">Usuario</h1>

                        <center>
                            <div class="mensaje-borrar"></div>
                        </center>

                        <div class="d-flex justify-content-between">
                            <form action="../ControladorUsuario" method="POST">
                                <a href="UsuariosRegistrar.jsp" class="btn btn-primary btn-administacion" id="btnAgregar"><i class="fas fa-user-plus"></i> Agregar Usuario</a>
                                <button type="submit" name="accion" value="Listar" class="btn btn-primary btn-administacion"><i class="fas fa-sync-alt"></i></button>
                            </form >

                            <form class="d-flex" > 
                                <input type="text" id="buscar" name="buscar" class="form-control" placeholder="Buscar">
                                <button type="submit" class="btn btn-primary btn-administacion" value="Buscar"><i class="uil uil-search"></i></button> 
                            </form>
                        </div>

                        <% UsuarioDao dao = new UsuarioDao();
                            List<Usuario> usuarios = dao.listar();
                            String nombuscar = request.getParameter("buscar");
                            if (nombuscar != null) {
                                usuarios = dao.buscarNombre(nombuscar);
                            } else {

                            }
                        %>
                        <!-- Tabla Autor  -->
                        <div class="table-responsive">
                            <table id="tablaUsuario" class="table table-striped table-bordered" cellspacing="0" width="100%">
                                <thead>
                                    <tr>
                                        <th>Código</th>
                                        <th>Usuario</th>
                                        <th>correo</th>
                                        <th>sesión_desde</th><!-- comment -->
                                        <th>sesión_hasta</th>
                                        <th>Acciones</th>
                                    </tr>
                                </thead>

                                <tbody>
                                    <!--listar auotres-->
                                    <%for (int i = 0; i < usuarios.size(); i++) {%>
                                    <tr>
                                        <td><%=usuarios.get(i).getCodigo()%></td>
                                        <td><%=usuarios.get(i).getNombre()%></td>
                                        <td><%=usuarios.get(i).getCorreo()%></td>
                                        <td><%=usuarios.get(i).getDesde()%></td>
                                        <td><%=usuarios.get(i).getHasta()%></td>
                                        <td>
                                            <form action="../ControladorUsuario" method="POST">
                                                <input type="hidden" name="id" value="<%=usuarios.get(i).getCodigo()%>">

                                                <!---<button type="submit" name="accion" value="Editar" class='btn btn-success btn'><i class='fas fa-edit'></i></i></button>-->
                                                <button type="submit" name="accion" value="Borrar" class='btn btn-danger btnBorrar'><i class='fas fa-trash'></i></button>
                                            </form>
                                        </td>
                                    </tr>
                                    <%}%>
                                </tbody>
                            </table>
                        </div>
                    </div> <!-- Fin Tabla Autor  -->

                    <p id="demo"> </p>
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
        <script src="../js/Usuarios.js"></script>
    </body>
</html>
