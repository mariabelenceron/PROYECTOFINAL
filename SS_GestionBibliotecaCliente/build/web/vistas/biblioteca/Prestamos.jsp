<%-- 
    Document   : Prestamos
    Created on : 23 nov. 2021, 18:35:02
    Author     : mabel
--%>

<%@page import="modelo.usuario.Auditoria"%>
<%@page import="modelo.usuario.dao.AuditoriaDao"%>
<%@page import="modelo.biblioteca.Prestamo"%>
<%@page import="modelo.biblioteca.dao.PrestamoDAO"%>
<%@page import="modelo.biblioteca.Libro"%>
<%@page import="java.util.List"%>
<%@page import="modelo.biblioteca.dao.LibroDAO"%>
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
                    Auditoria au = new Auditoria(usuario.getNombre(), "PRESTAMOS", hora + ":" + minutos + ":" + segundos, usuario.getCodigo());
                    auDao.agregar(au);
                %>
                <!-- Page Content -->
                <section id="content" class="bg-light w-100">
                    <div class="container">
                        <h1 class="text-center text-dark">Prestamos</h1>

                        <center>
                            <div class="mensaje-borrar"></div>
                        </center>

                        <div class="row">
                            <div class="col-5">
                                <button type="button" onclick="agregarPrestamo()" class="btn btn-primary btn-administacion" id="btnAgregar"><i class="uil uil-plus"></i> Agregar Prestamo</button>

                                <!-- Tabla Comprobante  -->
                                <div class="table-responsive">
                                    <table id="tablaComprobante" class="table table-striped table-bordered" cellspacing="0" width="100%">
                                        <thead>
                                            <tr>
                                                <th>Código</th>
                                                <th>Fecha</th>
                                                <th>Acciones</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td>CC0001</td>
                                                <td>13/11/2021</td>
                                                <td>
                                                    <button type='button' class='btn btn-success btnEditar'><i class='fas fa-edit'></i></button>
                                                    <button type='button' class='btn btn-danger btnBorrar'><i class='fas fa-trash'></i></button>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div> <!-- Fin Tabla Comprobante  -->
                            </div>
                            
                            <div class="col-6">
                                <div class="form-group row">
                                    <div class="col-6 mb-1" id="grupo_codigoComprobante">
                                        <label class="pb-0 col-auto col-form-label" for="buscarComprobante">Codigo:</label>
                                        <form class="col-auto d-flex">
                                            <select id="buscarComprobante" name="buscarComprobante" class="form-control">
                                                <option value="">- Seleccionar -</option>
                                                
                                            </select>

                                            <button type="submit" class="btn btn-primary btn-administacion" value="Buscar"><i class="uil uil-search"></i></button>
                                        </form>
                                    </div>
                                        
                                    <div class="col-6 mb-1" id="grupo_fechaPrestamo">
                                        <label class="pb-0 col-auto col-form-label" for="fechaPrestamo">Fecha:</label>
                                        <div class="col-auto">
                                            <input type="date" id="fechaPrestamo" name="fechaPrestamo" class="form-control" placeholder="Ingrese la fecha">
                                        </div>
                                    </div>
                                    <div class="col-12 mb-1" id="grupo_clientePrestamo">
                                        <label class="pb-0 col-auto col-form-label" for="clientePrestamo">Cliente:</label>
                                        <div class="col-auto">
                                            <input type="text" id="clientePrestamo" name="clientePrestamo" class="form-control" placeholder="Ingrese el cliente">
                                        </div>
                                    </div>
                                    <div class="col-12 mb-1">
                                        <label class="pb-0 col-auto col-form-label" for="descripcionPrestamo">Descripcion:</label>
                                        <div class="col-auto">
                                            <textarea id="descripcionPrestamo" name="descripcionPrestamo" rows="3" cols="50" class="form-control"></textarea>
                                        </div>
                                    </div>
                                </div>

                                <hr>

                                <table id="tablaPrestamo" class="table table-striped table-bordered" cellspacing="0" width="100%">
                                    <thead>
                                        <tr>
                                            <th>Libro</th>
                                            <th>Cantidad</th>
                                            <th>Fecha Entrega</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>
                                                <p id="libroPrestamo"></p>
                                            </td>
                                            <td>
                                                <p id="cantidadPrestamo"></p>
                                            </td>
                                            <td>
                                                <p id="fecEntPrestamo"></p>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div> 

                    <p id="demo"> </p>
                </section><!-- Fin Page Content -->

                <!-- Modal Formulario-->
                <div id="modalPrestamo" class="modal fade" tabindex="-1" aria-labelledby="modalTitleLabel" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-scrollable">
                        <div class="">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="modalTitleLabel"> Agregar Prestamo</h5>
                                </div>
                                <!-- Cuerpo Modal  -->
                                <div class="modal-body">
                                    <div class="col-12">
                                        <form id="formularioPrestamo" action="../../ControladorPrestamo" method="POST">
                                            <div class="form-group row">
                                                <% PrestamoDAO prestamoDAO = new PrestamoDAO();
                                                    String codigoIngresar =prestamoDAO.condigo();%>
                                                <div class="col-6 mb-1" id="grupo_codigoPrestamo">
                                                    <label class="pb-0 col-auto col-form-label" for="codigoPrestamo">Codigo:</label>
                                                    <div class="col-auto">
                                                        <input type="text" id="codigoPrestamo" name="codigoPrestamo" class="form-control" value = <%=codigoIngresar%> placeholder="Ingrese el codigo" required>
                                                    </div>
                                                </div>
                                                <div class="col-6 mb-1" id="grupo_fechaPrestamo">
                                                    <label class="pb-0 col-auto col-form-label" for="fechaPrestamo">Fecha:</label>
                                                    <div class="col-auto">
                                                        <input type="date" id="fechaPrestamo" name="fechaPrestamo" class="form-control" placeholder="Ingrese la fecha" required>
                                                    </div>
                                                </div>
                                                <div class="col-12 mb-1" id="grupo_clientePrestamo">
                                                    <label class="pb-0 col-auto col-form-label" for="clientePrestamo">Cliente:</label>
                                                    <div class="col-auto">
                                                        <input type="text" id="clientePrestamo" name="clientePrestamo" class="form-control" placeholder="Ingrese el cliente" required>
                                                    </div>
                                                </div>
                                                <div class="col-12 mb-1">
                                                    <label class="pb-0 col-auto col-form-label" for="descripcionPrestamo">Descripcion:</label>
                                                    <div class="col-auto">
                                                        <textarea id="descripcionPrestamo" name="descripcionPrestamo" rows="3" cols="50" class="form-control"></textarea>
                                                    </div>
                                                </div>
                                            </div>

                                            <hr>

                                            <% LibroDAO libroDAO = new LibroDAO();
                                                List<Libro> libro = libroDAO.listar();
                                                String listaCodigos = libroDAO.listarCodigoLibros(libro);
                                                String listaNombres = libroDAO.listarCodigoNombres(libro);
                                            %>
                                            <button type="button" onclick="agregarLibro(<%=listaCodigos%>,<%=listaNombres%>)" class="btn btn-primary btn-administacion" id="btnAgregarLibro"><i class="uil uil-plus"></i> Agregar Libro</button>
                                            <table id="tablaAgregarLibro" class="table table-striped table-bordered" cellspacing="0" width="100%">
                                                <thead>
                                                    <tr>
                                                        <th>Libro</th>
                                                        <th>Cantidad</th>
                                                        <th>Fecha Entrega</th>
                                                        <th>Accion</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    
                                                </tbody>
                                            </table>
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
                </div><!-- Fin Modal Formulario-->
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
        <script src="../../js/biblioteca/Prestamos.js"></script>
    </body>

</html>