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
      <title>Clinicas</title>
</head>

   <body ng-controller="ClinicaController">


<!--Barra Lateral-->
<header>
  <div class="container "><a href="#" data-activates="nav-mobile" class="button-collapse top-nav waves-effect waves-light circle hide-on-large-only"><i class="material-icons">menu</i></a></div>
        <ul id="nav-mobile" class="side-nav fixed">
          <img src="${pageContext.request.contextPath}/resources/images/Logo3.png" width="300px">

          <li><div class="divider"></div></li>

          <li class="bold "><a href="" class="waves-effect waves-light"><i class="material-icons">home</i>Inicio</a></li>
          <li class="bold"><a href="usuarios" class="waves-effect waves-light"><i class="material-icons">perm_identity</i>Usuarios</a></li>
          <li class="bold"><a href="pacientes" class="waves-effect waves-light"><i class="material-icons">pregnant_woman</i>Pacientes</a></li>
          <li class="bold"><a href="medicos" class="waves-effect waves-light"><i class="material-icons">people</i>Médicos</a></li>
          


           <li class="bold"><a href="empleados" class="waves-effect waves-light"><i class="material-icons">local_drink</i>Empleados</a></li>
            <li class="bold active"><a href="clinicas" class="waves-effect waves-light"><i class="material-icons">store</i>Clinicas</a></li>
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
            <form class="col s12" id="formulario" ng-submit="crearCli()">
              <div class="row">
                 <div class="input-field col s4">
                     <label for="id">Codigo de Clinica</label>
                     <input type="text" ng-model="newcli.codClinica" class="validate" required>
                </div>
                <div class="input-field col s4">
                    <input name="nombre" type="text" ng-model="newcli.nombre" class="validate" required>
                  <label for="first_name">Nombre</label>
                </div>
                <div class="input-field col s4">
                    <input name="apellido" type="text" ng-model="newcli.telefono" class="validate" required>
                  <label for="last_name">Telefono</label>
                </div>
               </div>
              <div class="row">
                 <div class="input-field col s6">
                     <input name="direccion" type="text" ng-model="newcli.direccion" class="validate" required>
                  <label for="Edad">Direccion</label>
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
          <h2 class="white-text left thin"><i class="material-icons"></i>Gestión de red de clinicas</h2>
          <div class='row center'>
          </div>
        </div>
        </div>
  <br>



<div class="container">
   
  <div class="row"></div>
  <a class="waves-effect waves-light btn modal-trigger #ffd600 yellow accent-4"  href="#modal1" id="addbutton"><i class="material-icons left ">person_add</i>Agregar clínica</a>
    <div>
       <br>
       <nav>
    <div class="nav-wrapper #f50057 red accent-3">
        <div class="input-field ">
          <input id="search" ng-model="searchString" class="search" type="search" required placeholder="Buscar Clinica">
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
              <th data-field="id">Cod clinica</th>
              <th data-field="price">Nombre</th>
              <th data-field="name">Telefono</th>
              <th data-field="price">Direccion</th>
              <th data-field="price">Opciones</th>
          </tr>
        </thead>
        <tbody class="list">
            <tr ng-repeat="cli in clinicas | searchFor:searchString">
                <td class="id">{{cli.codClinica}}</td>
                <td class="nombre">{{cli.nombre}}</td>
                <td class="apellido">{{cli.telefono}}</td>
                <td class="genero">{{cli.direccion}}</td>
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
  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/angularfiles/clinicas.js"></script>
  <script>
      
  </script>
</body>
</html>
