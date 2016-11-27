/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spring.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/**
 *
 * @author Benjamín
 */
@Entity
@Table(name="EXPEDIENTE"
    ,schema="TOOUSER"
)
public class Expediente  implements java.io.Serializable {
    

     private BigDecimal codExpediente;
     private Paciente paciente;
     private Date fechaCreacion;
     private Set<Examen> examens = new HashSet<Examen>(0);
     private Set<Tratamiento> tratamientos = new HashSet<Tratamiento>(0);
     private Set<Consultamedica> consultamedicas = new HashSet<Consultamedica>(0);

    public Expediente() {
    }

    
    public Expediente(BigDecimal codExpediente, Paciente paciente, Date fechaCreacion) {
       this.codExpediente = codExpediente;
       this.paciente = paciente;
       this.fechaCreacion = fechaCreacion;
    }

    @Id 
    @Column(name="COD_EXPEDIENTE", unique=true, nullable=false, precision=22, scale=0)
    public BigDecimal getCodExpediente() {
        return this.codExpediente;
    }
    
    public void setCodExpediente(BigDecimal codExpediente) {
        this.codExpediente = codExpediente;
    }

@ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="ID_AFILIADO", nullable=false)
    public Paciente getPaciente() {
        return this.paciente;
    }
    
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="FECHA_CREACION", nullable=false, length=7)
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }
    
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    
    @OneToMany(fetch=FetchType.LAZY, mappedBy="expediente")

    public Set<Examen> getExamens() {
        return this.examens;
    }
    
    public void setExamens(Set<Examen> examens) {
        this.examens = examens;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="expediente")

    public Set<Tratamiento> getTratamientos() {
        return this.tratamientos;
    }
    
    public void setTratamientos(Set<Tratamiento> tratamientos) {
        this.tratamientos = tratamientos;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="expediente")

    public Set<Consultamedica> getConsultamedicas() {
        return this.consultamedicas;
    }
    
    public void setConsultamedicas(Set<Consultamedica> consultamedicas) {
        this.consultamedicas = consultamedicas;
    }
}
