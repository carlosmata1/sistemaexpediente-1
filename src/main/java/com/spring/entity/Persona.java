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
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Benjamín
 */
@Entity
@Table(name="PERSONA"
    ,schema="TOOUSER"
)
public class Persona implements Serializable{
    
     @Id
     //@GeneratedValue(strategy=GenerationType.AUTO)
     @Column(name="COD_PERSONA", unique=true, nullable=false, precision=22, scale=0)
     private BigDecimal codPersona;
     @Column(name="DEPARTAMENTO", nullable=false)
     private String departamento;
     /*@Column(name="COD_MUNICIPIO", nullable=false)
     private BigDecimal municipio;*/
     @Column(name="NOMBRE", nullable=false, length=50)
     private String nombre;
     @Column(name="APELLIDO", nullable=false, length=50)
     private String apellido;
     @Temporal(TemporalType.DATE)
     @Column(name="FECHA_NACIMIENTO", nullable=false, length=7)
     private Date fechaNacimiento;
     @Column(name="CONYUGE", length=100)
     private String conyuge;
     @Column(name="DIRECCION", nullable=false, length=50)
     private String direccion;
     @Column(name="DUI", length=50)
     private String dui;
     @Column(name="PROFESION", length=50)
     private String profesion;
     @Column(name="ESTADO_CIVIL", nullable=false, length=25)
     private String estadoCivil;
     @Column(name="PADRE", length=100)
     private String padre;
     @Column(name="MADRE", length=100)
     private String madre;
     @Column(name="GENERO", nullable=false, length=1)
     private char genero;
     
     
    public BigDecimal getCodPersona() {
        return this.codPersona;
    }
    
    public void setCodPersona(BigDecimal codPersona) {
        this.codPersona = codPersona;
    }

    public String getDepartamento() {
        return this.departamento;
    }
    
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    /*public BigDecimal getMunicipio() {
        return this.municipio;
    }
    
    public void setMunicipio(BigDecimal municipio) {
        this.municipio = municipio;
    }*/

    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return this.apellido;
    }
    
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
    public Date getFechaNacimiento() {
        return this.fechaNacimiento;
    }
    
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getConyuge() {
        return this.conyuge;
    }
    
    public void setConyuge(String conyuge) {
        this.conyuge = conyuge;
    }

    public String getDireccion() {
        return this.direccion;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDui() {
        return this.dui;
    }
    
    public void setDui(String dui) {
        this.dui = dui;
    }

    public String getProfesion() {
        return this.profesion;
    }
    
    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public String getEstadoCivil() {
        return this.estadoCivil;
    }
    
    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getPadre() {
        return this.padre;
    }
    
    public void setPadre(String padre) {
        this.padre = padre;
    }

    public String getMadre() {
        return this.madre;
    }
    
    public void setMadre(String madre) {
        this.madre = madre;
    }

    public char getGenero() {
        return this.genero;
    }
    
    public void setGenero(char genero) {
        this.genero = genero;
    }
    
    public String toString(){
        return ("codPaciente: "+this.codPersona+"\n Nombre: "+this.nombre+"\n Direccion: "+this.direccion+"\n");
    }
}
