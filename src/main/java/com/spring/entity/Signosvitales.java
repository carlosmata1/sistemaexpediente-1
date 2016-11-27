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
@Table(name="SIGNOSVITALES"
    ,schema="TOOUSER"
)
public class Signosvitales implements Serializable{
    private BigDecimal codSignos;
    private Consultamedica consultamedica;
    private double temperatura;
    private double altura;
    private double presion;
    private double pulso;
    
    public Signosvitales() {
    }
    
    @Id 

    
    @Column(name="COD_SIGNOS", unique=true, nullable=false, precision=22, scale=0)
    public BigDecimal getCodSignos() {
        return this.codSignos;
    }
    
    public void setCodSignos(BigDecimal codSignos) {
        this.codSignos = codSignos;
    }

@ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="COD_CONSULTA")
    public Consultamedica getConsultamedica() {
        return this.consultamedica;
    }
    
    public void setConsultamedica(Consultamedica consultamedica) {
        this.consultamedica = consultamedica;
    }

    
    @Column(name="TEMPERATURA", nullable=false, precision=10, scale=0)
    public double getTemperatura() {
        return this.temperatura;
    }
    
    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    
    @Column(name="ALTURA", nullable=false, precision=10, scale=0)
    public double getAltura() {
        return this.altura;
    }
    
    public void setAltura(double altura) {
        this.altura = altura;
    }

    
    @Column(name="PRESION", nullable=false, precision=10, scale=0)
    public double getPresion() {
        return this.presion;
    }
    
    public void setPresion(double presion) {
        this.presion = presion;
    }

    
    @Column(name="PULSO", nullable=false, precision=10, scale=0)
    public double getPulso() {
        return this.pulso;
    }
    
    public void setPulso(double pulso) {
        this.pulso = pulso;
    }
}
