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

   <body ng-controller="ExpedientesController">


<!--Barra Lateral-->
<header>
  <div class="container "><a href="#" data-activates="nav-mobile" class="button-collapse top-nav waves-effect waves-light circle hide-on-large-only"><i class="material-icons">menu</i></a></div>
        <ul id="nav-mobile" class="side-nav fixed">
          <img src="${pageContext.request.contextPath}/resources/images/Logo3.png" width="300px">

          <li><div class="divider"></div></li>

          <li class="bold "><a href="" class="waves-effect waves-light"><i class="material-icons">home</i>Inicio</a></li>
            <li class="bold"><a href="logout" class="waves-effect waves-light"><i class="material-icons">close</i>Cerrar Sesión</a></li>
          </ul>
</header>
<!-- FIN Barra Lateral-->

<!-- barra de busqueda-->

 <main>    

  <!--<!encabezado-->
 <div class="section no-pad-bot #263238 blue-grey darken-4" id="ini">
         <div class="container ">
          <h2 class="white-text left thin"><i class="material-icons"></i>Búsqueda de expedientes</h2>
          <div class='row center'>
          </div>
        </div>
        </div>
  <br>



<div class="container">
    <div>
       <br>
       <nav>
    <div class="nav-wrapper #f50057 red accent-3">
        <div class="input-field ">
          <input id="search" ng-model="searchString" class="search" type="search" required placeholder="Buscar">
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
              <th data-field="id">Cod expediente</th>
              <th data-field="price">Nombre</th>
              <th data-field="name">Apellido</th>
              <th data-field="price">Dui</th>
              <th data-field="price">Departamento</th>
              <th data-field="price">Opciones</th>
          </tr>
        </thead>
        <tbody class="list">
            <tr ng-repeat="e in expedientes | searchFor:searchString">
                <td class="id">{{e.codExpediente}}</td>
                <td class="id">{{e.nombre}}</td>
                <td class="nombre">{{e.apellido}}</td>
                <td class="apellido">{{e.dui}}</td>
                <td class="genero">{{e.departamento}}</td>
                <td><a href="expediente?id={{e.codExpediente}}">Ver expediente</a></td>
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
  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/angularfiles/expedientes.js"></script>
  <script>

  </script>
</body>
</html>
