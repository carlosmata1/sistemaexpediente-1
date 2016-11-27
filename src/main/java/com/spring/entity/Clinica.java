/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spring.entity;
import java.io.Serializable;
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
@Table(name="CLINICA"
    ,schema="TOOUSER"
)
public class Clinica  implements Serializable {

     
     private BigDecimal codClinica;
     
     /*private BigDecimal departamento;
     
     private BigDecimal municipio;*/
     
     private String nombre;
     
     private String telefono;
     
     private String direccion;

    public Clinica() {
    }

	
    /*public Clinica(BigDecimal codClinica, BigDecimal departamento, BigDecimal municipio, String nombre, String telefono) {
        this.codClinica = codClinica;
        this.departamento = departamento;
        this.municipio = municipio;
        this.nombre = nombre;
        this.telefono = telefono;
    }*/
   
    @Id 
    @Column(name="COD_CLINICA", unique=true, nullable=false, precision=22, scale=0)
    public BigDecimal getCodClinica() {
        return this.codClinica;
    }
    
    public void setCodClinica(BigDecimal codClinica) {
        this.codClinica = codClinica;
    }

    /*@Column(name="COD_DEPARTAMENTO", nullable=false)
    public BigDecimal getDepartamento() {
        return this.departamento;
    }
    
    public void setDepartamento(BigDecimal departamento) {
        this.departamento = departamento;
    }

    @Column(name="COD_MUNICIPIO", nullable=false)
    public BigDecimal getMunicipio() {
        return this.municipio;
    }
    
    public void setMunicipio(BigDecimal municipio) {
        this.municipio = municipio;
    }*/

    
    @Column(name="NOMBRE", nullable=false, length=50)
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    @Column(name="TELEFONO", nullable=false, length=9)
    public String getTelefono() {
        return this.telefono;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    
    @Column(name="DIRECCION", length=75)
    public String getDireccion() {
        return this.direccion;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

/*@OneToMany(fetch=FetchType.LAZY, mappedBy="clinica")
    public Set<Expediente> getExpedientes() {
        return this.expedientes;
    }
    
    public void setExpedientes(Set<Expediente> expedientes) {
        this.expedientes = expedientes;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="clinica")
    public Set<Empleado> getEmpleados() {
        return this.empleados;
    }
    
    public void setEmpleados(Set<Empleado> empleados) {
        this.empleados = empleados;
    }*/




}
