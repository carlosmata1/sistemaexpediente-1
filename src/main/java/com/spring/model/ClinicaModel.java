package com.spring.model;
import com.google.gson.Gson;
import com.spring.entity.Clinica;
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
@Repository("clinicaService")
@Transactional
public class ClinicaModel extends AbstractModel<BigDecimal, Clinica>{
   public Clinica findById(BigDecimal id) {
		return getByKey(id);
	}

	public void saveClinica(Clinica clinica) {
		persist(clinica);
	}

	public void deleteClinicaById(BigDecimal codClinica) {
		Query query = getSession().createSQLQuery("delete from clinica where cod_clinica = :cod_clinica");
		query.setBigDecimal("cod_clinica", codClinica);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<Clinica> findAllClinicas() {
            //Comment
		Criteria criteria = createEntityCriteria();
		return (List<Clinica>) criteria.list();
	}
        
        public Clinica findByNombre(String nombre){
            Criteria criteria = createEntityCriteria();
            criteria.add(Restrictions.eq("nombre", nombre));
        return (Clinica) criteria.uniqueResult();
        }
        
        public void updateClinica(Clinica clinica) {
		Clinica entity = findById(clinica.getCodClinica());
		if(entity!=null){
                        entity.setNombre(clinica.getNombre());
                        //entity.setDireccion(clinica.getDireccion());
                        entity.setTelefono(clinica.getTelefono());
                        /*entity.setDepartamento(clinica.getDepartamento());
                        entity.setMunicipio(clinica.getMunicipio());*/
		}
	} 

    public String listCli() {
        String q = "select nombre from clinica";
        Query query = getSession().createSQLQuery(q);
	List<Object> results = query.list();
        String clinicas = new Gson().toJson(results);                
        
        return clinicas;
    }
       
}
