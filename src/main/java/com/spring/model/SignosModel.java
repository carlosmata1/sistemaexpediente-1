package com.spring.model;
import com.spring.entity.Signosvitales;
import java.math.BigDecimal;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author Benjamín
 */
@Repository("signosService")
@Transactional
public class SignosModel extends AbstractModel<BigDecimal, Signosvitales>{
    
   public Signosvitales findById(BigDecimal id) {
		Signosvitales p = getByKey(id);
                return p;
	}

	public void saveSignos(Signosvitales signos) {
		persist(signos);
	}
        

             
}
