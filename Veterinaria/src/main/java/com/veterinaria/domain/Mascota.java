

package com.veterinaria.domain;

import java.util.Date;
import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data   //Generar por dejabo los set y get
@Table(name = "FIDE_MASCOTA_TB")
public class Mascota implements Serializable       
{
    private static final long serialVersionUID = 1L;
    
    private Integer idMascota;
    private String nombreMascota; 
    private Integer edad;
    private double peso; 
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date fechaNacimiento;
    private Integer idRaza;
    private String descripcionRaza;
    private Integer idColor;
    private String descripcionColor;
    private Integer idCliente;
    private String nombreCliente;
    private Integer idEstado;
    private String estado;
    
    
    
    public Mascota() {
       }
       
    
       public Mascota(Integer idMascota, String nombreMascota, Integer edad, double peso, 
                      Date fechaNacimiento, Integer idRaza, String descripcionRaza, 
                      Integer idColor, String descripcionColor,
                      Integer idCliente, String nombreCliente,
                      Integer idEstado, String estado) {
           
           this.idMascota = idMascota;
           this.nombreMascota = nombreMascota;
           this.edad = edad;
           this.peso = peso;
           this.fechaNacimiento = fechaNacimiento;
           this.idRaza = idRaza;
           this.descripcionRaza = descripcionRaza;
           this.idColor = idColor;
           this.descripcionColor = descripcionColor;
           this.idCliente = idCliente;
           this.nombreCliente = nombreCliente;
           this.idEstado = idEstado;
           this.estado = estado;
       } 
       
       
       
}


  
    