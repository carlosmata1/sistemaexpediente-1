/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spring.controller;
import com.spring.entity.Consultamedica;
import com.spring.entity.Dosis;
import com.spring.entity.Receta;
import com.spring.entity.Signosvitales;
import com.spring.model.ConsultaModel;
import com.spring.model.ExpedienteModel;
import com.spring.model.SignosModel;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
/**
 *
 * @author Benjamín
 */
@RestController
public class ConsultaController {
    @Autowired
    ConsultaModel modelCon;
    
    @Autowired
    SignosModel modelSignos;
    
    @Autowired
    ExpedienteModel modelExp;
    

    
    
    @RequestMapping(value = "/exp/{id}/signosvitales/", method = RequestMethod.POST)
    public ResponseEntity<Void> addConsulta(@PathVariable("id") BigDecimal id, @RequestBody Signosvitales signos){
            System.out.println(id);
            signos.getConsultamedica().setFecha(new Date());
            signos.getConsultamedica().setExpediente(modelExp.findById(id));
            modelSignos.saveSignos(signos);
            return new ResponseEntity<>(HttpStatus.CREATED);
	}
    
    @RequestMapping(value="/exp/{exp}/consultas/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> terminarConsulta(@PathVariable("exp") BigDecimal expid, @RequestBody Consultamedica consulta){
        consulta.setExpediente(modelExp.findById(expid));
        modelCon.deleteConsultaById(consulta.getCodConsulta());
        System.out.println(consulta.getReceta());
        modelCon.saveConsulta(consulta);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    
}
