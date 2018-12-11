<%-- 
    Document   : cadastro
    Created on : 12/10/2018, 07:10:30
    Author     : felipe
--%>

<%@page import="services.Criptografia"%>
<%@page import="valuesObjects.UsuarioVO"%>
<%@page import="control.UsuarioControl"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de usuário</title>
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
    </head>
    <body>
        <div class="limiter">
            <div class="container-login100">
                <form class=" cad login100-form validate-form">
                    <span class="login100-form-title">
                        Faça o seu cadastro:
                    </span>
                    <div class="wrap-input100 validate-input" data-validate="Digite o seu nome">
                        <input class="input100" type="text" name="nome" placeholder="Nome">
                        <span class="focus-input100"></span>
                        <span class="symbol-input100">
                            <i class="fa fa-user" aria-hidden="true"></i>
                        </span>
                    </div>
                    <div class="wrap-input100 validate-input" data-validate="Digite o seu telefone ex:(018)99999-9999">
                        <input class="input100" type="text" name="telefone" placeholder="Telefone">
                        <span class="focus-input100"></span>
                        <span class="symbol-input100">
                            <i class="fa fa-phone" aria-hidden="true"></i>
                        </span>
                    </div>
                    <div class="wrap-input100 validate-input" data-validate="Digite o seu RA">
                        <input class="input100" type="text" name="RA" placeholder="RA">
                        <span class="focus-input100"></span>
                        <span class="symbol-input100">
                            <i class="fa fa-book" aria-hidden="true"></i>
                        </span>
                    </div>
                    <p class="selMod">Selecione o seu módulo:&nbsp;&nbsp;
                        <select name="modulo">
                            <option value="1">1° Módulo</option>
                            <option value="2">2° Módulo</option>
                            <option value="3">3° Módulo</option>
                            <option value="4">4° Módulo</option>
                            <option value="5">5° Módulo</option>
                            <option value="6">6° Módulo</option>
                        </select>
                    </p>
                    <div class="wrap-input100 validate-input" data-validate="Digite o seu email ex: ex@abc.xyz">
                        <input class="input100" type="email" name="email" placeholder="Email">
                        <span class="focus-input100"></span>
                        <span class="symbol-input100">
                            <i class="fa fa-envelope" aria-hidden="true"></i>
                        </span>
                    </div>
                    <div class="wrap-input100 validate-input" data-validate="Digite a sua senha">
                        <input class="input100" type="password" name="pass" placeholder="Senha">
                        <span class="focus-input100"></span>
                        <span class="symbol-input100">
                            <i class="fa fa-lock" aria-hidden="true"></i>
                        </span>
                    </div>
                    <div class="container-login100-form-btn">
                        <button class="login100-form-btn">
                            Cadastrar
                        </button>
                    </div>
                    <%
                        if (request.getParameter("email") != null) {
                            UsuarioControl controle = new UsuarioControl();
                            UsuarioVO user = new UsuarioVO();
                            user.setEmail(request.getParameter("email"));
                            user.setModulo(Integer.parseInt(request.getParameter("modulo")));
                            user.setNome(request.getParameter("nome"));
                            user.setPassword(Criptografia.criptografiaSHA256(request.getParameter("pass")));
                            user.setRA(request.getParameter("RA"));
                            user.setTelefone(request.getParameter("telefone"));
                            String resultado = controle.inserirUsuario(user);
                            if (resultado.equals("inserido")) {
                                out.println("<p>Usuário inserido com sucesso !</p>");
                                out.print("<br><a href='index.jsp'>Ir para tela de login</a>");
                            }else{
                                out.println(resultado);
                            }
                        }
                    %> 
                </form>
            </div>
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
    </body>
</html>
