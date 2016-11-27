/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spring.entity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/**
 *
 * @author Benjamín
 */
@Entity
@Table(name="RECETA"
    ,schema="TOOUSER"
)
public class Receta implements Serializable {
    
     private BigDecimal codReceta;
     private Date fecha;
     private Set<Dosis> dosises = new HashSet<Dosis>(0);
     
     public Receta(BigDecimal codReceta, Date fecha) {
         this.codReceta = codReceta;
         this.fecha = fecha;
    }
     
     public Receta() {
    }
     
     @Id
     @SequenceGenerator(name="receta_seq", sequenceName="receta_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator="receta_seq")
    @Column(name="COD_RECETA", unique=true, nullable=false, precision=22, scale=0)
    public BigDecimal getCodReceta() {
        return this.codReceta;
    }
    
    public void setCodReceta(BigDecimal codReceta) {
        this.codReceta = codReceta;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="FECHA", nullable=false, length=7)
    public Date getFecha() {
        return this.fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="receta", cascade = CascadeType.ALL)
    public Set<Dosis> getDosises() {
        return this.dosises;
    }
    
    public void setDosises(Set<Dosis> dosises) {
        this.dosises = dosises;
    }
    
}
