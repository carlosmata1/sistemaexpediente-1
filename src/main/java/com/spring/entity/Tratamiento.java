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
import java.util.HashSet;
import java.util.Set;
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
@Table(name="TRATAMIENTO"
    ,schema="TOOUSER"
)
public class Tratamiento implements Serializable{
     private BigDecimal codTratamiento;
     private Empleado empleado;
     private Expediente expediente;
     private Date fechaInicio; 
     
     public Tratamiento() {
    }
     
      @Id 

    
    @Column(name="COD_TRATAMIENTO", unique=true, nullable=false, precision=22, scale=0)
    public BigDecimal getCodTratamiento() {
        return this.codTratamiento;
    }
    
    public void setCodTratamiento(BigDecimal codTratamiento) {
        this.codTratamiento = codTratamiento;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="COD_EMPLEADO")
    public Empleado getEmpleado() {
        return this.empleado;
    }
    
    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
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

    @Temporal(TemporalType.DATE)
    @Column(name="FECHA_INICIO", nullable=false, length=7)
    public Date getFechaInicio() {
        return this.fechaInicio;
    }
    
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }


}
