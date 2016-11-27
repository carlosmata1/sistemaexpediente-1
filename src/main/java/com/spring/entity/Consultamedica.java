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

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Benjamín
 */
@Entity
@Table(name="CONSULTAMEDICA"
    ,schema="TOOUSER"
)
public class Consultamedica implements Serializable {
    
     private BigDecimal codConsulta;
     private Expediente expediente;
     private Receta receta;
     private Date fecha;
     //private Calendar hora;
     private String diagnostico;
     
     public Consultamedica() {
    }
    
     @Id  
    @Column(name="COD_CONSULTA", unique=true, nullable=false, precision=22, scale=0)
    public BigDecimal getCodConsulta() {
        return this.codConsulta;
    }
    
    public void setCodConsulta(BigDecimal codConsulta) {
        this.codConsulta = codConsulta;
    }

@ManyToOne(fetch=FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name="COD_EXPEDIENTE")
    @JsonIgnore
    public Expediente getExpediente() {
        return this.expediente;
    }
    
    public void setExpediente(Expediente expediente) {
        this.expediente = expediente;
    }

@ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="COD_RECETA")
    public Receta getReceta() {
        return this.receta;
    }
    
    public void setReceta(Receta receta) {
        this.receta = receta;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="FECHA", nullable=false, length=7)
    public Date getFecha() {
        return this.fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    
    /*@Column(name="HORA", nullable=false)
    public Calendar getHora() {
        return this.hora;
    }
    
    public void setHora(Calendar hora) {
        this.hora = hora;
    }*/
    


    @Column(name="DIAGNOSTICO", nullable=false, length=500)
    public String getDiagnostico() {
        return this.diagnostico;
    }
    
    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }
     
}
