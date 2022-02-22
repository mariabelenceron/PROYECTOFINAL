<%@page import="modelo.usuario.Auditoria"%>
<%@page import="modelo.usuario.dao.AuditoriaDao"%>
<%@page import="modelo.biblioteca.dao.AutorDAO"%>
<%@page import="java.util.List"%>
<%@page import="modelo.biblioteca.Autor"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
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

                <!-- Page Content -->
                <section id="content" class="bg-light w-100">
                    <div class="container">
                        <h1 class="text-center text-dark">Autor</h1>

                        <center>
                            <div class="mensaje-borrar"></div>
                        </center>

                        <div class="d-flex justify-content-between">
                            <form action="../../ControladorAutor" method="POST">
                                <button type="button" onclick="agregarAutor()" class="btn btn-primary btn-administacion" id="btnAgregar"><i class="fas fa-user-plus"></i> Agregar Autor</button>
                                <button type="submit" name="accion" value="Listar" class="btn btn-primary btn-administacion"><i class="fas fa-sync-alt"></i></button>
                            </form >

                            <form class="d-flex" > 
                                <input type="text" id="buscar" name="buscar" class="form-control" placeholder="Buscar">
                                <button type="submit" class="btn btn-primary btn-administacion" value="Buscar"><i class="uil uil-search"></i></button> 
                            </form>
                        </div>
                        <%                            
                            AuditoriaDao auDao = new AuditoriaDao();
                            Auditoria au = new Auditoria(usuario.getNombre(),"AUTOR",hora+":"+minutos+":"+segundos,usuario.getCodigo());
                            auDao.agregar(au);
                        %>

                        <% AutorDAO dao = new AutorDAO();
                            List<Autor> autores = dao.listar();
                            String nombuscar = request.getParameter("buscar");
                            if (nombuscar != null) {
                                autores = dao.buscarNombre(nombuscar);

                            } else {

                            }
                        %>
                        <!-- Tabla Autor  -->
                        <div class="table-responsive">
                            <table id="tablaAutor" class="table table-striped table-bordered" cellspacing="0" width="100%">
                                <thead>
                                    <tr>
                                        <th>Código</th>
                                        <th>Nombre</th>
                                        <th>Apellido</th>
                                        <th>Acciones</th>
                                    </tr>
                                </thead>

                                <tbody>
                                    <!--listar auotres-->
                                    <%for (int i = 0; i < autores.size(); i++) {%>
                                    <tr>
                                        <td><%=autores.get(i).getCodigo()%></td>
                                        <td><%=autores.get(i).getNombre()%></td>
                                        <td><%=autores.get(i).getApellido()%></td>
                                        <td>
                                            <form action="../../ControladorAutor" method="POST">
                                                <input type="hidden" name="id" value="<%=autores.get(i).getCodigo()%>">

                                                <a href="AutorEditar.jsp" class='btn btn-success btn'><i class='fas fa-edit'></i></a>
                                                <button type="submit" name="accion" value="Borrar" class='btn btn-danger btnBorrar'><i class='fas fa-trash'></i></button>
                                            </form>
                                        </td>
                                    </tr>
                                    <%}%>
                                </tbody>
                            </table>
                        </div>
                    </div> <!-- Fin Tabla Autor  -->
                </section><!-- Fin Page Content -->

                <!-- Modal Formulario Agregar-->
                <div id="modalAutor" class="modal fade" tabindex="-1" aria-labelledby="modalTitleLabel" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-scrollable">
                        <div class="">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="modalTitleLabel"> Agregar Autor</h5>
                                </div>
                                <!-- Cuerpo Modal  -->
                                <div class="modal-body">
                                    <div class="col-12">
                                        <form id="formularioAutor" action="../../ControladorAutor" method="POST" >
                                            <div class="form-group row">
                                                <div class="col-12 mb-1" id="grupo_codigoAutor">
                                                    <label class="pb-0 col-auto col-form-label" for="codigoAutor">Codigo:</label>
                                                    <div class="col-auto">
                                                        <% String codigoIngresar = dao.condigo();%>
                                                        <input type="text" id="codigoAutor" name="codigoAutor" class="form-control" value = <%=codigoIngresar%> placeholder="Ingrese el codigo" required>
                                                    </div>
                                                </div>
                                                <div class="col-12 mb-1" id="grupo_nombreAutor">
                                                    <label class="pb-0 col-auto col-form-label" for="nombreAutor">Nombre:</label>
                                                    <div class="col-auto">
                                                        <input type="text"  id="nombreAutor" name="nombreAutor" class="form-control" placeholder="Ingrese el nombre" required>
                                                    </div>
                                                </div>
                                                <div class="col-12 mb-1" id="grupo_apellidoAutor">
                                                    <label class="pb-0 col-auto col-form-label" for="apellidoAutor">Apellido:</label>
                                                    <div class="col-auto">
                                                        <input type="text" id="apellidoAutor" name="apellidoAutor" class="form-control" placeholder="Ingrese el apellido" required>
                                                    </div>
                                                </div>
                                            </div>

                                            <!-- Botones Modal  -->
                                            <div class="modal-footer">
                                                <div class="mensaje-error"></div>

                                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" id="btnCancelar">Cancelar</button>
                                                <button type="submit" name="accion" value="Guardar" onclick="registrar()" id="btnRegistrar" class="btn btn-primary"> Registrar</button>
                                            </div><!-- Fin Botones Modal  -->

                                        </form>
                                    </div>
                                </div><!-- Fin Cuerpo Modal  -->

                            </div>
                        </div>
                    </div>
                </div><!-- Fin Modal Formulario Agregar-->
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
        <script src="../../js/biblioteca/Autor.js"></script>
    </body>

</html>
