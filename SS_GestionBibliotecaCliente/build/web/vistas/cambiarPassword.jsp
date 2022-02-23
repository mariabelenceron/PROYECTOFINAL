<%@page import="modelo.usuario.Encrypt"%>
<%@page import="modelo.usuario.dao.UsuarioDao"%>
<%@page import="modelo.usuario.Usuario"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>

        <link rel="shortcut icon" href="../img/icono.svg">
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

    <body class="m-0 vh-100 row justify-content-center align-items-center">
        <%
            HttpSession sesionPass = request.getSession();
            Usuario usuario = (Usuario) sesionPass.getAttribute("USUARIO");
            UsuarioDao userDao = new UsuarioDao();
        %>
        <div class="col-auto">
            <div class="card">
                <div class="card-header">
                    <h5 class="card-title mt-2">Cambiar Contraseña</h5>
                </div>
                <div class="card-body">
                    <form id="formularioPassword">
                        <div class="form-group row">
                            <div class="col-12">
                                <p class="pb-0 col-auto col-form-label">Usuario: <span class="text-dark"><%out.write(usuario.getNombre());%></span></p>
                            </div>
                            <div class="col-12 mb-1" id="grupo_password">
                                <label class="pb-0 col-auto col-form-label" for="password">Contraseña Actual:</label>
                                <div class="col-auto">
                                    <input type="password"  id="password" name="password" class="form-control" placeholder="Ingrese la contraseña actual" required>
                                </div>
                            </div>
                            <div class="col-12 mb-1" id="grupo_nuevoPassword">
                                <label class="pb-0 col-auto col-form-label" for="nuevoPassword">Nueva Contraseña:</label>
                                <div class="col-auto">
                                    <input type="password"  id="nuevoPassword" name="nuevoPassword" class="form-control" placeholder="Ingrese la nueva contraseña" required>
                                </div>
                            </div>
                        </div>
                        <button type="submit" name="btnCambioPass" value="Cambiar" class="btn btn-blue mt-4" id="btnRegistrar">Cambiar Contraseña</button>
                        <%
                            if (request.getParameter("btnCambioPass") != null) {
                                String pass = request.getParameter("password");
                                String nuevoPass = request.getParameter("nuevoPassword");
                                Encrypt encry = new Encrypt();
                                String passEn = encry.getAES(pass.trim());
                                if (userDao.login(usuario.getCorreo().trim(), pass)) {
                                    userDao.cambioPass(usuario.getCodigo(), nuevoPass);
                                    out.write("Contraseña Cambiada");
                                    response.sendRedirect("Home.jsp");
                                }
                                else{
                                    out.write("Contraseña actual incorrecta");
                                }
                            }
                        %>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
