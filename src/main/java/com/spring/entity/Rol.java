/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spring.entity;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 *
 * @author Benjamín
 */
@Entity
@Table(name="ROL"
    ,schema="TOOUSER"
)
public class Rol implements java.io.Serializable {


     private BigDecimal codRol;
     private String nombre;

    public Rol() {
    }

	
    public Rol(BigDecimal codRol, String nombre) {
        this.codRol = codRol;
        this.nombre = nombre;
    }

   
    @Id 
    @Column(name="COD_ROL", unique=true, nullable=false, precision=22, scale=0)
    public BigDecimal getCodRol() {
        return this.codRol;
    }
    
    public void setCodRol(BigDecimal codRol) {
        this.codRol = codRol;
    }

    
    @Column(name="NOMBRE", nullable=false, length=50)
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

