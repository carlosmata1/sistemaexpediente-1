/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this tmedlate file, choose Tools | Tmedlates
 * and open the tmedlate in the editor.
 */
package com.spring.controller;
import com.spring.entity.Clinica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import com.spring.entity.Medico;
import com.spring.model.ClinicaModel;
import com.spring.model.EmpleadoModel;
import com.spring.model.MedicoModel;
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
public class MedicoController {
    
    @Autowired
    MedicoModel modelMed;
    
    @Autowired
    ClinicaModel modelCli;
    
    @Autowired
    PersonaModel modelPer;
    
    @Autowired
    EmpleadoModel modelEmp;
    
    @RequestMapping(value = "/medicos/", method = RequestMethod.GET)
    public String getMedicos(){
        String medicos = modelMed.findAllMedicos(); 
        return medicos;
    }
    
    @RequestMapping(value = "/medicos/", method = RequestMethod.POST)
    public ResponseEntity<Void> addMedico(@RequestBody Medico medico){
            Clinica cliEscogida = modelCli.findByNombre(medico.getEmpleado().getClinica().getNombre());
            medico.getEmpleado().setClinica(cliEscogida);      
            modelMed.saveMedico(medico);
            return new ResponseEntity<>(HttpStatus.CREATED);
	}
    
    @RequestMapping(value="/medicos/{id}", method = RequestMethod.GET )
	public ResponseEntity<Medico> getMedicoById(@PathVariable("id") BigDecimal id) {
            Medico med = modelMed.findById(id);
            return new ResponseEntity<>(med, HttpStatus.OK);
	}
    
    @RequestMapping(value="/medicos/{id}", method = RequestMethod.PUT )
	public ResponseEntity<Medico> updateMedico(@RequestBody Medico medico) { 
	modelPer.updatePersona(medico.getEmpleado().getPersona());
        modelEmp.updateEmpleado(medico.getEmpleado());
        modelMed.updateMedico(medico);
	return new ResponseEntity<>(HttpStatus.OK);
    }
        
    @RequestMapping(value="/medicos/{id}", method = RequestMethod.DELETE )
        public ResponseEntity<Void> deleteMedico(@PathVariable("id") BigDecimal id) {
            modelMed.deleteMedicoById(id);
            modelEmp.deleteEmpleadoById(id);
            modelPer.deletePersonaById(id);    
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
    
}
