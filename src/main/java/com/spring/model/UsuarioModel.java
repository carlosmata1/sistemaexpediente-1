/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spring.model;

import com.google.gson.Gson;
import com.spring.entity.Usuario;
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
@Repository("usuarioService")
@Transactional
public class UsuarioModel extends AbstractModel<BigDecimal, Usuario>{
    
   public Usuario findById(BigDecimal id) {
		Usuario u = getByKey(id);
                Hibernate.initialize(u.getEmpleado());
                Hibernate.initialize(u.getRol());
                return u;
	}
   
   public Usuario findbyEmail(String email) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("email", email));
        return (Usuario) criteria.uniqueResult();
   }

	public void saveUsuario(Usuario usuario) {
		persist(usuario);
	}

	public void deleteUsuarioById(BigDecimal codUsuario) {
		Query query = getSession().createSQLQuery("delete from usuario where cod_usuario = :cod_usuario");
		query.setBigDecimal("cod_usuario", codUsuario);
		query.executeUpdate();
	}
        
        //Necesita cambios, cambio la entidad usuario
	@SuppressWarnings("unchecked")
	public String findAllUsuarios() {
                String q = "select cod_usuario, email, contrasena, rol, nombre, apellido from (select cod_usuario, cod_empleado, email, contrasena, nombre as rol from usuario natural join rol) natural join (select * from empleado natural join persona) where activo=1";
                Query query = getSession().createSQLQuery(q);
		List<Object> results = query.list();
                String usuarios = new Gson().toJson(results);                
                
                return usuarios;
	}
        
        public String getClinica(BigDecimal codU) {
                String q = "select nombre from usuario natural join empleado natural join clinica where cod_usuario = :cod";
                Query query = getSession().createSQLQuery(q);
                query.setBigDecimal("cod", codU);
		String result = (String)query.uniqueResult();
                
                
                return result;
	}
          
}
