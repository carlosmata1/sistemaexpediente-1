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
@Table(name="MEDICO"
    ,schema="TOOUSER"
)
public class Medico  implements java.io.Serializable {


     private BigDecimal codMedico;
     private String especialidad;
     private Empleado empleado;

    public Medico() {
    }

    public Medico(BigDecimal codMedico, String especialidad, Empleado empleado) {
       this.codMedico = codMedico;
       this.especialidad = especialidad;
       this.empleado = empleado;
    }
   
    @Id 
    @Column(name="COD_MEDICO", unique=true, nullable=false, precision=22, scale=0)
    public BigDecimal getCodMedico() {
        return this.codMedico;
    }
    
    public void setCodMedico(BigDecimal codMedico) {
        this.codMedico = codMedico;
    }

    @Column(name="ESPECIALIDAD", nullable=false)
    public String getEspecialidad() {
        return this.especialidad;
    }
    
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

@ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="COD_EMPLEADO", nullable=false)
    public Empleado getEmpleado() {
        return this.empleado;
    }
    
    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }




}
