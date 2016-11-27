/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spring.entity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
@Table(name="SESION"
    ,schema="TOOUSER"
)
public class Sesion implements Serializable{
     private BigDecimal codSesion;
     private Tratamiento tratamiento;
     private Date fecha;
     
     public Sesion() {
    }
     
    @Id 
    @Column(name="COD_SESION", unique=true, nullable=false, precision=22, scale=0)
    public BigDecimal getCodSesion() {
        return this.codSesion;
    }
    
    public void setCodSesion(BigDecimal codSesion) {
        this.codSesion = codSesion;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="COD_TRATAMIENTO", nullable=false)
    public Tratamiento getTratamiento() {
        return this.tratamiento;
    }
    
    public void setTratamiento(Tratamiento tratamiento) {
        this.tratamiento = tratamiento;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="FECHA", length=7)
    public Date getFecha() {
        return this.fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    } 
     
     
}
