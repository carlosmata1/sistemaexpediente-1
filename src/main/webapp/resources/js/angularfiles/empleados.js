angular.module('Expediente', [])

//Controlador del empleado, metodos REST
.controller('EmpleadoController', function ($scope, $http) {
    $scope.newemp = {}; 
    $scope.guardar = true;
    $scope.logueado = {};
    var url = "/sistemaexpediente/empleados/";

    //Obtener que usuario esta logueado
    $http.get("/sistemaexpediente/sesion/").then(function success(response) {
                var resp = JSON.parse(response.data);
                $scope.logueado = resp;
            })
    
    $http.get("/sistemaexpediente/clinicalist/").then(function success(response) {   
            $scope.clinicas = JSON.parse(response.data);   
            })
  
    //Llamada ajax para traer todos los empleados incialmente
    $http.get(url).then(function success(response) {
            var r = [];
            var result = JSON.parse(response.data);
            result.forEach(function (result) {
                  r.push({
                        codEmpleado: result[0],
                        nombre: result[1],
                        apellido: result[2],
                        puesto: result[3],
                        fechaContratacion: result[4]
                    });  
                });
                $scope.empleados = r;                
            }, function error(error) {
                console.log(error);
            });
            
    //Llamada para borrar
    $scope.delete = function (indice) {
         var idEmp = $scope.empleados[indice].codEmpleado;
         $http.delete(url+idEmp).then(function success() { 
                 $scope.empleados.splice(indice, 1);
                });
        };
        
    //Llamada para editar        
    $scope.edit = function (indice) {
           var idEmp = $scope.empleados[indice].codEmpleado;
           $http.get(url+idEmp).then(function success(response) {
                    $scope.newemp = response.data;
                    $scope.guardar = false;
                    $('#modal1').openModal({dismissible: false});
                })
         };
         
    //Llamada para crear o guardar el nuevo empleado     
    $scope.crearEmp = function () {
        var emp = $scope.newemp;
        //Estos datos deberan de ser tomados al cambiar el estilo de la forma
        //Fin de datos agregados
        emp.persona.profesion = emp.puesto;
        emp.persona.fechaNacimiento = new Date(emp.persona.fechaNacimiento);
        emp.fechaContratacion = new Date(emp.fechaContratacion);
        emp.persona.codPersona = emp.codEmpleado;
        emp.clinica = {nombre: "Central"};
        emp.activo = 1;
        
        
        if($scope.guardar){ //En dado caso 
        console.log(emp);
        $http.post(url, JSON.stringify(emp)).then(function success() {
                    $scope.newpac = {};
                    $scope.empleados.push({codEmpleado: emp.codEmpleado, nombre: emp.persona.nombre, apellido: emp.persona.apellido, puesto: emp.puesto, fechaContratacion: emp.fechaContratacion});
                });
        }
        else { //En dado caso de update
           console.log(emp);
           $http.put(url+emp.codEmpleado, JSON.stringify(emp)).then(function (response) {
                       console.log("updated");
                       $scope.newemp = {};
                       $scope.guardar = true;
                    });
           
        }
        
        $('#modal1').closeModal(); //Cerrando cuando se completa
    }
        
    //Limpiar la forma
    $scope.clean = function () {
              $scope.newemp = {};
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
        angular.forEach(arr, function(empleado){
            if(empleado.nombre.toLowerCase().indexOf(searchString) !== -1 || empleado.puesto.toLowerCase().indexOf(searchString) !== -1){
            result.push(empleado);
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
