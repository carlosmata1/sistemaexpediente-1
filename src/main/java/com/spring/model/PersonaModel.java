/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spring.model;
import com.spring.entity.Persona;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author Benjamín
 */
@Repository("personaService")
@Transactional
public class PersonaModel extends AbstractModel<BigDecimal, Persona>{
   public Persona findById(BigDecimal id) {
		return getByKey(id);
	}

	public void savePersona(Persona persona) {
		persist(persona);
	}

	public void deletePersonaById(BigDecimal codPersona) {
		Query query = getSession().createSQLQuery("delete from persona where cod_persona = :cod_persona");
		query.setBigDecimal("cod_persona", codPersona);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<Persona> findAllPersonas() {
		Criteria criteria = createEntityCriteria();
		return (List<Persona>) criteria.list();
	}

	public Persona findPersonaById(BigDecimal codPersona) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("codPersona", codPersona));
		return (Persona) criteria.uniqueResult();
	}
        
        public void updatePersona(Persona persona) {
		Persona entity = findById(persona.getCodPersona());
		if(entity!=null){
                        entity.setCodPersona(persona.getCodPersona());
                        entity.setNombre(persona.getNombre());
                        entity.setApellido(persona.getApellido());
                        entity.setFechaNacimiento(persona.getFechaNacimiento());
                        entity.setConyuge(persona.getConyuge());
                        entity.setDireccion(persona.getDireccion());
                        //entity.setMunicipio(persona.getMunicipio());
                        entity.setDepartamento(persona.getDepartamento());
                        entity.setDui(persona.getDui());
                        entity.setProfesion(persona.getProfesion());
                        entity.setEstadoCivil(persona.getEstadoCivil());
                        entity.setPadre(persona.getPadre());
                        entity.setMadre(persona.getMadre());
                        entity.setGenero(persona.getGenero());
		}
	} 
}
