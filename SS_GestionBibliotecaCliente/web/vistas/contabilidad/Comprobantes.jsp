<%-- 
    Document   : Comprobantes
    Created on : 23 nov. 2021, 18:48:08
    Author     : mabel
--%>

<%@page import="modelo.usuario.Auditoria"%>
<%@page import="modelo.usuario.dao.AuditoriaDao"%>
<%@page import="modelo.contabilidad.dao.TipoCuentaDAO"%>
<%@page import="modelo.contabilidad.TipoCuenta"%>
<%@page import="modelo.contabilidad.Comprobante"%>
<%@page import="modelo.contabilidad.dao.ComprobanteDAO"%>
<%@page import="modelo.contabilidad.DetalleComprobante"%>
<%@page import="modelo.contabilidad.dao.DetalleComprobanteDAO"%>
<%@page import="java.util.List"%>
<%@page import="modelo.contabilidad.Cuenta"%>
<%@page import="modelo.contabilidad.dao.CuentaDAO"%>
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
                    Auditoria au = new Auditoria(usuario.getNombre(), "COMPROBANTES", hora + ":" + minutos + ":" + segundos, usuario.getCodigo());
                    auDao.agregar(au);
                %>
                <!-- Page Content -->
                <section id="content" class="bg-light w-100">
                    <div class="container">
                        <h1 class="text-center text-dark">Comprobantes</h1>

                        <center>
                            <div class="mensaje-borrar"></div>
                        </center>

                        <div class="row">
                            <div class="col-5">
                                <button type="button" onclick="agregarComprobante()" class="btn btn-primary btn-administacion" id="btnAgregar"><i class="uil uil-plus"></i> Agregar Comprobante</button>

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
                            <%
                                ComprobanteDAO comprobanteDAO = new ComprobanteDAO();
                                List<Comprobante> comprobante = comprobanteDAO.listar();

                                String codigo = "";

                            %>
                            <div class="col-6">
                                <div class="form-group row">
                                    <div class="col-6 mb-1" id="grupo_codigoComprobante">
                                        <label class="pb-0 col-auto col-form-label" for="buscarComprobante">Codigo:</label>
                                        <form class="col-auto d-flex">
                                            <select id="buscarComprobante" name="buscarComprobante" class="form-control">
                                                <option value="">- Seleccionar -</option>
                                                <%                                                    for (int i = 0; i < comprobante.size(); i++) {
                                                        codigo = comprobante.get(i).getCodigoComprobante();
                                                %>
                                                <option value=<%=codigo%>><%=codigo%></option>
                                                <%}%>
                                            </select>

                                            <button type="submit" class="btn btn-primary btn-administacion" value="Buscar"><i class="uil uil-search"></i></button>
                                        </form>
                                    </div>
                                    <%
                                        String codigobuscar = request.getParameter("buscarComprobante");
                                        Comprobante com = comprobanteDAO.buscarCodigo(codigobuscar);
                                        String fecha = com.getFecha();
                                        String descripcion = com.getObservaciones();
                                    %>        
                                    <div class="col-6 mb-1" id="grupo_fechaComprobante">
                                        <label class="pb-0 col-auto col-form-label" for="fechaComprobante"></label>
                                        <div class="col-auto">
                                            <input type="text" id="fechaComprobante" name="fechaComprobante" class="form-control" value=<%=fecha%>>
                                        </div>
                                    </div>
                                    <div class="col-12 mb-1">
                                        <label class="pb-0 col-auto col-form-label" for="observacionesComprobante"></label>
                                        <div class="col-auto">
                                            <textarea id="observacionesComprobante" name="observacionesComprobante" rows="3" cols="50" class="form-control"><%=descripcion%></textarea>
                                        </div>
                                    </div>
                                </div>

                                <hr>

                                <table id="tablaDetCom" class="table table-striped table-bordered" cellspacing="0" width="100%">
                                    <thead>
                                        <tr>
                                            <th>Cuenta</th>
                                            <th>Cantidad debe</th>
                                            <th>Cantidad haber</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%
                                                DetalleComprobanteDAO detalleDAO = new DetalleComprobanteDAO();
                                                ComprobanteDAO comDAO = new ComprobanteDAO();
                                                List<DetalleComprobante> detalle = detalleDAO.buscarCodigoComprobante(codigobuscar);
                                                for (int i = 0; i < detalle.size(); i++) {
                                                Cuenta c = new Cuenta();
                                                CuentaDAO cuentaDAO = new CuentaDAO();
                                                 c = cuentaDAO.buscarCodigo(detalle.get(i).getCodigoCuenta().trim());
                                        %>
                                        <tr>
                                            
                                            <td>
                                                <p id="cuentaComprobante"><%=c.getNombre()%></p>
                                            </td>
                                            <td>
                                                <p id="cDebeComprobante"><%=detalle.get(i).getDebeDetalle()%></p>
                                            </td>
                                            <td>
                                                <p id="cHaberComprobante"><%=detalle.get(i).getHaberDetalle()%></p>
                                            </td>
                                        </tr>
                                        <%}%>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div> 

                    <p id="demo"> </p>
                </section><!-- Fin Page Content -->

                <!-- Modal Formulario-->
                <div id="modalComprobante" class="modal fade" tabindex="-1" aria-labelledby="modalTitleLabel" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-scrollable">
                        <div class="">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="modalTitleLabel"> Agregar Comprobante</h5>
                                </div>
                                <!-- Cuerpo Modal  -->
                                <div class="modal-body">
                                    <div class="col-12">
                                        <form id="formularioComprobante" action="../../ControladorComprobante" method="POST">
                                            <div class="form-group row">
                                                <div class="col-6 mb-1" id="grupo_codigoComprobante">
                                                    <label class="pb-0 col-auto col-form-label" for="codigoComprobante">Codigo:</label>
                                                    <% String codigoIngresar =comDAO.condigo();%>
                                                    <div class="col-auto">
                                                        <input type="text" id="codigoComprobante" name="codigoComprobante" class="form-control" value = <%=codigoIngresar%> placeholder="Ingrese el codigo" required>
                                                    </div>
                                                </div>
                                                <div class="col-6 mb-1" id="grupo_fechaComprobante">
                                                    <label class="pb-0 col-auto col-form-label" for="fechaComprobante">Fecha:</label>
                                                    <div class="col-auto">
                                                        <input type="date" id="fechaComprobante" name="fechaComprobante" class="form-control" required>
                                                    </div>
                                                </div>
                                                <div class="col-12 mb-1">
                                                    <label class="pb-0 col-auto col-form-label" for="observacionesComprobante">Observaciones:</label>
                                                    <div class="col-auto">
                                                        <textarea id="observacionesComprobante" name="observacionesComprobante" rows="3" cols="50" class="form-control"></textarea>
                                                    </div>
                                                </div>
                                            </div>

                                            <hr>
                                            <% CuentaDAO cuentaDAO = new CuentaDAO();
                                                List<Cuenta> cuenta = cuentaDAO.listar();
                                                String listaCodigos = cuentaDAO.listarCodigoCuentas(cuenta);
                                                String listaNombres = cuentaDAO.listarCodigoNombres(cuenta);
                                            %>
                                            <button type="button" onclick="agregarAgregarDetCom(<%=listaCodigos%>,<%=listaNombres%>)" class="btn btn-primary btn-administacion" id="btnAgregarDetCom"><i class="uil uil-plus"></i> Agregar Detalle Comprobante</button>
                                            <table id="tablaAgregarDetCom" class="table table-striped table-bordered" cellspacing="0" width="100%">
                                                <thead>
                                                    <tr>
                                                        <th>Cuenta</th>
                                                        <th>Cantidad debe</th>
                                                        <th>Cantidad haber</th>
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
        <script src="../../js/contabilidad/Comprobantes.js"></script>
    </body>

</html>