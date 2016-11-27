<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="d" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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

   <body ng-controller="ConsultaController">


<!--Barra Lateral-->
<header>
  <div class="container "><a href="#" data-activates="nav-mobile" class="button-collapse top-nav waves-effect waves-light circle hide-on-large-only"><i class="material-icons">menu</i></a></div>
        <ul id="nav-mobile" class="side-nav fixed">
            <img src="resources/images/Logo3.png" width="300px" />

          <li><div class="divider"></div></li>
        
          <li class="bold "><a href="" class="waves-effect waves-light"><i class="material-icons">home</i>Inicio</a></li>
          <li class="bold "><a href="" class="waves-effect waves-light"><i class="material-icons">perm_identity</i>{{::you.email}}</a></li>
           <li class="bold "><a href="expedientes" class="waves-effect waves-light"><i class="material-icons">search</i>Buscar pacientes</a></li>
            <li class="bold"><a href="logout" class="waves-effect waves-light"><i class="material-icons">close</i>Cerrar Sesión</a></li>

          </ul>
</header>
<!-- FIN Barra Lateral-->


<!-- barra de busqueda-->

  <main>
 
 <!--<!encabezado-->
 <div class="section no-pad-bot #263238 blue-grey darken-4" id="ini">
         <div class="container ">
          <h2 class="white-text center thin"><i class="material-icons"></i>Expediente de {{::expediente.paciente.persona.nombre}} {{::expediente.paciente.persona.apellido}}</h2>
          <div class='row center'>
          </div>
        </div>
        </div>

<a class="waves-effect waves-light btn modal-trigger #ffd600 yellow accent-4"  href="#modal3" ><i class="material-icons left ">book</i>Agregar signos vitales</a>

<!-- tabla de resultados -->
<div class="container" ng-if="pendiente">
    <h4 class="black-text center thin"><i class="material-icons"></i>Consulta Pendiente</h4>
<table class="highlight centered">
        <tbody>
          <td>{{::pen.codConsulta}}</td>
          <td>{{::pen.fecha}}</td>
          <td>{{::pen.diagnostico}}</td>
          <td><button ng-click="terminar()">Finalizar consulta</button>
          </td>
        </tbody>
      </table>
