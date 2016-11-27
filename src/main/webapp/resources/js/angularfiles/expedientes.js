      angular.module('Expediente', [])
              .controller('ExpedientesController', function ($scope, $http) {
             $http.get('/sistemaexpediente/expedientes/').then(function success(response) {
             var r = [];
            var result = JSON.parse(response.data);
            
            result.forEach(function (result) {
                  r.push({
                        codExpediente: result[0],
                        nombre: result[1],
                        apellido: result[2],
                        dui: result[3],
                        departamento: result[4]
                    });  
                });
                $scope.expedientes = r;   
});
})
.filter('searchFor', function(){
        return function(arr, searchString){
        if(!searchString){
            return arr;
        }
        var result = [];
        searchString = searchString.toLowerCase();
        angular.forEach(arr, function(expediente){
            if(expediente.nombre.toLowerCase().indexOf(searchString) !== -1 || expediente.dui.toLowerCase().indexOf(searchString) !== -1 || expediente.apellido.toLowerCase().indexOf(searchString) !== -1){
            result.push(expediente);
        }
        });
        return result;
    };
});


