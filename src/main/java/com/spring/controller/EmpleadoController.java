/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spring.controller;
import com.spring.entity.Clinica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import com.spring.entity.Empleado;
import com.spring.model.ClinicaModel;
import com.spring.model.EmpleadoModel;
import com.spring.model.PersonaModel;
import java.math.BigDecimal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
/**
 *
 * @author Benjamín
 */
@RestController
public class EmpleadoController {
    
    @Autowired
    EmpleadoModel modelEmp;
    
    @Autowired
    ClinicaModel modelCli;
    
    @Autowired
    PersonaModel modelPer;
    
    @RequestMapping(value = "/empleados/", method = RequestMethod.GET)
    public String getEmpleados(){
        String empleados = modelEmp.findAllEmpleados(); 
        return empleados;
    }
    
    @RequestMapping(value = "/empleados/", method = RequestMethod.POST)
    public ResponseEntity<Void> addEmpleado(@RequestBody Empleado empleado){
            Clinica cliEscogida = modelCli.findByNombre(empleado.getClinica().getNombre());
            empleado.setClinica(cliEscogida);
            modelEmp.saveEmpleado(empleado);
            return new ResponseEntity<>(HttpStatus.CREATED);
	}
    
    @RequestMapping(value="/empleados/{id}", method = RequestMethod.GET )
	public ResponseEntity<Empleado> getEmpleadoById(@PathVariable("id") BigDecimal id) {
            Empleado emp = modelEmp.findById(id);
		return new ResponseEntity<>(emp, HttpStatus.OK);
	}
    
    @RequestMapping(value="/empleados/{id}", method = RequestMethod.PUT )
	public ResponseEntity<Empleado> updateEmpleado(@RequestBody Empleado empleado) { 
	modelEmp.updateEmpleado(empleado);
        modelPer.updatePersona(empleado.getPersona());
	return new ResponseEntity<>(HttpStatus.OK);
    }
        
    @RequestMapping(value="/empleados/{id}", method = RequestMethod.DELETE )
        public ResponseEntity<Void> deleteEmpleado(@PathVariable("id") BigDecimal id) {         
            modelEmp.deleteEmpleadoById(id);
            modelPer.deletePersonaById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
    
}
