angular.module('Expediente', [])

//Controlador del medico, metodos REST
.controller('MedicoController', function ($scope, $http) {
    $scope.newmed = {};
    $scope.guardar = true;
    var url = "/sistemaexpediente/medicos/";
    
    //Llamada ajax para traer todos los medicos incialmente
    $http.get(url).then(function success(response) {
            var r = [];
            var result = JSON.parse(response.data);
            result.forEach(function (result) {
                  r.push({
                        codMedico: result[0],
                        nombre: result[1],
                        apellido: result[2],
                        especialidad: result[3],
                        fechaContratacion: result[4]
                    });  
                });
                $scope.medicos = r;                
            }, function error(error) {
                console.log(error);
            });
            
    //Llamada para borrar
    $scope.delete = function (indice) {
         var idMed = $scope.medicos[indice].codMedico;
         $http.delete(url+idMed).then(function success() { 
                 $scope.medicos.splice(indice, 1);
                });
        };
        
    //Llamada para editar        
    $scope.edit = function (indice) {
           var idMed = $scope.medicos[indice].codMedico;
           $http.get(url+idMed).then(function success(response) {
                    $scope.newmed = response.data;
                    $scope.guardar = false;
                    $('#modal1').openModal({dismissible: false});
                })
         };
         
    //Llamada para crear o guardar el nuevo medico     
    $scope.crearMed = function () {
        var med = $scope.newmed;
        //Estos datos deberan de ser tomados al cambiar el estilo de la forma
        med.empleado.persona.fechaNacimiento = new Date(med.empleado.persona.fechaNacimiento);
        //med.empleado.persona.genero = "M";
       // med.empleado.persona.estadoCivil = "Soltero";
       // med.empleado.persona.departamento = "San Salvador";
        //med.empleado.persona.municipio = 1;
        med.empleado.persona.profesion = "Doctor";
        med.empleado.puesto = "Doctor";
        med.empleado.fechaContratacion = new Date(med.empleado.fechaContratacion);
        med.empleado.persona.codPersona = med.codMedico;
        med.empleado.codEmpleado = med.codMedico;
        med.empleado.clinica = {nombre: "Central"};
        med.empleado.activo = 1;
        //Fin de datos agregados
        
        if($scope.guardar){
        console.log(med);
        $http.post(url, JSON.stringify(med)).then(function success() {
                    $scope.newpac = {};
                    $scope.medicos.push({codMedico: med.codMedico, nombre: med.empleado.persona.nombre, apellido: med.empleado.persona.apellido, especialidad: med.especialidad, fechaContratacion: med.empleado.fechaContratacion});
                });
        }
        else { //En dado caso de update
           console.log(med);
           $http.put(url+med.codMedico, JSON.stringify(med)).then(function (response) {
                       console.log("updated");
                    });
           
        }
        
        $('#modal1').closeModal(); //Cerrando cuando se completa
    };
        
    //Limpiar la forma
    $scope.clean = function () {
              $scope.newmed = {};
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
        angular.forEach(arr, function(medico){
            if(medico.nombre.toLowerCase().indexOf(searchString) !== -1 || medico.especialidad.toLowerCase().indexOf(searchString) !== -1){
            result.push(medico);
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

