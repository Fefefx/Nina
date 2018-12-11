<%-- 
    Document   : recuperarSenha
    Created on : 13/10/2018, 10:54:44
    Author     : felipe
--%>

<%@page import="control.UsuarioControl"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!--===============================================================================================-->	
        <link rel="icon" type="image/png" href="images/icons/favicon.ico"/>
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
        <!--===============================================================================================-->	
        <link rel="stylesheet" type="text/css" href="vendor/css-hamburgers/hamburgers.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="css/util.css">
        <link rel="stylesheet" type="text/css" href="css/main.css">
        <link rel="stylesheet" type="text/css" href="css/mycss.css">
        <!--===============================================================================================-->
        <title>Recuperar senha</title>
    </head>
    <body>
        <div class="colocar">
            <div class="meucontainer">
                <form class=" recPass login100-form validate-form">
                    <span class="login100-form-title">
                        Recuperar senha
                    </span>
                    <p class="textPass"> Para recuperar a sua senha digite o seu email: </p>
                    <div class="wrap-input100 validate-input" data-validate="Digite o seu email ex: ex@abc.xyz">
                        <input class="input100" type="text" name="email" placeholder="Email">
                        <span class="focus-input100"></span>
                        <span class="symbol-input100">
                            <i class="fa fa-envelope" aria-hidden="true"></i>
                        </span>
                    </div>
                    <%
                        if (request.getParameter("email") != null) {
                            String email = request.getParameter("email");
                            UsuarioControl controle = new UsuarioControl();
                            String resultado = controle.recuperarLogin(email);
                            if (resultado.equals("enviado")) {
                                out.println("Uma nova senha foi gerada e enviada para o seu email.Verifique o seu correio eletrônico");
                                out.print("<br><a href='index.jsp'>Ir para tela de login</a>");
                            }else{
                                out.println(resultado);
                            }
                        }
                    %>
                    <div class="container-login100-form-btn">
                        <button class="login100-form-btn">
                            Recuperar
                        </button>
                    </div>
                </form>
            </div>
            <!--===============================================================================================-->	
            <script src="vendor/jquery/jquery-3.2.1.min.js"></script>
            <!--===============================================================================================-->
            <script src="vendor/bootstrap/js/popper.js"></script>
            <script src="vendor/bootstrap/js/bootstrap.min.js"></script>
            <!--===============================================================================================-->
            <script src="vendor/select2/select2.min.js"></script>
            <!--===============================================================================================-->
            <script src="vendor/tilt/tilt.jquery.min.js"></script>
            <script >
                $('.js-tilt').tilt({
                    scale: 1.1
                })
            </script>
            <!--===============================================================================================-->
            <script src="js/main.js"></script>
        </div>
    </body>
</html>
