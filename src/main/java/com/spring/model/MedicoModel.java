/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spring.model;
import com.google.gson.Gson;
import com.spring.entity.Medico;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author Benjamín
 */
@Repository("medicoService")
@Transactional
public class MedicoModel extends AbstractModel<BigDecimal, Medico>{
    
   public Medico findById(BigDecimal id) {
		Medico med = getByKey(id);
                Hibernate.initialize(med.getEmpleado());
                Hibernate.initialize(med.getEspecialidad());
                Hibernate.initialize(med.getEmpleado().getPersona());
                return med;
	}

	public void saveMedico(Medico medico) {
		persist(medico);
	}

	public void deleteMedicoById(BigDecimal codMedico) {
		Query query = getSession().createSQLQuery("delete from medico where cod_medico = :cod_medico");
		query.setBigDecimal("cod_medico", codMedico);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public String findAllMedicos() {
                String q = "select cod_medico,nombre, apellido, especialidad, fecha_contratacion from (select * from medico natural join empleado natural join persona) where activo = 1";
                Query query = getSession().createSQLQuery(q);
		List<Object> results = query.list();
                String medicos = new Gson().toJson(results);                
                return medicos;
	}
        
        public void updateMedico(Medico medico) {
		Medico entity = findById(medico.getCodMedico());
		if(entity!=null){
                        entity.setEspecialidad(medico.getEspecialidad());
		}
	} 
       
}
