<%@page import="modelo.usuario.Auditoria"%>
<%@page import="modelo.usuario.dao.AuditoriaDao"%>
<%@page import="modelo.biblioteca.Autor"%>
<%@page import="modelo.biblioteca.dao.AutorDAO"%>
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
                    Auditoria au = new Auditoria(usuario.getNombre(), "LIBRO", hora + ":" + minutos + ":" + segundos, usuario.getCodigo());
                    auDao.agregar(au);
                %>
                <!-- Page Content -->
                <section id="content" class="bg-light w-100">
                    <div class="container">
                        <h1 class="text-center text-dark">Libro</h1>

                        <center>
                            <div class="mensaje-borrar"></div>
                        </center>

                        <!--Botones-->
                        <div class="d-flex justify-content-between"> 
                            <div>
                                <button type="button" onclick="agregarLibro()" class="btn btn-primary btn-administacion" id="btnAgregar"><i class="uil uil-plus"></i> Agregar Libro</button>
                                <button type="button" class="btn btn-primary btn-administacion" id="btnAgregar"><i class="fas fa-sync-alt"></i></button>
                            </div>
                            <form class="d-flex" > 
                                <input type="text" id="buscar" name="buscar" class="form-control" placeholder="Buscar">
                                <button type="submit" class="btn btn-primary btn-administacion" value="Buscar"><i class="uil uil-search"></i></button> 
                            </form>
                        </div> <!-- Fin Botones-->

                        <% LibroDAO libroDao = new LibroDAO();
                           AutorDAO autorDao = new AutorDAO();
                            List<Libro> libros = libroDao.listar();
                            String nombuscar = request.getParameter("buscar");
                            if (nombuscar != null) {
                                libros = libroDao.buscarTitulo(nombuscar);
                            } else {

                            }
                        %>

                        <!-- Tabla Libro  -->
                        <div class="table-responsive">
                            <table id="tablaLibro" class="table table-striped table-bordered" cellspacing="0" width="100%">
                                <thead>
                                    <tr>
                                        <th>Código</th>
                                        <th>ISBN</th>
                                        <th>Titulo</th>
                                        <th>Autor</th>
                                        <th>Valor del prestamo</th>
                                        <th>Acciones</th>
                                    </tr>
                                </thead>

                                <tbody>
                                    <%for (int i = 0; i < libros.size(); i++) {%>
                                    <tr>
                                        <td><%=libros.get(i).getCodigoLibro()%></td>
                                        <td><%=libros.get(i).getISBN()%></td>
                                        <td><%=libros.get(i).getTitulo()%></td>
                                        <%Autor a = new Autor();
                                          a = autorDao.buscarCodigo(libros.get(i).getCodigoAutor());
                                        %>
                                        <td><%=a.getNombre()+" "+a.getApellido()%></td>
                                        <td><%=libros.get(i).getValorPrestamo()%></td>
                                        <td>
                                            <form action="../../ControladorLibro" method="POST">
                                                <input type="hidden" name="id" value="<%=libros.get(i).getCodigoLibro()%>">
                                                
                                                <a href="LibroEditar.jsp" class='btn btn-success'><i class='fas fa-edit'></i></a>
                                                <button type="submit" name="accion" value="Borrar" class='btn btn-danger btnBorrar'><i class='fas fa-trash'></i></button>
                                            </form>
                                        </td>
                                    </tr>
                                    <%}%>

                                </tbody>
                            </table>
                        </div>
                    </div> <!-- Fin Tabla Libro  -->

                    <p id="demo"> </p>
                </section><!-- Fin Page Content -->

                <!-- Modal Formulario-->
                <div id="modalLibro" class="modal fade" tabindex="-1" aria-labelledby="modalTitleLabel" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-scrollable">
                        <div class="">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="modalTitleLabel"> Agregar Libro</h5>
                                </div>
                                <!-- Cuerpo Modal  -->
                                <div class="modal-body">
                                    <div class="col-12">
                                        <form id="formularioLibro" action="../../ControladorLibro" method="POST">
                                            <div class="form-group row">
                                                <div class="col-6 mb-1" id="grupo_codigoLibro">
                                                    <label class="pb-0 col-auto col-form-label" for="codigoLibro">Codigo:</label>
                                                    <div class="col-auto">
                                                        <% String codigoIngresar =libroDao.condigo();%>
                                                        <input type="text" id="codigoLibro" name="codigoLibro" class="form-control" value = <%=codigoIngresar%> placeholder="Ingrese el codigo">
                                                    </div>
                                                </div>
                                                <div class="col-6 mb-1" id="grupo_isbnLibro">
                                                    <label class="pb-0 col-auto col-form-label" for="isbnLibro">ISBN:</label>
                                                    <div class="col-auto">
                                                        <input type="text" id="isbnLibro" name="isbnLibro" class="form-control" placeholder="Ingrese el ISBN">
                                                    </div>
                                                </div>
                                                <div class="col-12 mb-1" id="grupo_tituloLibro">
                                                    <label class="pb-0 col-auto col-form-label" for="tituloLibro">Titulo:</label>
                                                    <div class="col-auto">
                                                        <input type="text" id="tituloLibro" name="tituloLibro" class="form-control" placeholder="Ingrese el titulo">
                                                    </div>
                                                </div>
                                                <div class="col-12 mb-1">
                                                    <label class="pb-0 col-auto col-form-label" for="autorLibro">Autor:</label>
                                                    <div class="col-auto">
                                                        <select id="autorLibro" name="autorLibro" class="form-control">
                                                            <option value="">- Seleccionar -</option>
                                                            <%List<Autor> autores = autorDao.listar();
                                                                for (int i = 0; i < autores.size(); i++) {
                                                                    String nombre = autores.get(i).getNombre() + " " + autores.get(i).getApellido();
                                                            %>
                                                            <option value=<%=autores.get(i).getCodigo()%>><%=nombre%></option>

                                                            <%}%>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="col-6 mb-1" id="grupo_valorPrestamoLibro">
                                                    <label class="pb-0 col-auto col-form-label" for="valorPrestamoLibro">Valor del Prestamo:</label>
                                                    <div class="col-auto">
                                                        <input type="text" id="valorPrestamoLibro" name="valorPrestamoLibro" class="form-control" placeholder="Ingrese el valor del prestamo">
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
        <script src="../../js/biblioteca/Libro.js"></script>
    </body>

</html>
