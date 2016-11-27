/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spring.controller;
import com.google.gson.Gson;
import com.spring.entity.Clinica;
import com.spring.entity.Consultamedica;
import com.spring.entity.Expediente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import com.spring.entity.Paciente;
import com.spring.model.ClinicaModel;
import com.spring.model.ConsultaModel;
import com.spring.model.ExamenModel;
import com.spring.model.ExpedienteModel;
import com.spring.model.PacienteModel;
import com.spring.model.PersonaModel;
import com.spring.model.SignosModel;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
/**
 *
 * @author Benjamín
 */

@RestController
public class PacienteController {
    
    @Autowired
    PacienteModel modelPac;
    
    @Autowired
    ExpedienteModel modelExp;
    
    @Autowired
    ClinicaModel modelCli;
    
    @Autowired
    PersonaModel modelPer;
    
    @Autowired
    SignosModel modelSig;
   
    
    @Autowired
    HttpServletRequest request;
    
    
    @RequestMapping(value = "/pacientes/", method = RequestMethod.GET)
    public String getPacientes(){
        System.out.println(request.getSession().getAttribute("email"));
        String pacientes = modelPac.findAllPacientes();
        return pacientes;
    }
    
    //Creacion de uno nuevo
    @RequestMapping(value = "/pacientes/", method = RequestMethod.POST)
    public ResponseEntity<Void> addPaciente(@RequestBody Paciente paciente){    
            //JSON map parsing so we can get both objects, probably unnecesary in this case because Clinica will have to be stored in session
            /*LinkedHashMap req = (LinkedHashMap)request;
            Gson gson = new Gson();
            String jsonPersona = gson.toJson(req.get("paciente"), Map.class);  //json object with all the data
            Paciente paciente = gson.fromJson(jsonPersona, Paciente.class);
            Integer cli = Integer.parseInt(gson.toJson(req.get("clinica"), Integer.class));
            //End of json parsing, we obtain all the objects we needed in order to create Expediente
            Clinica cliEscogida = modelCli.findById(new BigDecimal(cli));*/
            //Clinica cliEscogida = modelCli.findById(BigDecimal.ONE);
            Expediente expediente = new Expediente(paciente.getIdAfiliado(), paciente, new Date());
            modelExp.saveExpediente(expediente);
            return new ResponseEntity<>(HttpStatus.CREATED);
	}
    
    @RequestMapping(value="/pacientes/{id}", method = RequestMethod.GET )
	public ResponseEntity<Paciente> getPacienteById(@PathVariable("id") BigDecimal id) {
            Paciente person = modelPac.findById(id);
		return new ResponseEntity<>(person, HttpStatus.OK);
	}
    
    @RequestMapping(value="/pacientes/{id}", method = RequestMethod.PUT )
	public ResponseEntity<Paciente> updatePaciente(@RequestBody Paciente paciente) { 
	//En realidad la mayoria de objetos que se actualizan, deberan actualizar la persona
        System.out.println(paciente);
        modelPer.updatePersona(paciente.getPersona());
	return new ResponseEntity<>(HttpStatus.OK);
    }
        
    @RequestMapping(value="/pacientes/{id}", method = RequestMethod.DELETE )
        public ResponseEntity<Void> deletePaciente(@PathVariable("id") BigDecimal id) {
            modelExp.deleteExpedienteById(id);
            modelPac.deletePacienteById(id);
            modelPer.deletePersonaById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
    
     @RequestMapping(value="/expedientes/{id}", method = RequestMethod.GET )   //Hace un if para de acuerdo al actor mandar los datos necesarios
	public ResponseEntity <List<Object>> getExpediente(@PathVariable("id") BigDecimal id) {
            Expediente exp = modelExp.findById(id);
            List<Object> respo = new ArrayList();
            respo.add(exp);
            boolean hay = false;
            Set<Consultamedica> cons = exp.getConsultamedicas();
            System.out.println(cons);
            Consultamedica pendiente = new Consultamedica();
            for(Consultamedica c : cons){
                c.setExpediente(null);
                if(c.getDiagnostico().compareTo("pendiente")==0){
                    hay = true;
                    pendiente = c;
                }
            }
            cons.remove(pendiente);
            respo.add(exp.getConsultamedicas());
            respo.add(exp.getExamens());
            respo.add(pendiente);
            
            if(hay){
                respo.add(modelSig.findById(pendiente.getCodConsulta()));
                        }
            
		return new ResponseEntity<>(respo, HttpStatus.OK);
	}
    
     @RequestMapping(value="/expedientes/", method = RequestMethod.GET )
	public String getExpedientes() {
            String exp = modelExp.findAllExpedientes();
		return exp;
	}
     @RequestMapping(value="/sesion/", method = RequestMethod.GET)
     public String getSession(){
         String session = new Gson().toJson(request.getSession());
         return session;
     }
        
}
