angular.module('Expediente', [])

//Controlador del paciente, metodos REST
.controller('PacienteController', function ($scope, $http) {
    $scope.logueado = {};
    $scope.newpac = {};
    $scope.guardar = true;
    $scope.disable="disabled";
    var url = "/sistemaexpediente/pacientes/";
    
    //Obtener que usuario esta logueado
    $http.get("/sistemaexpediente/sesion/").then(function success(response) {
                var resp = JSON.parse(response.data);
                console.log(resp.session.attributes);
            })
    
    //Llamada ajax para traer todos los pacientes incialmente
    $http.get(url).then(function success(response) {
            var r = [];
            var result = JSON.parse(response.data);
            result.forEach(function (result) {
                  r.push({
                        idAfiliado: result[0],
                        nombre: result[1],
                        apellido: result[2],
                        dui: result[3],
                        genero: result[4]
                    });  
                });
                $scope.pacientes = r;                
            }, function error(error) {
                console.log(error);
            });
            
    //Llamada para borrar
    $scope.delete = function (indice) {
         var idAf = $scope.pacientes[indice].idAfiliado;
         $http.delete(url+idAf).then(function success() { 
                 $scope.pacientes.splice(indice, 1);
                });
        };
        
    //Llamada para editar        
    $scope.edit = function (indice) {
           var idAfi = $scope.pacientes[indice].idAfiliado;
           $http.get(url+idAfi).then(function success(response) {
                    $scope.newpac = response.data.persona;
                    console.log($scope.newpac)
                    $scope.guardar = false;
                    $('#modal1').openModal({dismissible: false});
                    Materialize.updateTextFields();
                })
         };
         
    //Llamada para crear o guardar el nuevo paciente     
    $scope.crearPac = function () {
        var temp = $scope.newpac;
        console.log($scope.newpac.fechaNacimiento);
        temp.fechaNacimiento = new Date(temp.fechaNacimiento);
        var paciente = {idAfiliado: temp.codPersona, persona: temp, activo: 1};
        
        if($scope.guardar){ //En dado caso   
        $http.post(url, JSON.stringify(paciente)).then(function success() {
                    $scope.newpac = {};
                    $scope.pacientes.push({idAfiliado: paciente.idAfiliado, nombre: temp.nombre, apellido: temp.apellido, dui: temp.dui, genero: temp.genero});
                });
        }
        else { //En dado caso de update
           $http.put(url+paciente.idAfiliado, JSON.stringify(paciente)).then(function (response) {
                       console.log("updated");
                       $scope.newpac = {};
                       $scope.guardar = true;
                    });
           
        }
        
        $('#modal1').closeModal(); //Cerrando cuando se completa
    };
        
    //Limpiar la forma
    $scope.clean = function () {
              $scope.newpac = {};
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
        angular.forEach(arr, function(paciente){
            if(paciente.nombre.toLowerCase().indexOf(searchString) !== -1 || paciente.dui.toLowerCase().indexOf(searchString) !== -1){
            result.push(paciente);
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

