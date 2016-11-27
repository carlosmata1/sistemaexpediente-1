/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spring.model;
import com.google.gson.Gson;
import com.spring.entity.Paciente;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author Benjamín
 */
@Repository("pacienteService")
@Transactional
public class PacienteModel extends AbstractModel<BigDecimal, Paciente>{
    
   public Paciente findById(BigDecimal id) {
		Paciente p = getByKey(id);
                Hibernate.initialize(p.getPersona());
                return p;
	}

	public void savePaciente(Paciente paciente) {
		persist(paciente);
	}

	public void deletePacienteById(BigDecimal idAfiliado) {
		Query query = getSession().createSQLQuery("delete from paciente where id_afiliado = :id_afiliado");
		query.setBigDecimal("id_afiliado", idAfiliado);
		query.executeUpdate();
	}
        
        //Metodo usado para llenar las tablas, hacemos una query especial y lo asignamos a objetos que transformamos a json y retornamos a la pagina
	@SuppressWarnings("unchecked")
	public String findAllPacientes() {
                String q = "select id_afiliado, nombre, apellido, dui, genero from paciente natural join persona where activo=1";
                Query query = getSession().createSQLQuery(q);
		List<Object> results = query.list();
                String pacientes = new Gson().toJson(results);
                
                return pacientes;
	}
       
}
