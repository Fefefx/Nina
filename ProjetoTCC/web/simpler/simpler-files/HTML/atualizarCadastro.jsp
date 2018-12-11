<%
    String email = (String) session.getAttribute("user");
    if (email == null) {
        response.sendRedirect("redirect.jsp");
    }
%>
<%@page import="services.Criptografia"%>
<%@page import="valuesObjects.UsuarioVO"%>
<%@page import="control.UsuarioControl"%>
<!doctype html>
<html class="no-js">

    <head>
        <meta charset="utf-8" />
        <title>Minha conta</title>

        <link rel="icon" type="image/png" href="../../../Login_v1/images/icons/favicon.ico"/>


        <!--[if lt IE 9]>
                        <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
                <![endif]-->
        <link rel="stylesheet" media="all" href="css/style.css" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <!-- Adding "maximum-scale=1" fixes the Mobile Safari auto-zoom bug: http://filamentgroup.com/examples/iosScaleBug/ -->


        <!-- JS -->
        <script src="js/jquery-1.6.4.min.js"></script>
        <script src="js/css3-mediaqueries.js"></script>
        <script src="js/custom.js"></script>
        <script src="js/tabs.js"></script>

        <!-- Tweet -->
        <link rel="stylesheet" href="css/jquery.tweet.css" media="all" />
        <script src="js/tweet/jquery.tweet.js"></script>
        <!-- ENDS Tweet -->

        <!-- superfish -->
        <link rel="stylesheet" media="screen" href="css/superfish.css" />
        <script src="js/superfish-1.4.8/js/hoverIntent.js"></script>
        <script src="js/superfish-1.4.8/js/superfish.js"></script>
        <script src="js/superfish-1.4.8/js/supersubs.js"></script>
        <!-- ENDS superfish -->

        <!-- prettyPhoto -->
        <script src="js/prettyPhoto/js/jquery.prettyPhoto.js"></script>
        <link rel="stylesheet" href="js/prettyPhoto/css/prettyPhoto.css" media="screen" />
        <!-- ENDS prettyPhoto -->

        <!-- poshytip -->
        <link rel="stylesheet" href="js/poshytip-1.1/src/tip-twitter/tip-twitter.css" />
        <link rel="stylesheet" href="js/poshytip-1.1/src/tip-yellowsimple/tip-yellowsimple.css" />
        <script src="js/poshytip-1.1/src/jquery.poshytip.min.js"></script>
        <!-- ENDS poshytip -->

        <!-- GOOGLE FONTS -->
        <link href='http://fonts.googleapis.com/css?family=Arvo:400,700' rel='stylesheet' type='text/css'>

        <!-- Flex Slider -->
        <link rel="stylesheet" href="css/flexslider.css">
        <script src="js/jquery.flexslider-min.js"></script>
        <!-- ENDS Flex Slider -->

        <!-- Masonry -->
        <script src="js/masonry.min.js"></script>
        <script src="js/imagesloaded.js"></script>
        <!-- ENDS Masonry -->

        <!-- Less framework -->
        <link rel="stylesheet" media="all" href="css/lessframework.css" />

        <!-- modernizr -->
        <script src="js/modernizr.js"></script>

        <!-- SKIN -->
        <link rel="stylesheet" media="all" href="css/skin.css" />


    </head>

    <body lang="pt-br">

        <header>
            <div class="wrapper clearfix">

                <div id="logo">
                    <a href="inicio.jsp"><img src="img/logo.png" alt="Simpler"></a>
                </div>

                <!-- nav -->
                <ul id="nav" class="sf-menu">
                    <li><a href='inicio.jsp'>HOME</a></li>
                    <li><a href='aulas.jsp'>AULAS</a></li>
                    <li class='current-menu-item'><a href='atualizarCadastro.jsp'>MINHA CONTA</a></li>
                    <li><a href='redirect.jsp'>SAIR</a></li>
                </ul>
                <!-- ends nav -->
            </div>
        </header>

        <!-- MAIN -->
        <div id="main">
            <div class="wrapper clearfix">

                <!-- masthead -->
                <div class="masthead clearfix">
                    <h1>Minha conta</h1><span class="subheading">Gerencie a sua conta</span>
                </div>
                <div class='mh-div'></div>
                <!-- ENDS masthead -->

                <!-- page content -->
                <div id="page-content" class="clearfix">

                    <div class='section-div'></div>

                    <div class="left-page-block">
                        <h4 class="heading">Atualizar informações da sua conta</h4>
                        <!-- form -->
                                    <p>Clique no que você deseja atualizar:</p>
                                    <a href="atualizarSenha.jsp" style="text-decoration: none;">Senha da conta</a><br>
                                    <a href="atualizarDados.jsp" style="text-decoration: none;">Dados cadastrais</a>
                    </div>

                </div>
                <!-- ENDS page content -->

                <!-- Fold image -->
                <div id="fold"></div>
            </div>

        </div>
        <!-- ENDS MAIN -->


        <footer>
            <div class="wrapper clearfix">

                <div class="footer-bottom">
                    <div class="left">Nina um ambiente de ensino amigável baseado na web.</div>
                    <div class="right">
                        <ul id="social-bar">
                            <li><a href="https://www.facebook.com/FatecPrudente/" title="Curta a FATEC no facebook" class="poshytip"><img src="img/social/facebook.png"
                                                                                                                                          alt="Facebook" /></a></li>
                            <li><a href="https://www.youtube.com/channel/UCD8cIo7h8FpVDsKdhFPwnsA?view_as=subscriber" title="Inscreva-se no meu canal"
                                   class="poshytip"><img src="img/social/youtube.png" alt="Youtube" /></a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </footer>

    </body>

</html>