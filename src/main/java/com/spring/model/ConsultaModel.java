package com.spring.model;
import com.spring.entity.Consultamedica;
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
@Repository("consultaService")
@Transactional
public class ConsultaModel extends AbstractModel<BigDecimal, Consultamedica>{
    
   public Consultamedica findById(BigDecimal id) {
		Consultamedica p = getByKey(id);
                return p;
	}

	public void saveConsulta(Consultamedica consulta) {
                persist(consulta);
	}
        
        public void deleteConsultaById(BigDecimal codConsulta) {
		Query query = getSession().createSQLQuery("delete from consultamedica where cod_consulta = :cod_consulta");
		query.setBigDecimal("cod_consulta", codConsulta);
		query.executeUpdate();
	}
        
        public void updateConsulta(Consultamedica consulta) {
		Consultamedica entity = findById(consulta.getCodConsulta());
		if(entity!=null){
                        entity = consulta;
		}
	}
        


             
}

