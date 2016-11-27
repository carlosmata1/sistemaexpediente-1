/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spring.entity;
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
@Table(name="EMPLEADO"
    ,schema="TOOUSER"
)
public class Empleado implements Serializable {


     private BigDecimal codEmpleado;
     private Persona persona;
     public Clinica clinica;
     private Date fechaContratacion;
     private String puesto;
     private BigDecimal activo;

    public Empleado() {
    }

    public Empleado(BigDecimal codEmpleado, Persona persona, Clinica clinica, Date fechaContratacion, String puesto) {
       this.codEmpleado = codEmpleado;
       this.persona = persona;
       this.clinica = clinica;
       this.fechaContratacion = fechaContratacion;
       this.puesto = puesto;
    }
   
     @Id 

    
    @Column(name="COD_EMPLEADO", unique=true, nullable=false, precision=22, scale=0)
    public BigDecimal getCodEmpleado() {
        return this.codEmpleado;
    }
    
    public void setCodEmpleado(BigDecimal codEmpleado) {
        this.codEmpleado = codEmpleado;
    }

@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name="COD_PERSONA")
    public Persona getPersona() {
        return this.persona;
    }
    
    public void setPersona(Persona persona) {
        this.persona = persona;
    }

@ManyToOne(fetch=FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name="COD_CLINICA")
    public Clinica getClinica() {
        return this.clinica;
    }
    
    public void setClinica(Clinica clinica) {
        this.clinica = clinica;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="FECHA_CONTRATACION", nullable=false, length=7)
    public Date getFechaContratacion() {
        return this.fechaContratacion;
    }
    
    public void setFechaContratacion(Date fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    
    @Column(name="PUESTO", nullable=false, length=25)
    public String getPuesto() {
        return this.puesto;
    }
    
    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }
    
    @Column(name="ACTIVO", precision=22, scale=0)
    public BigDecimal getActivo() {
        return this.activo;
    }
    
    public void setActivo(BigDecimal activo) {
        this.activo = activo;
    }
}
