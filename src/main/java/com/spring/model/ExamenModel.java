package com.spring.model;
import com.spring.entity.Examen;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author Benjamín
 */
@Repository("examenService")
@Transactional
public class ExamenModel extends AbstractModel<BigDecimal, Examen>{
    
   public Examen findById(BigDecimal id) {
		Examen p = getByKey(id);
                return p;
	}

	public void saveExamen(Examen examen) {
                persist(examen);
	}
      

             
}