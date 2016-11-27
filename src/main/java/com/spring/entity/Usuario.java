/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spring.entity;
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
@Table(name="USUARIO"
    ,schema="TOOUSER"
)
public class Usuario implements java.io.Serializable {


     private BigDecimal codUsuario;
     private Empleado empleado;
     private Rol rol;
     private String nombreUsuario;
     private String contrasena;
     private String email;
     private BigDecimal activo;

    public Usuario() {
    }

	
    public Usuario(BigDecimal codUsuario, String nombreUsuario, String contrasena, String email) {
        this.codUsuario = codUsuario;
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.email = email;
    }
    public Usuario(BigDecimal codUsuario, Empleado empleado, Rol rol, String nombreUsuario, String contrasena, String email, BigDecimal activo) {
       this.codUsuario = codUsuario;
       this.empleado = empleado;
       this.rol = rol;
       this.nombreUsuario = nombreUsuario;
       this.contrasena = contrasena;
       this.email = email;
       this.activo = activo;
    }
   
    @Id
    @Column(name="COD_USUARIO", unique=true, nullable=false, precision=22, scale=0)
    public BigDecimal getCodUsuario() {
        return this.codUsuario;
    }
    
    public void setCodUsuario(BigDecimal codUsuario) {
        this.codUsuario = codUsuario;
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
    @JoinColumn(name="COD_ROL")
    public Rol getRol() {
        return this.rol;
    }
    
    public void setRol(Rol rol) {
        this.rol = rol;
    }

    
    @Column(name="NOMBRE_USUARIO", nullable=false, length=25)
    public String getNombreUsuario() {
        return this.nombreUsuario;
    }
    
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    
    @Column(name="CONTRASENA", nullable=false, length=32)
    public String getContrasena() {
        return this.contrasena;
    }
    
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    
    @Column(name="EMAIL", nullable=false, length=50)
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    
    @Column(name="ACTIVO", precision=22, scale=0)
    public BigDecimal getActivo() {
        return this.activo;
    }
    
    public void setActivo(BigDecimal activo) {
        this.activo = activo;
    }



}


