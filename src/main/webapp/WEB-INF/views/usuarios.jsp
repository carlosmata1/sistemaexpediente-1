<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
  <html ng-app="Expediente">
    <head>
    <img src="${pageContext.request.contextPath}/resources/images/Logo.png" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/materialize.min.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/index.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/ghpages-materialize.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/prism.css" />


    <!--<meta charset="UTF-8">-->
    <link rel="shortcut icon" href="${logoURL}">

      <!--Import Google Icon Font-->
      <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
      <!--Import materialize.css-->
      <link type="text/css" rel="stylesheet" href="${materializeURL}"  media="screen,projection"/>
      <link type="text/css" rel="stylesheet" href="${indexURL}"  media="screen,projection"/>
      <link href="${primURL}" rel="stylesheet">
  <link href="${ghpagesURL}" type="text/css" rel="stylesheet" media="screen,projection">

      <!--Let browser know website is optimized for mobile-->
      <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
      <title>Inicio</title>
</head>

   <body ng-controller="UsuarioController">


<!--Barra Lateral-->
<header>
  <div class="container "><a href="#" data-activates="nav-mobile" class="button-collapse top-nav waves-effect waves-light circle hide-on-large-only"><i class="material-icons">menu</i></a></div>
        <ul id="nav-mobile" class="side-nav fixed">
          <img src="${pageContext.request.contextPath}/resources/images/Logo3.png" width="300px">

          <li><div class="divider"></div></li>

          <li class="bold "><a href="" class="waves-effect waves-light"><i class="material-icons">home</i>Inicio</a></li>
          <li class="bold active"><a href="usuarios" class="waves-effect waves-light"><i class="material-icons">perm_identity</i>Usuarios</a></li>
          <li class="bold"><a href="pacientes" class="waves-effect waves-light"><i class="material-icons">pregnant_woman</i>Pacientes</a></li>
          <li class="bold"><a href="medicos" class="waves-effect waves-light"><i class="material-icons">people</i>Médicos</a></li>
          


           <li class="bold"><a href="empleados" class="waves-effect waves-light"><i class="material-icons">local_drink</i>Empleados</a></li>
            <li class="bold"><a href="clinicas" class="waves-effect waves-light"><i class="material-icons">store</i>Clinicas</a></li>
            <li class="bold"><a href="logout" class="waves-effect waves-light"><i class="material-icons">close</i>Cerrar Sesión</a></li>
          </ul>
</header>
<!-- FIN Barra Lateral-->

<!-- barra de busqueda-->

 <main>

 <div id="modal1" class="modal">
    <div class="modal-content">
      <h4>Clinica</h4>
    </div>
  
  <div class="container">
  <div class="row">
            <form class="col s12" id="formulario" ng-submit="crearUser()">
              <div class="row">
                 <!--<div class="input-field col s4">
                     <label for="id">Codigo de Clinica</label>
                     <input type="text" ng-model="newuser.codClinica" class="validate" required>
                </div>-->
                <div class="input-field col s4">
                    <input name="nombre" type="text" ng-model="newuser.nombreUsuario" class="validate" required>
                  <label for="first_name">Nombre de Usuario</label>
                </div>
                <div class="input-field col s4">
                    <input name="apellido" type="password" ng-model="newuser.contrasena" class="validate" required>
                  <label for="last_name">Contraseña</label>
                </div>
               </div>
              <div class="row">
                 <div class="input-field col s6">
                     <input name="email" type="email" ng-model="newuser.email" class="validate" required>
                  <label for="Email">Email</label>
                </div>
              </div>
              <div class="row">         
                <div class="row">
                  <div class="col m6 offset-m6">
                      <button ng-show="guardar" class="btn waves-effect waves-light #ffc107 amber" type="submit">Guardar</button>
                      <button ng-show="!guardar" class="btn waves-effect waves-light #ffc107 amber" type="submit">Confirmar</button>
                      <a href="#!" ng-click="clean()" class="modal-action modal-close waves-effect #e53935 red darken-1 waves-red btn-flat white-text">Cancelar</a>
                  </div>
                </div>
              </div>
        
       </form>   
      </div></div></div>

   <!-- FIN AGREGAR CLINICA-->       

  <!--<!encabezado-->
 <div class="section no-pad-bot #263238 blue-grey darken-4" id="ini">
         <div class="container ">
          <h2 class="white-text left thin"><i class="material-icons"></i>Administracion de usuarios</h2>
          <div class='row center'>
          </div>
        </div>
        </div>
  <br>



<div class="container">
   
  <div class="row"></div>
  <a class="waves-effect waves-light btn modal-trigger #ffd600 yellow accent-4"  href="#modal1" id="addbutton"><i class="material-icons left ">person_add</i>Crear nueva cuenta</a>
    <div>
       <br>
       <nav>
    <div class="nav-wrapper #f50057 red accent-3">
        <div class="input-field ">
          <input id="search" ng-model="searchString" class="search" type="search" required placeholder="Buscar cuentas de usuario">
          <label for="search"><i class="material-icons">search</i></label>
          <i class="material-icons">close</i>
        </div>
    </div>
  </nav>
        
        <br>  

</div>
        
<table class="highlight centered">
        <thead>
          <tr>
              <th data-field="id">Cod usuario</th>
              <th data-field="price">Email</th>
              <th data-field="name">Contraseña</th>
              <th data-field="price">Rol</th>
              <th data-field="price">Nombre completo</th>
              <th data-field="price">Opciones</th>
          </tr>
        </thead>
        <tbody class="list">
            <tr ng-repeat="u in usuarios | searchFor:searchString">
                <td class="id">{{u.codUsuario}}</td>
                <td class="nombre">{{u.email}}</td>
                <td class="apellido">{{u.contrasena}}</td>
                <td class="genero">{{u.rol}}</td>
                <td>{{u.nombrecompleto}}</td>
                <td><button ng-click="delete($index)">Borrar</button>
                <button ng-click="edit($index)">Editar</button></td>
            </tr>
        </tbody>
      </table>
</div>
</div>
<main>  

  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.1.0.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-serialize.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/materialize.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/angular.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/angularfiles/usuarios.js"></script>
  <script>
      
  </script>
</body>
</html>
