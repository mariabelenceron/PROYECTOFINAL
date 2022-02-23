<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.lang.Integer"%>
<%@page import="modelo.usuario.dao.RolUsuarioDao"%>
<%@page import="java.util.HashMap"%>
<%@page import="modelo.usuario.RolUsuario"%>
<%@page import="modelo.usuario.Usuario"%>
<%@page import="modelo.usuario.dao.UsuarioDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="shortcut icon" href="../img/icono.png">
    <title>Inicio de Sesión</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="https://unicons.iconscout.com/release/v2.1.9/css/unicons.css" />
    <link rel="stylesheet" href="css/login.css" />
</head>

<body>
    <div class="container">
        <div class="row full-height justify-content-center">
            <div class="col-12 text-center align-self-center py-5">
                <div class="section pb-5 pt-5 pt-sm-2 text-center">
                    <div class="card-3d-wrap mx-auto">
                        <div class="card-front">
                            <div class="center-wrap">

                                <form class="section text-center" id="login_form" action="#" method="POST">
                                    <h4 class="mb-4 pb-3 mt-4">
                                        Iniciar Sesión
                                    </h4>
                                    <div id="login_alert"></div>

                                    <div class="form-group">
                                        <input type="text" name="username" class="form-style" placeholder="Correo electrónico"
                                               id="username" autocomplete="none" required />
                                        <i class="input-icon uil uil-user"></i>
                                    </div>
                                    <div class="form-group mt-2">
                                        <input type="password" name="password" class="form-style"
                                               placeholder="Contraseña" id="password" autocomplete="none" required />
                                        <i class="input-icon uil uil-lock-alt"></i>
                                    </div>

                                    <div class="row mt-4">
                                        <div class="g-recaptcha col-10" 
                                             data-sitekey="6LcyUyMeAAAAAHpOFwjWoxld3QYLpH3_DU01vQGe"
                                             id="captcha"></div>
                                        <button type="button" name="btnVerificar" value="Verificar" class="btn btn-blue mt-4 col-2" id="btnVerificar"><i class="uil uil-check " style="font-size:25px;"/></i></button>
                                    </div>

                                    <button type="submit" name="btnIngresar" value="Ingresar" class="btn btn-blue mt-4" id="btnRegistrar">Iniciar sesión</button>
                                    <p class="mb-0 mt-4 text-center" id="error"></p>

                                    <%
                                        UsuarioDao userDao = new UsuarioDao();
                                        Usuario user = new Usuario();
                                        RolUsuarioDao rol = new RolUsuarioDao();
                                        HttpSession sesion = request.getSession();

                                        if (request.getParameter("btnIngresar") != null) {
                                            String correo = request.getParameter("username");
                                            String pass = request.getParameter("password");
                                            //sesion.setAttribute("ROLES",roles );

//                                            if (userDao.login(correo, pass) && userDao.accesoHoraAGCA(correo)) {
                                            if (userDao.login(correo.trim(), pass)) {
                                                user = userDao.buscarCorreo(correo);
                                                //obtener roles
                                                ////USU,1; TC,1; CUE,1; COM,1; RC,1; AUT,1; LIB,1; PRE,1; RB,1;
                                                response.setHeader("Refresh", "; URL = index.jsp");
                                                HashMap<String, Integer> roles = rol.roles(user.getCodigo().trim());

                                                sesion.setAttribute("ROLES", roles);
                                                sesion.setAttribute("USUARIO", user);
                                                int cerrar = 1;
                                                sesion.setAttribute("CERRAR", cerrar);
                                                if (user.getSesion() >= 3) {
                                                    out.write("Limite excedido de sesiones");
                                                } else {
                                                    int num = user.getSesion()+1;
                                                    if (user.getCambio() == 0) {
                                                        userDao.limiteSesion(user.getCodigo(),num );
                                                        user.setSesion(num);
                                                        response.sendRedirect("../SS_GestionBibliotecaCliente/vistas/cambiarPassword.jsp");
                                                    } else {
                                                        userDao.limiteSesion(user.getCodigo(),num );
                                                        user.setSesion(num);
                                                        response.sendRedirect("../SS_GestionBibliotecaCliente/vistas/Home.jsp");
                                                    }
                                                }
                                            } else {
                                                out.write("Hora no habilitada");
                                                out.write(pass);
                                            }
                                        }

                                        /*cerrar = (Integer) sesion.getAttribute("CERRAR");
                                        if (cerrar == 0) {
                                            response.sendRedirect("../SS_GestionBibliotecaCliente/index.jsp");
                                        }*/
                                    %>

                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://www.google.com/recaptcha/api.js" async defer></script>
    <!-- Bootstrap -->
    <script
        src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"
    ></script>
    <!-- Propio -->
    <script src="js/index.js"></script>
</body>

</html>
