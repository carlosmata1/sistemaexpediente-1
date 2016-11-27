<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html lang="en">
    
<html>
      
    <head>
        <img src="${pageContext.request.contextPath}/resources/images/Logo.png" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/materialize.min.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/index.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/ghpages-materialize.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/prism.css" />
       
      <!--Import Google Icon Font-->
      <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
     
      <!--Let browser know website is optimized for mobile-->
      <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
      <title>Expediente</title>
</head>
<body>


<header>
  <div class="container "><a href="#" data-activates="nav-mobile" class="button-collapse top-nav waves-effect waves-light circle hide-on-large-only"><i class="material-icons">menu</i></a></div>
        <ul id="nav-mobile" class="side-nav fixed">
            <img src="resources/images/Logo3.png" width="300px" />

          <li><div class="divider"></div></li>
        
          <li class="bold active "><a href="index" class="waves-effect waves-light"><i class="material-icons">home</i>Inicio</a></li>
            <li class="bold"><a href="logout" class="waves-effect waves-light"><i class="material-icons">close</i>Cerrar Sesión</a></li>

          </ul>
</header>
<!-- FIN Barra Lateral-->
<main>
    
<div class="section no-pad-bot #263238 blue-grey darken-4" id="ini">
         <div class="container ">
          <h2 class="white-text left thin"><i class="material-icons"></i>Administración de Expedientes</h2>
          <div class='row center'>
          </div>
        </div>
</div>
<div class="container">
    <div class="row">
      <br>
    <br>  
    <br>
    <!--Input para Código del examen-->
    <div class="input-field col s3">
              <input id="codigoExamen" type="text" class="validate">
              <label for="codigoExamen">Código exámen</label>
            </div>
    
    <!--Input para Titulo del examen-->
     <div class="input-field col s3">
              <input id="titulo" type="text" class="validate">
              <label for="titulo">Título</label>
            </div>   
    
     
    <!--Area de resultados-->
    <form class="col s8">
      <div class="row">
        <div class="input-field col s6">
            <br>
            <br>
          <textarea id="resultados" class="materialize-textarea"></textarea>
          <label for="resultados">Resultados</label>
        </div>
      </div>
    </form>
    
    <!--Boton para subir-->
    <br>
    <br>
    <br>
  <div class="row">     
    <form class= "col s3" action="#">
    <div class="file-field">
      <div class="btn">
        <span>Subir</span>
        <input type="file">
      </div>
      <div class="file-path-wrapper">
        <input class="file-path validate" type="text">
      </div>
    </div>
   </div>
  </form>
       
 <button class="btn waves-effect waves-light #66bb6a green lighten-1" type="submit" name=""></i>Guardar</button>     
    </div>
 
    <br>
    <br>
 <div class="divider"></div>
 <br>
 <h5>Exámenes previos</h5>
 </div> 
</main>
 


  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.1.0.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-serialize.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/materialize.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/angular.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/angularfiles/expediente.js"></script>

  <script>
     		    $(document).ready(function(){
            $(".button-collapse").sideNav();
            });
  </script>

</body>
</html>

