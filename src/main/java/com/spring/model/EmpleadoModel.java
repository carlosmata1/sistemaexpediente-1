/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spring.model;
import com.google.gson.Gson;
import com.spring.entity.Empleado;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author Benjamín
 */
@Repository("empleadoService")
@Transactional
public class EmpleadoModel extends AbstractModel<BigDecimal, Empleado>{
    
   public Empleado findById(BigDecimal id) {
		Empleado e = getByKey(id);
                Hibernate.initialize(e.getPersona());
                return e;
	}

	public void saveEmpleado(Empleado empleado) {
		persist(empleado);
	}

	public void deleteEmpleadoById(BigDecimal codEmpleado) {
		Query query = getSession().createSQLQuery("delete from empleado where cod_empleado = :cod_empleado");
		query.setBigDecimal("cod_empleado", codEmpleado);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public String findAllEmpleados() {
                String q = "select cod_empleado, nombre, apellido, puesto, fecha_contratacion from empleado natural join persona where puesto <> 'Doctor' and activo = 1";
                Query query = getSession().createSQLQuery(q);
		List<Object> results = query.list();
                String empleados = new Gson().toJson(results);                
                return empleados;
	}
        
        public void updateEmpleado(Empleado empleado) {
		Empleado entity = findById(empleado.getCodEmpleado());
		if(entity!=null){
                        entity.setFechaContratacion(empleado.getFechaContratacion());
                        entity.setPuesto(empleado.getPuesto());
		}
	} 
       
}
