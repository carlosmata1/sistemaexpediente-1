/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spring.controller;
import com.google.gson.Gson;
import com.spring.entity.Usuario;
import com.spring.model.UsuarioModel;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
/**
 *
 * @author Benjamín
 */
@Controller
public class PageController {
    
    @Autowired
    HttpServletRequest request;
    
    @Autowired
    UsuarioModel modUser;
    
    
    @RequestMapping(value="/")
    public String inicio(){      
        return "index";
    }
    //Metodo para el inicio de sesion
    @RequestMapping(value="/login", method=RequestMethod.POST)
    public ResponseEntity iniciarSesion(@RequestBody Object credenciales){
        LinkedHashMap req = (LinkedHashMap)credenciales;
        String email = (String)req.get("email");
        String contrasena = (String)req.get("contrasena");
        boolean isValid = false;
        Usuario auth = new Usuario();
        //Validacion del usuario
        try {
            MessageDigest dig = MessageDigest.getInstance("SHA-256");
            dig.update(contrasena.getBytes(StandardCharsets.UTF_8));
            String hash = (new HexBinaryAdapter()).marshal(dig.digest()).toLowerCase();       
            Usuario user = modUser.findbyEmail(email);
            auth = user;
            if(user.getContrasena().equals(hash)) {
                isValid=true;
            }
            
        } catch (/*NoSuchAlgorithm*/Exception ex) {
            //Logger.getLogger(sesionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Fin de validacion, se debe de realizar por email, no por nombre usuario
        if(isValid){
            request.getSession().setAttribute("email", auth.getEmail());
            request.getSession().setAttribute("rol", auth.getRol().getCodRol().intValueExact());
            request.getSession().setAttribute("clinica", modUser.getClinica(auth.getCodUsuario()));
            System.out.println(modUser.getClinica(auth.getCodUsuario()));
             //Posiblemente adjuntar clinica para hacer la distincion
             String response = new Gson().toJson(request.getSession());
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
    
    @RequestMapping(value="/logout")
    public String logout(){
        request.getSession().invalidate();
        return "redirect:";
    }
    
    @RequestMapping(value="/pacientes")
    public String pacientes(){   
        try {
            Integer rol = (Integer)request.getSession().getAttribute("rol");
            if(rol!=1 && rol!=2){
                System.out.println("bloqueado");
              return "redirect:bloqueado";  
            } 
           
        } catch (Exception e) {
            System.out.println(e);
            return "redirect:";
        }
       
        return "pacientes";
    }
    
    @RequestMapping(value="/expedientes")  //Tambien requiere autenticacion para roles mayores a 2
    public String expedientes(){
        try {
            Integer rol = (Integer)request.getSession().getAttribute("rol");
            if(rol==1 || rol==2){
              return "redirect:bloqueado";  
            }                 
        } catch (Exception e) {
            System.out.println(e);
            return "redirect:";
        }
       
        return "expedientes";
    }
    
    @RequestMapping(value="/expediente", method = RequestMethod.GET)  //Se devuelve un jsp distinto de acuerdo a que actor lo solicita
    public String expediente(@RequestParam(value="id") int id){
        try {
            Integer rol = (Integer)request.getSession().getAttribute("rol");
            if(rol==1 || rol==2) return "redirect:bloqueado";
            
            if(rol<5) return "expedienteMed";
            
            if(rol<9) return "expedienteEx";
            
            if(rol==9) return "expedienteFs";

            
        } catch (Exception e) {
            return "redirect:";
        }
        return "redirect:";
    }
    
    @RequestMapping(value="/empleados")
    public String empleados(){
        try {
            Integer rol = (Integer)request.getSession().getAttribute("rol");
            if(rol!=1){
               return "redirect:bloqueado";
            }
        } catch (Exception e) {
            return "redirect:";
        }
        return "empleados";
    }
    
    @RequestMapping(value="/medicos")
    public String medicos(){
        try {
            Integer rol = (Integer)request.getSession().getAttribute("rol");
            if(rol!=1){
               return "redirect:bloqueado";
            }
        } catch (Exception e) {
            return "redirect:";
        }
        return "medicos";
    }
    
    @RequestMapping(value="/clinicas")
    public String clinicas(){
        try {
            Integer rol = (Integer)request.getSession().getAttribute("rol");
            if(rol!=1){
               return "redirect:bloqueado";
            }
        } catch (Exception e) {
            return "redirect:";
        }
        return "clinicas";
    }
    
    @RequestMapping(value="/usuarios")
    public String usuarios(){
        System.out.println("Requested users");
       try {
            Integer rol = (Integer)request.getSession().getAttribute("rol");
            if(rol!=1){
              return "redirect:bloqueado";  
            } 
        } catch (Exception e) {
            return "redirect:";
        }
        return "usuarios";
    }
    
    @RequestMapping(value="/bloqueado")
    public String bloqueado(){
        return "bloqueado";
    }
    
    
    
}
