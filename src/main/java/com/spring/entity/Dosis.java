/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spring.entity;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
/**
 *
 * @author Benjamín
 */
@Entity
@Table(name="DOSIS"
    ,schema="TOOUSER"
)
public class Dosis implements Serializable{
     private BigDecimal codDosis;
     private Receta receta;
     private String unidad;
     private String medicamento;

    public Dosis() {
    }

    public Dosis(BigDecimal codDosis, Receta receta, String unidad, String medicamento) {
       this.codDosis = codDosis;
       this.receta = receta;
       this.unidad = unidad;
       this.medicamento = medicamento;
    }
   
     @Id 
    @SequenceGenerator(name="dosis_seq", sequenceName="dosis_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator="dosis_seq")   
    @Column(name="COD_DOSIS", unique=true, nullable=false, precision=22, scale=0)
    public BigDecimal getCodDosis() {
        return this.codDosis;
    }
    
    public void setCodDosis(BigDecimal codDosis) {
        this.codDosis = codDosis;
    }

@ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="COD_RECETA", nullable=false)
    public Receta getReceta() {
        return this.receta;
    }
    
    public void setReceta(Receta receta) {
        this.receta = receta;
    }

    
    @Column(name="UNIDAD", nullable=false, length=10)
    public String getUnidad() {
        return this.unidad;
    }
    
    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    
    @Column(name="MEDICAMENTO", nullable=false, length=50)
    public String getMedicamento() {
        return this.medicamento;
    }
    
    public void setMedicamento(String medicamento) {
        this.medicamento = medicamento;
    }

}
