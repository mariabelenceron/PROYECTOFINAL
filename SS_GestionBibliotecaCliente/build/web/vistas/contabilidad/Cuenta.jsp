<%-- 
    Document   : Cuenta
    Created on : 23 nov. 2021, 18:48:33
    Author     : mabel
--%>

<%@page import="modelo.usuario.Auditoria"%>
<%@page import="modelo.usuario.dao.AuditoriaDao"%>
<%@page import="modelo.contabilidad.Cuenta"%>
<%@page import="modelo.contabilidad.dao.CuentaDAO"%>
<%@page import="modelo.contabilidad.TipoCuenta"%>
<%@page import="java.util.List"%>
<%@page import="modelo.contabilidad.dao.TipoCuentaDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                    Auditoria au = new Auditoria(usuario.getNombre(), "CUENTA", hora + ":" + minutos + ":" + segundos, usuario.getCodigo());
                    auDao.agregar(au);
                %>
                <!-- Page Content -->
                <section id="content" class="bg-light w-100">
                    <div class="container">
                        <h1 class="text-center text-dark">Cuenta</h1>

                        <center>
                            <div class="mensaje-borrar"></div>
                        </center>

                        <!--Botones-->
                        <div class="d-flex justify-content-between"> 
                            <div>
                                <button type="button" onclick="agregarCuenta()" class="btn btn-primary btn-administacion" id="btnAgregar"><i class="uil uil-plus"></i> Agregar Cuenta</button>
                                <button type="button" class="btn btn-primary btn-administacion" id="btnAgregar"><i class="fas fa-sync-alt"></i></button>
                            </div>
                            <form class="d-flex" > 
                                <input type="text" id="buscar" name="buscar" class="form-control" placeholder="Buscar">
                                <button type="submit" class="btn btn-primary btn-administacion" value="Buscar"><i class="uil uil-search"></i></button> 
                            </form>
                        </div> <!-- Fin Botones-->

                        <% CuentaDAO cuentaDao = new CuentaDAO();
                           TipoCuentaDAO tipCuentaDAO = new TipoCuentaDAO();
                            List<Cuenta> cuenta = cuentaDao.listar();
                            String nombuscar = request.getParameter("buscar");
                            if (nombuscar != null) {
                                cuenta = cuentaDao.buscarNombre(nombuscar);
                            } else {

                            }
                        %>

                        <!-- Tabla Cuenta  -->
                        <div class="table-responsive">
                            <table id="tablaCuenta" class="table table-striped table-bordered" cellspacing="0" width="100%">
                                <thead>
                                    <tr>
                                        <th>Código</th>
                                        <th>Nombre</th>
                                        <th>Tipo de cuenta</th>
                                        <th>Acciones</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%for (int i = 0; i < cuenta.size(); i++) {%>
                                    <tr>
                                        <td><%=cuenta.get(i).getCodigoCuenta()%></td>
                                        <td><%=cuenta.get(i).getNombre()%></td>
                                        <%TipoCuenta tc = new TipoCuenta();
                                          tc = tipCuentaDAO.buscarCodigo(cuenta.get(i).getCodigoTipoCuenta());
                                        %>
                                        <td><%=tc.getNombre()%></td>
                                        <td>
                                            <form action="../../ControladorCuenta" method="POST">
                                                <input type="hidden" name="id" value="<%=cuenta.get(i).getCodigoCuenta()%>">
                                                
                                                <a href="CuentaEditar.jsp" class='btn btn-success'><i class='fas fa-edit'></i></a>
                                                <button type="submit" name="accion" value="Borrar" class='btn btn-danger btnBorrar'><i class='fas fa-trash'></i></button>
                                            </form>
                                        </td>
                                    </tr>
                                    <%}%>
                                </tbody>
                            </table>
                        </div>
                    </div> <!-- Fin Tabla Cuenta  -->

                    <p id="demo"> </p>
                </section><!-- Fin Page Content -->

                <!-- Modal Formulario-->
                <div id="modalCuenta" class="modal fade" tabindex="-1" aria-labelledby="modalTitleLabel" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-scrollable">
                        <div class="">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="modalTitleLabel"> Agregar Cuenta</h5>
                                </div>
                                <!-- Cuerpo Modal  -->
                                <div class="modal-body">
                                    <div class="col-12">
                                        <form id="formularioCuenta" action="../../ControladorCuenta" method="POST">
                                            <div class="form-group row">
                                                <div class="col-12 mb-1" id="grupo_codigoCuenta">
                                                    <label class="pb-0 col-auto col-form-label" for="codigoCuenta">Codigo:</label>
                                                    <div class="col-auto">
                                                        <% String codigoIngresar =cuentaDao.condigo();%>
                                                        <input type="text" id="codigoCuenta" name="codigoCuenta" class="form-control" value = <%=codigoIngresar%> placeholder="Ingrese el codigo" required>
                                                    </div>
                                                </div>
                                                <div class="col-12 mb-1" id="grupo_nombreCuenta">
                                                    <label class="pb-0 col-auto col-form-label" for="nombreCuenta" pattern="[a-z]{1,15}">Nombre:</label>
                                                    <div class="col-auto">
                                                        <input type="text" id="nombreCuenta" name="nombreCuenta" class="form-control" placeholder="Ingrese el nombre" required>
                                                    </div>
                                                </div>
                                                <div class="col-12 mb-1">
                                                    <label class="pb-0 col-auto col-form-label" for="tipCueCuenta">Tipo de cuenta:</label>
                                                    <div class="col-auto">
                                                        <select id="tipCueCuenta" name="tipCueCuenta" class="form-control" required>
                                                            <option value="" disabled selected>- Seleccionar -</option>
                                                            <%TipoCuentaDAO Adao = new TipoCuentaDAO();
                                                                List<TipoCuenta> tipCuentas = Adao.listar();
                                                                for (int i = 0; i < tipCuentas.size(); i++) {
                                                                    String nombre = tipCuentas.get(i).getNombre();
                                                            %>
                                                            <option value=<%=tipCuentas.get(i).getCodigo()%>><%=nombre%></option>
                                                            <%}%>
                                                        </select>
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
        <script src="../../js/contabilidad/Cuenta.js"></script>
    </body>

</html>