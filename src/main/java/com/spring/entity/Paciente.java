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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Benjamín
 */
@Entity
@Table(name="PACIENTE"
    ,schema="TOOUSER"
)
public class Paciente implements Serializable{
    
     private BigDecimal idAfiliado;
     private Persona persona;
     private BigDecimal activo;

    public Paciente() {
    }
     
     
    public Paciente(BigDecimal idAfiliado, Persona persona) {
        this.idAfiliado = idAfiliado;
        this.persona = persona;
    }
     
    @Id 
    @Column(name="ID_AFILIADO", unique=true, nullable=false, precision=22, scale=0)
     public BigDecimal getIdAfiliado() {
        return this.idAfiliado;
    }
    
    public void setIdAfiliado(BigDecimal idAfiliado) {
        this.idAfiliado = idAfiliado;
    }
    
    @Column(name="ACTIVO", precision=22, scale=0)
    public BigDecimal getActivo() {
        return this.activo;
    }
    
    public void setActivo(BigDecimal activo) {
        this.activo = activo;
    }
    
     @ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
     @JoinColumn(name="COD_PERSONA", nullable=false)
     public Persona getPersona() {
        return this.persona;
    }
    
    public void setPersona(Persona persona) {
        this.persona = persona;
    }
    
    public String toString(){
        return ("Id Afiliado: "+this.idAfiliado+"\nPersona: "+this.persona.toString());
    }
    
}
