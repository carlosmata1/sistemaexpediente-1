/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spring.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import com.spring.entity.Clinica;
import com.spring.model.ClinicaModel;
import java.math.BigDecimal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Benjamín
 */
@RestController
public class ClinicaController {
    
    @Autowired
    ClinicaModel modelCli;
    
    @RequestMapping(value = "/clinicas/", method = RequestMethod.GET)
    public List getClinicas(){
        List<Clinica> clinicas = modelCli.findAllClinicas(); 
        return clinicas;
    }
    
    @RequestMapping(value = "/clinicas/", method = RequestMethod.POST)
    public ResponseEntity<Void> addClinica(@RequestBody Clinica clinica){
            modelCli.saveClinica(clinica);
            return new ResponseEntity<>(HttpStatus.CREATED);
	}
    
    @RequestMapping(value="/clinicas/{id}", method = RequestMethod.GET )
	public ResponseEntity<Clinica> getClinicaById(@PathVariable("id") BigDecimal id) {
            Clinica emp = modelCli.findById(id);
		return new ResponseEntity<>(emp, HttpStatus.OK);
	}
    
    @RequestMapping(value="/clinicas/{id}", method = RequestMethod.PUT )
	public ResponseEntity<Clinica> updateClinica(@RequestBody Clinica clinica) { 
        modelCli.updateClinica(clinica);
        
	return new ResponseEntity<>(HttpStatus.OK);
    }
        
    @RequestMapping(value="/clinicas/{id}", method = RequestMethod.DELETE )
        public ResponseEntity<Void> deleteClinica(@PathVariable("id") BigDecimal id) {
            modelCli.deleteClinicaById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
        
    @RequestMapping(value = "/clinicalist/", method = RequestMethod.GET)
    public String getListCli(){
        String clinicas = modelCli.listCli();
        return clinicas;
    }    
    
}
