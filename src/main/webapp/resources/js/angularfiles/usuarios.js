angular.module('Expediente', [])

//Controlador del usuario, metodos REST
.controller('UsuarioController', function ($scope, $http) {
    $scope.newuser = {};
    $scope.guardar = true;
    var url = "/sistemaexpediente/usuarios/";
    
    //Llamada ajax para traer todos los usuarios incialmente
    $http.get(url).then(function success(response) {
             var r = [];
            var result = JSON.parse(response.data);
            console.log(result);
            result.forEach(function (result) {
                  r.push({
                        codUsuario: result[0],
                        email: result[1],
                        contrasena: result[2],
                        rol: result[3],
                        nombrecompleto: result[4] + " " + result[5]
                    });  
                });
                $scope.usuarios = r;             
            }, function error(error) {
                console.log(error);
            });
            
    //Llamada para borrar
    $scope.delete = function (indice) {
         var idUs = $scope.usuarios[indice].codUsuario;
         $http.delete(url+idUs).then(function success() { 
                 $scope.usuarios.splice(indice, 1);
                });
        };
        
    //Llamada para editar        
    $scope.edit = function (indice) {
           var idUs = $scope.usuarios[indice].codUsuario;
           $http.get(url+idUs).then(function success(response) {
                    $scope.newuser = response.data;
                    $scope.guardar = false;
                    $('#modal1').openModal({dismissible: false});
                })
         };
         
    //Llamada para crear el nuevo usuari     
    $scope.crearUser = function () { 
        var user = $scope.newuser;
        user.activo = 0;
        user.rol = {codRol: 3, nombre: "Médico"}; //Lista cargada con todos los objetos de rol y angular select
        //user.clinica = {codClinica: 1, nombre: "Central", telefono: "2225-9854"}; //Lista cargada con todos los objetos de rol y angular select
        user.empleado = {codEmpleado: 4, fechaContratacion: null, puesto: null}; //Se tendra que hacer una lista desplegable con solo el id
        //Fin de datos agregados
        
        if($scope.guardar){
        console.log(user);
        $http.post(url, JSON.stringify(user)).then(function success() {
                    $scope.newuser = {};
                    //$scope.usuarios.push();
                });
        }
        else { //En dado caso de update
           $http.put(url+user.codUser, JSON.stringify(user)).then(function (response) {
                       console.log("updated");
                    });
           
        }
        
        $('#modal1').closeModal(); //Cerrando cuando se completa
    };
        
    //Limpiar la forma
    $scope.clean = function () {
              $scope.newuser = {};
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
        angular.forEach(arr, function(usuario){
            if(usuario.nombrecompleto.toLowerCase().indexOf(searchString) !== -1 || usuario.email.toLowerCase().indexOf(searchString) !== -1 || usuario.rol.toLowerCase().indexOf(searchString) !== -1){
            result.push(usuario);
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



