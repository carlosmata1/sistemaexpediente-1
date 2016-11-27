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
import com.spring.entity.Usuario;
import com.spring.model.EmpleadoModel;
import com.spring.model.UsuarioModel;
import java.math.BigDecimal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

/**
 *
 * @author Benjamín
 */
@RestController
public class UsuarioController {
    
    @Autowired
    UsuarioModel modelUsu;
    
    @Autowired
    EmpleadoModel modelEmp;
    
    @RequestMapping(value = "/usuarios/", method = RequestMethod.GET)
    public String getUsuarios(){
        String usuarios = modelUsu.findAllUsuarios(); 
        return usuarios;
    }
    
    @RequestMapping(value = "/usuarios/", method = RequestMethod.POST)
    public ResponseEntity<Void> addUsuario(@RequestBody Usuario usuario){
            String hashPass = encrypt(usuario.getContrasena());
            usuario.setContrasena(hashPass);
            BigDecimal idEmp = usuario.getEmpleado().getCodEmpleado();
            usuario.setEmpleado(modelEmp.findById(idEmp));
            usuario.setCodUsuario(new BigDecimal("2"));
            modelUsu.saveUsuario(usuario);
            return new ResponseEntity<>(HttpStatus.CREATED);
	}
    
    @RequestMapping(value="/usuarios/{id}", method = RequestMethod.GET )
	public ResponseEntity<Usuario> getUsuarioById(@PathVariable("id") BigDecimal id) {
            Usuario emp = modelUsu.findById(id);
            return new ResponseEntity<>(emp, HttpStatus.OK);
	}
    
    @RequestMapping(value="/usuarios/{id}", method = RequestMethod.PUT )
	public ResponseEntity<Usuario> updateUsuario(@RequestBody Usuario paciente) { 
	//modelPac.updateUsuario(paciente);
	return new ResponseEntity<>(paciente, HttpStatus.OK);
    }
        
    @RequestMapping(value="/usuarios/{id}", method = RequestMethod.DELETE )
        public ResponseEntity<Void> deleteUsuario(@PathVariable("id") BigDecimal id) {
            modelUsu.deleteUsuarioById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
    
        public static String encrypt(String val) {
        String hash = null; 
        
        try {
            //Encriptando contraseña ingresada
            MessageDigest dig = MessageDigest.getInstance("SHA-256");
            dig.update(val.getBytes(StandardCharsets.UTF_8));
//            String hash = Base64.getEncoder().encodeToString( dig.digest() );
            hash = (new HexBinaryAdapter()).marshal(dig.digest()).toLowerCase();
        } catch (NoSuchAlgorithmException ex) {
            return hash;
        }
        
        return hash;
    }
    
}
