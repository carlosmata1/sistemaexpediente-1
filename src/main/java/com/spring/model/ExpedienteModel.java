/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spring.model;

import com.google.gson.Gson;
import com.spring.entity.Clinica;
import com.spring.entity.Expediente;
import com.spring.entity.Paciente;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Benjamín
 */
@Repository("expedienteService")
@Transactional
public class ExpedienteModel extends AbstractModel<BigDecimal, Expediente>{
    
        public Expediente findById(BigDecimal id) {
		Expediente exp = getByKey(id);
                if(exp!=null){
                Hibernate.initialize(exp.getPaciente());
                Hibernate.initialize(exp.getPaciente().getPersona());
                Hibernate.initialize(exp.getConsultamedicas());
                Hibernate.initialize(exp.getExamens());
                Hibernate.initialize(exp.getTratamientos());
                }
                
                return exp;
	}

	public void saveExpediente(Expediente expediente) {
                persist(expediente);
	}

	public void deleteExpedienteById(BigDecimal codExpediente) {
		Query query = getSession().createSQLQuery("delete from expediente where cod_expediente = :cod_expediente");
		query.setBigDecimal("cod_expediente", codExpediente);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public String findAllExpedientes() {
            String q = "select cod_expediente, nombre, apellido, dui, departamento from (select * from expediente natural join paciente) natural join persona";
                Query query = getSession().createSQLQuery(q);
		List<Object> results = query.list();
                String expedientes = new Gson().toJson(results);                
                return expedientes;
	}
               
}
