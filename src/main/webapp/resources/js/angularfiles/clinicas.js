angular.module('Expediente', [])

//Controlador del clinica, metodos REST
.controller('ClinicaController', function ($scope, $http) {
    $scope.newcli = {};
    $scope.guardar = true;
    var url = "/sistemaexpediente/clinicas/";
    
    //Llamada ajax para traer todos los clinicas incialmente
    $http.get(url).then(function success(response) {
                $scope.clinicas = response.data;              
            }, function error(error) {
                console.log(error);
            });
            
    //Llamada para borrar
    $scope.delete = function (indice) {
         var idCli = $scope.clinicas[indice].codClinica;
         $http.delete(url+idCli).then(function success() { 
                 $scope.clinicas.splice(indice, 1);
                });
        };
        
    //Llamada para editar        
    $scope.edit = function (indice) {
           var idCli = $scope.clinicas[indice].codClinica;
           $http.get(url+idCli).then(function success(response) {
                    $scope.newcli = response.data;
                    $scope.guardar = false;
                    $('#modal1').openModal({dismissible: false});
                })
         };
         
    //Llamada para crear o guardar el nuevo clinica     
    $scope.crearCli = function () {
        var cli = $scope.newcli;
        /*cli.departamento = 1;
        cli.municipio = 1;*/
        //Fin de datos agregados
        
        if($scope.guardar){
        console.log(cli);
        $http.post(url, JSON.stringify(cli)).then(function success() {
                    $scope.newpac = {};
                    $scope.clinicas.push(cli);
                });
        }
        else { //En dado caso de update
           $http.put(url+cli.codClinica, JSON.stringify(cli)).then(function (response) {
                       console.log("updated");
                    });
           
        }
        
        $('#modal1').closeModal(); //Cerrando cuando se completa
    };
        
    //Limpiar la forma
    $scope.clean = function () {
              $scope.newcli = {};
              $scope.guardar = true;
            };

}) //Busqueda de elementos
.filter('searchFor', function(){
        return function(arr, searchString){
        if(!searchString){
            return arr;
        }
        var result = [];
        searchString = searchString.toLowerCase();
        angular.forEach(arr, function(clinica){
            if(clinica.nombre.toLowerCase().indexOf(searchString) !== -1 || clinica.direccion.toLowerCase().indexOf(searchString) !== -1){
            result.push(clinica);
        }
        });
        return result;
    };
});

//Materialize code
        $(document).ready(function(){
            $('.modal-trigger').leanModal({
                dismissible: false
            });
            });

        $(document).ready(function() {
            $('select').material_select();
        }); 


