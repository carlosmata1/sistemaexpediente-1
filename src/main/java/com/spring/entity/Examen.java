/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Benjamín
 */
@Entity
@Table(name="EXAMEN"
    ,schema="TOOUSER"
)
public class Examen implements Serializable{
        private BigDecimal codExamen;
     private Expediente expediente;
     private String tipo;
     private String ubicacion;
     private String resultado;
     
     public Examen() {
    }
     
     @Id 

    
    @Column(name="COD_EXAMEN", unique=true, nullable=false, precision=22, scale=0)
    public BigDecimal getCodExamen() {
        return this.codExamen;
    }
    
    public void setCodExamen(BigDecimal codExamen) {
        this.codExamen = codExamen;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="COD_EXPEDIENTE")
    @JsonIgnore
    public Expediente getExpediente() {
        return this.expediente;
    }
    
    public void setExpediente(Expediente expediente) {
        this.expediente = expediente;
    }

    
    @Column(name="TIPO", nullable=false, length=50)
    public String getTipo() {
        return this.tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    
    @Column(name="UBICACION", nullable=false, length=75)
    public String getUbicacion() {
        return this.ubicacion;
    }
    
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    
    @Column(name="RESULTADO", nullable=false, length=500)
    public String getResultado() {
        return this.resultado;
    }
    
    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

}
