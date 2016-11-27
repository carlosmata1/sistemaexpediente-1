<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<!-- fksdfksdj-->
<html ng-app="Expediente">
<!--================================================================================
	Item Name: Materialize - Material Design Admin Template
	Version: 3.1
	Author: GeeksLabs
	Author URL: http://www.themeforest.net/user/geekslabs
================================================================================ -->
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/Login/materialize.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/Login/style.css" />
    <!--<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/Login/custom.css"/>-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/Login/page-center.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/Login/prism.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/Login/perfect-scrollbar.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/Login/page-center.css" />
    
    
    
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="msapplication-tap-highlight" content="no">

  <title>Centro Médico</title>
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/resoures/images/logo.png">

  <!-- Favicons-->
   <!-- <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet"> -->

  <!--<link rel="icon" href="http://demo.geekslabs.com/materialize/v3.1/images/favicon/favicon-32x32.png" sizes="32x32">-->
  <!-- Favicons-->
  <link rel="apple-touch-icon-precomposed" href="http://demo.geekslabs.com/materialize/v3.1/images/favicon/apple-touch-icon-152x152.png">
  <!-- For iPhone -->
  <meta name="msapplication-TileColor" content="#00bcd4">
  <meta name="msapplication-TileImage" content="images/favicon/mstile-144x144.png">
  <!-- For Windows Phone -->


  <!-- CORE CSS-->
  </head>


<body class="cyan loaded" data-pinterest-extension-installed="ff1.37.9" ng-controller="LoginController">
  <!-- Start Page Loading -->
  <div id="loader-wrapper">
      <div id="loader"></div>
      <div class="loader-section section-left"></div>
      <div class="loader-section section-right"></div>
  </div>
  <!-- End Page Loading -->



  <div id="login-page" class="row">
    <div class="col m12 z-depth-4 card-panel pull-m10">
                <form cssClass="login-form" modelAttribute="sesion" ng-submit="login()">
        <div class="row">
          <div class="input-field col s12 center">
            <!--<img src=" Images/logo1.png" alt="" class="circle responsive-img valign profile-image-login>-->
            <img src="${pageContext.request.contextPath}/resources/images/Logo1.png">

          </div>
        </div>
        <div class="row margin">
          <div class="input-field col s12">
                            <input ng-model="credenciales.email" type="email" path="nombre"></input>
                                <label for="nombre" class="center-align">Email</label>
                                <!--<input id="username" type="text">-->
          </div>
        </div>
        <div class="row margin">
          <div class="input-field col s12">
           <!-- <i class="material-icons prefix black-text">lock_outline</i>-->
                                <!--<input id="password" type="password">-->
                            <input ng-model="credenciales.contrasena" type ="password" path="contrasena"></input>
                                <label for="contrasena">Contraseña</label>
          </div>
        </div>
        <div class="row">
          <div class="input-field col s12 m12 l12  login-text">
              <input id="remember-me" type="checkbox">
              <!--<label for="remember-me">Remember me</label>-->
          </div>
        </div>
        <div class="row">
          <div class="input-field col s12">
                                <!--<a href="login.htm" class="btn waves-effect waves-light col s12">Iniciar Sesión</a>-->
                            <button name="submit" class="btn waves-effect waves-light col s12">Iniciar Sesión</button>
          </div>
        </div>
         
                        </div>
      </form>
    </div>
  </div>
        
       
            
  <!-- ================================================
    Scripts
    ================================================ -->

  <!-- jQuery Library -->
  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/Login/jquery-1.js"></script>
  <!--materialize js-->
  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/Login/materialize.js"></script>
  <!--prism-->
  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/Login/prism.js"></script>
  <!--scrollbar-->
  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/Login/perfect-scrollbar.js"></script>

      <!--plugins.js - Some Specific JS codes for Plugin Settings-->
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/Login/plugins.js"></script>
    <!--custom-script.js - Add your own theme custom JS-->
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/Login/custom-script.js"></script>
      <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/angular.js"></script>
    
    <script>
        angular.module('Expediente', [])
                .controller('LoginController', function ($http, $scope, $window, $rootScope) {
        $scope.login =  function () {
            $http.post('/sistemaexpediente/login',$scope.credenciales).then(function success(response) {
                var resp = JSON.parse(response.data);
                var session = resp.session.attributes;
                
                if(session.rol===1 || session.rol===2){
                    $window.location = "/sistemaexpediente/pacientes";
                } else {
                    $window.location = "/sistemaexpediente/expedientes";
                }

            }
            , function error(error) {
                console.log('Credenciales incorrectas');
            })
        };            
    
    });
    </script>



