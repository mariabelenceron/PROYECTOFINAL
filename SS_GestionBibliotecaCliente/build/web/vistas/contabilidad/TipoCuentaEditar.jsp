<%-- 
    Document   : Cuenta
    Created on : 23 nov. 2021, 18:48:33
    Author     : mabel
--%>

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

                <!-- Page Content -->
                <section id="content" class="bg-light w-100">
                    <div class="container">
                        <h1 class="text-center text-dark">Editar Tipo Cuenta</h1>

                        <center>
                            <div class="mensaje-borrar"></div>
                        </center>
                        
                        <!-- Formulario  -->
                        <div class="col-12">
                            <form id="formularioTipCueEditar" action="../../ControladorTipoCuentaEditar" method="POST" >
                                <div class="form-group row">
                                    <div class="col-12 mb-1" id="grupo_codigoTipCueEditar">
                                        <label class="pb-0 col-auto col-form-label" for="codigoTipCueEditar">Codigo:</label>
                                        <div class="col-auto">
                                            <input type="text" id="codigoTipCueEditar" name="codigoTipCueEditar" class="form-control" placeholder="Ingrese el codigo">
                                        </div>
                                    </div>
                                    <div class="col-12 mb-1" id="grupo_nombreTipCueEditar">
                                        <label class="pb-0 col-auto col-form-label" for="nombreTipCueEditar">Nombre:</label>
                                        <div class="col-auto">
                                            <input type="text" id="nombreTipCueEditar" name="nombreTipCueEditar" class="form-control" placeholder="Ingrese el nombre">
                                        </div>
                                    </div>
                                </div>

                                <center>
                                    <div class="mensaje-error"></div>

                                    <a href="TipoCuenta.jsp" class="btn btn-secondary" id="btnCancelar">Cancelar</a>
                                    <button type="submit" name="accion" value="Actualizar" id="btnActualizar" class="btn btn-primary"> Actualizar</button>
                                </center>
                            </form>
                        </div><!--Fin Formulario  -->
                    </div>
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
        <script src="../../js/contabilidad/TipoCuentaEditar.js"></script>
    </body>

</html>