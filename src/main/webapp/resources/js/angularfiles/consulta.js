angular.module('Expediente', [])
        .controller('ConsultaController', function ($scope, $http, $window) {
            $scope.you = {};
            $scope.pen = {};
            $scope.newsig = {};
            $scope.count = 0;           
            
            //Obtener que usuario esta logueado
    $http.get("/sistemaexpediente/sesion/").then(function success(response) {
                var resp = JSON.parse(response.data);
                console.log(resp.session.attributes);
                $scope.you = resp.session.attributes;
            });
            
            //Inicializando variables
            $scope.pendiente = false;
            //$scope.expediente = {};
            var pacId = parseQuery($window)["id"];
            var url = '/sistemaexpediente/expedientes/'+pacId;
            
            //Recuperando el expediente
            $http.get(url).then(function success(response) {
                 $scope.expediente = response.data[0];
                 $scope.consultas = response.data[1];
                $scope.examens = response.data[2];
                $scope.pen = response.data[3];
                $scope.signospen = response.data[4];
                if($scope.pen.codConsulta!==null) {console.log('something'); $scope.pendiente = true}     
                }, function error(error) {
                  console.log(error);
                });
                
           //Mostrando un examen
           $scope.mostrarExa = function (indice) {
                $('#modal2').openModal({dismissible: false});
                $scope.examenPan = $scope.expediente.examens[indice];
            };
            
            //Mostrando la consulta
            $scope.mostrarCon = function (indice) {
                $('#modal1').openModal({dismissible: false});
                $scope.newcon = null;
            };
            //Cerrando consulta
           $scope.cerrarCon = function () {
                $('#modal1').closeModal();
            };
            
            //Guardando signos vitales
            $scope.crearSignos = function () {
                var newsig = $scope.newsig;               
                var consulta = {codConsulta: newsig.codSignos, expediente: null, fecha: null, diagnostico: 'pendiente'}; 
                newsig.consultamedica = consulta; 
                console.log(newsig);
                $scope.pen = consulta;
                $scope.pendiente = true;
                $http.post('/sistemaexpediente/exp/'+$scope.expediente.codExpediente+'/signosvitales/', JSON.stringify(newsig)).then(function success() {
                    console.log("saved");
                    $('#modal3').closeModal();
                })
            };
            
            $scope.terminar = function () {
              $('#modal1').openModal({dismissible: false});
              $scope.newcon = $scope.pen;
              $scope.newcon.receta = {fecha: $scope.pen.fecha, dosises: []};
            };
            
            $scope.guardarCon = function () {
                console.log($scope.newcon);
              
                $http.put('/sistemaexpediente/exp/'+$scope.expediente.codExpediente+'/consultas/'+$scope.newcon.codConsulta, JSON.stringify($scope.newcon)).then(function success() {
                    console.log("updated");
                    $scope.pen = {};
                    $scope.pendiente=false;
                    $scope.newcon = {};
                });
            }
            
            $scope.addDo = function (newDosis) {
                $scope.newcon.receta.dosises.push({medicamento: "", unidad: ""});
                $scope.count = $scope.count+1; 
            }
           
})


    function parseQuery($window) {

          var str = $window.location.search;
          var objURL = {};
          str.replace(
          new RegExp( "([^?=&]+)(=([^&]*))?", "g" ),
          function( $0, $1, $2, $3 ){
            objURL[ $1 ] = $3;
            }
        );
        return objURL;
        };
        
//Materialize code
        $(document).ready(function(){
            $('.modal-trigger').leanModal({
                dismissible: false
            });
            });

        $(document).ready(function() {
            $('select').material_select();
        }); 