<div class="hiddendiv common"></div>
<!--<span style="border-radius: 2px; text-indent: 20px; width: auto; padding: 0px 4px 0px 0px; text-align: center; font: bold 11px/20px &quot;Helvetica Neue&quot;,Helvetica,sans-serif; color: rgb(255, 255, 255); background: rgb(189, 8, 28) url(&quot;data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIGhlaWdodD0iMzBweCIgd2lkdGg9IjMwcHgiIHZpZXdCb3g9Ii0xIC0xIDMxIDMxIj48Zz48cGF0aCBkPSJNMjkuNDQ5LDE0LjY2MiBDMjkuNDQ5LDIyLjcyMiAyMi44NjgsMjkuMjU2IDE0Ljc1LDI5LjI1NiBDNi42MzIsMjkuMjU2IDAuMDUxLDIyLjcyMiAwLjA1MSwxNC42NjIgQzAuMDUxLDYuNjAxIDYuNjMyLDAuMDY3IDE0Ljc1LDAuMDY3IEMyMi44NjgsMC4wNjcgMjkuNDQ5LDYuNjAxIDI5LjQ0OSwxNC42NjIiIGZpbGw9IiNmZmYiIHN0cm9rZT0iI2ZmZiIgc3Ryb2tlLXdpZHRoPSIxIj48L3BhdGg+PHBhdGggZD0iTTE0LjczMywxLjY4NiBDNy41MTYsMS42ODYgMS42NjUsNy40OTUgMS42NjUsMTQuNjYyIEMxLjY2NSwyMC4xNTkgNS4xMDksMjQuODU0IDkuOTcsMjYuNzQ0IEM5Ljg1NiwyNS43MTggOS43NTMsMjQuMTQzIDEwLjAxNiwyMy4wMjIgQzEwLjI1MywyMi4wMSAxMS41NDgsMTYuNTcyIDExLjU0OCwxNi41NzIgQzExLjU0OCwxNi41NzIgMTEuMTU3LDE1Ljc5NSAxMS4xNTcsMTQuNjQ2IEMxMS4xNTcsMTIuODQyIDEyLjIxMSwxMS40OTUgMTMuNTIyLDExLjQ5NSBDMTQuNjM3LDExLjQ5NSAxNS4xNzUsMTIuMzI2IDE1LjE3NSwxMy4zMjMgQzE1LjE3NSwxNC40MzYgMTQuNDYyLDE2LjEgMTQuMDkzLDE3LjY0MyBDMTMuNzg1LDE4LjkzNSAxNC43NDUsMTkuOTg4IDE2LjAyOCwxOS45ODggQzE4LjM1MSwxOS45ODggMjAuMTM2LDE3LjU1NiAyMC4xMzYsMTQuMDQ2IEMyMC4xMzYsMTAuOTM5IDE3Ljg4OCw4Ljc2NyAxNC42NzgsOC43NjcgQzEwLjk1OSw4Ljc2NyA4Ljc3NywxMS41MzYgOC43NzcsMTQuMzk4IEM4Ljc3NywxNS41MTMgOS4yMSwxNi43MDkgOS43NDksMTcuMzU5IEM5Ljg1NiwxNy40ODggOS44NzIsMTcuNiA5Ljg0LDE3LjczMSBDOS43NDEsMTguMTQxIDkuNTIsMTkuMDIzIDkuNDc3LDE5LjIwMyBDOS40MiwxOS40NCA5LjI4OCwxOS40OTEgOS4wNCwxOS4zNzYgQzcuNDA4LDE4LjYyMiA2LjM4NywxNi4yNTIgNi4zODcsMTQuMzQ5IEM2LjM4NywxMC4yNTYgOS4zODMsNi40OTcgMTUuMDIyLDYuNDk3IEMxOS41NTUsNi40OTcgMjMuMDc4LDkuNzA1IDIzLjA3OCwxMy45OTEgQzIzLjA3OCwxOC40NjMgMjAuMjM5LDIyLjA2MiAxNi4yOTcsMjIuMDYyIEMxNC45NzMsMjIuMDYyIDEzLjcyOCwyMS4zNzkgMTMuMzAyLDIwLjU3MiBDMTMuMzAyLDIwLjU3MiAxMi42NDcsMjMuMDUgMTIuNDg4LDIzLjY1NyBDMTIuMTkzLDI0Ljc4NCAxMS4zOTYsMjYuMTk2IDEwLjg2MywyNy4wNTggQzEyLjA4NiwyNy40MzQgMTMuMzg2LDI3LjYzNyAxNC43MzMsMjcuNjM3IEMyMS45NSwyNy42MzcgMjcuODAxLDIxLjgyOCAyNy44MDEsMTQuNjYyIEMyNy44MDEsNy40OTUgMjEuOTUsMS42ODYgMTQuNzMzLDEuNjg2IiBmaWxsPSIjYmQwODFjIj48L3BhdGg+PC9nPjwvc3ZnPg==&quot;) no-repeat scroll 3px 50% / 14px 14px; position: absolute; opacity: 1; z-index: 8675309; display: none; cursor: pointer; border: medium none;">Guardar</span></body></html>-->
    </body>
</html>