</div>
<br>
<br>
<div class="container">
  <!-- Modal Trigger 
  <a class="modal-trigger waves-effect waves-light btn" href="#modal1">Agregar Consulta</a>-->

  <!-- Modal Structure -->
  <div id="modal1" class="modal">
      <form class="col s12" ng-submit="guardarCon()">
    <div class="modal-content">
        <div class="container">
        <br> 
        <div class="row">
       <div class="input-field col s4">
        <h6 for="codConsulta">Código de la Consulta</h6>
          <input type="text" ng-model="newcon.codConsulta" id="codConsulta" disabled/>
       </div>
        </div>
        <div class="row">
                <div class="input-field col s8">
                   <h6 for="textarea1">Diagnóstico</h6>
                  <textarea ng-model="newcon.diagnostico" class="materialize-textarea"></textarea>
                </div>
        </div>
        <div class="row"> 
            <div class="input-field col s6">
            <h4>Receta</h4>
            </div>
            <div class="input-field col s6">
                <button type="button" class="btn waves-effect waves-light" ng-click="addDo(newDosis)" >Añadir</button>  
            </div> 
          </div> 
        
        <div class="row" ng-repeat="dosis in newcon.receta.dosises track by $index">
            
           <div class="input-field col s6">
            <h6 for="codConsulta">Medicamento</h6>
          <input type="text" ng-model="dosis.medicamento"/>
            </div>
            <div class="input-field col s6">
            <h6>Indicaciones</h6>
          <input type="text" ng-model="dosis.unidad"/>
            </div> 

        </div>
        
        <br> 
        <br>      
 
    <div>
     <button class="btn waves-effect waves-light #66bb6a green lighten-1" type="submit" name="">Guardar Consulta</button>
    <a ng-click="cerrarCon()" class="waves-effect waves-light btn red"  href="#!">Cancelar</a>
       
    </div>
      </form>
  </div>
  </div>
  </div>        
   

    <div class="row">
      <div class="col s12 m6">
        <div class="card-panel #01579b light-blue darken-4">
            <center><span class="white-text ">Consultas
                </span></center>
        </div>
        <a href=""><div ng-repeat="con in consultas" class="card-panel #f5f5f5 grey lighten-6" ng-click="mostrarCon($index)">
          <span class="white-text"> {{::con.codConsulta}} {{::con.fecha}}
          </span>
             </div></a> 
      </div>
        
     <div class="col s12 m6">
        <div class="card-panel #00838f cyan darken-3">
            <center><span class="white-text">Examenes
          </span></center>
        </div>
         <a href=""><div ng-repeat="exp in examens" class="card-panel #f5f5f5 grey lighten-6" ng-click="mostrarExa($index)">
          <span class="white-text"> {{::exp.codExamen}} {{::exp.tipo}}
          </span>
             </div></a>
      </div>

    </div>
  
 
  <!-- Modal Structure -->
  <div id="modal2" class="modal modal-fixed-footer">
    <div class="modal-content" >
        <h4>{{::examenPan.tipo}}</h4>
      <center><img src="${pageContext.request.contextPath}/resources/public/{{::examenPan.ubicacion}}" width="90%" /></center>
      <br> 
      <h4>Resultados</h4>
       <br>
       <h6>A bunch of text</h6>
    </div>
    <div class="modal-footer">
      <a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat ">Cerrar</a>
    </div>
  </div>
  
  <div id="modal3" class="modal">
    <div class="modal-content" >
        <h4>Signos vitales</h4>
    </div>

        <div class="container">
            <form class="col s12" ng-submit="crearSignos()">
            <div class="row">

          <div class="input-field col s4">
          <input id="idsigno" ng-model="newsig.codSignos" type="text" placeholder="ID de los signos" >
          </div>

          <div class="input-field col s4">
          <h6 for="temperatura">Temperatura (Celcius)</h6>
          <input id="temperatura" ng-model="newsig.temperatura" type="text" name="temperatura" class="validate" min="0" max="100" step="any" required="" placeholder="Temperatura (Celcius)" >
          </div>

          <div class="input-field col s4">
              <h6 for="altura">Altura (metros)</h6>
          <input id="altura" ng-model="newsig.altura" type="text" name="altura" class="validate" min="0" max="3" step="any" required="" placeholder="Altura (metros)">
          </div>

           <!--<div class="input-field col s4">
          <input id="peso" type="number" class="validate" name="peso" min="0" step="any" required="" placeholder="Peso (Kg)">
          <h6 for="peso"></h6>
        </div>-->

            </div>


            <div class="row">

          <div class="input-field col s4">
              <h6 for="presion">Presion Alterial (mmHg)</h6>
          <input id="presion" ng-model="newsig.presion" type="text" class="validate" min="0" name="presion" step="any" required="" placeholder="Presion Alterial (mmHg)">
          
          </div>

          <div class="input-field col s4">
              <h6 for="pulso">Pulso cardiaco (latidos/minuto)</h6>
          <input id="pulso" ng-model="newsig.pulso" type="text" class="validate" min="0" step="any" name="pulso" required="" placeholder="Pulso cardiaco (latidos/minuto)">
          
          </div>



           <!--<div class="input-field col s4">
            <input id="medico" type="number" class="validate" min="0" name="medico" placeholder="Id del médico"/>
            <h6 for="medico"></h6>
          </div>-->
        </div>
            <button class="btn waves-effect waves-light" type="submit" name="action">Guardar<i class="material-icons right">send</i>         
          </button>
                <a href="#!"  class="modal-action modal-close waves-effect #e53935 red darken-1 waves-red btn-flat white-text">Cancelar</a>
            </form>  
          </div>


        
 
  </div>    
          
 
  
</div> <!--Fin COntainer-->
         

</main>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.1.0.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-serialize.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/materialize.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/angular.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/angularfiles/consulta.js"></script>


</body>
</html>
