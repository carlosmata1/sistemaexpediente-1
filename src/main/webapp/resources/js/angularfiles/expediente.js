//Controlador del expediente, metodos REST        
angular.module('Expediente', [])
.controller('ExpedienteController', function ($scope, $http, $window) {
        
        $scope.expediente = {};
        var pacId = parseQuery($window)["id"];
        var url = '/sistemaexpediente/expedientes/'+pacId;
        console.log(url);
        $http.get(url).then(function success(response) {
                console.log(response.data);
                $scope.expediente = response.data;
            }, function error(error) {
                console.log(error);
            });
        
});

//Para extraer url
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


