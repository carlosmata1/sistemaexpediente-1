function populateForm(frm, data) {   
            $.each(data, function(key, value){  
            var $ctrl = $('[name='+key+']', frm); 
            if($ctrl.is('select')){
            $("option",$ctrl).each(function(){ 
            if (this.value===value) { 
                $ctrl.find('option[value="'+value+'"]')[0].selected = true;
            }
                    
            });
            }
            else {
                switch($ctrl.attr("type"))  
                {  
                    case "text" :   case "hidden":  case "textarea":  
                    $ctrl.val(value);
                    $ctrl.change();
                    break;   
                case "radio" : case "checkbox": 
                    $ctrl.each(function(){  
                   if($(this).attr('value') === value) {  $(this).attr("checked",value); } });   
                    break;
            } 
            } 
        })};
    
            var intention = "post";
                 
            //Get all data on init
            $(document).ready(function () {
                $.ajax({
                type: "GET",
                url: "/sistemaexpediente/pacientes/",
                processData:false,
                success: function(pacientes){
                    console.log(pacientes)
                    for (var i = 0; i < pacientes.length; i++) {
                        var persona = pacientes[i].persona;
                        /*pacientesList.add({
                            id: persona.codPersona,
                            apellido: persona.apellido,
                            nombre: persona.nombre,
                            genero: persona.genero
                        })*/
                        $('#tabPacientes').append('<tr><td class="id">'+persona.codPersona+'</td><td class="nombre">'+persona.nombre+'</td><td class="apellido">'+persona.apellido+'</td><td class="genero">'+persona.genero+'</td><td><button class="delete">Borrar</button></td><td><button class="update">Editar</button></td></tr>');  
                    }
                }
                });
            });
            
            //POST on form submit
            $('#formulario').submit(function(e) {
                var per = $('#formulario').serializeObject();
                e.preventDefault();
                
                if(intention==="post"){
                    
                console.log(intention)    
                $.ajax({
                    type: "POST",
                    url: "/sistemaexpediente/pacientes/",
                    data: JSON.stringify(per),
                    contentType: 'application/json; charset=utf-8',         
                    processData:false,
                    success: function() {
                        $('#tabPacientes').append('<tr><td class="id">'+per.codPersona+'</td><td class="nombre">'+per.nombre+'</td><td class="apellido">'+per.apellido+'</td><td class="genero">'+per.genero+'</td><td><button class="delete">Borrar</button></td><td><button class="update">Editar</button></td></tr>');  
                    }
                });
                
                
                } else {
                    
                    console.log(intention);
                    $.ajax({
                    type: "PUT",
                    url: "/sistemaexpediente/pacientes/"+per.codPersona,
                    data: JSON.stringify(per),
                    contentType: 'application/json; charset=utf-8',         
                    processData:false,
                    success: function(pacUp) {
                        intention = "post";
                        var tableRow = $("td").filter(function() {
                            return $(this).text() == per.codPersona;
                        }).closest("tr");
                         tableRow.remove();
                         $('#tabPacientes').append('<tr><td class="id">'+pacUp.codPersona+'</td><td class="nombre">'+pacUp.nombre+'</td><td class="apellido">'+pacUp.apellido+'</td><td class="genero">'+pacUp.genero+'</td><td><button class="delete">Borrar</button></td><td><button class="update">Editar</button></td></tr>');  
                    }
                })
                
                }
                
                $('#modal1').find('input:text, input:password, select, textarea').val(['']);
                $('#modal1').closeModal();
              });
             
              //Delete from table 
             $(document).on('click', '#tabPacientes .delete', function () {
                   var row = $(this).closest('tr')
                   var codPac = row.find('td')[0].innerHTML;
                   $.ajax({
                    type: "DELETE",
                    url: "/sistemaexpediente/pacientes/"+codPac,      
                    success: function() {
                        row.remove();
                    }
                });
            });
            
            //Put data on form with get request
            $(document).on('click', '#tabPacientes .update', function () {
                   var row = $(this).closest('tr')
                   var codPac = row.find('td')[0].innerHTML;
                   $.ajax({
                    type: "GET",
                    url: "/sistemaexpediente/pacientes/"+codPac,      
                    success: function(paciente) {
                       paciente.departamento = String(paciente.departamento); 
                       paciente.municipio = String(paciente.municipio); 
                       populateForm('#formulario', paciente); 
                       $('#modal1').openModal();
                       intention = "update";
                    }
                });
            });
            
            $(document).ready(function(){
            $(".button-collapse").sideNav();
            });

             $(document).ready(function(){
                // the "href" attribute of .modal-trigger must specify the modal ID that wants to be triggered
                $('.modal-trigger').leanModal();  
              });

            /*Scrip del boton pop up*/
            $(document).ready(function() {
              $('select').material_select();
              });
              
           
            /*Script para las fechas*/
             $('.datepicker').pickadate({
                selectMonths: true, // Creates a dropdown to control month
               selectYears: 15, // Creates a dropdown of 15 years to control year
               format: 'yyyy-mm-dd'
              });
              

              //validacion de Formulario
                 $('#addbutton').click(function(){ 
                     
                  });

                  

