<%-- 
    Document   : BalanceGeneral
    Created on : 23 nov. 2021, 18:47:41
    Author     : mabel
--%>

<%@page import="modelo.usuario.Auditoria"%>
<%@page import="modelo.usuario.dao.AuditoriaDao"%>
<%@page import="modelo.contabilidad.dao.DetalleComprobanteDAO"%>
<%@page import="modelo.contabilidad.Cuenta"%>
<%@page import="java.util.List"%>
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
                    Auditoria au = new Auditoria(usuario.getNombre(), "BALANCE GENERAL", hora + ":" + minutos + ":" + segundos, usuario.getCodigo());
                    auDao.agregar(au);
                %>
            <!-- Page Content -->
            <section id="content" class="bg-light w-100">
                <div class="container" id="contenedorPadre">
                    <h1 class="text-center text-dark">Balance General</h1>

                    <center class="d-flex justify-content-between mb-3">
                        <div class="d-flex">
<!--                            <div class="" id="grupo_fechaInicio">
                                <label class="pb-0 col-auto col-form-label" for="fechaInicio">Fecha Inicio</label>
                                <div class="col-auto">
                                    <input type="date" id="fechaInicio" name="fechaInicio" class="form-control">
                                </div>
                            </div>
                            <div class="" id="grupo_fechaFinal">
                                <label class="pb-0 col-auto col-form-label" for="fechaFinal">Fecha Final</label>
                                <div class="col-auto">
                                    <input type="date" id="fechaFinal" name="fechaFinal" class="form-control">
                                </div>
                            </div>
                            <div class="mt-2">
                                <label class="pb-0 col-auto col-form-label"></label>
                                <div class="col-auto">
                                    <button type="submit" class="btn btn-primary btn-administacion" value="Buscar"><i class="uil uil-search"></i></button>
                                </div>
                            </div>-->
                        </div>
                        <button class="btn btn-primary btn-administacion" id="btnCrearPdf"><i class="uil uil-print"></i></button> 
                    </center>
                    
                    <%
                    CuentaDAO cuentaDAO = new CuentaDAO();
                    List<Cuenta> cuentasA = cuentaDAO.buscarTipoCuenta("TC001");
                    List<Cuenta> cuentasP = cuentaDAO.buscarTipoCuenta("TC002");
                    DetalleComprobanteDAO detalleDAO = new DetalleComprobanteDAO();
                    float totalActivo =0;
                    float totalPasivo=0;
                    %>
                    
                    <!-- Tabla Entregas  -->
                    <div class="table-responsive" id="cuerpoImpresion">
                        <table id="tablaBalGen" class="table table-striped table-bordered" cellspacing="0" width="100%">
                            <tbody>
                                <tr>
                                    <td>1.</td>
                                    <td>ACTIVO</td>
                                    <td></td>
                                    <td></td>
                                </tr>
                                <%
                                for (int i = 0; i < cuentasA.size(); i++) {
                                        float sum = detalleDAO.sumaCuentaDebe(cuentasA.get(i).getCodigoCuenta());
                                        totalActivo+=sum;
                                %>
                                <tr>
                                    <td><%=cuentasA.get(i).getCodigoCuenta()%></td>
                                    <td><%=cuentasA.get(i).getNombre()%></td>
                                    <td><%=sum%></td>
                                    <td></td>
                                </tr>
                                <%}
                                %>
                                <tr>
                                    <td></td>
                                    <td>TOTAL ACTIVO</td>
                                    <td>----------------</td>
                                    <td><%=totalActivo%></td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td>2.</td>
                                    <td>PASIVO</td>
                                    <td></td>
                                    <td></td>
                                </tr>
                                <%
                                for (int i = 0; i < cuentasP.size(); i++) {
                                        float sumP = detalleDAO.sumaCuentaHaber(cuentasP.get(i).getCodigoCuenta());
                                        totalPasivo+=sumP;
                                %>
                                <tr>
                                    <td><%=cuentasP.get(i).getCodigoCuenta()%></td>
                                    <td><%=cuentasP.get(i).getNombre()%></td>
                                    <td><%=sumP%></td>
                                    <td></td>
                                </tr>
                                <%}
                                %>
                                <tr>
                                    <td></td>
                                    <td>TOTAL PASIVO</td>
                                    <td>----------------</td>
                                    <td><%=totalPasivo%></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    
                    <center>
                        <h5 class="text-dark">TOTAL ACTIVO: <span id="resultados"><%=totalActivo%></span></h5>
                        <h5 class="text-dark">TOTAL PASIVO + PATRIMONIO: <span id="resultados"><%=totalPasivo%></span></h5>
                    </center>
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
    <script src="../../js/contabilidad/BalanceGeneral.js"></script>
</body>

</html>