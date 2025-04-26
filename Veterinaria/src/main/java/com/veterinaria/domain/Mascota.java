

package com.veterinaria.domain;

import java.util.Date;
import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data   //Generar por dejabo los set y get
@Table(name = "FIDE_MASCOTA_TB")
public class Mascota implements Serializable       
{
    private static final long serialVersionUID = 1L;
    
    private String nombreMascota; 
    private int edad;
    private double peso; 
    private Date fechaNacimiento;
    private String descripcionRaza;
    private String descripcionColor;
    private String nombreCliente;
    private String estado;
    
    
    public Mascota() {
       }
               
       public Mascota(String nombreMascota, int edad, double peso, 
                                Date fechaNacimiento, String descripcionRaza, String descripcionColor,
        String nombreCliente, String estado ) {
           this.nombreMascota = nombreMascota;
           this.edad = edad;
           this.peso = peso;
           this.fechaNacimiento = fechaNacimiento;
           this.descripcionRaza = descripcionRaza;
           this.descripcionColor = descripcionColor;
           this.nombreCliente = nombreCliente;
       }
       
       
       
}


  
    